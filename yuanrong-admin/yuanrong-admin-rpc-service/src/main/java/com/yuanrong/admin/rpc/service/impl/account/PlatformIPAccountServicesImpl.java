package com.yuanrong.admin.rpc.service.impl.account;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.account.Account;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.account.PlatformIPAccountPrice;
import com.yuanrong.admin.bean.account.ShortVideoPlatformInfo;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.result.PlatformIPAccountResult;
import com.yuanrong.admin.rpc.api.account.PlatformIPAccountServicesI;
import com.yuanrong.admin.rpc.exception.YRParamterException;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.seach.PlatformIpAccountSeach;
import com.yuanrong.admin.seach.PlatformIpAccountSearchMall;
import com.yuanrong.admin.seach.RegisteredUserAndCompany;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.*;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.httpclient.util.DateParseException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zhonghang on 2018/4/27.
 */
@Service
public class PlatformIPAccountServicesImpl extends BaseServicesAbstract<PlatformIPAccount> implements PlatformIPAccountServicesI {
    @Override
    public PlatformIPAccount getById(Integer id) {
        return platformIPAccountDaoI.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        platformIPAccountDaoI.deleteById(id);
    }

    @Override
    public void save(PlatformIPAccount object) {
        platformIPAccountDaoI.save(object);
    }

    @Override
    public List<PlatformIPAccount> getAll() {
        return platformIPAccountDaoI.getAll();
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "id desc");
        List<PlatformIPAccount> result = platformIPAccountDaoI.list(data);
        return new PageInfo(result);
    }

    @Override
    public List<ShortVideoPlatformInfo> getPlatFormInfo() {
        return platformIPAccountDaoI.getPlatFormInfo();
    }

    @Override
    public void savePlatformIpAccount(PlatformIPAccount data , List<PlatformIPAccountPrice> ipAccountPrices) {
        //处理账号和平台唯一标识关系
        Account account = platformIPAccountDaoI.getAccountByPidAndPlatformId(data.getPid() , data.getPlatformID());
        if(account == null){
            account = new Account(data.getPid() , data.getPlatformID());
            platformIPAccountDaoI.saveAccount(account);
        }
        data.setAccountOnlyId(account.getAccountId());
        //保存账号
        platformIPAccountDaoI.save(data);
        BigDecimal originalPrice = BigDecimal.ZERO;
        //保存价格信息
        for(int i = 0 ; i < ipAccountPrices.size() ; i ++ ){
            ipAccountPrices.get(i).setiPAcctountID(data.getId());
            platformIPAccountPriceDaoI.save(ipAccountPrices.get(i));
            if(ipAccountPrices.get(i).getPlatformPriceNameID() == 2){
                originalPrice = ipAccountPrices.get(i).getPrice();
            }
        }
        //创建创作者
        if(data.getIsCreatedYrAuthor() != null && data.getIsCreatedYrAuthor().intValue() == 1){
            YRAuthor  yrAuthor = new YRAuthor();
            yrAuthor.setAuthorNickname(data.getName());
            yrAuthor.setAuthorImg(data.getHeadImageUrlLocal());
            yrAuthor.setEnumChannel(EnumChannel.系统创建);
            yrAuthor.setEnumAuthorStatus(EnumAuthorStatus.未上架);
            yrAuthor.setEnumYesOrNo(EnumYesOrNo.FORBIDDEN);
            yrAuthor.setRegisteredUserInfoID(data.getRegisteredUserInfoID());
            yrAuthor.setIntroduction(data.getIntroduction());
            yrAuthor.setCreatedPrice(originalPrice);
            yRAuthorDaoI.save(yrAuthor);
        }
    }

    /**
     * 查询无IP的已有账号
     * @param platformIPAccount
     * @param baseModel
     * @return
     */
    @Override
    public PageInfo ipFindAccount(PlatformIPAccount platformIPAccount, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() ,baseModel.getRows() , "pa.createdTime desc");
        return new PageInfo<PlatformIPAccount>(platformIPAccountDaoI.ipFindAccount(platformIPAccount));
    }

    /**
     * 批量添加无IP的已有账号
     * @param accountId
     * @param ipID
     */
    @Override
    public void updateAccount(String accountId, Integer ipID) {
        List<PlatformIPAccount> list = new ArrayList<PlatformIPAccount>();
        String[] split = accountId.split(",");
        for (int i = 0; i < split.length; i++) {
            PlatformIPAccount platformIPAccount = new PlatformIPAccount();
            platformIPAccount.setId(Integer.parseInt(split[i]));
            platformIPAccount.setiPID(ipID);
            list.add(platformIPAccount);
        }
        platformIPAccountDaoI.updateAccount(list);
    }

    @Override
    public PageInfo<Map> list(PlatformIpAccountSeach platformIpAccountSeach , BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , platformIpAccountSeach.getOrderBy());
        List<Map> result = platformIPAccountDaoI.list(platformIpAccountSeach);
        if(CollectionUtil.size(result) > 0){
            int[] ipAccountIds = new int[result.size()];
            int index = 0 ;
            for(Map<String,Object> map : result){
                ipAccountIds[index++] = Integer.parseInt(map.get("id").toString());
            }
            List<Map<String,Object>> prices = platformIPAccountPriceDaoI.getGroupPriceByIPAccountIDs(ipAccountIds , platformIpAccountSeach.getPlatformPrice());
            if(CollectionUtil.size(prices) > 0){
                for(Map<String,Object> ele : prices){
                    for(Map<String,Object> map : result){
                        if(map.get("id").toString().equals(ele.get("iPAcctountID").toString())){
                            map.put("info" , ele.get("info"));
                        }
                    }
                }
            }
        }
        return new PageInfo(result);
    }

    /**
     * 通过ID获取平台账号信息
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getPlatFormIPAccountInfoById(int id) {
        return platformIPAccountDaoI.getPlatFormIPAccountInfoById(id);
    }

    @Override
    public void updatePlatFormIPAccountAndPrice(PlatformIPAccount data) {
        //处理报价
        JSONArray jsonArray = JSON.parseArray(data.getPlatPrices());
        if(CollectionUtil.size(jsonArray) > 0){
            for(int i = 0 ; i< jsonArray.size() ; i ++){
                JSONObject object = jsonArray.getJSONObject(i);
                int isOriginal = object.getInteger("isOriginal");
                BigDecimal price = object.getBigDecimal("price");
                //IP账号报价记录ID
                Integer id = object.getInteger("id");
                //价格名字ID
                Integer priceNameID = object.getInteger("priceNameID");
                if(id != null && id > 0){
                    PlatformIPAccountPrice accountPrice = platformIPAccountPriceDaoI.getById(id);
                    if(accountPrice != null){
                        accountPrice.setIsOriginal(isOriginal);
                        accountPrice.setPrice(price);
                        platformIPAccountPriceDaoI.update(accountPrice);
                    }

                }else{
                    //如果不存在，则为新增新的报价信息
                    if(priceNameID != null && priceNameID > 0 ){
                        PlatformIPAccountPrice accountPrice = new PlatformIPAccountPrice();
                        accountPrice.setPrice(price);
                        accountPrice.setiPAcctountID(data.getId());
                        accountPrice.setPlatformPriceNameID(priceNameID);
                        accountPrice.setIsOriginal(isOriginal);
                        platformIPAccountPriceDaoI.save(accountPrice);
                    }
                }
            }
        }
        platformIPAccountDaoI.update(data);
    }

    @Override
    public void batchSavePlatformIpAccount(List<PlatformIPAccount> data) {
        for(PlatformIPAccount platformIPAccount : data){
            this.savePlatformIpAccount(platformIPAccount , platformIPAccount.getIpAccountPrices());
        }
    }

    @Override
    public PlatformIPAccount getByParam(Map<String,Object> param) {
        return platformIPAccountDaoI.getByParam(param);
    }

    @Override
    public PlatformIPAccount getByPidAndRegisterUserId(String pid, int registerUserId) {
        return platformIPAccountDaoI.getByPidAndRegisterUserId(pid , registerUserId);
    }

    @Override
    public void batchUpperLowerShelves(PlatformIpAccountSeach data) {
        platformIPAccountDaoI.batchUpperLowerShelves(data);
    }

    @Override
    public void batchUpdateInvalidTime(PlatformIpAccountSeach data) {
        platformIPAccountDaoI.batchUpdateInvalidTime(data);
    }

    @Override
    public void batchUpdatePrice(PlatformIpAccountSeach data) {
        platformIPAccountDaoI.batchUpdatePrice(data);
    }

    @Override
    public PageInfo<PlatformIPAccountResult> listMall(PlatformIpAccountSearchMall data, BaseModel baseModel) {
        //查询全部与分页查询
        if(data.getIsSelectAll()==EnumYesOrNo.FORBIDDEN.getIndex()){
            PageHelper.startPage(baseModel.getCp() , baseModel.getRows());
        }
        List<PlatformIPAccountResult> result = platformIPAccountDaoI.listMall(data);
        if(CollectionUtil.size(result) > 0){
            String[] pids = new String[result.size()];
            int index = 0 ;
            for(PlatformIPAccountResult map : result){
                pids[index++] =  map.getPid();
            }
            List<Map<String,Object>> prices = platformIPAccountPriceDaoI.getGroupPriceByPids(pids);
            if(CollectionUtil.size(prices) > 0){
                for(Map<String,Object> ele : prices){
                    for(PlatformIPAccountResult map : result){
                        if(map.getPid().equals(ele.get("pid").toString())){
                            map.setPriceInfo(ele.get("info") == null ? null : ele.get("info").toString());
                        }
                    }
                }
            }
        }
        return new PageInfo<PlatformIPAccountResult>(result);
    }

    @Override
    public void updateAccountStatusByUserIDs(RegisteredUserAndCompany registeredUserAndCompany) {
        List<Integer> list= new ArrayList<Integer>();
        list.add(registeredUserAndCompany.getRecID());
        platformIPAccountDaoI.updateAccountStatusByUserIDs(list);
    }

    @Override
    public PlatformIPAccount getAccountInfoByPlatformAndAccountID(String accountID , Integer platformId) {
        return platformIPAccountDaoI.getAccountInfoByPlatformAndAccountID(accountID , platformId);
    }

    @Override
    public List<PlatformIPAccount> saveQueueMQ(JSONObject queInfo) throws InterruptedException {
        JSONArray jsonArray = queInfo.getJSONArray("data");
        if(queInfo == null || jsonArray == null || jsonArray.size() == 0 ){
            return null;
        }
        //抓取失败的账号
        List<PlatformIPAccount> failAccount = new ArrayList<>();
        String accountInfoUrl = configurationDaoI.getbyKey(SystemParam.GET_PLATFORMIPACCOUNT_INFO_URL);
        for (int i = 0; i < jsonArray.size(); i++) {
            int errIndex = 1;
            StringBuffer errInfo = new StringBuffer();
            Thread.sleep(1000);
            JSONObject ele = jsonArray.getJSONObject(i);
            PlatformIPAccount platformIPAccount = new PlatformIPAccount();
            String uniqueFetch = ele.get("uniqueFetch").toString();
            platformIPAccount.setArticleUrl(uniqueFetch);
            platformIPAccount.setPlatformID(ele.getInteger("platFormID"));
            platformIPAccount.setPlatformName(ele.getString("platformName"));
            platformIPAccount.setRegisteredUserInfoID(ele.getInteger("userID"));
            platformIPAccount.setCategoryID(ele.getInteger("categoryID"));
            platformIPAccount.setLowerCauseID(ele.getString("lowerCauseID"));
            platformIPAccount.setiPID(ele.getInteger("ipID"));
            platformIPAccount.setCreaterAdminID(ele.getInteger("createdUserID"));
            platformIPAccount.setAccountStatus(ele.getInteger("accountStatusID"));
            platformIPAccount.setAccountStatusName(ele.getString("accountStatusName"));
            platformIPAccount.setInvalidTime(ele.getString("invalidTime"));
            platformIPAccount.setCategoryName(ele.getString("categoryName"));
            platformIPAccount.setChannel(EnumChannel.后台创建);
            //处理价格
            JSONArray priceInfo = ele.getJSONArray("pricesInfo");
            if(priceInfo != null){
                for (int j = 0; j < priceInfo.size() ; j++) {
                    JSONObject priceJSON = priceInfo.getJSONObject(j);
                    PlatformIPAccountPrice accountPrice = new PlatformIPAccountPrice();
                    accountPrice.setPlatformPriceNameID(priceJSON.getInteger("id"));
                    accountPrice.setPrice(priceJSON.getBigDecimal("price"));
                    accountPrice.setPriceName(priceJSON.getString("priceName"));
                    platformIPAccount.getIpAccountPrices().add(accountPrice);
                }
            }

            RegisteredUserInfo registeredUserInfo = registeredUserInfoDAO.getById(platformIPAccount.getRegisteredUserInfoID());
            if(registeredUserInfo == null) {
                errInfo.append((errIndex++) + "：userid系统不存在！");
            }
            Map<String,Object> param = new HashMap<>();
            param.put("data" , StringUtil.format(uniqueFetch));
            String platformName = ele.get("platformName").toString();
            String typename = null;
            switch (platformName){
                case "微信" : typename = "a";
                platformIPAccount.setFans(ele.getInteger("fans"));
                break;
                case "微博" : typename = "f";break;
                case "美拍" : typename = "d";break;
                case "秒拍" : typename = "e";break;
                case "快手" : typename = "c";break;
                case "抖音" : typename = "b";break;
            }
            param.put("typename" ,typename);
            String accountStr = HttpUtil.post(accountInfoUrl ,param );

            JSONObject fetchInfo;
            if(StringUtil.isBlank(accountStr) || "null".equals(accountStr)){
                errInfo.append((errIndex ++) +"：抓取失败，请检查抓取唯一项是否正确");
            }else{
                try{
                    fetchInfo = JSON.parseObject(accountStr);
                    platformIPAccount.setAccountID(fetchInfo.getString("accountID"));
                    platformIPAccount.setName(fetchInfo.getString("name"));
                    platformIPAccount.setPid(fetchInfo.getString("pid"));
                    if(StringUtil.isBlank(platformIPAccount.getPid())){
                        errInfo.append((errIndex ++) +"：抓取失败，未返回PID，请检查抓取唯一项是否正确");
                    }else{
                        //验证是否被录入过
                        if(this.getByPidAndRegisterUserId(fetchInfo.getString("pid"), ele.getInteger("userID")) !=null ){
                            errInfo.append((errIndex ++) +"：该账号已经录入过，不能重复录入！");
                        }
                    }
                    if(StringUtil.isBlank(fetchInfo.getString("indexUrl"))){
                        errInfo.append((errIndex ++) +"：主页URL抓取失败");
                    }
                    //用户主页
                    platformIPAccount.setIndexUrl(fetchInfo.getString("indexUrl"));
                    //头像
                    platformIPAccount.setHeadImageUrlLocal(fetchInfo.getString("headImageUrlLocal"));
                    platformIPAccount.setHeadImageUrl(fetchInfo.getString("headImageUrl"));
                    //粉丝数 如果以前没有粉丝数则添加，否则信任录入的粉丝数
                    if(platformIPAccount.getFans() <= 0){
                        platformIPAccount.setFans(fetchInfo.get("fans") == null ? 0 : fetchInfo.getInteger("fans"));
                    }
                    //账号简介
                    platformIPAccount.setIntroduction(fetchInfo.getString("introduction"));
                    //二维码
                    platformIPAccount.setqRcodeImageUrl(fetchInfo.getString("qRcodeImageUrl"));
                    platformIPAccount.setqRcodeImageUrlLocal(fetchInfo.getString("qRcodeImageUrlLocal"));

                }catch (Exception e){
                    errInfo.append((errIndex ++) +"：抓取失败，请检查抓取唯一项是否正确");
                }
            }

            if(StringUtil.isBlank(errInfo)){
                //没有错误则保存
                savePlatformIpAccount(platformIPAccount , platformIPAccount.getIpAccountPrices());
            }else{
                //保存到需要处理的结果集中
                platformIPAccount.setImportErrorMsg(errInfo.toString());
                failAccount.add(platformIPAccount);
            }
        }
        return failAccount;
    }

    @Override
    public void createdExcelSendMsg(List<PlatformIPAccount> accountList , String fileName , String toUser) throws WxErrorException, IOException {
        if(CollectionUtil.size(accountList) > 0){
            List<String> title = new ArrayList<>();
            List<String> errorTitle = new ArrayList<>();
            Workbook wb = new SXSSFWorkbook();
            Vector<Vector<Object>> resultContent = new Vector<>();
            title.addAll(Arrays.asList(new String[]{"userid","IPID","平台","账号ID","账号名称","账号唯一抓取项","账号分类","账号状态","下架原因说明","粉丝数","价格有效期结束时间"}));
            errorTitle.addAll(Arrays.asList(new String[]{"userid","账号名称","账号唯一抓取项","错误信息"}));
            Vector<Vector<Object>> errorContent = new Vector<>();
            for(PlatformIPAccount account : accountList){
                Vector<Object> content = new Vector<>(title.size());
                Vector<Object> eContent = new Vector<>(title.size());

                eContent.add(account.getRegisteredUserInfoID());
                eContent.add(account.getName());
                eContent.add(account.getArticleUrl());
                eContent.add(account.getImportErrorMsg());

                content.add(account.getRegisteredUserInfoID());
                content.add(account.getiPID());
                //平台
                content.add(account.getPlatformName());
                content.add(account.getAccountID());
                content.add(account.getName());

                content.add(account.getArticleUrl());
                //账号分类
                content.add(account.getCategoryName());
                // 账号状态
                content.add(account.getAccountStatusName());
                content.add(account.getLowerCauseID());
                content.add(account.getFans());
                content.add(account.getInvalidTime());
                //价格信息
                for(PlatformIPAccountPrice accountPrice : account.getIpAccountPrices()){
                    //标题不存在报价名字，则添加
                    if(!title.contains(accountPrice.getPriceName())){
                        title.add(accountPrice.getPriceName());
                    }
                    for (int i = content.size(); i < title.size() ; i++) {
                        content.add("");
                    }
                    //在指定位置添加报价
                    content.add(title.indexOf(accountPrice.getPriceName()) , accountPrice.getPrice());
                }
                resultContent.add(content);
                errorContent.add(eContent);
            }
            ExcelUtil.created(wb,null , "账号信息",title , resultContent);
            ExcelUtil.created(wb,null , "错误信息",errorTitle , errorContent);
            //上传Excel到微信
            File file = new File("/tmp/"+fileName);
            OutputStream outputStream = new FileOutputStream(file);
            wb.write(outputStream);
            outputStream.close();
            WxMediaUploadResult uploadResult =  wechatServicesI.uploadFile("file" , file);
            //将临时文件删除
            file.delete();
            //发送消息给上传者
            wechatServicesI.sendMediaMsg(EnumWeChartType.通知,null ,uploadResult.getMediaId() , toUser);
        }
    }


    @Override
    public List<Map<String, Object>> getShortVideoPlatformInfo() {
        return platformIPAccountDaoI.getShortVideoPlatformInfo();
    }

    @Override
    public PageInfo<JSONObject> list_platformIPAccount(int[] ids, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp(),baseModel.getRows());
        List<PlatformIPAccount> list = platformIPAccountDaoI.list_PlatformIPAccountByIds(ids);
        List<Map<String,Object>> priceList = platformIPAccountPriceDaoI.getGroupPriceByIPAccountIDs(ids,13);
        List<JSONObject> rntlist = new ArrayList<JSONObject>();
        for(int i = 0; i < list.size(); i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fans",list.get(i).getFans());
            jsonObject.put("name",list.get(i).getName());
            jsonObject.put("accountId",list.get(i).getAccountID());
            jsonObject.put("headImageUrlLocal", list.get(i).getHeadImageUrlLocal());

            // 判断 价格项可能没有
            if(i<priceList.size()){
//                jsonObject.put("priceInfo",priceList.get(i).get("info"));
                //大项 ：头条发布_-_300.00_-_1_-_22_-_1_-_圆融-_-视频发布_-_900.00_-_0_-_23_-_3_-_圆融
                //切割： -_-
                    //  头条发布_-_300.00_-_1_-_22_-_1_-_圆融
                    //  视频发布_-_900.00_-_0_-_23_-_3_-_圆融
                String[]  priceInfoArray = priceList.get(i).get("info").toString().split("-_-");
                List<JSONObject> innerList = new ArrayList<JSONObject>();
                for(int j = 0;j < priceInfoArray.length; j++ ){
                    JSONObject innerJsonObject = new JSONObject();
                    //切割小项：头条发布_-_300.00_-_1_-_22_-_1_-_圆融
                       // 价格项 ： 头条发布
                       // 参考报价： 300.00
                       // isOriginal:  0
                       //  iPAcctountID: 22
                      // platformPriceNameID : 1/3
                   String[] innerStr = priceInfoArray[j].split("_-_");

                   innerJsonObject.put("referenceItem",innerStr[0]);
                   innerJsonObject.put("referencePrice",innerStr[1]);
                   innerJsonObject.put("isOriginal",innerStr[2]);
                    innerJsonObject.put("platformPriceNameID",innerStr[4]);
                    innerJsonObject.put("iPAcctountID",innerStr[3]);
                   innerList.add(innerJsonObject);
                }
                jsonObject.put("priceInfo",innerList);
            }else {
                jsonObject.put("priceInfo","--");
            }
            rntlist.add(jsonObject);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list",list);
        jsonObject.put("priceList",priceList);

        return new PageInfo(rntlist);
    }

    @Override
    public Account getAccountById(int id) {
        return platformIPAccountDaoI.getAccountById(id);
    }

    @Override
    public List<PlatformIPAccount> getShelvesbyAccountId(int accountOnlyId) {
        return platformIPAccountDaoI.getShelvesbyAccountId(accountOnlyId);
    }

    @Override
    public PageInfo<JSONObject> list_PlatformIPAccountByShopCartIds(int registeredUserInfoId, String shopCartIds,BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp(),baseModel.getRows());
        List<PlatformIPAccount> list =  platformIPAccountDaoI.list_PlatformIPAccountByShopCartIds(registeredUserInfoId,shopCartIds);
        if(CollectionUtil.size(list)==0){
            throw new YRParamterException("所选账号信息不存在！");
        }
        Map<String,Integer> map = new HashMap();
        //获取PlatformIPAccount ids
        int [] ids = null;
        ids = new int[list.size()];
        for(int i=0; i < list.size(); i++){
            ids[i] = list.get(i).getId();
            map.put(list.get(i).getId()+"",list.get(i).getShoppingCartId());
        }
        List<Map<String,Object>> priceList = platformIPAccountPriceDaoI.getGroupPriceByIPAccountIDs(ids,13);
        List<JSONObject> rntlist = new ArrayList<JSONObject>();
        for(int i = 0; i < list.size(); i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fans",list.get(i).getFans());
            jsonObject.put("name",list.get(i).getName());
            jsonObject.put("accountId",list.get(i).getAccountID());
            jsonObject.put("icoUrl",list.get(i).getIcoUrl());
            jsonObject.put("headImageUrlLocal",list.get(i).getHeadImageUrlLocal());
            jsonObject.put("shoppingCartId",map.get(list.get(i).getId()+""));
            // 判断 价格项可能没有
            if(i<priceList.size()){
//                jsonObject.put("priceInfo",priceList.get(i).get("info"));
                //大项 ：头条发布_-_300.00_-_1_-_22_-_1_-_圆融-_-视频发布_-_900.00_-_0_-_23_-_3_-_圆融
                //切割： -_-
                //  头条发布_-_300.00_-_1_-_22_-_1_-_圆融
                //  视频发布_-_900.00_-_0_-_23_-_3_-_圆融
                String[]  priceInfoArray = priceList.get(i).get("info").toString().split("-_-");
                List<JSONObject> innerList = new ArrayList<JSONObject>();
                for(int j = 0;j < priceInfoArray.length; j++ ){
                    JSONObject innerJsonObject = new JSONObject();
                    //切割小项：头条发布_-_300.00_-_1_-_22_-_1_-_圆融
                    // 价格项 ： 头条发布
                    // 参考报价： 300.00
                    // isOriginal:  0
                    //  iPAcctountID: 22
                    // platformPriceNameID : 1/3
                    String[] innerStr = priceInfoArray[j].split("_-_");

                    innerJsonObject.put("referenceItem",innerStr[0]);
                    innerJsonObject.put("referencePrice",innerStr[1]);
                    innerJsonObject.put("iPAcctountID",innerStr[3]);
                    innerJsonObject.put("isOriginal",innerStr[2]);
                    innerJsonObject.put("platformPriceNameID",innerStr[4]);
                    innerList.add(innerJsonObject);
                }
                jsonObject.put("priceInfo",innerList);
            }else {
                jsonObject.put("priceInfo",new JSONArray());
            }
            rntlist.add(jsonObject);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list",list);
        jsonObject.put("priceList",priceList);

        return new PageInfo(rntlist);
    }

    @Override
    public PageInfo<PlatformIPAccount> getPlatformIPAccountByNameLikeSearch(PlatformIPAccount data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp(),baseModel.getRows(),"createdTime DESC");
        return new PageInfo(platformIPAccountDaoI.getPlatformIPAccountByNameLikeSearch(data));
    }

    @Override
    public PlatformIPAccount getPlatformIPAccountById_RegistId(PlatformIPAccount data) {
        return platformIPAccountDaoI.getPlatformIPAccountById_RegistId(data);
    }

    public String getPlatformIPAccountIds(int registeredUserInfoId, String shopCartIds){
        List<PlatformIPAccount> list =  platformIPAccountDaoI.list_PlatformIPAccountByShopCartIds(registeredUserInfoId,shopCartIds);
        String ipAccountIds = null;
        for(PlatformIPAccount platformIPAccount: list){
            ipAccountIds = platformIPAccount.getId()+",";
        }
        ipAccountIds = ipAccountIds.substring(0,ipAccountIds.length()-1);
        return  ipAccountIds;
    }

    /**
     * 列表页搜索
     * @param
     * @return
     */
    @Override
    public PageInfo<Map> getList(PlatformIpAccountSeach platformIpAccountSeach, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "pfi."+platformIpAccountSeach.getOrderByField()+" desc");
        List<Map> result = platformIPAccountDaoI.list(platformIpAccountSeach);
        return new PageInfo(result);
    }

    /**\
     * 修改是否代理
     * @param data
     */
    @Override
    public void updateAgent(PlatformIpAccountSeach data) {
        platformIPAccountDaoI.updateAgent(data);
    }

    @Override
    public Map<String, Object> getPlatformIdsByCategorys(String category) {
        return platformIPAccountDaoI.getPlatformIdsByCategorys(category);
    }
}

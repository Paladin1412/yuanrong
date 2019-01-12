package com.yuanrong.admin.server.controller.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumChannel;
import com.yuanrong.admin.Enum.EnumDictInfoType;
import com.yuanrong.admin.Enum.EnumPlatformIPAccountStatus;
import com.yuanrong.admin.bean.account.IP;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.account.PlatformIPAccountPrice;
import com.yuanrong.admin.bean.account.ShortVideoPlatformInfo;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.result.PlatformIPAccountResult;
import com.yuanrong.admin.seach.PlatformIpAccountSeach;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * Created by zhonghang on 2018/4/27.
 */
@Controller
@RequestMapping("account")
public class PlatformIPAccountController extends BaseController{

    @RequestMapping(value = "platformIPAccount_save" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate save(PlatformIPAccount data ){
        if(data.getPlatformID() <1 ){
            return new ResultTemplate("平台为空" , null);

        }

        if(StringUtil.isBlank(data.getPid())){
            return new ResultTemplate("平台唯一标识为空！" , null);

        }

        if(StringUtil.isBlank(data.getName())){
            return new ResultTemplate("账号名为空" , null);

        }

        if (StringUtil.isBlank(data.getAccountID())){
            return new ResultTemplate("账号ID信息为空" , null);

        }

        if(StringUtil.isBlank(data.getHeadImageUrl())){
            return new ResultTemplate("头像地址为空！" , null);

        }

        if ( data.getFans() <= 0){
            return new ResultTemplate("粉丝数为空！" , null);

        }

        if (StringUtil.isBlank(data.getIntroduction())){
            return new ResultTemplate("账号ID信息为空" , null);

        }
        if (data.getCategoryID() <= 0 ){
            return new ResultTemplate("账号分类为空" , null);

        }

        if(StringUtil.isBlank(data.getIndexUrl())){
            return new ResultTemplate("用户主页为空" , null);

        }

        if(StringUtil.isBlank(data.getArticleUrl())){
            return new ResultTemplate("唯一抓取项为空！",null);

        }

        //IP账号
        if(data.getiPID() != null && data.getiPID() > 0 ){
            IP ip = ipServicesI.selectById(data.getiPID());
            if(ip == null){
                return new ResultTemplate("ip不存在" , null);
    
            }
            data.setRegisteredUserInfoID(ip.getRegisteredUserInfoID());
            data.setCreaterAdminID(getUser().getRecID());
        }else{
            //验证是否关联注册用户
            if(data.getRegisteredUserInfoID() <= 0 ){
                return new ResultTemplate("注册用户参数为空" , null);
    
            }

            RegisteredUserInfo registeredUserInfo = registeredUserInfoService.getById(data.getRegisteredUserInfoID());
            if(registeredUserInfo == null){
                return new ResultTemplate("注册用户不存在" , null);
    
            }
            data.setRegisteredUserInfoID(registeredUserInfo.getRecID());
            data.setCreaterAdminID(getUser().getRecID());
        }

        RegisteredUserInfo registeredUserInfo = registeredUserInfoService.getById(data.getRegisteredUserInfoID());
        if(registeredUserInfo == null){
            return new ResultTemplate("注册用户不存在" , null);

        }

        /*if(registeredUserInfo.getSellerStatusValue() == null ||
                (registeredUserInfo.getSellerStatusValue().intValue() != EnumUserRoleLicenseStatus.审核成功.getIndex() &&
                data.getPlatformIPAccountStatus().getIndex() == EnumPlatformIPAccountStatus.上架.getIndex())){
             data.setPlatformIPAccountStatus(EnumPlatformIPAccountStatus.待实名);
        }*/
        //前台只有上架跟下载操作，没有待实名状态，所以注释掉上述代码

        //查找pid是否已经录入过
        PlatformIPAccount account = platformIPAccountServicesI.getByPidAndRegisterUserId(data.getPid() , data.getRegisteredUserInfoID());
        if(account != null){
            return new ResultTemplate("账号已经录入过了，您不能重复录入！" , null);

        }

        JSONArray prices = JSON.parseArray(data.getPlatPrices());
        List<PlatformIPAccountPrice> ipAccountPrices = new ArrayList<>();
        if(prices == null){
            return new ResultTemplate("参数错误" , null);
        }
        for(int i = 0 ; i < prices.size() ; i ++ ){
            try {
                JSONObject jsonObject = prices.getJSONObject(i);
                PlatformIPAccountPrice accountPrice = new PlatformIPAccountPrice();
                accountPrice.setiPAcctountID(data.getId());
                accountPrice.setPlatformPriceNameID(jsonObject.getInteger("priceId"));
                //验证报价是否存在
                if(platformIPAccountPriceNameRelationServicesI.getPlatformPriceNameByID(accountPrice.getPlatformPriceNameID()) == null){
                    return new ResultTemplate("价格不存在！" ,null);
        
                }
                accountPrice.setPrice(jsonObject.getBigDecimal("price"));
                accountPrice.setIsOriginal(jsonObject.getInteger("isOriginal"));
                ipAccountPrices.add(accountPrice);
            } catch (Exception e) {
                return new ResultTemplate("价格参数错误！" , null);
    
            }
        }
        if(data.getPlatformIPAccountStatus().getIndex() == EnumPlatformIPAccountStatus.上架.getIndex() && CollectionUtil.size(ipAccountPrices) <= 0){
            return new ResultTemplate("上架的账号，至少需要一个报价！" , null);

        }

        data.setChannel(EnumChannel.后台创建);
        //保存
        platformIPAccountServicesI.savePlatformIpAccount(data ,  ipAccountPrices);
        return new ResultTemplate("" , null);

    }

    @RequestMapping("exs_getPlatFormInfo")
    @ResponseBody
    public ResultTemplate getPlatFormInfo(){
        List<ShortVideoPlatformInfo> result = platformIPAccountServicesI.getPlatFormInfo();
        if(CollectionUtil.size(result) > 0){
            return new ResultTemplate("" , ShortVideoPlatformInfo.getArrrayByList(result));
        }else{
            return new ResultTemplate("" , null);
        }

    }

    @RequestMapping(value = "platformIPAccount_list" ,method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(PlatformIpAccountSeach platformIpAccountSeach , BaseModel baseModel){
        platformIpAccountSeach.setCurrLoginUser(getUser());
//        platformIpAccountSeach.setPlatformPrice(13);
        PageInfo<Map> result = platformIPAccountServicesI.list(platformIpAccountSeach , baseModel);
        JSONArray resultArray = new JSONArray();
        if(result!=null||!"".equals(result)||!"null".equals(result))
            if(CollectionUtil.size(result.getList()) > 0){
                for(Map<String ,Object> ele : result.getList()){
                    JSONObject jsonObject = PlatformIPAccount.mapPareJSONObject(ele);
                    jsonObject.put("pricesInfo",ele.get("info") == null ? "" : new PlatformIPAccountResult(ele.get("info").toString()).getPriceInfoJSONArray());
                    jsonObject.put("channelName" , ele.get("channelIndex") == null ? "" : ((EnumChannel) EnumUtil.valueOf(EnumChannel.class,Integer.parseInt(ele.get("channelIndex").toString()))).getName());
                    resultArray.add(jsonObject);
                }
            }
        return new ResultTemplate(result , resultArray);
    }



    @RequestMapping("getPlatFormIPAccountInfoById")
    @ResponseBody
    public ResultTemplate getPlatFormIPAccountInfoById(int id){
        if(id <=0){
            return new ResultTemplate("参数错误！" , null);

        }
        Map<String , Object> result = platformIPAccountServicesI.getPlatFormIPAccountInfoById(id);
        if(result == null){
            return new ResultTemplate("数据不存在！" ,null);

        }
        JSONObject resultJSON = PlatformIPAccount.mapPareJSONObject(result);
        resultJSON.put("notes" , result.get("notes"));
        resultJSON.put("successUrl" , result.get("successUrl"));
        resultJSON.put("successTitle" , result.get("successTitle"));
        resultJSON.put("advantage" , result.get("advantage"));
        resultJSON.put("isAuthentication" , result.get("isAuthentication"));
        resultJSON.put("authenticationInfo" , result.get("authenticationInfo"));
        resultJSON.put("cityID" , result.get("cityID"));
        resultJSON.put("womanPercent" , result.get("womanPercent"));
        resultJSON.put("sex" , result.get("sex"));
        return new ResultTemplate("" , resultJSON);
    }

    @RequestMapping("exs_getAccountStatus")
    @ResponseBody
    public ResultTemplate getAccountStatus(){
        return new ResultTemplate("" , EnumPlatformIPAccountStatus.getMapInfo());
    }

    @RequestMapping( value = "platformIPAccount_update" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate update(PlatformIPAccount data){
        if(data.getId() <=0){
            return new ResultTemplate("ID参数为空" , null);

        }
        PlatformIPAccount thePlatformIPAccount = platformIPAccountServicesI.getById(data.getId());
        if(thePlatformIPAccount == null){
            return new ResultTemplate("记录不存在" , null);

        }

        if(StringUtil.isNoneBlank(data.getAccountID())){
            thePlatformIPAccount.setAccountID(data.getAccountID());
        }else{
            return new ResultTemplate("账号ID为空" , null);

        }
        if(StringUtil.isNoneBlank(data.getName())){
            thePlatformIPAccount.setName(data.getName());
        }else{
            return new ResultTemplate("账号名为空" , null);

        }

        if(StringUtil.isNoneBlank(data.getHeadImageUrl())){
            thePlatformIPAccount.setHeadImageUrl(data.getHeadImageUrl());
        }else{
            return new ResultTemplate("头像为空" , null);

        }

        if(StringUtil.isNoneBlank(data.getHeadImageUrlLocal())){
            thePlatformIPAccount.setHeadImageUrlLocal(data.getHeadImageUrlLocal());
        }
        if (data.getFans() > 0) {
            thePlatformIPAccount.setFans(data.getFans());
        }else{
            return new ResultTemplate("粉丝数为空" , null);

        }

        if(StringUtil.isNotBlank(data.getIntroduction())){
            thePlatformIPAccount.setIntroduction(data.getIntroduction());
        }else{
            return new ResultTemplate("简介为空" , null);

        }

        //账号分类
        if(data.getCategoryID() >0){
            thePlatformIPAccount.setCategoryID(data.getCategoryID());
        }else{
            return new ResultTemplate("账号为空" , null);

        }

        if(data.getAccountStatus() > 0){
            thePlatformIPAccount.setAccountStatus(data.getAccountStatus());
        }else{
            return new ResultTemplate("账号状态为空" , null);

        }

        thePlatformIPAccount.setLowerCauseID(data.getLowerCauseID());

        //报价
        if(data.getPlatformIPAccountStatus().getIndex() == EnumPlatformIPAccountStatus.上架.getIndex() && StringUtil.isBlank(data.getPlatPrices())){
            return new ResultTemplate("上架状态的账号，至少需要一个报价" ,null);

        }
        thePlatformIPAccount.setPlatPrices(data.getPlatPrices());

        thePlatformIPAccount.setInvalidTime(data.getInvalidTime());
        thePlatformIPAccount.setNotes(data.getNotes());
        thePlatformIPAccount.setSuccessUrl(data.getSuccessUrl());
        thePlatformIPAccount.setSuccessTitle(data.getSuccessTitle());
        thePlatformIPAccount.setAdvantage(data.getAdvantage());
        if(data.getIsAuthenticationEnum() != null){
            thePlatformIPAccount.setIsAuthenticationEnum(data.getIsAuthenticationEnum());
        }
        thePlatformIPAccount.setAuthenticationInfo(data.getAuthenticationInfo());
        thePlatformIPAccount.setWomanPercent(data.getWomanPercent());
        thePlatformIPAccount.setCityID(data.getCityID());
        if(data.getSexEnum() != null){
            thePlatformIPAccount.setSex(data.getSex());
        }
        if(data.getIsAgent() != null){
            thePlatformIPAccount.setIsAgent(data.getIsAgent());
        }
        if(data.getAgentCoopBrand() != null && data.getAgentCoopBrand().length()<=200){
            thePlatformIPAccount.setAgentCoopBrand(data.getAgentCoopBrand());
        }
        if(data.getAgentCoopCondition() != null && data.getAgentCoopCondition().length()<=200){
            thePlatformIPAccount.setAgentCoopCondition(data.getAgentCoopCondition());
        }
        platformIPAccountServicesI.updatePlatFormIPAccountAndPrice(thePlatformIPAccount);
        return new ResultTemplate("" , null);
    }


    @RequestMapping(value = "importIPAccount" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate importIPAccount(MultipartFile file){
        if(file == null){
            return new ResultTemplate("文件信息为空！");
        }

        if(StringUtil.isBlank(getUser().getCpWeiXinId())){
            return new ResultTemplate("您当前未关注企业号，处理失败的账号，您将无法收到消息。关注后联系管理员配置信息并重新登录后，即可使用该功能！");
        }

        try {
            List<List<String>> result = ExcelUtil.read(file , 0);
            if(CollectionUtil.size(result) <=1 || result.get(0).size() < 12){
                return new ResultTemplate("表格数据不完整，请按照模板填写数据！" );
            }

            //0 userid	1 IPID	2 平台	3 账号ID	4 账号名称	5 账号唯一抓取项	6 账号分类	7 账号状态	8 下架原因说明
            // 9 粉丝数 10 价格有效期结束时间 	11次条发布报价	 13原创报价	14 直发报价	15 转发报价	16 视频发布报价	17 原创视频报价
            // 18 活动现场直播报价	19 头条发布报价
            JSONArray successArray = new JSONArray();
            JSONArray errorArray = new JSONArray();
            for(int i=1 ; i < result.size() ; i ++){
                int errIndex = 1;
                StringBuffer errInfo = new StringBuffer();
                JSONObject accountJSON = new JSONObject();
                accountJSON.put("row" , (i+1));
                //如果用户ID和IPID同时为空，则不合法
                if(StringUtil.isBlank(StringUtil.format(result.get(i).get(0))) && StringUtil.isBlank(StringUtil.format(result.get(i).get(1)))){
                    errInfo.append((errIndex ++) +"：userID和IPID不能同时为空；");
                }

                //如果userID不为空，查找IP是否存在
                if(StringUtil.isNotBlank(result.get(i).get(0))){
                    try{
                        RegisteredUserInfo registeredUserInfo = registeredUserInfoService.getById(Integer.parseInt(StringUtil.format(result.get(i).get(0))));
                        if(registeredUserInfo == null) {
                            errInfo.append((errIndex++) + "：userid系统不存在！");
                        }else{
                            accountJSON.put("userID" , registeredUserInfo.getRecID());
                        }
                    }catch (Exception e){
                        errInfo.append((errIndex ++) +"：userid数据类型错误！");
                    }
                }else  if(StringUtil.isNotBlank(result.get(i).get(1))){
                    //如果IP不为空，查找IP是否存在
                    try{
                        IP ip = ipServicesI.selectById(Integer.parseInt(StringUtil.format(result.get(i).get(1))));
                        if(ip == null){
                            errInfo.append((errIndex ++) +"：IP系统不存在！");
                        }else{
                            accountJSON.put("userID" , ip.getRegisteredUserInfoID());
                            accountJSON.put("ipID" , ip.getRecID());
                        }
                    }catch (Exception e){
                        errInfo.append((errIndex ++) +"：IP数据类型错误！");
                    }
                }else{
                    errInfo.append((errIndex ++) +"：userId和IPID不能同时为空！");
                }

                //平台
                if(StringUtil.isBlank(result.get(i).get(2))){
                    errInfo.append((errIndex ++) +"：平台不能为空！");
                }else{
                    if(result.get(i).get(2).contains("微信") && StringUtil.isBlank(StringUtil.format(result.get(i).get(9)))){
                        errInfo.append((errIndex ++) +"：微信平台粉丝不能为空！");
                    }
                    if(result.get(i).get(2).contains("微信") && StringUtil.isNotBlank(StringUtil.format(result.get(i).get(9)))){
                        try{
                            if(Integer.parseInt(StringUtil.format(result.get(i).get(9))) <= 0){
                                errInfo.append((errIndex ++) +"：粉丝数录入不合法，必须录入正整型数！");
                            }
                            accountJSON.put("fans" , result.get(i).get(9));
                        }catch (Exception e){
                            errInfo.append((errIndex ++) +"：粉丝数录入不合法，必须录入正整型数！");
                        }
                    }

                    Map<String , Object> platform = platformIPAccountPriceNameRelationServicesI.getPlatFormByName(StringUtil.format(result.get(i).get(2)));
                    if(platform == null){
                        errInfo.append((errIndex ++) +"：平台不存在或录入错误！");
                    }else{
                        accountJSON.put("platFormID" , platform.get("RecID"));
                        accountJSON.put("platformName" , StringUtil.format(result.get(i).get(2)));
                        //验证分类
                        if(StringUtil.isNotBlank(result.get(i).get(6))){
                            DictInfo dictInfo ;
                            if(StringUtil.format(result.get(i).get(2)).contains("微信")){
                                dictInfo = dictInfoServicesI.getDictInfoByTypeAndName(EnumDictInfoType.圆融分类.getIndex() , result.get(i).get(6));
                            }else if(StringUtil.format(result.get(i).get(2)).contains("微博")){
                                dictInfo = dictInfoServicesI.getDictInfoByTypeAndName(EnumDictInfoType.圆融分类.getIndex() , result.get(i).get(6));
                            } else{
                                //短视频
                                dictInfo = dictInfoServicesI.getDictInfoByTypeAndName(EnumDictInfoType.短视频分类.getIndex() , result.get(i).get(6));
                            }
                            if(dictInfo == null){
                                errInfo.append((errIndex ++) +"：分类不存在！");
                            }else{
                                //账号分类
                                accountJSON.put("categoryName" , StringUtil.format(result.get(i).get(6)));
                                accountJSON.put("categoryID" , dictInfo.getId());
                            }
                        }else{
                            errInfo.append((errIndex ++) +"：账号分类不能为空！");
                        }
                    }
                }

                //账号名称
                accountJSON.put("accountID" , StringUtil.format(result.get(i).get(3)));
                accountJSON.put("accountName" , StringUtil.format(result.get(i).get(4)));

                //唯一抓取项
                if(StringUtil.isBlank(result.get(i).get(5))){
                    errInfo.append((errIndex ++) +"：唯一抓取项不能为空！");
                }
                accountJSON.put("uniqueFetch" , StringUtil.format(result.get(i).get(5)));

                //是否上架
                boolean isUpShelf = false;
                //账号状态
                if(StringUtil.isNotBlank(result.get(i).get(7))){
                    List<Map<String,Object>> status = EnumPlatformIPAccountStatus.getMapInfo();
                    boolean accountFlage = true;
                    for(Map<String , Object> ele : status){
                        if(ele.get("name").toString().equals(StringUtil.format(result.get(i).get(7)))){
                            //账号状态
                            accountJSON.put("accountStatusID" , ele.get("id"));
                            accountJSON.put("accountStatusName" , StringUtil.format(result.get(i).get(7)));
                            accountFlage = false;
                            //如果为下架，下架原因必填
                            if(Integer.parseInt(ele.get("id").toString()) == EnumPlatformIPAccountStatus.下架.getIndex()){
                                if(StringUtil.isBlank(result.get(i).get(8))){
                                    errInfo.append((errIndex ++) +"：下架状态，下架原因必填！");
                                }else{
                                    accountJSON.put("lowerCauseID" , StringUtil.format(result.get(i).get(8)));
                                }
                            } else if (Integer.parseInt(ele.get("id").toString()) == EnumPlatformIPAccountStatus.上架.getIndex()){
                                isUpShelf = true;
                                //上架状态验证价格有效期
                                if(StringUtil.isBlank(result.get(i).get(10))){
                                    errInfo.append((errIndex ++) +"：上架状态，价格有效期必填！");
                                }else{
                                    try {
                                        DateUtil.parseDate(StringUtil.format(result.get(i).get(10)),"yyyy-MM-dd");
                                    } catch (ParseException e) {
                                        errInfo.append((errIndex ++) +"：价格有效期格式错误！");
                                    }
                                    accountJSON.put("invalidTime" , StringUtil.format(result.get(i).get(10)));
                                }
                            }
                        }
                    }
                    if(accountFlage){
                        errInfo.append((errIndex ++) +"：账号状态无效！");
                    }
                }else{
                    errInfo.append((errIndex ++) +"：账号状态不能为空！");
                }

                boolean isPrice = false;
                //价格
                if(StringUtil.isNotBlank(accountJSON.getString("platFormID"))){
                    int[] id = {Integer.parseInt(accountJSON.getString("platFormID"))};
                    //查找平台报价信息
                    List<Map<String , Object>> platPrices = platformIPAccountPriceNameRelationServicesI.getByShortVideoPlatformInfoIDs(id);
                    if(CollectionUtil.size(platPrices) > 0){
                        JSONArray priceArray = new JSONArray();
                        for(Map<String , Object> ele : platPrices){
                            //价格，判断是否有价格
                            int colIndex = result.get(0).lastIndexOf(ele.get("priceName"));
                            JSONObject priceObj = new JSONObject();
                            priceObj.put("id" , ele.get("id"));
                            priceObj.put("priceName" , ele.get("priceName"));
                            //存在该名字 , 并且有值
                            if(colIndex != -1 && StringUtil.isNotBlank(result.get(i).get(colIndex))){
                                try{
                                    BigDecimal price = new BigDecimal(StringUtil.format(result.get(i).get(colIndex)));
                                    priceObj.put("price" , price);
                                    if(price.compareTo(BigDecimal.ZERO) <=0){
                                        errInfo.append((errIndex ++) +"：报价必须大于0！");
                                    }
                                    isPrice = true;
                                }catch (Exception e){
                                    errInfo.append((errIndex ++) +"：报价格式录入错误！");
                                }
                            } else{
                                //不存在(关系表中有价格项，但是表格中未录入)
                                priceObj.put("price" , BigDecimal.ZERO);
                            }
                            priceArray.add(priceObj);
                        }
                        accountJSON.put("pricesInfo" , priceArray);
                    }
                }

                //如果为上架，并且没有报价，则说明
                if(isUpShelf && !isPrice){
                    errInfo.append((errIndex ++) +"：账号为上架，但是没有合法报价！");
                }
                if(StringUtil.isNotBlank(errInfo)){
                    accountJSON.put("errorMsg" , errInfo);
                    errorArray.add(accountJSON);
                }else{
                    accountJSON.put("createdUserID" , getUser().getRecID());
                    successArray.add(accountJSON);
                }
                accountJSON.put("firstReleasePrice" , result.get(i).get(11));
                accountJSON.put("secondReleasePrice" , result.get(i).get(12));
                accountJSON.put("originalPrice" , result.get(i).get(13));
                accountJSON.put("sentDirectlyPrice" , result.get(i).get(14));
                accountJSON.put("forwardPrice" , result.get(i).get(15));
                accountJSON.put("videoReleasePrice" , result.get(i).get(16));
                accountJSON.put("originalVideoPrice" , result.get(i).get(17));
                accountJSON.put("livePrice" , result.get(i).get(18));
            }
            //进入队列
            if(successArray.size() > 0){
                JSONObject queue = new JSONObject();
                queue.put("fileName" , file.getOriginalFilename());
                queue.put("cpWeiXinId" ,getUser().getCpWeiXinId());
                queue.put("data" , successArray);
                queue.put("createdTime" , DateUtil.getNowDateTime());
                rabbitMQServicesI.addIPAccountToMQ(queue);
            }
            return new ResultTemplate(errorArray);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultTemplate("文件读取失败，请检查文件格式是否正确！" , null);
        }
    }

    /**
     * 批量修改价格
     * @param file
     */
    @RequestMapping(value = "batchUpdateIPAccountPrice" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate batchUpdateIPAccountPrice(MultipartFile file){
        if(file == null){
            return new ResultTemplate("文件信息为空！" , null);

        }
        try {
            List<List<String>> result = ExcelUtil.read(file, 0);
            if (CollectionUtil.size(result) <= 1) {
                return new ResultTemplate("数据不存在！", null);
    
            }

            if(result.get(0).size() < 6){
                return new ResultTemplate("表格数据不完整，无法导入！", null);
    
            }

            //1.accountID不为空；2.（注册手机号，userID）和 账号ID 不为空
            // accountID	注册手机号	userID	账号ID	价格有效期结束时间
            int errIndex = 1;
            StringBuffer sb = new StringBuffer();
            JSONArray errorArray = new JSONArray();
            //需要更新的集合
            List<PlatformIPAccount> needUpdate = new ArrayList<>();
            for(int i = 1 ; i < result.size() ; i ++ ){
                PlatformIPAccount platformIPAccount = null;
                //手机号，或者注册用户ID，平台账号ID不为空
                String mobile = StringUtil.format(result.get(i).get(1));
                String userID = StringUtil.format(result.get(i).get(2));
                String accountID = StringUtil.format(result.get(i).get(3));
                String invalidTime = StringUtil.format(result.get(i).get(4));
                if(StringUtil.isBlank(invalidTime)){
                    sb.append((errIndex ++) + "：有效期必填");
                }
                if(StringUtil.isNotBlank(result.get(i).get(0))){
                    try{
                        platformIPAccount = platformIPAccountServicesI.getById(Integer.parseInt(result.get(i).get(0)));
                    }catch (Exception e){
                        sb.append((errIndex ++) + "：系统IP账号ID数据类型错误");
                    }
                } else if (StringUtil.isNotBlank(mobile) && StringUtil.isNotBlank(accountID)){
                    //查询IP账号
                    Map<String,Object> param = new HashMap();
                    param.put("accountID" , accountID);
                    param.put("mobile" , mobile);
                    platformIPAccount = platformIPAccountServicesI.getByParam(param);
                }else if (StringUtil.isNotBlank(userID) && StringUtil.isNotBlank(accountID)){
                    //查询IP账号
                    Map<String,Object> param = new HashMap();
                    param.put("registeredUserInfoID" , userID);
                    param.put("accountID" , accountID);
                    platformIPAccount = platformIPAccountServicesI.getByParam(param);
                }


                //查询报价
                if(platformIPAccount != null){
                    //该账号已有的历史价格
                    List<PlatformIPAccountPrice> accountPriceList = platformIPAccountPriceServicesI.getAccountPriceByIPAccountID(platformIPAccount.getId());
                    if(CollectionUtil.size(accountPriceList) > 0){
                        for(PlatformIPAccountPrice price : accountPriceList){
                            int colIndex = result.get(0).lastIndexOf(price.getPriceName());
                            //存在该价格，并且该单元格有值
                            if(colIndex != -1 && StringUtil.isNotBlank(result.get(i).get(colIndex))){
                                price.setPrice(new BigDecimal(result.get(i).get(colIndex)));
                                platformIPAccount.getIpAccountPrices().add(price);
                            }
                        }
                    }
                    //处理过期时间
                    if(StringUtil.isNotBlank(invalidTime)){
                        platformIPAccount.setInvalidTime(invalidTime);
                    }

                }else{
                    sb.append((errIndex ++) + "：系统IP账号ID该记录不存在");
                }

                //添加错误信息
                if(StringUtil.isNotBlank(sb)){
                    JSONObject error = new JSONObject();
                    error.put("rows" , (i+1));
                    error.put("info" , sb);
                    errorArray.add(error);
                }else{
                    needUpdate.add(platformIPAccount);
                }
            }
            //批量修改
            platformIPAccountPriceServicesI.batchUpdatePrice(needUpdate);
            return new ResultTemplate("" , errorArray);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultTemplate("文件读取失败！" , null);

        }
    }

    /**
     * 批量上下架
     */
    @RequestMapping("batchUpperLowerShelves" )
    @ResponseBody
    public ResultTemplate upperLowerShelves(PlatformIpAccountSeach data){
        if(StringUtil.isBlank(data.getPlatformAccountStatus()) || data.getIds() == null || data.getIds().length <=0){
            return new ResultTemplate("参数错误" , null);

        }
        EnumPlatformIPAccountStatus accountStatus = (EnumPlatformIPAccountStatus) EnumUtil.valueOf(EnumPlatformIPAccountStatus.class , data.getPlatformAccountStatus());
        if(accountStatus == null){
            return new ResultTemplate("状态错误" , null);

        }

        if(accountStatus.getIndex() == EnumPlatformIPAccountStatus.下架.getIndex() && data.getLowerCauseID() == null){
            return new ResultTemplate("下架，必须有下架原因" , null);

        }

        if(accountStatus.getIndex() == EnumPlatformIPAccountStatus.下架.getIndex()){
            //验证下架原因是否存在
            DictInfo dictInfo = dictInfoServicesI.getDictInfoByTypeAndID(EnumDictInfoType.未上架原因.getIndex() , data.getLowerCauseID());
            if(dictInfo == null){
                return new ResultTemplate("下架原因无效！" , null);
    
            }
        }


        platformIPAccountServicesI.batchUpperLowerShelves(data);
        return new ResultTemplate("" ,null);
    }

    @RequestMapping("batchUpdateInvalidTime")
    @ResponseBody
    public ResultTemplate batchUpdateInvalidTime(PlatformIpAccountSeach data){
        if(StringUtil.isBlank(data.getInvalidTime()) || data.getIds() == null || data.getIds().length <=0){
            return new ResultTemplate("参数错误" , null);

        }
        platformIPAccountServicesI.batchUpdateInvalidTime(data);
        return new ResultTemplate("" , null);
    }

    @RequestMapping("exs_platformIPAccount_exist")
    @ResponseBody
    public ResultTemplate exist(PlatformIPAccount data){
        PlatformIPAccount platformIPAccount = platformIPAccountServicesI.getByPidAndRegisterUserId(data.getPid() , data.getRegisteredUserInfoID());
        if(platformIPAccount != null){
            return new ResultTemplate("该账号已经被录入过了" , null);

        }
        return new ResultTemplate("",null);
    }
}

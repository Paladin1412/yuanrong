package com.yuanrong.admin.rpc.service.impl.author;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.base.*;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.result.YRAuthorInfoResult;
import com.yuanrong.admin.rpc.api.author.YRAuthorServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.seach.AuthorListParamSeach;
import com.yuanrong.admin.seach.AuthorListParamSeachMall;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.common.util.SystemParam;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 圆融创作者的services实现类
 * Created MDA
 */
@Service
public class YRAuthorServicesImpl extends BaseServicesAbstract<YRAuthor> implements YRAuthorServicesI {
    @Override
    public YRAuthor getById(Integer id) {
        return yRAuthorDaoI.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        yRAuthorDaoI.deleteById(id);
    }

    @Override
    public void save(YRAuthor object) {
        yRAuthorDaoI.save(object);
    }

    @Override
    public List<YRAuthor> getAll() {
        return yRAuthorDaoI.getAll();
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        return null;
    }

    /**
     * 通过同用户下作者名称判断作者是否存在
     *
     * @param authorName
     * @param recID
     * @return
     */
    @Override
    public YRAuthor getYRAuhorByName(String authorName, Integer recID) {
        return yRAuthorDaoI.getYRAuhorByName(authorName, recID);
    }

    /**
     * 通过作者名称判断作者是否存在
     *
     * @param authorName
     * @return
     */
    @Override
    public YRAuthor getYRAuhorByName(String authorName) {
        return getYRAuhorByName(authorName, null);
    }

    /**
     * 创作者列表
     * @param data
     * @param baseModel
     * @return
     */
    @Override
    public PageInfo<Map> list(AuthorListParamSeach data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "yra.createdTime desc,yra.recId desc");
        List<Map> result = yRAuthorDaoI.listResutMap(data);
        return new PageInfo(result);
    }

    /**
     * 前台-获取创作者详情信息
     * @param recId
     * @return
     */
    @Override
    public List<Map<String, Object>> getAuthorDetailInfo(Integer recId) {
        return yRAuthorDaoI.getAuthorDetailInfo(recId);
    }

    /**
     * 添加创作者
     * @param yRAuthor
     */
    @Override
    public Integer saveAuthor(YRAuthor yRAuthor) {
        //未爬取
        yRAuthor.setCrawlerStatus(EnumYesOrNo.FORBIDDEN.getIndex());
        //访问次数—假
        yRAuthor.setAccessNum(RandomUtil.randomInt(50,100));
        //保存创作者
        yRAuthorDaoI.save(yRAuthor);
        //内容形式、使用场景、擅长领域、作品标签、IP标签处理
        dealParam(yRAuthor,null);
        return yRAuthor.getRecId();
    }

    /**
     * 内容形式、使用场景、擅长领域、作品标签、IP标签处理
     * @param yRAuthor
     */
    private void dealParam(YRAuthor yRAuthor, Integer flag) {
        //删除创作者-内容形式 | 内容分类 | 使用场景关联关系
        yRAuthorDaoI.deleteAuthorRelation(yRAuthor.getRecId());
        if (flag != null){
            //后台—删除创作者-标签 | IP标签关联关系
            yRAuthorDaoI.deleteAuthorLableRelation(yRAuthor.getRecId());
        }
        //内容形式处理
        if(StringUtil.isNotBlank(yRAuthor.getContentForm())){
            String[] splitCon = yRAuthor.getContentForm().split(",");
            for (String str : splitCon){
                if(!str.equals("")){
                    ContentForm con = contentFormDaoI.getByName(StringUtil.format(str));
                    if(con !=null){
                        //保存创作者-内容形式关联表
                        yRAuthorDaoI.saveAuthorContentForm(yRAuthor.getRecId(),con.getId());
                    }else if(con == null){
                        //保存内容形式
                        ContentForm content= new ContentForm();
                        content.setContentFormName(StringUtil.format(str));
                        content.setStatusValue(EnumYesOrNo.FORBIDDEN.getIndex());
                        contentFormDaoI.save(content);
                        //保存创作者—内容形式关联表
                        yRAuthorDaoI.saveAuthorContentForm(yRAuthor.getRecId(),content.getId());
                    }
                }

            }
        }

        //内容分类处理
        if(StringUtil.isNotBlank(yRAuthor.getCategory())){
            String[] splitCate = yRAuthor.getCategory().split(",");
            for (String str : splitCate) {
                DictInfo dic = dictInfoDaoI.getById(Integer.parseInt(str));
                if(dic !=null){
                    //保存创作者—内容分类关联表
                    yRAuthorDaoI.saveAuthorCategory(yRAuthor.getRecId(), dic.getId());
                }
            }
        }

        //使用场景处理
        if(StringUtil.isNotBlank(yRAuthor.getScenes())){
            String[] splitScenes = yRAuthor.getScenes().split(",");
            for (String str : splitScenes){
                if(!str.equals("")) {
                    Scenes sce = scenesDaoI.getByName(StringUtil.format(str));
                    if (sce != null) {
                        //保存创作者—使用场景关联表
                        yRAuthorDaoI.saveAuthorScenes(yRAuthor.getRecId(), sce.getId());
                    } else if (sce == null) {
                        //保存使用场景
                        Scenes scenes1 = new Scenes();
                        scenes1.setScenesName(StringUtil.format(str));
                        scenes1.setStatusValue(EnumYesOrNo.FORBIDDEN.getIndex());
                        scenesDaoI.save(scenes1);
                        //保存创作者—使用场景关联表
                        yRAuthorDaoI.saveAuthorScenes(yRAuthor.getRecId(), scenes1.getId());
                    }
                }
            }
        }
        //后台—作品标签—内容属性
        if(StringUtil.isNotBlank(yRAuthor.getContentLable())){
            String[] splitCLable = yRAuthor.getContentLable().split(",");
            for (String str : splitCLable) {
                Lable lable = lableDaoI.getById(Integer.parseInt(str));
                if(lable !=null){
                    //保存创作者—标签(内容属性、表现风格)关联表
                    yRAuthorDaoI.saveAuthorLable(yRAuthor.getRecId(), lable.getId());
                }
            }
        }
        //后台—作品标签—表现风格
        if(StringUtil.isNotBlank(yRAuthor.getStyleLable())){
            String[] splitCLable = yRAuthor.getStyleLable().split(",");
            for (String str : splitCLable) {
                Lable lable = lableDaoI.getById(Integer.parseInt(str));
                if(lable !=null){
                    //保存创作者—标签(内容属性、表现风格)关联表
                    yRAuthorDaoI.saveAuthorLable(yRAuthor.getRecId(), lable.getId());
                }
            }
        }
        //后台—IP标签—母IP
        if(StringUtil.isNotBlank(yRAuthor.getParentIP())){
            String[] splitIPLable = yRAuthor.getParentIP().split(",");
            for (String str : splitIPLable) {
                IPLable ipLable = iPLableDaoI.getById(Integer.parseInt(str));
                if(ipLable !=null){
                    //保存创作者—标签(内容属性、表现风格)关联表
                    yRAuthorDaoI.saveAuthorIPLable(yRAuthor.getRecId(), ipLable.getRecID());
                }
            }
        }
        //后台—IP标签—子IP
        if(StringUtil.isNotBlank(yRAuthor.getSonIP())){
            String[] splitIPLable = yRAuthor.getSonIP().split(",");
            for (String str : splitIPLable) {
                IPLable ipLable = iPLableDaoI.getById(Integer.parseInt(str));
                if(ipLable !=null){
                    //保存创作者—IP标签关联表
                    yRAuthorDaoI.saveAuthorIPLable(yRAuthor.getRecId(), ipLable.getRecID());
                }
            }
        }
    }

    /**
     * 保存创作者—内容形式关联表
     * @param userId
     * @param contentformId
     */
    @Override
    public void saveAuthorContentForm(Integer userId, Integer contentformId) {
        yRAuthorDaoI.saveAuthorContentForm(userId, contentformId);
    }

    /**
     * 保存创作者—内容分类关联表
     * @param yrAuthorId
     * @param categoryId
     */
    @Override
    public void saveAuthorCategory(Integer yrAuthorId, Integer categoryId) {
        yRAuthorDaoI.saveAuthorCategory(yrAuthorId, categoryId);
    }

    /**
     * 保存创作者—使用场景关联表
     * @param userId
     * @param scenesId
     */
    @Override
    public void saveAuthorScenes(Integer userId, Integer scenesId) {
        yRAuthorDaoI.saveAuthorScenes(userId , scenesId);
    }

    /**
     * 批量修改创作者价格
     * @param ids
     * @param pricePercent
     */
    @Override
    public void updateBatchPrice(String ids, BigDecimal pricePercent) {
        String[] authorIds = ids.split(",");
        yRAuthorDaoI.updateBatchPrice(authorIds , pricePercent);
    }

    /**
     * 修改创作者
     * @param yRAuthor
     */
    @Override
    public void updateAuthor(YRAuthor yRAuthor,Integer flag) {
        //未爬取
        yRAuthor.setCrawlerStatus(EnumYesOrNo.FORBIDDEN.getIndex());
        //修改创作者匿名状态
        yRAuthor.setIsAnonymous(EnumYesOrNo.FORBIDDEN.getIndex());
        //修改创作者信息
        yRAuthor.setAuthorStatus(EnumAuthorStatus.待审核.getIndex());
        yRAuthorDaoI.updateAuthor(yRAuthor);
        //处理关联关系
        dealParam(yRAuthor,flag);
    }

    /**
     * 前台—批量操作（修改作者状态——上下架）
     * @param ids
     * @param status
     * @param recID
     */
    @Override
    public void batchApply(String ids, Integer status, Integer recID) {
        String[] split = ids.split(",");
        /*String str = "";*/
        /*RegisteredUserInfo userInfo = registeredUserInfoDAO.getById(recID);*/
        if(status == EnumAuthorStatus.上架.getIndex()){//上架
            /*for (int i = 0; i < split.length; i++) {
                //查询代表作，如果创作者代表作的个数小于3，状态不改变(不上架)
                List<YRProduction> list = yRProductionDaoI.findAuthorMagnum(Integer.parseInt(split[i]));
                if(list !=null && list.size() >=3){
                    if(str == null || str.length() <= 0){
                        str += split[i];
                    }else {
                        str += "," + split[i];
                    }
                }
            }*/
            //判断用户的认证情况（用户注册成功、审核失败、待审核状态时——创作者状态由审核失败，下架改为待审核）
            Integer authorStatus = EnumAuthorStatus.待审核.getIndex();
            yRAuthorDaoI.batchApply(split,authorStatus,status,recID);
        }else if (status == EnumAuthorStatus.未上架.getIndex()){//下架
            //创作者状态由上架、待审核改为下架
            Integer authorStatus = EnumAuthorStatus.未上架.getIndex();//前台—创作者自己下架
            yRAuthorDaoI.batchAuthorUnderReason(split,dictInfoDaoI.getDictInfoByTypeAndName(EnumDictInfoType.作者作品下架原因.getIndex(),"创作者自己下架").getName(),authorStatus,recID);
        }
    }

    /**
     * 创作者编辑页面
     * @param recId
     * @return
     */
    @Override
    public List<Map<String, Object>> getAuthorInfoUpdate(Integer recId) {
        return yRAuthorDaoI.getAuthorInfoUpdate(recId);
    }
    /**
     *
     * @author      ShiLinghuai
     * @param        errIndex 错误个数
     * @return
     * @exception
     * @date        2018/5/31 9:41
     */
    private void crawlAndAddProductionToList(StringBuffer sb, List<YRProduction> listProduction, int errIndex, boolean idFlag, String id, String masterWorkUrl, String productQuotedPrice) {
        BigDecimal prices = new BigDecimal(productQuotedPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
        YRProduction yrProduction = new YRProduction();
        //报价
        yrProduction.setProductQuotedPrice(prices);
        //设置代表作的url
        yrProduction.setProductUrl(masterWorkUrl);
        //是否是代表作
        yrProduction.setIsRepresentative(EnumYesOrNo.NORMAL.getIndex());
        //状态
        yrProduction.setYrProductionStatus(EnumYRProductionStatus.下架);
        //设置发表状态
        yrProduction.setPublishStatus(EnumPublishStatus.已公开);
        //作品形式
        //以后传来的是"图文"还要根据方法转成id
        ContentForm contentForm = new ContentForm();
        contentForm.setId(SystemParam.CONTENTFORMARTICLE);
        yrProduction.setContentForm(contentForm);
        yrProduction.setChannel(EnumChannel.后台创建);
        listProduction.add(yrProduction);

    }

    @Override
    public void batchSaveAuthor(List<YRAuthor> list) {
        for (YRAuthor yrAuthor :list) {
            saveAuthorInBatch(yrAuthor);
        }

    }
    public void saveAuthorInBatch(YRAuthor yrAuthor) {
        yRAuthorDaoI.save(yrAuthor);
        for (YRProduction production: yrAuthor.getYrProductions()
             ) {
            production.setYrAuthor(yrAuthor);
            yRProductionDaoI.save(production);
        }
            dealParam(yrAuthor,null);
        //继续保存作品

    }


    @Override
    public JSONObject batchGetSaveSucceedInfoAndErrorInfoByList(List<List<String>> result) {
        JSONArray successArray = new JSONArray();
        JSONArray errorArray = new JSONArray();
        List<YRAuthor> authors = new ArrayList<YRAuthor>();
        for (int i = 1; i < result.size(); i++) {

            int errIndex = 1;
            JSONObject jsonAuthor = new JSONObject();
            jsonAuthor.put("recID", result.get(i).get(0));
            jsonAuthor.put("authorNickname",result.get(i).get(1));
            jsonAuthor.put("authorImg", result.get(i).get(2));
            jsonAuthor.put("introduction",result.get(i).get(3));
            jsonAuthor.put("authorCreationTime",result.get(i).get(4));
            jsonAuthor.put("creationTimeRemark",result.get(i).get(5));
            jsonAuthor.put("createdPrice",result.get(i).get(6));
            jsonAuthor.put("createdPriceRemark",result.get(i).get(7));
            //代表作url
            jsonAuthor.put("masterWorkUrl1" , result.get(i).get(8));
            jsonAuthor.put("masterWorkUrl2" , result.get(i).get(9));
            jsonAuthor.put("masterWorkUrl3" , result.get(i).get(10));
            jsonAuthor.put("masterWorkPrice" , result.get(i).get(11));
            jsonAuthor.put("contentForm", result.get(i).get(12));
            jsonAuthor.put("scenes", result.get(i).get(13));
            jsonAuthor.put("contentLable",result.get(i).get(14));
            jsonAuthor.put("styleLable", result.get(i).get(15));
            jsonAuthor.put("category", result.get(i).get(16));



            //开始验证
            boolean idFlag = false;
            //代表作品1是否为空
            boolean masterWorkUrl1Flag = false;
            boolean masterWorkUrl2Flag = false;
            boolean masterWorkUrl3Flag = false;
            //作品转载价空不空标志
            boolean productQuotedPriceFlag = false;
            //新建作者对象
            YRAuthor yrAuthorN = new YRAuthor();
            //userID判断(必传)
            String id = result.get(i).get(0);
            String authorNickname = result.get(i).get(1);
            String headImg = result.get(i).get(2);
            StringBuffer sb = new StringBuffer();
            //判断id
            if (StringUtil.isBlank(id)) {
                sb.append((errIndex ++) +"，userID不能为空。");
            } else {
                try {
                    RegisteredUserInfo registeredUserInfo = registeredUserInfoDAO.getById(Integer.parseInt(result.get(i).get(0)));
                    if (registeredUserInfo == null) {//验证用户是否存在
                        sb.append((errIndex ++) +"，userID对应用户信息不存在。");
                    }else {
                        //id验证通过进行赋值操作
                        //证明id是好的。
                        idFlag  = true;
                        yrAuthorN.setRegisteredUserInfoID(Integer.parseInt(id));
                    }
                } catch (Exception e) {
                    sb.append((errIndex ++) +"，userID数据类型有问题。");
                }
            }
            //作者名称的判断不空的话，这个用户的作者名不能在库中存在，也不能在表里重复(必传)
            if (StringUtil.isBlank(authorNickname)) {
                sb.append((errIndex ++) +"，作者名称不能为空。");
            } else {
                try {
                    YRAuthor yrAuthor = yRAuthorDaoI.getYRAuhorByName(authorNickname, Integer.parseInt(id));
                    if (yrAuthor != null) {
                        sb.append((errIndex ++) +"，作者名称在用户id：" + id + "已存在。");
                    }else {
                        //如果名称正常就赋值，一会做插入操作
                        yrAuthorN.setAuthorNickname(authorNickname);
                    }
                } catch (Exception e) {
                    sb.append((errIndex ++) +"，userID数据类型有问题。");

                }
                //判断作者名称是否在表格中重复
                Integer nicknameCount= 0;
                for (List<String> oneLineData : result
                        ) {
                    //取出一行
                    if (authorNickname.equals(oneLineData.get(1))) {
                        nicknameCount++;
                        if(nicknameCount>1){
                            sb.append((errIndex ++) +"，作者名称在表格中重复。");
                        }
                    }
                }
            }
            //头像url判空(必传)
            if (StringUtil.isBlank(headImg)) {
                sb.append((errIndex ++) +"，头像url不能为空。");
            }else {
                //头像rul正常的话赋值
                yrAuthorN.setAuthorImg(headImg);
            }
            //创作者介绍判空(必传)
            String introduction = result.get(i).get(3);
            if (StringUtil.isBlank(introduction)) {
                sb.append((errIndex ++) +"，创作者介绍不能为空。");
            }else {
                //创作者介绍正常的话赋值
                yrAuthorN.setIntroduction(introduction);
            }
            //创作用时判空，判格式(必传)
            String createdTime = result.get(i).get(4);
            if (StringUtil.isBlank(createdTime)) {
                sb.append((errIndex ++) +"，创作用时不能为空。");
            } else {
                try {
                    Integer day = Integer.parseInt(result.get(i).get(4));
                    if(day<0||day>30){
                        sb.append((errIndex ++) +"，创作用时为1~30天整数。");
                    }else {
                        yrAuthorN.setAuthorCreationTime(day);
                    }
                } catch (Exception e) {
                    sb.append((errIndex ++) +"，创作用时类型错误。");
                }
            }
            //创作用时备注
            String creationTimeRemark = result.get(i).get(5);
            if (!StringUtil.isBlank(creationTimeRemark)) {
                yrAuthorN.setCreationTimeRemark(creationTimeRemark);
            }
            //创作参考价（必填）
            String createdPrice = result.get(i).get(6);
            if (StringUtil.isBlank(createdPrice)) {
                sb.append((errIndex ++) +"，创作参考价不能为空。");
            }else {
                //验证报价
                try{
                    BigDecimal bd=new BigDecimal(createdPrice);
                    if(bd.compareTo(BigDecimal.ZERO) <=0||bd.compareTo(new BigDecimal(999999999)) > 0){
                        sb.append((errIndex ++) +"，原创报价的范围是1-999999999之间。");

                    }else {
                        //设置报价
                        yrAuthorN.setCreatedPrice(bd);
                    }
                }catch (Exception e){
                    sb.append((errIndex ++) +"，创作参考价原创报价格式转换错误。");

                }

            }
            //创作报价备注
            String createdPriceRemark = result.get(i).get(7);
            if (!StringUtil.isBlank(introduction)) {
                yrAuthorN.setCreatedPriceRemark(createdPriceRemark);
            }
            //代表作品1url(必填)
            String masterWork1url = result.get(i).get(8);
            if (StringUtil.isBlank(masterWork1url)) {
                sb.append((errIndex ++) + "，代表作品1url不能为空。");
            }else {
                masterWorkUrl1Flag = true;
            }
            //代表作品2url（必填）
            String masterWork2url = result.get(i).get(9);
            if (StringUtil.isBlank(masterWork2url)) {
                sb.append((errIndex ++) +"，代表作品2url不能为空。");
            }else {
                masterWorkUrl2Flag = true;
            }
            //代表作品3url(必填)
            String masterWork3url = result.get(i).get(10);
            if (StringUtil.isBlank(masterWork3url)) {
                sb.append((errIndex ++) +"，代表作品3url不能为空。");
            }else {
                masterWorkUrl3Flag = true;
            }
            //报价判空
            String productQuotedPrice = result.get(i).get(11);
            if (StringUtil.isBlank(productQuotedPrice)) {
                sb.append((errIndex ++) +"，代表作报价不能为空。");
            }else {
                try {
                    BigDecimal prices = new BigDecimal(productQuotedPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
                    String productPrice = prices.toString();
                    if (productPrice.length() > 12) {
                        sb.append((errIndex ++) +"，代表作报价范围0.00-999999999.00。");
                    }else {
                        productQuotedPriceFlag = true;
                    }
                } catch (Exception e) {
                    sb.append((errIndex ++) +"，代表作报价格式转化错误，请检查是否存在。");
                }
            }
            //如果三个代表作都不为空，且都不相等，且报价不为空且格式正确。则去请求爬虫接口，赋值作品对象。
            if(masterWorkUrl1Flag==true&&masterWorkUrl2Flag==true&&masterWorkUrl3Flag==true&&productQuotedPriceFlag==true
                    &&!masterWork1url.equals(masterWork2url)&&!masterWork1url.equals(masterWork3url)&&!masterWork2url.equals(masterWork3url)){
                List<YRProduction> listProduction = new ArrayList<YRProduction>();
                //爬取第一个代表作
                crawlAndAddProductionToList(sb, listProduction, errIndex, idFlag, id, masterWork1url, productQuotedPrice);
                crawlAndAddProductionToList(sb, listProduction, errIndex, idFlag, id, masterWork2url, productQuotedPrice);
                crawlAndAddProductionToList(sb, listProduction, errIndex, idFlag, id, masterWork3url, productQuotedPrice);
                yrAuthorN.setYrProductions(listProduction);
            }else {
                sb.append((errIndex ++) +"，代表作重复。");
            }
            //创作形式（必填）
            //创作形式
            String contentForm = result.get(i).get(12);
            if (StringUtil.isBlank(contentForm)) {
                sb.append((errIndex ++) + "，创作形式不能为空。");
            }else {
                //创作形式不能多于三个
                String[] splitCon = contentForm.split("，");
                if(splitCon.length>3){
                    sb.append((errIndex ++) +"，创作形式不能多于三个。");
                }else {
                    //赋值
                    String contentFormN = contentForm.replace("，",",");
                    //判断有没有库里不存在的创作形式。
                    String contentFormS[] = contentFormN.split(",");
                    int contentFormCount = 0;
                    for (String str : contentFormS) {
                        ContentForm contentFormI  = contentFormDaoI.getByName(str);
                        if(contentFormI == null||contentFormI.getStatusValue().equals(EnumYesOrNo.FORBIDDEN.getIndex())){
                            contentFormCount++;
                            if(contentFormCount>1){
                                sb.append((errIndex ++) +"，其它的创作形式不能多于1个。");
                            }else {
                                contentFormN =  contentFormN+",其它";
                            }
                        }

                    }
                    yrAuthorN.setContentForm(contentFormN);
                }
            }
            //擅长场景（必填）
            //擅长场景
            String sences = result.get(i).get(13);
            if (StringUtil.isBlank(sences)) {
                sb.append((errIndex ++) +"，擅长场景不能为空。");
            }else {
                String[] splitSences = sences.split("，");
                if(splitSences.length>3){
                    sb.append((errIndex ++) +"，擅长场景不能多于三个。");
                }else {
                    //赋值
                    String splitSencesN = sences.replace("，",",");
                    //判断擅长场景里是否有其他
                    String sceness[] = splitSencesN.split(",");
                    int scenesCount = 0;
                    for (String str : sceness) {
                        Scenes scenes  = scenesDaoI.getByName(str);
                        if(scenes == null||scenes.getStatusValue().equals(EnumYesOrNo.FORBIDDEN.getIndex())){
                            scenesCount++;
                            if(scenesCount>1){
                                sb.append((errIndex ++) +"，其它的擅长场景不能多于1个。");
                            }else {
                                splitSencesN =  splitSencesN+",其它";
                            }
                        }

                    }
                    yrAuthorN.setScenes(splitSencesN);
                }

            }
            //作者标签-内容属性
            String contentLable = result.get(i).get(14);
            StringBuffer contentLableN = new StringBuffer();
            if (StringUtil.isBlank(contentLable)) {
                sb.append((errIndex ++) +"，作者内容属性标签不能为空。");
            }else {
                String[] splitAuthorTag = contentLable.split("，");
                if(splitAuthorTag.length>5){
                    sb.append((errIndex ++) +"，作者内容属性标签不能多于五个。");
                }else {
                    //把内容属性的id封装成接口需要的类型
                    for (String str : splitAuthorTag) {
                        Lable lable  = lableDaoI.getByNameAndType(str,EnumLableType.内容属性.getIndex());
                        if(lable ==null){
                            sb.append((errIndex ++) +str+"，此作者内容属性标签数据库不存在，抱歉。");
                        }else {
                            contentLableN.append(lable.getId()+",");
                        }
                    }
                    //删除最后一个逗号
                    try {
                        String contentLableS = contentLableN.substring(0,contentLableN.length()-1);
                        yrAuthorN.setContentLable(contentLableS);
                    }catch (StringIndexOutOfBoundsException e){
                        yrAuthorN.setContentLable(null);
                    }

                }

            }
            //作者标签-表现风格属性标签
            String styleLable = result.get(i).get(15);
            StringBuffer styleLableN = new StringBuffer();
            if (StringUtil.isBlank(styleLable)) {
                sb.append((errIndex ++) +"，作者表现风格标签不能为空。");
            }else {
                String[] styleLableTag = styleLable.split("，");
                if(styleLableTag.length>5){
                    sb.append((errIndex ++) +"，作者表现风格标签不能多于五个。");
                }else {
                    for (String str : styleLableTag) {
                        Lable lable  = lableDaoI.getByNameAndType(str,EnumLableType.表现风格.getIndex());
                        if(lable ==null){
                            sb.append((errIndex ++) +str+"，此作者表现风格标签数据库不存在，抱歉。");
                        }else {
                            styleLableN.append(lable.getId()+",");
                        }
                    }
                    //删除最后一个逗号
                    try {
                        String styleLableS = styleLableN.substring(0,styleLableN.length()-1);
                        yrAuthorN.setStyleLable(styleLableS);
                    }catch (StringIndexOutOfBoundsException e){
                        yrAuthorN.setStyleLable(null);
                    }

                }
            }
            //擅长领域
            String category = result.get(i).get(16);
            StringBuffer categoryB = new StringBuffer();
            if (StringUtil.isBlank(category)) {
                sb.append((errIndex ++) +"，擅长领域不能为空。");
            }else {
                String[] categoryName = category.split("，");
                if(categoryName.length>5){
                    sb.append((errIndex ++) +"，擅长领域不能多于五个。");
                }else {
                    for (String str : categoryName) {
                        DictInfo dictInfo = dictInfoDaoI.getDictInfoByTypeAndName(EnumDictInfoType.圆融分类.getIndex(),str);
                        if(dictInfo ==null){
                            sb.append((errIndex ++) + str+"，擅长领域数据库不存在，抱歉。");
                        }else {
                            categoryB.append(dictInfo.getId()+",");
                        }
                    }
                    try{
                        String categoryNew = categoryB.substring(0,categoryB.length()-1);
                        yrAuthorN.setCategory(categoryNew);
                    }catch (StringIndexOutOfBoundsException e){
                        yrAuthorN.setCategory(null);
                    }

                }
            }
            //设置作者状态
            yrAuthorN.setAuthorStatus(EnumAuthorStatus.未上架.getIndex());
            yrAuthorN.setEnumChannel(EnumChannel.后台创建);
            if(StringUtil.isNotBlank(sb)){
                jsonAuthor.put("errorMsg" , sb);
                errorArray.add(jsonAuthor);
            }else{
                authors.add(yrAuthorN);
                successArray.add(jsonAuthor);
            }
        }
        //保存
        batchSaveAuthor(authors);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("successInfo" , successArray);
        jsonObject.put("errorInfo" , errorArray);

        return jsonObject;
    }
    /**
    * 批量修改作者价格
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/5/30 17:31
    */
    @Override
    public void batchUpdateAuthorPrice(List<YRAuthor> list) {
        for (YRAuthor yrAuthor: list) {
            yRAuthorDaoI.updatePriceByUserIDMoblieName(yrAuthor);
        }
    }

    private void vertifyAuthorNickname(StringBuffer sb, int errIndex, YRAuthor yrAuthorN, String authorNickname) {
        //验证作者名称
        yrAuthorN.setAuthorNickname(authorNickname);
        //作者名称是否存在数据库
        YRAuthor yrAuthor = yRAuthorDaoI.getByUIDOrPhoneAndNickname(yrAuthorN);
        if (yrAuthor == null) {
            sb.append((errIndex ++) +"，该用户的作者名称不存在数据库中。");
        }
    }

    private void vertifyPhone(StringBuffer sb, int errIndex, YRAuthor yrAuthorN, RegisteredUserInfo registeredUserInfoN, String phone,String authorNickname) {
        List<RegisteredUserInfo> listN = registeredUserInfoDAO.getByMobile(phone);
        if (listN.size() >= 1) {
            //说明手机存在
            registeredUserInfoN.setMobile(phone);
            yrAuthorN.setRegisteredUserInfo(registeredUserInfoN);
            //接着验证作者昵称
            if (StringUtil.isBlank(authorNickname)) {
                sb.append((errIndex ++) +"，作者名称不能为空。");
            } else {
                vertifyAuthorNickname(sb, errIndex, yrAuthorN, authorNickname);
            }
        } else {
            sb.append((errIndex ++) +"，手机号不存在库中。");
        }
    }

    private void vertifyID(StringBuffer sb, int errIndex, YRAuthor yrAuthorN, RegisteredUserInfo registeredUserInfoN, String id, String column,String authorNickname) {
        try {
            RegisteredUserInfo registeredUserInfo = registeredUserInfoDAO.getById(Integer.parseInt(id));
            if (registeredUserInfo == null) {
                sb.append((errIndex ++) + "，id不存在数据库中。");
            } else {
                //存在则赋值id
                registeredUserInfoN.setRecID(registeredUserInfo.getRecID());
                yrAuthorN.setRegisteredUserInfo(registeredUserInfoN);
                //去验证作者名称
                //创作者名称的验证
                if (StringUtil.isBlank(authorNickname)) {
                    sb.append((errIndex ++) +"，作者名称不能为空。");
                } else {
                    vertifyAuthorNickname(sb, errIndex, yrAuthorN, authorNickname);

                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            sb.append((errIndex ++) +"，id格式转化错误。");

        }
    }

    private void puteErrorInfoToArray(JSONArray erroList, int row, String column, String errorMsg) {
        JSONObject erroInfo = new JSONObject();
        erroInfo.put("row", row);
        erroInfo.put("column", column);
        erroInfo.put("errorMsg", errorMsg);
        erroList.add(erroInfo);
    }
    /**
     * 后台—批量操作(申请上架、下架、审核通过、审核不通过)
     * @param ids
     * @param status
     * @param realName
     * @param reason
     */
    @Override
    public void batchApplyBack(String ids, Integer status,String reason,String realName) {

        String[] split = ids.split(",");
        Integer authorStatus = 0;
        String newSplit = "";
        if(status == EnumAuthorStatus.上架.getIndex()){//上架
            /*for (String str : split) {
                //查询代表作，如果创作者代表作的个数小于3，状态不改变(不上架)
                List<YRProduction> list = yRProductionDaoI.findAuthorMagnum(Integer.parseInt(str));
                if(list !=null && list.size() >=3){
                    if(newSplit == null || newSplit.length() <= 0){
                        newSplit += str;
                    }else {
                        newSplit += "," + str;
                    }
                }
            }
            split = newSplit.split(",");*/
            //判断用户的认证情况（用户注册成功、审核失败、待审核状态时——创作者状态由审核失败，下架改为待审核）
            authorStatus = EnumAuthorStatus.待审核.getIndex();
        }else if (status == EnumAuthorStatus.未上架.getIndex()){//下架
            //创作者状态由上架、待审核改为下架
            authorStatus = EnumAuthorStatus.未上架.getIndex();
        }else if(status == EnumAuthorStatus.审核不通过.getIndex()){//审核不通过
            //创作者状态由待审核状态改为审核失败
            authorStatus = EnumAuthorStatus.审核不通过.getIndex();
        } else if (status == EnumAuthorStatus.待审核.getIndex()) {//审核通过 判断用户的状态
            /*for (String str : split) {
                //通过创作者id获取创作者信息及其用户信息
                YRAuthor yrAuthor = yRAuthorDaoI.getUserByAuthorId(Integer.parseInt(str));
                if (yrAuthor != null) {
                    if (yrAuthor.getRegisteredUserInfo() != null) {
                        RegisteredUserInfo userInfo = yrAuthor.getRegisteredUserInfo();
                        //判断用户的认证情况（用户注册成功、审核失败、待审核状态时——创作者状态由待审核改为待审核）
                        if(userInfo.getSellerStatusValue() == EnumUserRoleLicenseStatus.注册成功.getIndex() || userInfo.getSellerStatusValue() == EnumUserRoleLicenseStatus.审核失败.getIndex() || userInfo.getSellerStatusValue() == EnumUserRoleLicenseStatus.待审核.getIndex()){
                            authorStatus = EnumAuthorStatus.待审核.getIndex();
                        }else if (userInfo.getSellerStatusValue() == EnumUserRoleLicenseStatus.审核成功.getIndex()){//用户审核成功——创作者由待审核改为上架
                            authorStatus = EnumAuthorStatus.上架.getIndex();
                        }
                    }
                }
            }*/
            authorStatus = EnumAuthorStatus.上架.getIndex();
        }
        if(authorStatus == EnumAuthorStatus.审核不通过.getIndex()){//审核不通过
            yRAuthorDaoI.batchFailReason(split,reason,EnumAuthorStatus.审核不通过.getIndex(),realName);
        }else if(authorStatus == EnumAuthorStatus.未上架.getIndex()){//下架
            if(reason == null){//前台—创作者自己下架
                reason = dictInfoDaoI.getDictInfoByTypeAndName(EnumDictInfoType.作者作品下架原因.getIndex(),"创作者自己下架").getName();
            }
            yRAuthorDaoI.batchAuthorUnderReason(split,reason,authorStatus,null);
        }else if(authorStatus == EnumAuthorStatus.上架.getIndex()) {//审核通过
            //上架-创作者
            yRAuthorDaoI.batchApplyUp(split,authorStatus,status,realName);
        }else {//待审核
            yRAuthorDaoI.batchApply(split,authorStatus,status,null);
        }
    }

    /**
     * 前台商城—创作者列表
     * @param map
     * @param baseModel
     * @return
     */
    @Override
    public PageInfo<Map> authorList(AuthorListParamSeachMall map, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "yra.sortScore , yra.yRIndex DESC , yra.recId ASC");
        List<Map> result = yRAuthorDaoI.authorList(map);
        return new PageInfo(result);
    }

    /**
     * 前台商城—创作者\作品详情
     * @param recid
     * @param userId
     * @return
     */
    @Override
    public YRAuthorInfoResult getAuthorInfo(Integer recid,Integer userId) {
        return yRAuthorDaoI.getAuthorInfo(recid,userId);
    }
    /**
    * 批量改价格
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/6/5 14:36
    */
    @Override
    public JSONObject batchGetUpdatePriceSucceedInfoAndErrorInfoByList(List<List<String>> result) {
        JSONArray successArray = new JSONArray();
        JSONArray errorArray = new JSONArray();
        List<YRAuthor> authors = new ArrayList<YRAuthor>();
        for (int i = 1; i < result.size(); i++) {
            Integer errIndex = 0;
            StringBuffer sb = new StringBuffer();
            //新建作者对象
            YRAuthor yrAuthorN = new YRAuthor();
            RegisteredUserInfo registeredUserInfoN = new RegisteredUserInfo();
            String id = result.get(i).get(1);
            String phone = result.get(i).get(0);
            String authorNickname = result.get(i).get(2);
            String createPrice = result.get(i).get(3);

            JSONObject accountJSON = new JSONObject();
            accountJSON.put("id" , id);
            accountJSON.put("phone" , phone);
            accountJSON.put("authorNickname" , authorNickname);
            accountJSON.put("createPrice" , createPrice);
            //手机号和id的验证
            if (StringUtil.isBlank(id) && StringUtil.isBlank(phone)) {
                sb.append((errIndex ++) +"，手机号和id必须填一个");
            } else {
                 if (StringUtil.isNotBlank(id) && StringUtil.isNotBlank(phone) ) {
                    //如果id存在验证id是否存在，通过则赋值
                     if(StringUtil.isNotBlank(id)){
                         vertifyID(sb, errIndex, yrAuthorN, registeredUserInfoN, id, "A",authorNickname);
                     }
                    //如果手机号存在验证此手机号注册用户中是否存在,验证通过赋值

                    vertifyPhone(sb, errIndex, yrAuthorN, registeredUserInfoN, phone,authorNickname);
                    //如果手机号和id都存在，则验证匹配
                    RegisteredUserInfo registeredUserInfo = registeredUserInfoDAO.getByObject(registeredUserInfoN);
                    if (registeredUserInfo == null) {
                        sb.append((errIndex ++) + "，手机号和id不匹配。");
                    }
                    //验证作者名称
                    //以上方法块中已付值id和mobile
                    vertifyAuthorNickname(sb, errIndex, yrAuthorN, authorNickname);
                } else if (StringUtil.isNotBlank(id)) {
                    //如果id存在验证id是否存在，通过则赋值且去查作者
                    vertifyID(sb, errIndex, yrAuthorN, registeredUserInfoN, id, "A",authorNickname);
                    //以上方法块中已付值id和mobile
                    //验证作者名称
                } else if (StringUtil.isNotBlank(phone) ) {
                    //如果手机号存在验证此手机号注册用户中是否存在，通过则赋值
                    vertifyPhone(sb, errIndex, yrAuthorN, registeredUserInfoN, phone,authorNickname);
                    //以上方法块中已付值id和mobile
                    vertifyAuthorNickname(sb, errIndex, yrAuthorN, authorNickname);
                }

            }
            //创作者名称的验证
            if (StringUtil.isBlank(authorNickname)) {
                sb.append((errIndex ++) +"，作者名称不能为空。");
            } else {

            }
            //创作参考价的验证
            if (StringUtil.isBlank(createPrice)) {
                sb.append((errIndex ++) +"，报价不能为空。");
            } else {
                //验证报价的格式
                try {
                    BigDecimal prices = new BigDecimal(createPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
                    String productPrice = prices.toString();
                    if (productPrice.length() > 12) {
                        sb.append((errIndex ++) +"，作品报价范围0.00-999999999.00。");
                    }else {
                        yrAuthorN.setCreatedPrice(prices);
                    }
                } catch (Exception e) {
                    sb.append((errIndex ++) +"，作品报价格式转化错误，请检查是否存在。");
                }
            }
            if(StringUtil.isNotBlank(sb)){
                accountJSON.put("errorMsg" , sb);
                errorArray.add(accountJSON);
            }else{
                authors.add(yrAuthorN);
                successArray.add(accountJSON);
            }
        }
        //保存成功记录
        batchUpdateAuthorPrice(authors);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("successInfo" , successArray);
        jsonObject.put("errorInfo" , errorArray);
        return jsonObject;
    }


    @Override
    public List<YRAuthor> getAuthorsByName(YRAuthor data) {
        return yRAuthorDaoI.getAuthorsByName(data);
    }

    /**
     * 获取创作者的字典信息（内容形式、使用场景、擅长领域）
     * @return
     */
    @Override
    public JSONObject getAuthorDicinfo() {
        JSONObject jsonObject = new JSONObject();
        //内容形式
        JSONArray contFormInfo = new JSONArray();
        List<ContentForm> contentFormList = contentFormDaoI.list(EnumYesOrNo.NORMAL.getIndex());
        if(CollectionUtil.size(contentFormList) >0){
            contFormInfo.addAll(ContentForm.packageContentForm(contentFormList));
        }
        //使用场景
        JSONArray scenesInfo = new JSONArray();
        List<Scenes> scenesList = scenesDaoI.list(EnumYesOrNo.NORMAL.getIndex());
        if(CollectionUtil.size(scenesList) > 0){
            scenesInfo.addAll(Scenes.packageScenes(scenesList));
        }
        //擅长领域
        JSONArray categoryInfo = new JSONArray();
        List<DictInfo> dicList = dictInfoDaoI.getDictInfoByType(EnumDictInfoType.圆融分类.getIndex());
        if(CollectionUtil.size(dicList) >0){
            categoryInfo.addAll(DictInfo.pareToJSONObject(dicList));
        }
        jsonObject.put("contFormInfo",contFormInfo);
        jsonObject.put("scenesInfo",scenesInfo);
        jsonObject.put("categoryInfo",categoryInfo);

        return jsonObject;
    }

    @Override
    public List<YRAuthor> getByRegisterUserId(Integer registerUserInfoId) {
        return yRAuthorDaoI.getByRegisterUserId(registerUserInfoId);
    }

    @Override
    public PageInfo<YRAuthor> getYRAuthorListByRegisterUserId(YRAuthor data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "createdTime DESC ");
        return new PageInfo(yRAuthorDaoI.getYRAuhorByName_LikeSearch(data));
    }

    /**
     * C端—创作者列表—V1.3
     * @param mapParam
     * @param baseModel
     * @return
     */
    @Override
    public PageInfo<YRAuthorInfoResult> getAuthorAndProList(AuthorListParamSeachMall mapParam, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "yra.sortScore DESC, yra.yRIndex DESC , yra.recId ASC");
        List<YRAuthorInfoResult> result = yRAuthorDaoI.getAuthorAndProList(mapParam);
        return new PageInfo(getAuthorProList(result));
    }

    /**
     * C端— 获取创作者作品列表V1.3
     * @param result
     * @return
     */
    public List<YRAuthorInfoResult> getAuthorProList(List<YRAuthorInfoResult> result){
        if(CollectionUtil.size(result) > 0){
            String[] recIds = new String[result.size()];
            int index = 0 ;
            for(YRAuthorInfoResult author : result){
                recIds[index++] =  author.getRecId().toString();
            }
            List<YRProduction> proList = yRAuthorDaoI.getAuthorProductionList(recIds);
            if(CollectionUtil.size(proList) > 0){
                for (YRAuthorInfoResult yrAuthor : result){
                    List<YRProduction> list = new ArrayList<YRProduction>();
                    for (YRProduction pro : proList){
                        if(pro.getYrAuthorId().intValue() == yrAuthor.getRecId().intValue()){
                            list.add(pro);
                        }
                    }
                    yrAuthor.setYrProductions(list);
                }
            }
        }
        return result;
    }
    /**
     * 获取相似创作者列表—V1.3
     * @param a
     * @param b
     * @param categoryId
     * @param recid
     * @return
     */
    @Override
    public List<YRAuthorInfoResult> getLikeAuthor(Integer a, Integer b, Integer categoryId, Integer recid) {
        List<YRAuthorInfoResult> yrAuthors = yRAuthorDaoI.getLikeAuthor(a, b, categoryId,recid);
        return getAuthorProList(yrAuthors);
    }

    /**
     * 获取相似创作者列表数量—V1.3
     * @param categoryId
     * @param sortScore
     * @return
     */
    @Override
    public List<Map<String, Object>> getLikeAuthorNum(Integer categoryId, BigDecimal sortScore) {
        return yRAuthorDaoI.getLikeAuthorNum(categoryId, sortScore);
    }

    /**
     * 获取批量创作者
     * @param ids
     * @return
     */
    @Override
    public PageInfo<JSONObject> list_yrAuthorByIds(String ids,BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows());
        List<YRAuthor> list = yRAuthorDaoI.list_yrAuthorByIds(ids);
        List<JSONObject> rntList = new ArrayList<JSONObject>();
        for (YRAuthor yrAuthor : list){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("authorImg",yrAuthor.getAuthorImg());
            jsonObject.put("introduction",yrAuthor.getIntroduction());
            jsonObject.put("authorNickname",yrAuthor.getAuthorNickname());
            jsonObject.put("createdPrice",yrAuthor.getCreatedPrice());
            jsonObject.put("yrAuthorId",yrAuthor.getRecId());
            jsonObject.put("price","按需定价");
            rntList.add(jsonObject);
        }

        return  new PageInfo(rntList);
    }

    /**
     * 后台—需求选购创作者列表
     * @param demandId
     * @param baseModel
     * @param likeName
     * @param authorStatus
     * @return
     */
    @Override
    public PageInfo<Map> getDemandAuthorList(Integer demandId, String[] likeName, Integer authorStatus, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "yra.authorNickname desc,yra.recId desc");
        String date = DateUtil.format(DateUtils.addDays(new Date(),-180), "yyyy-MM-dd");
        List<Map> result = yRAuthorDaoI.getDemandAuthorList(demandId,likeName,authorStatus,date);
        return new PageInfo(result);
    }

    /**
     * 计算创作者排序分数
     */
    @Override
    public void updateCalculateSortScore() {
        yRAuthorDaoI.calculateSortScore();
    }

    /**
     * C端—修改创作者访问次数
     */
    @Override
    public void updateAuthorAccessTimes(YRAuthor yrAuthor) {
        String accessNumKey = YRAuthor.class.getName()+":accessNum:"+yrAuthor.getRecId();
        String accessTimesKey = YRAuthor.class.getName()+":accessTimes:"+yrAuthor.getRecId();
        long accessNum = getValue(accessNumKey);
        if(accessNum <=0){
            //保存数据库的值
            redisTemplate.opsForValue().set(accessNumKey , yrAuthor.getAccessNum() == null ? 0 : yrAuthor.getAccessNum()+"");
            redisTemplate.opsForValue().set(accessTimesKey,yrAuthor.getAccessTimes() == null ? 0 :  yrAuthor.getAccessTimes()+"");
        }
        //自增
        incr(accessNumKey);
        incr(accessTimesKey);
    }

    @Override
    public List<YRAuthor> getyrAuthorByshoppingCartId(int registeredUserInfoId, String shopCartIds) {
        List<YRAuthor> list = yRAuthorDaoI.getyrAuthorByshoppingCartId(registeredUserInfoId,shopCartIds);
        return list;
    }

    @Override
    public YRAuthor getYRAuthorById_RegUserId(YRAuthor data) {
        return yRAuthorDaoI.getYRAuthorById_RegUserId(data);
    }

    /**
     * 通过创作者ID查询创作者信息——判断
     * @param recId
     * @return
     */
    @Override
    public YRAuthor getAuthorInfoById(Integer recId) {
        return yRAuthorDaoI.getAuthorInfoById(recId);
    }

    @Override
    public void updateFlushRedisAccessTimeToDb() {
        String accessNumKey = YRAuthor.class.getName()+":accessNum:*";
        String accessTimesKey = YRAuthor.class.getName()+":accessTimes:*";
        Set<String> accessNumKeys = redisTemplate.keys(accessNumKey);
        Set<String> accessTimesKeys = redisTemplate.keys(accessTimesKey);

        Map<Integer , Long> accessNums = new HashMap<>();
        Map<Integer , Long> accessTimes = new HashMap<>();
        for(String ele : accessNumKeys){
            int yrProductionId = Integer.parseInt(ele.replace(YRAuthor.class.getName()+":accessNum:",""));
            accessNums.put( yrProductionId , getValue(ele));
        }

        for(String ele : accessTimesKeys){
            int yrProductionId = Integer.parseInt(ele.replace(YRAuthor.class.getName()+":accessTimes:",""));
            accessTimes.put( yrProductionId , getValue(ele));
        }
        yRAuthorDaoI.batchUpdateAccessNums(accessNums);
        yRAuthorDaoI.batchUpdateAccessTime(accessTimes);
    }


    /**
     * 通过购物车id,获取YRAuthor
     * @param registeredUserInfoId
     * @param shopCartIds
     * @return
     */
    public String getYRAuthorIds(int registeredUserInfoId, String shopCartIds){
        List<YRAuthor> list = yRAuthorDaoI.getyrAuthorByshoppingCartId(registeredUserInfoId,shopCartIds);
        String yrAuthorIds = null;
        for( YRAuthor yrAuthor : list){
            yrAuthorIds = yrAuthor.getRecId()+",";
        }
        yrAuthorIds = yrAuthorIds.substring(0,yrAuthorIds.length()-1);
        return yrAuthorIds;
    }
}

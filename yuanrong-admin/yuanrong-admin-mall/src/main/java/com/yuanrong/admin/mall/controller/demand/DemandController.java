package com.yuanrong.admin.mall.controller.demand;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.qiniu.util.Json;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.base.SMSValidateCode;
import com.yuanrong.admin.bean.base.UserInfo;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.rpc.api.demand.DemandFastServicesI;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基本需求信息的controller
 * Created MDA
 */
@Controller
@RequestMapping("demand")
public class DemandController extends BaseController {
    private static final Logger logger = Logger.getLogger(DemandController.class);

    /**
     *   C端 发布需求
     *   String authorIds, String accountIPIds, 前端不传值，通过shopCartIds查询
     * @param demand
     */
    @RequestMapping("demand_save")
    @ResponseBody
    public ResultTemplate save(Demand demand, String smsCode, String authorIds, String accountIPIds, String shopCartIds) {
        if (getWebUser() != null) {
            demand.setRegisteredUserInfoId(getWebUser().getRecID());
            demand.setMobile(getWebUser().getMobile());
        } else {
            if (StringUtil.isBlank(smsCode)) {
                return new ResultTemplate("输入短信验证码不为空，请输入短信验证码！", null);
            }
            SMSValidateCode smsValidateCode = (SMSValidateCode) getSession().getAttribute("smsValidateCode");
            if(smsValidateCode==null || StringUtil.isBlank(smsValidateCode.getSmsCode())){
                return  new ResultTemplate("请先获取短信验证码!");
            }
            if (!smsCode.toLowerCase().equals(smsValidateCode.getSmsCode().toLowerCase())) {
                return new ResultTemplate("输入短信验证码错误！", null);
            }
            if(StringUtil.isBlank(demand.getMobile())){
                return new ResultTemplate("手机号不能为空！", null);
            }else {
                //用户未登录情况下发布需求，判断该用户是否是已注册用户，若是注册用户，将注册用户Id关联到需求表中
               List< RegisteredUserInfo > retList = registeredUserInfoService.getByMobile(demand.getMobile());
               if(retList.size()>0){
                   demand.setRegisteredUserInfoId(retList.get(0).getRecID());
               }
            }
        }
        //验证公共参数，预算及稿件费用
        if (demand.getDemandName() != null && demand.getDemandTypeIndex() != null &&
                demand.getBudgetMoney() != null) {
            if (demand.getDemandName().length() > 50 || demand.getDemandName().length() < 4) {
                return new ResultTemplate("需求名称字数在4-50个字", null);
            }
        } else {
            return new ResultTemplate("需求名称、需求类型、预算传参不能为空", null);
        }
        //需求类型前台传参数1/2/3/4
        if (demand.getDemandTypeIndex() == EnumDemandType.ip代理.getIndex() ||
                demand.getDemandTypeIndex() == EnumDemandType.定制内容.getIndex() ||
                demand.getDemandTypeIndex() == EnumDemandType.营销分发.getIndex() ||
                demand.getDemandTypeIndex() ==EnumDemandType.原创征稿.getIndex()) {
        } else {
            return new ResultTemplate("需求类型传参错误", null);
        }
        // 1、定制内容
        if (demand.getDemandTypeIndex() == EnumDemandType.定制内容.getIndex()) {
            if (StringUtil.isBlank(demand.getScenes())) {
                return new ResultTemplate("定制内容：使用场景不能为空", null);
            }
            if (StringUtil.isBlank(demand.getYrCategory())) {
                return new ResultTemplate("定制内容：内容领域不能为空", null);
            }
            if (StringUtil.isBlank(demand.getContentForms())) {
                return new ResultTemplate("定制内容：内容形式不能为空", null);
            }
            if (StringUtil.isBlank(demand.getExpectedTime())) {
                return new ResultTemplate("定制内容：期望完成时间不能为空！", null);
            }
            if (demand.getTradeId() == null) {
                return new ResultTemplate("定制内容：所属行业不能为空！", null);
            }
            if (dictInfoServicesI.getDictInfoByTypeAndID(15,demand.getTradeId()) == null){
                return new ResultTemplate("所属行业信息不存在",null);
            }
        }
        // IP代理权: ContentForm ip形式, YrCategory ip类型
        if (demand.getDemandTypeIndex() == EnumDemandType.ip代理.getIndex()) {
            if (StringUtil.isBlank(demand.getYrCategory())) {
                return new ResultTemplate("IP行业不能为空", null);
            }
            if (StringUtil.isBlank(demand.getContentForms())) {
                return new ResultTemplate("IP类型不能为空", null);
            }
        }
        //定制内容：不通过购物车
        if (demand.getDemandTypeIndex() == EnumDemandType.定制内容.getIndex() && StringUtil.isBlank(shopCartIds)) {
            if(demand.getExpectNum() == null || demand.getExpectNum()<1){
                return new ResultTemplate("定制内容：期望作者数量为空,或者小于1", null);
            }
            if(StringUtil.isBlank(demand.getExpectOffer())){
                return new ResultTemplate("定制内容：期望报价项不能为空！",null);
            }
        }
        //营销分发: 平台名称、推广时间 、账号分类、粉丝数、所属分类
        //通过购物车
        if (demand.getDemandTypeIndex() == EnumDemandType.营销分发.getIndex() && StringUtil.isNotBlank(shopCartIds)) {
            if (StringUtil.isBlank(demand.getSpreadTime())) {
                return new ResultTemplate("营销分发: 推广时间,不能为空", null);
            }
            if (demand.getTradeId() == null) {
                return new ResultTemplate("营销分发: 所属分类,不能为空", null);
            }
            if (dictInfoServicesI.getDictInfoByTypeAndID(15,demand.getTradeId()) == null){
                return new ResultTemplate("所属行业信息不存在",null);
            }
        }
        //营销分发:
        //不通过购物车
        if (demand.getDemandTypeIndex() == EnumDemandType.营销分发.getIndex() && StringUtil.isBlank(shopCartIds)) {
            if (StringUtil.isBlank(demand.getPlatformName())) {
                return new ResultTemplate("营销分发: 平台名称,不能为空", null);
            }
            if (StringUtil.isBlank(demand.getSpreadTime())) {
                return new ResultTemplate("营销分发: 推广时间,不能为空", null);
            }
            if (StringUtil.isBlank(demand.getFans())) {
                return new ResultTemplate("营销分发: 粉丝数,不能为空", null);
            }
            if (StringUtil.isBlank(demand.getYrCategory())) {
                return new ResultTemplate("营销分发: 账号分类,不能为空", null);
            }
            if (demand.getTradeId() == null) {
                return new ResultTemplate("营销分发: 所属分类,不能为空", null);
            }
            if (dictInfoServicesI.getDictInfoByTypeAndID(15,demand.getTradeId()) == null){
                return new ResultTemplate("所属行业信息不存在",null);
            }
            if(demand.getExpectNum() == null || demand.getExpectNum()<1){
                return new ResultTemplate("营销分发：期望账号数量为空,或者小于1", null);
            }
            if(StringUtil.isBlank(demand.getExpectOffer())){
                return new ResultTemplate("营销分发：期望报价项为空", null);
            }
        }
        //4、原创征稿
        //字数要求（仅针对图文文章）、征集数量、截止日期、征稿要求
        if(demand.getDemandTypeIndex()==EnumDemandType.原创征稿.getIndex()){
            if (StringUtil.isBlank(demand.getContentForms())) {
                return new ResultTemplate("原创征稿：内容形式不能为空", null);
            }
//            if(demand.getContentForms().equals("文章")){
//                if (StringUtil.isBlank(demand.getRequireWordNum())) {
//                    return new ResultTemplate("原创征稿：字数不能为空", null);
//                }
//            }
            if (demand.getExpectNum()==null || demand.getExpectNum()<1) {
                return new ResultTemplate("原创征稿：征集数量不能为空", null);
            }
            if (StringUtil.isBlank(demand.getExpectedTime())) {
                return new ResultTemplate("原创征稿：截止日期不能为空", null);
            }
            if (StringUtil.isBlank(demand.getRemark())) {
                return new ResultTemplate("原创征稿：征稿要求不能为空", null);
            }
        }
        demand.setDemandStatusIndex(EnumDemandStatus.待审核.getIndex());
        demand.setDemandSn(orderSnFactoryServicesI.createDemandOrderSn());
        demand.setSourceId(EnumOrderSource.前台创建.getIndex());
        demand.setCreateUser(EnumUserRole.买家.getName());
        UserInfo userInfo = getWebUser();
//            batchSave(demand, authorIds, accountIPIds, shopCartIds,userInfo);
        demandServicesI.batchSave(demand, authorIds, accountIPIds, shopCartIds, userInfo);
        return new ResultTemplate();

    }

    /**
     * 发布需求---创作者列表
     * @param shopCartIds 购物者Id
     */
    @RequestMapping(value = "list_yrAuthorByIds")
    @ResponseBody
    public ResultTemplate list_yrAuthorByIds(String shopCartIds,BaseModel baseModel) {
        if(getWebUser()==null){
            return new ResultTemplate("该用户没有登录",null);
        }
        List<YRAuthor> listYRAuthor =  yRAuthorServicesI.getyrAuthorByshoppingCartId(getWebUser().getRecID(),shopCartIds);
       Map<String,Integer> map = new HashMap<>();
        String yrAuthorIds = "";
        for( YRAuthor yrAuthor : listYRAuthor){
            yrAuthorIds += yrAuthor.getRecId()+",";
            map.put(yrAuthor.getRecId().toString(),yrAuthor.getShoppingCartId());
        }
        if(StringUtil.isBlank(yrAuthorIds)){
            yrAuthorIds = null;
        }else {
            yrAuthorIds = yrAuthorIds.substring(0,yrAuthorIds.length()-1);
        }
        PageInfo<JSONObject> pageInfo = yRAuthorServicesI.list_yrAuthorByIds(yrAuthorIds,baseModel);
        JSONArray jsonArray = new JSONArray();
        if(CollectionUtil.size(pageInfo.getList())>0){
            for(JSONObject jsonObject : pageInfo.getList()){
                jsonObject.put("shoppingCartId",map.get(jsonObject.getString("yrAuthorId")));
                jsonArray.add(jsonObject);
            }
        }
        return new ResultTemplate(pageInfo,jsonArray);
    }

    /**
     * 发布需求--营销分发列表
     */
    @RequestMapping(value = "list_platformIPAccount")
    @ResponseBody
    public  ResultTemplate list_platformIPAccount(String platformInfoIDS,BaseModel baseModel){
        if(StringUtil.isBlank(platformInfoIDS)){
            return new ResultTemplate("平台参数为空！" , null);
        }
        int[] ids = null;
        String[] idstr = platformInfoIDS.split(",");
        ids = new int[idstr.length];
        for(int i=0; i<idstr.length ;i++){
            ids[i] = Integer.parseInt(idstr[i]);
        }
        PageInfo<JSONObject> pageInfo = platformIPAccountServicesI.list_platformIPAccount(ids,  baseModel );
        return new ResultTemplate(pageInfo);
    }

    /**
     * 发布需求--营销分发列表
     * @param  shoppingCartIDS 购物车Ids
     *
     */
    @RequestMapping(value = "list_PlatformIPAccountByShopCartIds")
    @ResponseBody
    public ResultTemplate list_PlatformIPAccountByShopCartIds(String shoppingCartIDS, BaseModel baseModel) {
        if (StringUtil.isBlank(shoppingCartIDS)) {
            return new ResultTemplate("购物车ID不能为空", null);
        }
        if (getWebUser() == null) {
            return new ResultTemplate("该用户未登录，请先登录", null);
        }
        PageInfo<JSONObject> pageInfo = platformIPAccountServicesI.list_PlatformIPAccountByShopCartIds(getWebUser().getRecID(), shoppingCartIDS, baseModel);
        JSONArray resultArray = new JSONArray();
        if(CollectionUtil.size(pageInfo.getList())>0){
            for(JSONObject jsonObject : pageInfo.getList() ){
                String a =jsonObject.toString();
                JSONObject object = new JSONObject();
                object.put("accountId",jsonObject.getString("accountId"));
                object.put("name",jsonObject.getString("name"));
                object.put("fans",jsonObject.getString("fans"));
                object.put("icoUrl",jsonObject.getString("icoUrl"));
                object.put("headImageUrlLocal",jsonObject.getString("headImageUrlLocal"));
                object.put("shoppingCartId",jsonObject.getString("shoppingCartId"));
                jsonObject.getString("priceInfo");
                JSONArray innerJSONArray = jsonObject.getJSONArray("priceInfo");
                object.put("priceInfo",innerJSONArray);
                resultArray.add(object);
            }
        }
        return new ResultTemplate(pageInfo,resultArray);
    }
}

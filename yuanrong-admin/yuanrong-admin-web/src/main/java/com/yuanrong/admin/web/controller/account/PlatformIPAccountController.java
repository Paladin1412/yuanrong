package com.yuanrong.admin.web.controller.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.account.PlatformIPAccountPrice;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.seach.PlatformIpAccountSeach;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/5/19.
 */
@Controller
@RequestMapping("account")
public class PlatformIPAccountController extends BaseController{
    @RequestMapping(value = "platformIPAccount_list" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(PlatformIpAccountSeach platformIpAccountSeach , BaseModel baseModel, HttpSession session){
        platformIpAccountSeach.setWebUser(getWebUser(session));
        platformIpAccountSeach.setPlatformPrice(13);
        PageInfo<Map> result = platformIPAccountServicesI.list(platformIpAccountSeach , baseModel);
        JSONArray resultArray = new JSONArray();
        if(CollectionUtil.size(result.getList()) > 0){
            for(Map<String ,Object> ele : result.getList()){
                resultArray.add(PlatformIPAccount.mapPareJSONObject(ele));
            }
        }
        return new ResultTemplate(result , resultArray);
    }

    @RequestMapping("platformIPAccount_save")
    @ResponseBody
    public ResultTemplate save(PlatformIPAccount data,HttpSession session){
        if(data.getPlatformID() <1 ){
            return new ResultTemplate("平台为空" , null);

        }
        if(StringUtil.isBlank(data.getName())){
            return new ResultTemplate("账号名为空" , null);

        }
        if(StringUtil.isBlank(data.getPid())){
            return new ResultTemplate("平台唯一标识为空！" , null);

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

        //验证分类是否为空
        if(dictInfoServicesI.getById(data.getCategoryID()) == null){
            return new ResultTemplate("账号分类不存在" , null);

        }

        //注册用户
        data.setRegisteredUserInfoID(getWebUser(session).getRecID());

        //查找pid是否已经录入过
        PlatformIPAccount account = platformIPAccountServicesI.getByPidAndRegisterUserId(data.getPid() , getWebUser(session).getRecID());
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
                accountPrice.setPlatformPriceNameID(jsonObject.getInteger("priceId"));
                accountPrice.setiPAcctountID(data.getId());
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
        if(data.getPlatformIPAccountStatus() != null && data.getPlatformIPAccountStatus().getIndex() == EnumPlatformIPAccountStatus.上架.getIndex()){
            //验证是否录入过价格
            if(CollectionUtil.size(ipAccountPrices) <= 0){
                return new ResultTemplate("上架的账号，至少需要一个报价！" , null);

            }
            //验证当前用户是否被审核
            RegisteredUserInfo checkUser = registeredUserInfoService.getById(getWebUser(session).getRecID());
            if(checkUser == null){
                return new ResultTemplate("用户信息为空！" , null);

            }
            //前台只有上架跟下载操作，没有待实名状态，所以注释掉上述代码
            /*if(checkUser.getEnumStatusOfUser().getIndex() != EnumStatusOfUser.审核成功.getIndex()){
                data.setPlatformIPAccountStatus(EnumPlatformIPAccountStatus.待实名);
            }*/
        }else{
            data.setPlatformIPAccountStatus(EnumPlatformIPAccountStatus.下架);
        }
        data.setChannel(EnumChannel.前台创建);
        //保存
        platformIPAccountServicesI.savePlatformIpAccount(data ,  ipAccountPrices);
        return new ResultTemplate("" , null);
    }

    @RequestMapping("getAccountStatus")
    @ResponseBody
    public ResultTemplate getAccountStatus(){
        return new ResultTemplate("" , EnumPlatformIPAccountStatus.getMapInfo());
    }

    @RequestMapping("getPlatFormIPAccountInfoById")
    @ResponseBody
    public ResultTemplate getPlatFormIPAccountInfoById(int id,HttpSession session){
        if(id <=0){
            return new ResultTemplate("参数错误！" , null);

        }
        if(platformIPAccountServicesI.getById(id).getRegisteredUserInfoID() != getWebUser(session).getRecID()){
            return new ResultTemplate("账号不存在" , null);

        }
        Map<String , Object> result = platformIPAccountServicesI.getPlatFormIPAccountInfoById(id);
        if(result == null){
            return new ResultTemplate("数据不存在！" ,null);

        }
        return new ResultTemplate("" , PlatformIPAccount.mapPareJSONObject(result));
    }

    @RequestMapping( value = "platformIPAccount_update" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate update(PlatformIPAccount data,HttpSession session){
        if(data.getId() <=0){
            return new ResultTemplate("ID参数为空" , null);

        }
        PlatformIPAccount thePlatformIPAccount = platformIPAccountServicesI.getById(data.getId());
        if(thePlatformIPAccount != null && thePlatformIPAccount.getRegisteredUserInfoID() != getWebUser(session).getRecID()){
            return new ResultTemplate("账号不存在" , null);

        }
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

        thePlatformIPAccount.setPlatformIPAccountStatus(EnumPlatformIPAccountStatus.上架);

//报价
        /*if(data.getPlatformIPAccountStatus().getIndex() == EnumPlatformIPAccountStatus.上架.getIndex() && StringUtil.isBlank(data.getPlatPrices())){
            return new ResultTemplate("上架状态的账号，至少需要一个报价" ,null);
            
        }*/

        thePlatformIPAccount.setPlatPrices(data.getPlatPrices());
        thePlatformIPAccount.setInvalidTime(data.getInvalidTime());
        platformIPAccountServicesI.updatePlatFormIPAccountAndPrice(thePlatformIPAccount);
        return new ResultTemplate("" , null);
    }

    @RequestMapping("batchUpdateInvalidTime")
    @ResponseBody
    public ResultTemplate batchUpdateInvalidTime(PlatformIpAccountSeach data,HttpSession session){
        if(StringUtil.isBlank(data.getInvalidTime()) || data.getIds() == null || data.getIds().length <=0){
            return new ResultTemplate("参数错误" , null);

        }
        data.setWebUser(getWebUser(session));
        platformIPAccountServicesI.batchUpdateInvalidTime(data);
        return new ResultTemplate("" , null);
    }
    /**
     * 批量上下架
     */
    @RequestMapping("batchUpperLowerShelves" )
    @ResponseBody
    public ResultTemplate upperLowerShelves(PlatformIpAccountSeach data,HttpSession session){
        if(StringUtil.isBlank(data.getPlatformAccountStatus()) || data.getIds() == null || data.getIds().length <=0){
            return new ResultTemplate("参数错误" , null);

        }
        EnumPlatformIPAccountStatus accountStatus = (EnumPlatformIPAccountStatus) EnumUtil.valueOf(EnumPlatformIPAccountStatus.class , data.getPlatformAccountStatus());
        if(accountStatus == null){
            return new ResultTemplate("状态错误" , null);

        }

        if(accountStatus.getIndex() == EnumPlatformIPAccountStatus.下架.getIndex()){
            //账号不合作
            data.setLowerCauseID(173);
        }

        data.setWebUser(getWebUser(session));

        platformIPAccountServicesI.batchUpperLowerShelves(data);
        return new ResultTemplate("" ,null);
    }


    /**
     * 批量修改价格
     */
    @RequestMapping("batchUpdatePrice" )
    @ResponseBody
    public ResultTemplate batchUpdatePrice(PlatformIpAccountSeach data,HttpSession session){
        if(data.getIds() == null || data.getIds().length <=0){
            return new ResultTemplate("参数错误" , null);

        }
        if(data.getRangePer() == null || data.getRangePer() == 0 || data.getRangePer() < -100 || data.getRangePer() > 100 ){
            return new ResultTemplate("百分比错误" , null);

        }
        data.setWebUser(getWebUser(session));
        platformIPAccountServicesI.batchUpdatePrice(data);
        return new ResultTemplate("" ,null);
    }

    @RequestMapping("platformIPAccount_exist")
    @ResponseBody
    public ResultTemplate exist(PlatformIPAccount data,HttpSession session){
        PlatformIPAccount platformIPAccount = platformIPAccountServicesI.getByPidAndRegisterUserId(data.getPid() , getWebUser(session).getRecID());
        if(platformIPAccount != null){
            return new ResultTemplate("该账号已经被录入过了" , null);

        }
        return new ResultTemplate("",null);
    }
}

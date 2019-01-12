package com.yuanrong.admin.web.controller.author;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.base.ContentForm;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.seach.YRProductionChangePrice;
import com.yuanrong.admin.seach.YRProductionListParam;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.common.oss.CloudStorageConfig;
import com.yuanrong.common.oss.CloudStorageFactory;
import com.yuanrong.common.oss.CloudStorageServiceAbstract;
import com.yuanrong.common.util.FilterHtmlUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.common.util.SystemParam;
import com.yuanrong.common.util.Word2String;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 有关作品的控制器层
 * @Author: ShiLinghuai
 * @CreateDate: 2018/5/21 9:43
 * @UpdateUser: ShiLinghuai
 * @UpdateDate: 2018/5/21 9:43
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
@RequestMapping("author")
public class YRProductionController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 按条件查询作品
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/21 9:56
     */
    @RequestMapping(value = "yRProduction_listFront", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate listByCondition(YRProductionListParam yrProductionListParam, HttpSession session) {
        //验证用户是否登录
        RegisteredUserInfo registeredUserInfo = registeredUserInfoService.getById(getWebUser(session).getRecID());
        if (registeredUserInfo == null) {
            return new ResultTemplate("缓存信息未存在数据库",null);

        }
        //进行业务操作--设置当前用户
        yrProductionListParam.setRegisteredUserInfo(registeredUserInfo);
        JSONArray ProductionArray = new JSONArray();
        PageInfo<YRProduction> pageInfo = this.yRProductionServicesI.listByCondition(yrProductionListParam);
        if(CollectionUtil.size(pageInfo.getList())>0) {

            for (YRProduction production : pageInfo.getList()
                    ) {
                JSONObject ele = new JSONObject();
                ele.put("recID", production.getRecId());
                ele.put("title", production.getTitle());
                ele.put("isAnonymous", production.getYrAuthor()!=null?production.getYrAuthor().getIsAnonymous():null);
                ele.put("contentFormName", production.getContentForm()!=null?production.getContentForm().getContentFormName():null);
                ele.put("createdPrice", production.getProductQuotedPrice());
                ele.put("authorNickname", production.getYrAuthor()!=null?production.getYrAuthor().getAuthorNickname():null);
                ele.put("yrAuthorRecId", production.getYrAuthor()!=null?production.getYrAuthor().getRecId():null);
                ele.put("publishStatusIndex", production.getPublishStatus()!=null?production.getPublishStatus().getName():null);
                ele.put("yrProductionStatusIndex", production.getYrProductionStatus()!=null?production.getYrProductionStatus().getIndex():null);
                ele.put("productionAuditFailReason", production.getProductionAuditFailReasonId());
                ele.put("isRepresentative", production.getIsRepresentative());
                ele.put("proUnderReason",production.getProUnderReasonId());
                if(production.getContentForm().getContentFormName().equals("文章")){
                    ele.put("wordNum",production.getWordNum());
                    ele.put("imgNum",production.getImgNum());
                }

                ProductionArray.add(ele);
            }
        }
        return new ResultTemplate(pageInfo,ProductionArray);



        //return new ResultTemplate(yRAuthorPageInfo,resultList);
    }

    /**
     * 申请上架/支持批量
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/21 9:56
     */
    @RequestMapping(value = "yRProduction_applyPutaway", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate applyPutaway(String recID,HttpSession session) {
        RegisteredUserInfo registeredUserInfo = registeredUserInfoService.getById(getWebUser(session).getRecID());
        if (recID == null || recID == "") {
            return new ResultTemplate("recID参数未传");

        }
        List<Integer> list = null;
        try {
            list = new ArrayList<Integer>();
            String[] sIDs = recID.split(",");
            for (int i = 0; i < sIDs.length; i++) {
                list.add(Integer.parseInt(sIDs[i]));
            }
        } catch (Exception e) {
            return new ResultTemplate("id参数格式转换错误");
        }
        //检查作品id是否都有效
        //检查作品id是否对应上了作者
        //检查作者的状态字段是否异常

        Integer isLegaled = this.yRProductionServicesI.updateYRProductionStatusIndexByProIDAndUserIDAndUpDown(list, registeredUserInfo.getRecID(), EnumMethodParamUpDown.上架.getIndex());
        if(isLegaled!=null){
            return new ResultTemplate("该用户没有创作者",null);

        }else {
            return new ResultTemplate("", null);
        }


    }

    /**
     * 申请下架/支持批量
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/21 9:56
     */
    @RequestMapping(value = "yRProduction_applySoldOut", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate applySoldOut(String recID,HttpSession session) {
        RegisteredUserInfo registeredUserInfo = registeredUserInfoService.getById(getWebUser(session).getRecID());
        if (recID == null || recID == "") {
            return new ResultTemplate("recID参数未传");

        }
        List<Integer> list = null;
        try {
            list = new ArrayList<Integer>();
            String[] sIDs = recID.split(",");
            for (int i = 0; i < sIDs.length; i++) {
                list.add(Integer.parseInt(sIDs[i]));
            }
        } catch (Exception ne) {
            return new ResultTemplate("格式转换错误");
        }
        //检查作品id是否都有效
        //检查作品id是否对应上了作者
        //检查作者的状态字段是否异常
        Integer isLegaled =this.yRProductionServicesI.updateYRProductionStatusIndexByProIDAndUserIDAndUpDown(list, registeredUserInfo.getRecID(), EnumMethodParamUpDown.下架.getIndex());
        if(isLegaled!=null){
            return new ResultTemplate("该用户没有创作者",null);

        }else {
            return new ResultTemplate("", null);
        }
    }
    /**
     * 改价格/支持批量
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/21 9:56
     */
    @RequestMapping(value = "yRProduction_changePrice", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate changePrice(YRProductionChangePrice yrProductionChangePrice) {
       if(yrProductionChangePrice.getRecID()==null||yrProductionChangePrice.getPrice()==null){
           return new ResultTemplate("参数不全");
       }
       if(yrProductionChangePrice.getPrice().compareTo(BigDecimal.ZERO) <0){
           return new ResultTemplate("价格不能小于0");
       }
        List<Integer> list = null;
        try {
            list = new ArrayList<Integer>();
            String[] sIDs = yrProductionChangePrice.getRecID().split(",");
            for (int i = 0; i < sIDs.length; i++) {
                list.add(Integer.parseInt(sIDs[i]));
            }
            yrProductionChangePrice.setIds(list);
        } catch (Exception ne) {
            return new ResultTemplate("格式转换错误");

        }
        //检查作品id是否都有效
        //检查作品id是否对应上了作者
        //检查作者的状态字段是否异常
     //
        // this.yRProductionServicesI.updateYRProductionStatusIndexByProIDAndUserIDAndUpDown(list, registeredUserInfo.getRecID(), EnumMethodParamUpDown.下架.getIndex());
        String percent = configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_BUYER_PERCENT) ;
        BigDecimal fee = StringUtil.isBlank(percent) ? BigDecimal.ZERO : new BigDecimal(percent).multiply(new BigDecimal("0.01"));
        BigDecimal sellPrice = yrProductionChangePrice.getPrice().add( yrProductionChangePrice.getPrice().multiply(fee));
        yRProductionServicesI.updatePriceByID(yrProductionChangePrice.getIds(),yrProductionChangePrice.getPrice(),sellPrice,getWebUser().getRecID());
        return new ResultTemplate("", null);
    }

    @RequestMapping(value = "yRProduction_save", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate save(YRProduction data,HttpSession session){
        if(StringUtil.isBlank(data.getTitle()) || StringUtil.isBlank(data.getLocalcontent())){
            return new ResultTemplate("标题或正文为空！",null);

        }
        //验证创作者
        //已经录入了创作者
        if(CollectionUtil.size(yRAuthorServicesI.getByRegisterUserId(getWebUser(session).getRecID())) > 0){
            YRAuthor theYRAuthor = (data.getYrAuthor() == null || data.getYrAuthor().getRecId() <=0) ? null : yRAuthorServicesI.getById(data.getYrAuthor().getRecId());
            if(theYRAuthor == null){
                return new ResultTemplate("创作者不存在！",null);

            }
            if(theYRAuthor.getRegisteredUserInfoID().compareTo(getWebUser(session).getRecID()) !=0 ){
                return new ResultTemplate("创作者不存在！",null);

            }
        }

        data.setRegisteredUserInfoId(getWebUser(session).getRecID());
        data.setChannel(EnumChannel.前台创建);

        ContentForm theContentForm = (data.getContentForm() == null || data.getContentForm().getId() <= 0) ? null : contentFormServicesI.getById(data.getContentForm().getId());
        if(theContentForm == null){
            return new ResultTemplate("内容形式不存在！",null);

        }

        if(data.getPublishStatus() == null){
            return new ResultTemplate("发布情况为空！",null);

        }

        if(data.getProductQuotedPrice() == null || data.getProductQuotedPrice().compareTo(BigDecimal.ZERO) <= 0 ){
            return new ResultTemplate("价格错误！",null);

        }

        //是否代表作 ，默认为否
        if(data.getIsRepresentativeStatus() == null){
            data.setIsRepresentativeStatus(EnumYesOrNo.FORBIDDEN);
        }
        data.setYrProductionStatus(EnumYRProductionStatus.待审核);
        data.setOperator(getWebUser(session).getMobile());
        data.setImgNum(data.getLocalcontent().split("<img").length-1);
        data.setWordNum(FilterHtmlUtil.Html2Text(data.getLocalcontent())==null?0:FilterHtmlUtil.Html2Text(data.getLocalcontent()).length());
        data.setProductUrl(data.getProductUrl());
        data.setAccessNum(RandomUtil.randomInt(50,100));
        JSONObject result = new JSONObject();
        result.put("productId",yRProductionServicesI.saveGetKey(data));

        return new ResultTemplate("" , result);
    }

    @RequestMapping(value = "yRProduction_update", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate update(YRProduction data){
        YRProduction yrProduction = (data.getRecId() == null || data.getRecId() <=0) ? null : yRProductionServicesI.getById(data.getRecId());
        if(yrProduction == null ){
            return new ResultTemplate("该作品不存在！" ,null);

        }
        if(yrProduction.getYrAuthor().getRegisteredUserInfo().getRecID().compareTo(getWebUser(session).getRecID()) !=0){
            return new ResultTemplate("该作品不存在！",null);

        }
        if(StringUtil.isBlank(data.getTitle()) || StringUtil.isBlank(data.getLocalcontent())){
            return new ResultTemplate("标题或正文为空！",null);

        }else{
            yrProduction.setLocalcontent(data.getLocalcontent());
            yrProduction.setTitle(data.getTitle());
        }

        if(StringUtil.isBlank(data.getCoverLocalUrl())){
            return new ResultTemplate("封面为空！",null);

        }else{
            yrProduction.setCoverLocalUrl(data.getCoverLocalUrl());
        }

        ContentForm theContentForm = (data.getContentForm() == null || data.getContentForm().getId() <= 0) ? null : contentFormServicesI.getById(data.getContentForm().getId());
        if(theContentForm == null){
            return new ResultTemplate("内容形式不存在不存在！",null);

        }else{
            yrProduction.setContentForm(theContentForm);
        }

        if(data.getPublishStatus() == null){
            return new ResultTemplate("发布情况为空！",null);
        }else{
            //如果为导入的作品，用户不能调整为未发布
            if(data.getPublishStatus().getIndex() == EnumYesOrNo.FORBIDDEN.getIndex()
                    && yrProduction.getIsImportEnum()!= null && yrProduction.getIsImportEnum().getIndex() == EnumYesOrNo.NORMAL.getIndex()){
                return new ResultTemplate("导入的作品不能调整为未发布！",null);
            }
            yrProduction.setPublishStatus(data.getPublishStatus());
        }

        if(data.getProductQuotedPrice() == null || data.getProductQuotedPrice().compareTo(BigDecimal.ZERO) < 0 ){
            return new ResultTemplate("价格错误！",null);

        }else{
            yrProduction.setProductQuotedPrice(data.getProductQuotedPrice());
        }

        //是否代表作 ，默认为否
        if(data.getIsRepresentativeStatus() == null){
            yrProduction.setIsRepresentativeStatus(EnumYesOrNo.FORBIDDEN);
        }else{
            yrProduction.setIsRepresentativeStatus(data.getIsRepresentativeStatus());
        }

        if(StringUtil.isNotBlank(data.getPublishTime())){
            yrProduction.setPublishTime(data.getPublishTime());
        }
        yrProduction.setPublishPlatform(data.getPublishPlatform());
        yrProduction.setWordNum(data.getWordNum());
        yrProduction.setYrProductionStatus(EnumYRProductionStatus.待审核);
        data.setOperator(getWebUser(session).getMobile());
        yrProduction.setImgNum(data.getLocalcontent().split("<img").length-1);
        if(!data.getTitle().equals(yrProduction.getTitle()) || !data.getLocalcontent().equals(yrProduction.getLocalcontent())){
            yrProduction.setEnumCrawlerStatus(EnumYesOrNo.FORBIDDEN);
            yrProduction.setEnumOnlyIndex(EnumYesOrNo.FORBIDDEN);
        }
        yRProductionServicesI.update(yrProduction);
        return new ResultTemplate("" , null);
    }

    @RequestMapping("yRProduction_getById")
    @ResponseBody
    public ResultTemplate getById(YRProduction data,HttpSession session){
        if(data.getRecId() == null || data.getRecId() <= 0 ){
            return new ResultTemplate("参数错误！",null);

        }
        YRProduction production = yRProductionServicesI.getById(data.getRecId());
        if(production == null){
            return new ResultTemplate("该作品不存在！",null);

        }
        if(production.getYrAuthor().getRegisteredUserInfo().getRecID().compareTo(getWebUser(session).getRecID()) != 0){
            return new ResultTemplate("该作品不存在！",null);

        }
        return new ResultTemplate("" , YRProduction.getJSONObjectByYRProduction(production));
    }
    /**
     * 发布状态
     */
    @RequestMapping("exs_getPublishStatus")
    @ResponseBody
    public ResultTemplate getPublishStatus(){
        return new ResultTemplate("" , EnumPublishStatus.getMapInfo());
    }
    /**
     * 作品状态
     */
    @RequestMapping("getYRProductionStatus")
    @ResponseBody
    public ResultTemplate getYRProductionStatus(){
        return new ResultTemplate("" , EnumYRProductionStatus.getMapInfo());
    }

    @RequestMapping("getAccountInfo")
    @ResponseBody
    public ResultTemplate getAccountInfoByPlatformAndAccountID(String accountID , Integer platformId){
        if(StringUtil.isBlank(accountID) || platformId == null){
            return new ResultTemplate("参数错误！",null);

        }

        PlatformIPAccount data = platformIPAccountServicesI.getAccountInfoByPlatformAndAccountID(accountID , platformId);
        if(data == null){
            return new ResultTemplate("未获取到信息",null);

        }
        JSONObject result = new JSONObject();
        result.put("accountId" , data.getAccountID());
        result.put("name" , data.getName());
        result.put("headImageUrlLocal" , data.getHeadImageUrlLocal());
        result.put("pid" , data.getPid());
        result.put("areaName" , data.getAreaName());
        result.put("qRcodeImageUrlLocal" , data.getqRcodeImageUrlLocal());
        result.put("fans" , data.getFans());
        result.put("introduction" , data.getIntroduction());
        return new ResultTemplate("" , result);
    }
    /**
     * 申请下架/支持批量
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/21 9:56
     */
    @RequestMapping(value = "yRProduction_setIsRepresentative", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate setIsRepresentative(String recID,Integer setIsRepresentative ,HttpSession session) {
        RegisteredUserInfo registeredUserInfo = registeredUserInfoService.getById(getWebUser(session).getRecID());
        if (recID == null || recID == "") {
            return new ResultTemplate("recID参数未传",null);

        }
        List<Integer> list = null;
        if(setIsRepresentative == null){
            return new ResultTemplate("设置代表作参数未传",null);

        }
        try {
            list = new ArrayList<Integer>();
            String[] sIDs = recID.split(",");
            for (int i = 0; i < sIDs.length; i++) {
                list.add(Integer.parseInt(sIDs[i]));
            }
        } catch (Exception ne) {
            return new ResultTemplate("格式转换错误");
        }
        //检查作品id是否都有效
        //检查作品id是否对应上了作者
        //检查作者的状态字段是否异常
        Integer isLegaled =this.yRProductionServicesI.updateYRProductionStatusIndexByProIDAndUserIDAndUpDown(list, registeredUserInfo.getRecID(), setIsRepresentative);
        if(isLegaled!=null){
            return new ResultTemplate("该用户没有创作者",null);

        }else {
            return new ResultTemplate("", null);
        }
    }

    /**
     *@author songwq
     *@param
     *@date 2018/10/11
     *@description word导入
     */
    @RequestMapping("batchSaveWord")
    @ResponseBody
    public ResultTemplate batchSaveWord(MultipartFile  file){
        logger.info("进入了word导入controller------------------------------");
        if(file==null){
            return new ResultTemplate("文件信息为空！" , null);
        }
        CloudStorageConfig cloudStorageConfig = uploadServicesI.getCloudStorageConfig();
        JSONObject result = null;
        try {
            result = Word2String.packageResult(cloudStorageConfig,file);
        } catch (IOException e) {
            e.printStackTrace();
            return  new ResultTemplate("IOException文件处理失败");
        } catch (TransformerException e) {
            e.printStackTrace();
            return  new ResultTemplate("TransformerException文件处理失败");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return  new ResultTemplate("ParserConfigurationException文件处理失败");
        }
        return  new ResultTemplate(result);
    }
}

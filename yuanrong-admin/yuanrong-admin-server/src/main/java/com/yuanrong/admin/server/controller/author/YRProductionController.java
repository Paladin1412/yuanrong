package com.yuanrong.admin.server.controller.author;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.base.ContentForm;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.seach.YRProductionListParam;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.oss.CloudStorageConfig;
import com.yuanrong.common.oss.CloudStorageFactory;
import com.yuanrong.common.oss.CloudStorageServiceAbstract;
import com.yuanrong.common.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
//import com.yuanrong.admin.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 圆融作品的controller
 * Created MDA
 */
@Controller
@RequestMapping("author")
public class YRProductionController extends BaseController {

    private static final Logger logger = Logger.getLogger(YRProductionController.class);

    @RequestMapping( value = "yRProduction_list" ,method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(YRProductionListParam data , BaseModel baseModel){
        PageInfo<YRProduction> yRProductionPageInfo = yRProductionServicesI.list(data , baseModel);
        if(CollectionUtil.size(yRProductionPageInfo.getList()) <= 0){
            return new ResultTemplate();
        }

        JSONArray result = new JSONArray();
        for(YRProduction production : yRProductionPageInfo.getList()){
            JSONObject ele = new JSONObject();
            //id
            ele.put("productionId" , production.getRecId());
            //作者
            ele.put("authorNikeName" , production.getYrAuthor() == null ? "" : production.getYrAuthor().getAuthorNickname());
            //文章标题
            ele.put("title" , production.getTitle());
            //内容长度
            ele.put("contentSize" , production.getWordNum());
            //状态
            ele.put("yrProductionStatus" ,production.getYrProductionStatus() == null ? "" : production.getYrProductionStatus().getName());
            //报价（元）
            ele.put("price" , production.getProductQuotedPrice());
            //发布状态
            ele.put("publishStatus" ,production.getPublishStatus() == null ? "" : production.getPublishStatus().getName());
            //内容形式
            ele.put("contentForm" ,production.getContentForm() == null ? "" : production.getContentForm().getContentFormName());
            //发布日期
            ele.put("publishTime" , StringUtil.isBlank(production.getPublishTime()) ? "" : DateUtil.format(DateUtil.parseDate(production.getPublishTime()),"yyyy-MM-dd"));
            //注册用户简称
            ele.put("userNikeName" ,(production.getYrAuthor()== null || production.getYrAuthor().getRegisteredUserInfo() == null) ? "" : production.getYrAuthor().getRegisteredUserInfo().getNickName());
            //注册用户ID
            ele.put("user_id" ,(production.getYrAuthor()== null || production.getYrAuthor().getRegisteredUserInfo() == null) ? "" :  production.getYrAuthor().getRegisteredUserInfo().getRecID());
            //是否代表作
            ele.put("isRepresentative" , production.getIsRepresentative());
            ele.put("isRepresentativeStatus" , production.getIsRepresentativeStatus().getName());
            //入库时间
            ele.put("createdTime" , DateUtil.format(production.getCreatedTime(), "yyyy-MM-dd HH:mm:ss"));
            //作者ID
            ele.put("authorId" , production.getYrAuthor()== null?"":production.getYrAuthor().getRecId());
            //审核失败原因
            ele.put("productionAuditFailReasonName" , production.getProductionAuditFailReasonId());
            ele.put("imgNum" , production.getImgNum());
            ele.put("proUnderReason",production.getProUnderReasonId());
            ele.put("originalScore" , production.getOriginalScore());
            ele.put("channelName" , production.getChannel().getName());
            ele.put("reStatus" , production.getEnumReStatus());
            ele.put("reStatusIndex" ,production.getEnumReStatus() == null ? 1 :  production.getEnumReStatus().getIndex());
            result.add(ele);
        }
        return new ResultTemplate(yRProductionPageInfo , result);
    }

    @RequestMapping(value = "yRProduction_save", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate save(YRProduction data){
        if(StringUtil.isBlank(data.getTitle()) || StringUtil.isBlank(data.getLocalcontent())){
            return new ResultTemplate("标题或正文为空！",null);
            
        }
        RegisteredUserInfo registeredUserInfo = data.getRegisteredUserInfoId() == null ? null : registeredUserInfoService.getById(data.getRegisteredUserInfoId());
        if(registeredUserInfo == null){
            return new ResultTemplate("注册用户为空！",null);
            
        }

        //验证创作者
        List<YRAuthor> yrAuthor = data.getRegisteredUserInfoId() == null ? null : yRAuthorServicesI.getByRegisterUserId(data.getRegisteredUserInfoId());
        if(CollectionUtil.size(yrAuthor) > 0){
            YRAuthor theYRAuthor = (data.getYrAuthor() == null || data.getYrAuthor().getRecId() == null || data.getYrAuthor().getRecId().intValue() <=0)
                    ? null : yRAuthorServicesI.getById(data.getYrAuthor().getRecId());
            if(theYRAuthor == null){
                return new ResultTemplate("创作者不存在！",null);
                
            }
        }

        ContentForm theContentForm = (data.getContentForm() == null || data.getContentForm().getId() <= 0) ? null : contentFormServicesI.getById(data.getContentForm().getId());
        if(theContentForm == null){
            return new ResultTemplate("内容形式不存在不存在！",null);
            
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
        data.setChannel(EnumChannel.后台创建);
        data.setYrProductionStatus(EnumYRProductionStatus.待审核);
        data.setOperator(getUser().getRealName());
        data.setImgNum(data.getLocalcontent().split("<img").length-1);
        data.setAccessNum(RandomUtil.randomInt(50,100));
        yRProductionServicesI.save(data);
        return new ResultTemplate("" , null);
    }

    @RequestMapping(value = "yRProduction_update", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate update(YRProduction data){
        YRProduction yrProduction = (data.getRecId() == null || data.getRecId() <=0) ? null : yRProductionServicesI.getById(data.getRecId());
        if(yrProduction == null ){
            return new ResultTemplate("该作品不存在！" ,null);
            
        }
        if(StringUtil.isBlank(data.getTitle()) || StringUtil.isBlank(data.getLocalcontent())){
            return new ResultTemplate("标题或正文为空！",null);
            
        }else{
            yrProduction.setTitle(data.getTitle());
            yrProduction.setLocalcontent(data.getLocalcontent());
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
                return new ResultTemplate("导入的作品不能调整为未发布！");
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
        yrProduction.setYrProductionStatus(EnumYRProductionStatus.待审核);
        data.setOperator(getUser().getRealName());
        yrProduction.setWordNum(data.getWordNum());
        yrProduction.setImgNum(data.getLocalcontent().split("<img").length-1);
        if(data.getYrCategoryId() != 0 && dictInfoServicesI.getDictInfoByTypeAndID(EnumDictInfoType.圆融分类.getIndex() , data.getYrCategoryId()) != null ){
            yrProduction.setYrCategoryId(data.getYrCategoryId());
        }
        yrProduction.setTags(data.getTags());
        if(!data.getTitle().equals(yrProduction.getTitle()) || !data.getLocalcontent().equals(yrProduction.getLocalcontent())){
            yrProduction.setEnumCrawlerStatus(EnumYesOrNo.FORBIDDEN);
            yrProduction.setEnumOnlyIndex(EnumYesOrNo.FORBIDDEN);
        }
        yRProductionServicesI.update(yrProduction);
        return new ResultTemplate("" , null);
    }

    @RequestMapping("yRProduction_getById")
    @ResponseBody
    public ResultTemplate getById(YRProduction data){
        if(data.getRecId() == null || data.getRecId() <= 0 ){
            return new ResultTemplate("参数错误！",null);
            
        }
        YRProduction production = yRProductionServicesI.getById(data.getRecId());
        if(production == null){
            return new ResultTemplate("该作品不存在！",null);
            
        }
        return new ResultTemplate("" , YRProduction.getJSONObjectByYRProduction(production));
    }


    /**
     * 验证作品报价
     * 注册手机号（必填）   userID （必填）  创作者名称（必填）   文章名称（必填）   作品报价
     *  注册手机号与userID二选一必填
     * @param file
     */
    @RequestMapping( value ="exs_vilidateProductPrice")
    @ResponseBody
    public ResultTemplate batchProductQuotedPrice(MultipartFile file){
        if(file == null){
            return new ResultTemplate("文件信息为空",null);
            
        }

        try {
            List<List<String>> result = ExcelUtil.read(file,0);
            if (CollectionUtil.size(result)<=1){
                return new ResultTemplate("数据不存在",null);
                
            }
            int si = result.get(0).size();
//            if(result.get(0).size()!=5){
//                return new ResultTemplate("表格数据不完整，无法导入",null);
//                
//            }
            result.remove(0);//删除表头
            JSONObject info = new JSONObject();
            //验证必填项
            JSONArray rightList = new JSONArray();
            JSONArray errorList = new JSONArray();
            for(int i = 0; i < result.size(); i++){
                JSONObject rightInfo = new JSONObject();
                StringBuilder str = new StringBuilder();
                //注册手机号（必填）   userID （必填）
                if(StringUtil.isBlank(result.get(i).get(0))&&StringUtil.isBlank(result.get(i).get(1))){
                    JSONObject erroInfo = new JSONObject();
                    erroInfo.put("row",i+2);
                    erroInfo.put("column","A");
                    erroInfo.put("erroMsg","注册手机号与userID二选一必填");
                    errorList.add(erroInfo);
                    JSONObject erroInfo1 = new JSONObject();
                    erroInfo1.put("row",i+2);
                    erroInfo1.put("column","B");
                    erroInfo1.put("erroMsg","注册手机号与userID二选一必填");
                    errorList.add(erroInfo1);
                    if(str == null || str.length() <=0){
                        str.append("注册手机号与userID二选一必填");
                    }else{
                        str.append(","+"注册手机号与userID二选一必填");
                    }
                }else{
                    try {
                        // 判断手机号是否存在

                        if(!StringUtil.isBlank(result.get(i).get(0))){
                            List<RegisteredUserInfo> listRUI = registeredUserInfoService.getByMobile(result.get(i).get(0));
                            if(listRUI.size()==0){//验证手机号是否注册
                                JSONObject erroInfo = new JSONObject();
                                erroInfo.put("row",i+2);
                                erroInfo.put("column","A");
                                erroInfo.put("erroMsg","手机号对应用户信息不存在");
                                errorList.add(erroInfo);
                                if(str == null || str.length()<=0){
                                    str.append("该手机号不存在");
                                }else{
                                    str.append(","+"手机号不存在");
                                }
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        JSONObject erroInfo = new JSONObject();
                        erroInfo.put("row",i+2);
                        erroInfo.put("column","A");
                        erroInfo.put("erroMsg","手机号-数据；类型有问题");
                        errorList.add(erroInfo);
                        if(str == null || str.length()<=0){
                            str.append("手机号-数据；类型有问题");
                        }else{
                            str.append(","+"手机号-数据；类型有问题");
                        }
                    }

                    try {
                        // 判断userID是否存在
                        if(!StringUtil.isBlank(result.get(i).get(1))){

                            RegisteredUserInfo registeredUserInfo = registeredUserInfoService.getById(Integer.parseInt(result.get(i).get(1)));
                            if( registeredUserInfo== null ){//验证用户是否存在
                                JSONObject erroInfo = new JSONObject();
                                erroInfo.put("row",i+2);
                                erroInfo.put("column","B");
                                erroInfo.put("erroMsg","userID对应用户信息不存在");
                                errorList.add(erroInfo);
                                if(str == null || str.length() <= 0){
                                    str.append("userID不存在");
                                }else {
                                    str.append("，" + "userID不存在");
                                }
                            }
                        }
                    }catch (Exception e){
//                        e.printStackTrace();
                        JSONObject erroInfo = new JSONObject();
                        erroInfo.put("row",i+2);
                        erroInfo.put("column","B");
                        erroInfo.put("erroMsg","userID数据类型有问题");
                        errorList.add(erroInfo);
                        if(str == null || str.length() <= 0){
                            str.append("userID数据类型有问题");
                        }else {
                            str.append("，" + "userID数据类型有问题");
                        }
                    }
                }
                //创作者名称判断
                if(StringUtil.isBlank(result.get(i).get(2))){
                    JSONObject erroInfo = new JSONObject();
                    erroInfo.put("row",i+2);
                    erroInfo.put("column","C");
                    erroInfo.put("erroMsg","创作者名称不能为空");
                    errorList.add(erroInfo);
                    if(str == null || str.length() <= 0){
                        str.append("创作者名称不能为空");
                    }else {
                        str.append("，" + "创作者名称不能为空");
                    }
                }else {
                    YRAuthor yRAuhor =   yRAuthorServicesI.getYRAuhorByName(result.get(i).get(2));
                    if(yRAuhor == null){
                        JSONObject erroInfo = new JSONObject();
                        erroInfo.put("row",i+2);
                        erroInfo.put("column","C");
                        erroInfo.put("erroMsg","创作者名称在数据库中不存在");
                        errorList.add(erroInfo);
                        if(str == null || str.length() <= 0){
                            str.append("创作者名称在数据库中不存在");
                        }else {
                            str.append("，" + "创作者名称在数据库中不存在");
                        }
                    }

                }
                //文章名称判断
                if(StringUtil.isBlank(result.get(i).get(3))){
                    JSONObject erroInfo = new JSONObject();
                    erroInfo.put("row",i+2);
                    erroInfo.put("column","D");
                    erroInfo.put("erroMsg","文章名称不能为空");
                    errorList.add(erroInfo);
                    if(str == null || str.length() <= 0){
                        str.append("文章名称不能为空");
                    }else {
                        str.append("，" + "文章名称不能为空");
                    }
                }else{
                   YRProduction yrProduction = yRProductionServicesI.getByTitle(result.get(i).get(3));
                   if(yrProduction==null){
                       JSONObject erroInfo = new JSONObject();
                       erroInfo.put("row",i+2);
                       erroInfo.put("column","D");
                       erroInfo.put("erroMsg","文章名称不存在");
                       errorList.add(erroInfo);
                       if(str == null || str.length() <= 0){
                           str.append("文章名称不存在");
                       }else {
                           str.append("，" + "文章名称不存在");
                       }
                   }
                }

                //作品报价
                if(!StringUtil.isBlank(result.get(i).get(4))){
                    try{
                        BigDecimal prices = new BigDecimal(result.get(i).get(4)).setScale(2,BigDecimal.ROUND_HALF_UP);
                        String productPrice = prices.toString();
                        if(productPrice.length()>12){
                            JSONObject erroInfo = new JSONObject();
                            erroInfo.put("row",i+2);
                            erroInfo.put("column","E");
                            erroInfo.put("erroMsg","作品报价范围0.00-999999999.00");
                            errorList.add(erroInfo);
                            if(str == null || str.length() <= 0){
                                str.append("作品报价范围0.00-999999999.00");
                            }else {
                                str.append("，" + "作品报价范围0.00-999999999.00");
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        JSONObject erroInfo = new JSONObject();
                        erroInfo.put("row",i+2);
                        erroInfo.put("column","E");
                        erroInfo.put("erroMsg","作品报价必须为数字");
                        errorList.add(erroInfo);
                        if(str == null || str.length() <= 0){
                            str.append("作品报价必须为数字0");
                        }else {
                            str.append("，" + "作品报价必须为数字");
                        }
                    }
                }

                //判断是否格式有错，正确则添加正确信息
                if(str == null || str.length()<=0){
                    rightInfo.put("mobile",result.get(i).get(0));
                    rightInfo.put("userID",result.get(i).get(1));
                    rightInfo.put("authorName",result.get(i).get(2));
                    rightInfo.put("title",result.get(i).get(3));
                    rightInfo.put("price",result.get(i).get(4));
                    rightList.add(rightInfo);
                }


            }
            info.put("rightList",rightList);
            info.put("errorList",errorList);
            if(errorList==null || errorList.size()<=0){//将正确数据信息添加到Session中
                JSONArray rightListProductPrice = rightList;
                getSession().setAttribute("rightListProductPrice",rightList);

            }

            return new ResultTemplate("",info);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultTemplate("文件读取失败，请检查内容后，再提交",null);
            
        }
    }

    //exs_batchProductQuotedPrice
    @RequestMapping(value = "exs_saveImportProductQuotedPrice")
    @ResponseBody
    public ResultTemplate saveImportProductQuotedPrice(){
       JSONArray result = (JSONArray) getSession().getAttribute("rightListProductPrice");
       if(result == null){
           return new ResultTemplate("数据有问题",null);
           
       }
//       result.g
       if(result.size()>0){
           for(int i=0; i<result.size();i++){
                JSONObject jb = result.getJSONObject(i);
//                boolean b = result.contains("data");
//                String artname = jb.getString("title");
//                BigDecimal d =  jb.getBigDecimal("price");
//                logger.info(d);
                Map<String,Object> map = new HashMap<>();
                map.put("title",jb.getString("title"));
                map.put("price",jb.getBigDecimal("price"));
                yRProductionServicesI.updateProductQuotedPriceByTitle(map);
           }
       }

       getSession().removeAttribute("rightListProductPrice");
       return new ResultTemplate("",result);
    }

    /**
     * 作品状态
     */
    @RequestMapping("exs_getYrProductionStatus")
    @ResponseBody
    public ResultTemplate getYrProductionStatus(){
        return new ResultTemplate("" , EnumYRProductionStatus.getMapInfo());
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
     * 上下架
     */
    @RequestMapping("yRProduction_applyUpperAndLower")
    @ResponseBody
    public ResultTemplate applyUpperAndLower(Integer[] ids , Integer yrProductionStatusIndex , String verifyFailReason){

        EnumYRProductionStatus yrProductionStatus =yrProductionStatusIndex == null ? null :  (EnumYRProductionStatus) EnumUtil.valueOf(EnumYRProductionStatus.class , yrProductionStatusIndex);
        if(yrProductionStatus == null){
            return new ResultTemplate("作品状态为空" ,null);
            
        }
        if(CollectionUtil.size(ids) <=   0 ){
            return new ResultTemplate("作品ID为空" ,null);
            
        }
        if(yrProductionStatus.getIndex() == EnumYRProductionStatus.待审核.getIndex()
                || yrProductionStatus.getIndex() == EnumYRProductionStatus.下架.getIndex()){
            yRProductionServicesI.updateYRProductionStatus(ids , yrProductionStatus ,verifyFailReason);
            return new ResultTemplate("" ,null);
        }else{
            return new ResultTemplate("作品状态错误" ,null);
            
        }

    }

    /**
     * 审核
     */
    @RequestMapping("yRProduction_verify")
    @ResponseBody
    public ResultTemplate verify(Integer[] ids , Integer yrProductionStatusIndex , String verifyFailReason){
        EnumYRProductionStatus yrProductionStatus =yrProductionStatusIndex == null ? null :  (EnumYRProductionStatus) EnumUtil.valueOf(EnumYRProductionStatus.class , yrProductionStatusIndex);
        if(yrProductionStatus == null){
            return new ResultTemplate("作品状态为空" ,null);
            
        }
        if(CollectionUtil.size(ids) <= 0 ){
            return new ResultTemplate("作品ID为空" ,null);
            
        }
        if(yrProductionStatus.getIndex() == EnumYRProductionStatus.审核不通过.getIndex()
                || yrProductionStatus.getIndex() == EnumYRProductionStatus.上架.getIndex()){

        }else{
            return new ResultTemplate("参数错误" ,null);
            
        }
        if(yrProductionStatus.getIndex() == EnumYRProductionStatus.审核不通过.getIndex()){
            if(StringUtil.isBlank(verifyFailReason) ){
                return new ResultTemplate("审核失败理由不能为空" ,null);
                
            }
        }else{
            verifyFailReason = null;
        }

        yRProductionServicesI.verify(ids , yrProductionStatus , verifyFailReason , getUser().getRealName());
        return new ResultTemplate("" ,null);
    }

    /**
     * 修改价格
     */
    @RequestMapping("yRProduction_changePrice")
    @ResponseBody
    public ResultTemplate changePrice(Integer[] ids , BigDecimal price){
        if(CollectionUtil.size(ids) <= 0 || price == null || price.compareTo(BigDecimal.ZERO) < 0){
            return new ResultTemplate("参数错误" , null);
            
        }
        String percent = configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_BUYER_PERCENT) ;
        BigDecimal fee = StringUtil.isBlank(percent) ? BigDecimal.ZERO : new BigDecimal(percent).multiply(new BigDecimal("0.01"));
        BigDecimal sellPrice = price.add( price.multiply(fee));
        yRProductionServicesI.changePrice(ids , price , sellPrice);
        for (int i = 0; i < ids.length; i++) {
            systemLogServicesI.log(YRProduction.class.getName() , ids[i] , "修改价格为：" + price , getUser().getRealName());
        }
        return new ResultTemplate("" ,null);
    }

    /**
     * 设为代表作
     *
     * @param
     * @return
     * @throws
     * @author songwq
     * @date
     */
    @RequestMapping("yRProduction_setIsRepresentative")
    @ResponseBody
    public ResultTemplate setIsRepresentative(String recID,String setIsRepresentative ) {
        if (recID == null || recID == "") {
            return new ResultTemplate("recID参数未传",null);
            
        }
        if(setIsRepresentative == null){
            return new ResultTemplate("设置代表作参数未传",null);
            
        }else if (!(setIsRepresentative.equals("0") || setIsRepresentative.equals("1"))){
            return new ResultTemplate("设置代表作参数有误，请确认值是否正确",null);
            
        }

        Integer isLegaled =this.yRProductionServicesI.updateYRProductionStatusIndexByProID(recID, Integer.valueOf(setIsRepresentative));
        if(isLegaled!=null){
            return new ResultTemplate("","操作成功");

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
        if(file==null){
            return new ResultTemplate("文件信息为空！" , null);
        }
        CloudStorageConfig cloudStorageConfig = uploadServicesI.getCloudStorageConfig();
        JSONObject result = null;
        try {
            result = Word2String.packageResult(cloudStorageConfig,file);
        } catch (IOException e) {
            e.printStackTrace();
            return  new ResultTemplate("文件处理失败IOException");
        } catch (TransformerException e) {
            e.printStackTrace();
            return  new ResultTemplate("文件处理失败TransformerException");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return  new ResultTemplate("文件处理失败ParserConfigurationException");
        }
        return  new ResultTemplate(result);
    }
}

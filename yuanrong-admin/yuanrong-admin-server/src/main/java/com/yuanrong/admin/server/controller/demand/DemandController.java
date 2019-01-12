package com.yuanrong.admin.server.controller.demand;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.EnumValues;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.bean.base.SMSValidateCode;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.demand.DemandFast;
import com.yuanrong.admin.bean.order.*;
import com.yuanrong.admin.result.DemandListResult;
import com.yuanrong.admin.result.DemandOrderListResult;
import com.yuanrong.admin.result.DemandSignListResult;
import com.yuanrong.admin.seach.DemandListParamSearch;
import com.yuanrong.admin.seach.OrderOfferParam;
import com.yuanrong.admin.seach.PlatformIpAccountSeach;
import com.yuanrong.admin.seach.YRProductionListParam;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
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
     * 后台—需求列表查询
     * @param data
     * @param baseModel
     */
    @RequestMapping( value = "demand_list" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(DemandListParamSearch data , BaseModel baseModel){
        PageInfo<DemandListResult> demandPageInfo = demandServicesI.demandList(data , baseModel);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(demandPageInfo.getList()) >0){
            for (DemandListResult demand : demandPageInfo.getList()){
                result.add(DemandListResult.packageDemand(demand));
            }
        }
        JSONObject resultNum = new JSONObject();
        List<Map<String, Object>> map = demandServicesI.statusNum();
        //待审核数
        resultNum.put("checkNum",map.get(0).get("checkNum"));
        //待推荐数
        resultNum.put("recommendNum",map.get(0).get("recommendNum"));
        return new ResultTemplate(demandPageInfo, result,resultNum);
    }

    /**
     * 查询状态个数（待审核、待推荐）—废除
     */
    @RequestMapping(value = "demand_num",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate statusNum(){
        JSONObject result = new JSONObject();
        List<Map<String, Object>> map = demandServicesI.statusNum();
        //待审核数
        result.put("checkNum",map.get(0).get("checkNum"));
        //待推荐数
        result.put("recommendNum",map.get(0).get("recommendNum"));
        return new ResultTemplate("",result);
    }

    /**
     * 获取普通需求状态
     */
    @RequestMapping("exs_getDemandStatus")
    @ResponseBody
    public ResultTemplate getDemandStatus(){
        return new ResultTemplate("", EnumDemandStatus.getMapInfo());
    }

    /**
     * 获取需求类型
     */
    @RequestMapping("exs_getDemandType")
    @ResponseBody
    public ResultTemplate getDemandType(){
        return new ResultTemplate("", EnumDemandType.getMapInfo());
    }

    /**
     *   后台 发布需求
     * @param demand
     */
    @RequestMapping("demand_save")
    @ResponseBody
    public ResultTemplate save(Demand demand,String smsCode,Integer type,String userName, Integer demandFastId){
        if(type !=null && type==1){
            if(StringUtil.isBlank(smsCode) ){
                return new ResultTemplate("修改手机号，短信验证码不能为空",null);
            }
            SMSValidateCode smsValidateCode =(SMSValidateCode) getSession().getAttribute("smsValidateCode");
            if(smsValidateCode==null || StringUtil.isBlank(smsValidateCode.getSmsCode())){
                return  new ResultTemplate("请先获取短信验证码!");
            }
            if(!smsCode.toLowerCase().equals(smsValidateCode.getSmsCode().toLowerCase())){
                return new ResultTemplate("输入短信验证码错误！",null);
            }

        }
        if(StringUtil.isBlank(userName)){
            return new ResultTemplate("用户名称不能为空");
        }

        //验证公共参数
        if(StringUtil.isBlank(demand.getDemandName())){
            return new ResultTemplate("需求名称不能为空");
        }
        if(StringUtil.isNotBlank(demand.getDemandName())){
            if(demand.getDemandName().length()>50 || demand.getDemandName().length()<4){
                return new ResultTemplate("需求名称字数在4-50个字",null);
            }
        }
        if(demand.getDemandTypeIndex() == null){
            return new ResultTemplate("需求类型不能为空");
        }
        if(demand.getDemandTypeIndex() != null && demand.getDemandTypeIndex().compareTo(EnumDemandType.ip代理.getIndex()) !=0
                && demand.getDemandTypeIndex().compareTo(EnumDemandType.营销分发.getIndex()) !=0
                && demand.getDemandTypeIndex().compareTo(EnumDemandType.定制内容.getIndex()) !=0
                && demand.getDemandTypeIndex().compareTo(EnumDemandType.原创征稿.getIndex()) !=0){
            return new ResultTemplate("需求类型有误");
        }
        if(demand.getBudgetMoney() == null){
            if(demand.getDemandTypeIndex().compareTo(EnumDemandType.原创征稿.getIndex()) ==0){
                return new ResultTemplate("请输入单篇稿件费用");
            }else {
                return new ResultTemplate("需求预算不能为空");
            }
        }
        if(demand.getBudgetMoney() != null &&
                (demand.getBudgetMoney().compareTo(BigDecimal.ZERO) <=0 || demand.getBudgetMoney().compareTo(new BigDecimal(999999999)) > 0)){
            return new ResultTemplate("稿件费用的范围是1-999999999之间");
        }
        if(demand.getDemandTypeIndex().compareTo(EnumDemandType.ip代理.getIndex()) !=0){
            if(demand.getTradeId()==null ){
                return new ResultTemplate("所属行业不能为空",null);
            }
            if (dictInfoServicesI.getDictInfoByTypeAndID(15,demand.getTradeId()) == null){
                return new ResultTemplate("所属行业信息不存在",null);
            }
        }
        if(demand.getRegisteredUserInfoId() == null || registeredUserInfoService.getById(demand.getRegisteredUserInfoId()) == null){
            return new ResultTemplate("注册用户Id有误");
        }
        String str = dealParam(demand);
        if(StringUtil.isNotBlank(str)){
            return new ResultTemplate(str, null);
        }

        demand.setDemandSn(orderSnFactoryServicesI.createDemandOrderSn());
        demand.setDemandStatusIndex(EnumDemandStatus.处理中.getIndex());
        demand.setSourceId(EnumOrderSource.后台创建.getIndex());
        demand.setCreateUser(userName);
        if (demandFastId != null && demandFastServicesI.getById(demandFastId) == null){
            return new ResultTemplate("快速需求信息不存在");
        }
        Integer demandId = demandServicesI.saveGetKey(demand,getUser().getRealName());
        if (demandFastId != null){
            DemandFast demandFast = new DemandFast();
            demandFast.setDemandId(demandId);
            demandFast.setDemandFastId(demandFastId);
            demandFast.setDemandFastStatusIndex(EnumDemandFastStatus.已接收.getIndex());
            demandFastServicesI.updateStatus(demandFast,getUser().getRealName());
        }
//        systemLogServicesI.log(Demand.class.getName(),demandId ,"需求修新增",getUser().getRealName());
        return new ResultTemplate("",null);

    }

    /**
     * 后台—需求详情(编辑页面)
     * @param demandId
     */
    @RequestMapping(value = "demand_detail",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate demandMarketDetail(Integer demandId) throws ParseException {
        if(demandId == null){
            return new ResultTemplate("需求Id有误",null);
        }
        Demand demand = demandServicesI.getById(demandId);
        if(demand == null){
            return new ResultTemplate("需求信息不存在",null);
        }
        if(demand != null && demand.getDemandTypeIndex().compareTo(EnumDemandType.营销分发.getIndex()) !=0
                && demand.getDemandTypeIndex().compareTo(EnumDemandType.ip代理.getIndex()) !=0
                && demand.getDemandTypeIndex().compareTo(EnumDemandType.定制内容.getIndex()) !=0
                && demand.getDemandTypeIndex().compareTo(EnumDemandType.原创征稿.getIndex()) !=0){
            return new ResultTemplate("需求信息类型有误",null);
        }
        //需求基本信息
        Demand demandInfo = demandServicesI.findById(demandId);
        JSONObject result = Demand.packageDemanInfo(demandInfo);
        if(demandInfo.getTradeId() != null){//所属行业的名字
            DictInfo dictInfo = dictInfoServicesI.getById(demandInfo.getTradeId());
            if (dictInfo != null){
                result.put("tradeName",dictInfo.getName());
            }
        }
        if(demand.getDemandTypeIndex() == EnumDemandType.营销分发.getIndex() || demand.getDemandTypeIndex() == EnumDemandType.定制内容.getIndex()){
            //后台—获取需求的选购商品(前台选购的创作者/账号)
            List<Map<String , Object>> mapList = demandServicesI.getDemandGoods(demand.getDemandId(), demand.getDemandTypeIndex());
            if(CollectionUtil.size(mapList) > 0){
                result.put("goodsList",mapList);
            }
        }
        return new ResultTemplate("",result);
    }

    /**
     * 后台—需求基本信息
     * @param demandId
     */
    @RequestMapping(value = "demand_base",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate demandBaseInfo(Integer demandId) {
        if(demandId == null){
            return new ResultTemplate("需求Id有误",null);
        }
        Demand demand = demandServicesI.getById(demandId);
        if(demand == null){
            return new ResultTemplate("需求信息不存在",null);
        }
        Demand demandInfo = demandServicesI.findById(demandId);
        JSONObject result = new JSONObject();
        //需求Id
        result.put("demandId",demandInfo.getDemandId());
        //需求号
        result.put("demandSn",demandInfo.getDemandSn());
        //需求名称
        result.put("demandName",demandInfo.getDemandName());
        //创建人
        result.put("createUser",demandInfo.getCreateUser());
        //需求状态id
        result.put("demandStatusIndex",demandInfo.getDemandStatusIndex());
        //需求状态
        result.put("demandStatus",demandInfo.getDemandStatusIndex() == null ? "" : EnumUtil.valueOf(EnumDemandStatus.class,demandInfo.getDemandStatusIndex()));
        //需求类型ID
        result.put("demandTypeIndex",demandInfo.getDemandTypeIndex());
        //需求类型
        result.put("demandType",demandInfo.getDemandTypeIndex() == null ? "" : EnumUtil.valueOf(EnumDemandType.class,demandInfo.getDemandTypeIndex()));
        //需求拒绝原因
        result.put("refuseReason",demandInfo.getRefuseReason());
        //需求取消原因
        result.put("cancelReason",demandInfo.getCancelReason());
        //用户名
        result.put("userNickName",demandInfo.getRegisteredUserInfo() == null? "" : demandInfo.getRegisteredUserInfo().getNickName());
        //用户Id
        result.put("userId",demandInfo.getRegisteredUserInfo() == null? "" : demandInfo.getRegisteredUserInfo().getRecID());
        if(demandInfo.getDemandTypeIndex().compareTo(EnumDemandType.原创征稿.getIndex()) == 0){
            //稿件费用
            result.put("free",demandInfo.getBudgetMoney());
            //征集数量
            result.put("expectNum",demandInfo.getExpectNum());
        }else {
            //价格项
            result.put("expectOffer",demandInfo.getExpectOffer());
        }
        return new ResultTemplate("",result);
    }

    /**
     * 后台—需求编辑成功
     * @param demand
     */
    @RequestMapping( value = "demand_updateOk"  , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate updateOk(Demand demand){
        if(demand.getDemandId() == null || demandServicesI.getById(demand.getDemandId()) == null){
            return new ResultTemplate("需求Id不能为空");
        }
        if (StringUtil.isBlank(demand.getDemandName())){
            return new ResultTemplate("需求名称不能为空");
        }
        if(demand.getDemandTypeIndex() == null || (demand.getDemandTypeIndex() != EnumDemandType.定制内容.getIndex()
                && demand.getDemandTypeIndex() != EnumDemandType.营销分发.getIndex()
                && demand.getDemandTypeIndex() != EnumDemandType.ip代理.getIndex() && demand.getDemandTypeIndex() != EnumDemandType.原创征稿.getIndex())){
            return new ResultTemplate("需求类型有误");
        }
        if(demand.getBudgetMoney() == null){
            if(demand.getDemandTypeIndex() == EnumDemandType.原创征稿.getIndex()){
                return new ResultTemplate("稿件费用有误");
            }else {
                return new ResultTemplate("需求预算有误");
            }
        }
        if(demand.getRegisteredUserInfoId() == null || registeredUserInfoService.getById(demand.getRegisteredUserInfoId()) == null){
            return new ResultTemplate("注册用户Id有误");
        }
        String str = dealParam(demand);
        if(StringUtil.isNotBlank(str)){
            return new ResultTemplate(str, null);
        }
        demandServicesI.updateDemand(demand,getUser().getRealName());
//        systemLogServicesI.log(Demand.class.getName(),demand.getDemandId() ,"需求修改",getUser().getRealName());
        return new ResultTemplate("",null);
    }

    /**
     * 参数处理
     * @param demand
     * @return
     */
    public String dealParam(Demand demand){
        if (demand.getDemandTypeIndex() == EnumDemandType.营销分发.getIndex()){//营销分发
            if(StringUtil.isBlank(demand.getPlatformName())){
                return "平台不能为空";
            }
            if (StringUtil.isBlank(demand.getSpreadTime())) {
                return "预计推广时间不能为空";
            }
            if (StringUtil.isBlank(demand.getYrCategory())) {
                return "账号分类不能为空";
            }
            if (StringUtil.isBlank(demand.getFans())) {
                return "粉丝数不能为空";
            }
            /*if (StringUtil.isBlank(demand.getExpectedTime())) {
                return "最后反馈时间不能为空";
            }*/
            if(StringUtil.isBlank(demand.getExpectOffer())){
                return "期望报价项不能为空";
            }
            if (demand.getExpectNum() == null){
                return "期望账号个数不能为空";
            }
            if(demand.getExpectNum() != null && (demand.getExpectNum().compareTo(0) <=0 || demand.getExpectNum().compareTo(999999999) > 0)){
                return "期望账号个数的范围是1-999999999之间";
            }
            if(demand.getIsShow() == null){//是否在需求大厅展示
                return "请选择是否在需求大厅展示";
            }
        }else if (demand.getDemandTypeIndex() == EnumDemandType.ip代理.getIndex()){//IP代理
            if(StringUtil.isBlank(demand.getContentForms())){
                return "IP类型不能为空";
            }
            if(demand.getYrCategory() == null){
                return "IP行业不能为空";
            }
        }else if(demand.getDemandTypeIndex() == EnumDemandType.定制内容.getIndex()){//定制内容
            if(StringUtil.isBlank(demand.getScenes())){
                return "创作要求的使用场景不能为空";
            }
            if(StringUtil.isBlank(demand.getContentForms())){
                return "创作要求的内容形式不能为空";
            }
            if(StringUtil.isBlank(demand.getYrCategory())){
                return "创作要求的内容领域不能为空";
            }
            if(StringUtil.isBlank(demand.getExpectedTime())){
                return "期望完成时间不能为空";
            }
            if(StringUtil.isBlank(demand.getExpectOffer())){
                return "期望报价项不能为空";
            }
            if (demand.getExpectNum() == null){
                return "期望创作者个数不能为空";
            }
            if(demand.getExpectNum() != null && (demand.getExpectNum().compareTo(0) <=0 || demand.getExpectNum().compareTo(999999999) > 0)){
                return "期望账号个数的范围是1-999999999之间";
            }
            if(demand.getIsShow() == null){//是否在需求大厅展示
                return "请选择是否在需求大厅展示";
            }
        }else if(demand.getDemandTypeIndex() == EnumDemandType.原创征稿.getIndex()){//原创征稿
            if(StringUtil.isBlank(demand.getContentForms())){
                return "表现形式不能为空";
            }
            if(StringUtil.isBlank(demand.getExpectedTime())){//截至日期
                return "请选择征稿截止时间";
            }
            if(StringUtil.isBlank(demand.getRemark())){//征稿要求
                return "请输入征稿要求";
            }
            if (StringUtil.isNotBlank(demand.getRemark())){
                if(demand.getRemark().length()>1000 || demand.getRemark().length()<20){
                    return "征稿要求不能短于20字,不能大于1000字";
                }
            }
            if(demand.getExpectNum() == null){
                return "请输入期望征集稿件的数量";
            }
            if(demand.getExpectNum() != null && (demand.getExpectNum().compareTo(0) <=0 || demand.getExpectNum().compareTo(999999999) > 0)){
                return "征稿数量的范围是1-999999999之间";
            }
            if(StringUtil.isNotBlank(demand.getReferURL())){
                if(!demand.getReferURL().contains("http") && !demand.getReferURL().contains("https")){
                    return "请输入正常的文章链接";
                }
            }
            if(demand.getIsShow() == null){//是否在需求大厅展示
                return "请选择是否在需求大厅展示";
            }
        }
        return "";
    }

    /**
     * 后台—修改需求状态(2-处理中 | 拒绝 | 5-已取消 | 3-待推荐 | 6-完成推荐 | 4-已完成)
     * @param demand
     */
    @RequestMapping("demand_updateStatus")
    @ResponseBody
    public ResultTemplate updateDemandStatus(Demand demand){
        Demand demandOld =demandServicesI.getById(demand.getDemandId());
        if(demand.getDemandId() == null || demandOld == null){
            return new ResultTemplate("需求Id有误",null);
        }
        if(demand.getDemandStatusIndex() == null){
            return new ResultTemplate("需求状态为空", null);
        }
        EnumDemandStatus enumDemandStatus = (EnumDemandStatus) EnumUtil.valueOf(EnumDemandStatus.class,demand.getDemandStatusIndex());
        if(enumDemandStatus == null){
            return new ResultTemplate("需求状态不存在", null);
        }
        if(demand.getDemandStatusIndex().compareTo(EnumDemandStatus.已取消.getIndex()) ==0){
            if(demandOld.getDemandStatusIndex().compareTo(EnumDemandStatus.待审核.getIndex()) != 0){
                return new ResultTemplate("当前需求状态不可修改",null);
            }
        }else if(demand.getDemandStatusIndex().compareTo(EnumDemandStatus.处理中.getIndex()) ==0){
            if(demandOld.getDemandStatusIndex().compareTo(EnumDemandStatus.待审核.getIndex()) != 0){
                return new ResultTemplate("当前需求状态不可修改",null);
            }
        }else if(demand.getDemandStatusIndex().compareTo(EnumDemandStatus.待推荐.getIndex()) ==0){
            if(demandOld.getDemandStatusIndex().compareTo(EnumDemandStatus.处理中.getIndex()) != 0){
                return new ResultTemplate("当前需求状态不可修改",null);
            }
        }else if(demand.getDemandStatusIndex().compareTo(EnumDemandStatus.完成推荐.getIndex()) ==0){
            if(demandOld.getDemandStatusIndex().compareTo(EnumDemandStatus.待推荐.getIndex()) != 0){
                return new ResultTemplate("当前需求状态不可修改",null);
            }
        }else if(demand.getDemandStatusIndex().compareTo(EnumDemandStatus.已完成.getIndex()) ==0){
            if(demandOld.getDemandStatusIndex().compareTo(EnumDemandStatus.已取消.getIndex()) == 0
                    || demandOld.getDemandStatusIndex().compareTo(EnumDemandStatus.待审核.getIndex()) == 0){
                return new ResultTemplate("当前需求状态不可修改",null);
            }
        }
        if(demand.getDemandStatusIndex().compareTo(EnumDemandStatus.已取消.getIndex()) ==0 && StringUtil.isBlank(demand.getCancelReason())){
            return new ResultTemplate("请写入需求取消原因", null);
        }
        if(demand.getDemandStatusIndex() == EnumDemandStatus.处理中.getIndex()){//审核通过
            if(demandOld.getRegisteredUserInfoId() == null){
                return new ResultTemplate("需求所属用户不存在",null);
            }
            demand.setAuditUser(getUser().getRealName());
        }
        demandServicesI.updateDemandStatus(demand,getUser().getRealName());

        return new ResultTemplate("",null);
    }

    /**
     * 后台—需求选购创作者列表
     * (定制内容)
     * @param demand
     */
    @RequestMapping(value = "getDemandAuthorList",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getDemandAuthorList(Demand demand, BaseModel baseModel){
        PageInfo<Map> yRAuthorPageInfo = yRAuthorServicesI.getDemandAuthorList(demand.getDemandId(),demand.getLikeName(), EnumAuthorStatus.上架.getIndex(), baseModel);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(yRAuthorPageInfo.getList()) > 0){
            for ( Map<String ,Object> map : yRAuthorPageInfo.getList()){
                JSONObject authorInfo = new JSONObject();
                authorInfo.put("authorId",map.get("recId"));
                authorInfo.put("authorName",map.get("authorNickname"));
                authorInfo.put("authorImg",map.get("authorImg"));
                authorInfo.put("userId",map.get("userId"));
                authorInfo.put("userName",map.get("userName"));
                authorInfo.put("authorPrice",map.get("createdPrice"));
                authorInfo.put("orderNum",map.get("orderNum"));
                authorInfo.put("realName",map.get("RealName"));
                result.add(authorInfo);
            }
        }
        return new ResultTemplate(yRAuthorPageInfo, result);
   }

    /**
     * 后台—需求推荐(营销分发/定制内容)—作品投稿
     * @param orderOfferParam
     */
    @RequestMapping("demand_confirm")
    @ResponseBody
    public ResultTemplate confirmDemand(OrderOfferParam orderOfferParam){
        Demand demand = demandServicesI.getById(orderOfferParam.getDemandId());
        if(orderOfferParam.getDemandId() == null || demand == null){
            return new ResultTemplate("需求Id有误",null);
        }
        if (demand != null){
            if(demand.getDemandTypeIndex().compareTo(EnumDemandType.原创征稿.getIndex()) == 0){
                YRProduction yrProduction = yRProductionServicesI.getById(orderOfferParam.getProductId());
                if(orderOfferParam.getProductId() == null || yrProduction == null
                        || (Integer.parseInt(yrProduction.getYrProductionStatusIndex()) != EnumYRProductionStatus.上架.getIndex()
                        && Integer.parseInt(yrProduction.getYrProductionStatusIndex()) != EnumYRProductionStatus.待审核.getIndex())){
                    return new ResultTemplate("作品信息不存在或状态有误",null);
                }
            }else if(demand.getDemandTypeIndex() == EnumDemandType.定制内容.getIndex()){
                YRAuthor yrAuthor = yRAuthorServicesI.getById(orderOfferParam.getProductId());
                if(orderOfferParam.getProductId() == null || yrAuthor == null || yrAuthor.getAuthorStatus().compareTo(EnumAuthorStatus.上架.getIndex()) != 0){
                    return new ResultTemplate("创作者信息不存在或状态有误",null);
                }
                String msg = judgePrice(orderOfferParam);
                if(StringUtil.isNotBlank(msg)){
                    return new ResultTemplate(msg);
                }
                if(StringUtil.isBlank(orderOfferParam.getUsableDate())){
                    return new ResultTemplate("创作耗时有误",null);
                }
            }else if(demand.getDemandTypeIndex() == EnumDemandType.营销分发.getIndex()){
                PlatformIPAccount platformIPAccount = platformIPAccountServicesI.getById(orderOfferParam.getProductId());
                if(orderOfferParam.getProductId() == null || platformIPAccount == null || platformIPAccount.getAccountStatus() != EnumPlatformIPAccountStatus.上架.getIndex()){
                    return new ResultTemplate("账号信息不存在或状态有误",null);
                }
                String msg = judgePrice(orderOfferParam);
                if(StringUtil.isNotBlank(msg)){
                    return new ResultTemplate(msg);
                }
                if(StringUtil.isBlank(orderOfferParam.getUsableDate())){
                    return new ResultTemplate("可用排期有误",null);
                }
                if(StringUtil.isNotBlank(orderOfferParam.getUsableDate())){
                    String[] split = orderOfferParam.getUsableDate().split(",");
                    if(split == null || split.length < 0 ){
                        return new ResultTemplate("可用排期有误",null);
                    }
                    for (String str : split){
                        try {
                            DateUtil.parseDate(str,"yyyy-MM-dd");
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return new ResultTemplate("可用排期格式有误",null);
                        }
                    }
                }
            }
        }
//        demandServicesI.saveDemandOrderAndSnap(orderOfferParam);
        demandServicesI.saveSignRecord(orderOfferParam,getUser().getRealName());
        return new ResultTemplate();
    }

    /**
     * 参数处理—判断价格项
     * @param orderOfferParam
     * @return
     */
    public String judgePrice(OrderOfferParam orderOfferParam){
        if (orderOfferParam.getPriceNames() == null || orderOfferParam.getPriceNames().length < 0){
            return "账号报价名有误";
        }
        if (orderOfferParam.getPrices() == null || orderOfferParam.getPrices().length < 0){
            return "账号的报价有误";
        }
        if (orderOfferParam.getPriceNames().length != orderOfferParam.getPrices().length){
            return "账号的报价和报价名称有误";
        }
        if(orderOfferParam.getPrices().length > 0){
            for (String str : orderOfferParam.getPrices()){
                try {
                    new BigDecimal(str);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "账号的报价格式有误";
                }
            }
        }
        return "";
    }

    /**
     * 后台—获取需求的订单列表—账号/创作者/原创征稿
     * @param demandId
     */
    @RequestMapping(value = "demand_getOrderList",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getOrderListByDemandId(Integer demandId,BaseModel baseModel){
        if (demandId == null){
            return new ResultTemplate("需求Id参数有误",null);
        }
        Demand demand = demandServicesI.getById(demandId);
        if (demand == null){
            return new ResultTemplate("需求信息不存在",null);
        }
        List<DemandOrderListResult> orderInfo = demandServicesI.getOrderListByDemandId(demandId,baseModel);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(orderInfo) > 0){
            for (DemandOrderListResult infoSeller : orderInfo){
                result.add(DemandOrderListResult.packageOrderData(infoSeller));
            }
        }
        return new ResultTemplate("",result);
    }

    /**
     * 后台—买家订单执行终止
     * (定制内容/营销分发)
     * @param
     */
    @RequestMapping(value = "orderEnd",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate orderExecuteEnd(OrderInfoBuyer orderInfo){

        if (orderInfo.getOrderInfoBuyerId() == null){
            return new ResultTemplate("订单Id不能为空",null);
        }
        OrderInfoBuyer infoBuyer = orderInfoBuyerServicesI.getById(orderInfo.getOrderInfoBuyerId());
        if( infoBuyer== null){
            return new ResultTemplate("订单信息不存在",null);
        }
        if(StringUtil.isBlank(orderInfo.getCancelReason())){
            return new ResultTemplate("请选择终止原因",null);
        }
        if(infoBuyer.getOrderStatusValue().compareTo(EnumOrderBuyerStatus.待卖家执行.getIndex()) !=0){
            return new ResultTemplate("订单信息有误",null);
        }
        infoBuyer.setOrderStatusValue(EnumOrderBuyerStatus.已取消.getIndex());
        infoBuyer.setCancelReason(orderInfo.getCancelReason());
        orderInfoBuyerServicesI.updateBuyerOrder(infoBuyer,getUser().getRealName());
        return new ResultTemplate("",null);
    }

    /**
     * 需求选购账号
     * (营销分发)
     * @param accountSeach
     * @param baseModel
     * @return
     */
    @RequestMapping(value = "getDemandAccount",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getDemandAccount(PlatformIpAccountSeach accountSeach , BaseModel baseModel){
        accountSeach.setCurrLoginUser(getUser());//当前登陆用户—目前没有用
        accountSeach.setPlatformPrice(13);//平台—圆融
        accountSeach.setPlatformAccountStatus("1");//上架账号
        PageInfo<Map> result = demandServicesI.getDemandAccount(accountSeach , baseModel);
        JSONArray jsonArray = new JSONArray();
        if(CollectionUtil.size(result.getList()) > 0){
            for(Map<String ,Object> ele : result.getList()){
                JSONObject jsonObject = PlatformIPAccount.mapPareJSONObject(ele);
                jsonArray.add(jsonObject);
            }
        }
        return new ResultTemplate(result,jsonArray);
    }

    /**
     * 删除需求—假删
     * @param demandId
     * @return
     */
    @RequestMapping(value = "deleteDemand",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate deleteDemand(Integer demandId){

        if(demandId == null){
            return new ResultTemplate("需求Id不能为空");
        }
        Demand demand = demandServicesI.getById(demandId);
        if(demand == null){
            return new ResultTemplate("需求信息不存在");
        }
        demandServicesI.deleteDemand(demandId);
        return new ResultTemplate();
    }

    /**
     * 原创征稿—获取作品列表
     * (原创征稿)
     * @param data
     * @param baseModel
     * @return
     */
    @RequestMapping(value = "getDemandProduct",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getDemandProduct(YRProductionListParam data , BaseModel baseModel){
        //查询未发布作品
        data.setPublishStatus(String.valueOf(EnumPublishStatus.未公开.getIndex()));
        //查询上架、待审核状态
        data.setKeyWord(String.valueOf(EnumYRProductionStatus.待审核.getIndex()) + "," + String.valueOf(EnumYRProductionStatus.上架.getIndex()));
        PageInfo<YRProduction> yRProductionPageInfo = yRProductionServicesI.getDemandProduct(data , baseModel);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(yRProductionPageInfo.getList()) > 0){
            for(YRProduction production : yRProductionPageInfo.getList()){
                JSONObject ele = new JSONObject();
                //id
                ele.put("productionId" , production.getRecId());
                //作者
                ele.put("authorNikeName" , production.getYrAuthor() == null ? "" : production.getYrAuthor().getAuthorNickname());
                //文章标题
                ele.put("title" , production.getTitle());
                //字数
                ele.put("wordNum" , production.getWordNum());
                //图片数
                ele.put("imgNum" , production.getImgNum());
                //状态
                ele.put("yrProductionStatus" ,production.getYrProductionStatus() == null ? "" : production.getYrProductionStatus().getName());
                //报价（元）
                ele.put("price" , production.getProductQuotedPrice());
               //作者ID
                ele.put("authorId" , production.getYrAuthor()== null? "" : production.getYrAuthor().getRecId());
                //作者头像
                ele.put("authorImg" , production.getYrAuthor()== null? "" : production.getYrAuthor().getAuthorImg());
                //注册用户简称
                ele.put("userName" ,(production.getYrAuthor()== null || production.getYrAuthor().getRegisteredUserInfo() == null) ? "" : production.getYrAuthor().getRegisteredUserInfo().getNickName());
                //注册用户ID
                ele.put("userId" ,(production.getYrAuthor()== null || production.getYrAuthor().getRegisteredUserInfo() == null) ? "" :  production.getYrAuthor().getRegisteredUserInfo().getRecID());
                //媒介经理
                ele.put("medialName" , production.getAdminUser()== null? "" : production.getAdminUser().getRealName());
                result.add(ele);
            }
        }
        return new ResultTemplate(yRProductionPageInfo , result);
    }

    /**
     * 后台—获取需求的报名记录
     * (定制内容/营销分发/原创征稿)
     * @param demandId
     * @param baseModel
     *
     */
    @RequestMapping(value = "demand_getSignList",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getDemandSign(Integer demandId, BaseModel baseModel){
        if (demandId == null){
            return new ResultTemplate("需求Id参数有误",null);
        }
        Demand demand = demandServicesI.getById(demandId);
        if (demand == null){
            return new ResultTemplate("需求信息不存在",null);
        }
        Integer orderType = 0;
        String invoiceFee = "";
        if(demand.getDemandTypeIndex().compareTo(EnumDemandType.原创征稿.getIndex()) == 0){
            orderType = EnumOrderSellerType.原创征稿.getIndex();
            invoiceFee = configurationServicesI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_COLLECTION);
        }else if(demand.getDemandTypeIndex().compareTo(EnumDemandType.营销分发.getIndex()) == 0){
            orderType = EnumOrderSellerType.营销分发.getIndex();
            invoiceFee = configurationServicesI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_ACCOUNT);
        }else if (demand.getDemandTypeIndex().compareTo(EnumDemandType.定制内容.getIndex()) == 0){
            orderType = EnumOrderSellerType.定制内容.getIndex();
            invoiceFee = configurationServicesI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_AUTHOR);
        }
        //获取报名信息
        List<DemandSignListResult> signList = demandServicesI.getDemandSignList(demandId,orderType,baseModel);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(signList) > 0){
            for (DemandSignListResult signInfo : signList){
                result.add(DemandSignListResult.packageSignData(signInfo,invoiceFee));
            }
        }
        return new ResultTemplate("",result);
    }

    /**
     * 后台—确认使用—查询
     * (定制内容/营销分发)
     * @param orderInfoSellerId
     * @return
     */
    @RequestMapping(value = "demand_confirmUse",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate confirmUse(Integer orderInfoSellerId){
        if(orderInfoSellerId == null){
            return new ResultTemplate("报名Id有误");
        }
        if(orderInfoSellerServicesI.getById(orderInfoSellerId) == null){
            return new ResultTemplate("报名信息不存在");
        }
        OrderInfoSeller orderInfoSeller = orderInfoSellerServicesI.getAccountInfo(orderInfoSellerId,null);
        JSONObject result = new JSONObject();
        result.put("orderInfoSellerId",orderInfoSeller.getOrderInfoSellerId());
        result.put("orderSn",orderInfoSeller.getOrderSn());
        result.put("name",orderInfoSeller.getProduction());
        result.put("buyerServiceRate",orderInfoSeller.getBuyerServiceRate() == null ? 0 : orderInfoSeller.getBuyerServiceRate());
        result.put("usableDate",orderInfoSeller.getUsableDate());
        result.put("invoiceRate",orderInfoSeller.getInvoiceRate() == null ? new BigDecimal("0") : orderInfoSeller.getInvoiceRate());
        JSONArray objects = new JSONArray();
        if(CollectionUtil.size(orderInfoSeller.getOrderInfoOfferList()) > 0){
            for (OrderInfoOffer offer : orderInfoSeller.getOrderInfoOfferList()){
                JSONObject object = new JSONObject();
                object.put("orderInfoOfferId",offer.getOrderInfoOfferId());
                object.put("priceName",offer.getPriceName());
                object.put("price",offer.getPrice());
                object.put("executePrice",offer.getExecutePrice());
                BigDecimal invoice = orderInfoSeller.getInvoiceRate() == null ? new BigDecimal("0") : orderInfoSeller.getInvoiceRate().divide(new BigDecimal("100"));
                BigDecimal invoiceFee = offer.getExecutePrice().multiply(invoice).setScale(2,BigDecimal.ROUND_UP);
                object.put("invoiceFee",invoiceFee);
                object.put("sumMoney",offer.getExecutePrice().add(invoiceFee));
                objects.add(object);
            }
        }
        result.put("priceDetail",objects);
        return new ResultTemplate(result);
    }

    /**
     * 后台—确认使用—提交(定制内容/营销分发)
     * 确认使用生成订单
     * @param orderOfferParam
     * @return
     */
    @RequestMapping(value = "demand_confirmUseOk",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate confirmUseOk(OrderOfferParam orderOfferParam){
        if(orderOfferParam.getOrderInfoOfferId() == null){
            return new ResultTemplate("价格项Id有误");
        }
        OrderInfoOffer infoOffer = orderInfoOfferServicesI.getById(orderOfferParam.getOrderInfoOfferId());
        OrderInfoSeller infoSeller = orderInfoSellerServicesI.getById(infoOffer.getOrderInfoSeller());
        if(infoOffer == null || infoSeller == null){
            return new ResultTemplate("价格项信息不存在");
        }
        if(StringUtil.isBlank(orderOfferParam.getExecuteTime())){
            if(infoSeller.getOrderTypeValue().compareTo(EnumOrderSellerType.营销分发.getIndex()) == 0){
                return new ResultTemplate("发布日期不能为空");
            }else if(infoSeller.getOrderTypeValue().compareTo(EnumOrderSellerType.定制内容.getIndex()) == 0){
                return new ResultTemplate("作品提交时间不能为空");
            }
        }
        if(infoSeller.getOrderStatusValue().compareTo(EnumOrderSellerStatus.待买家确认.getIndex()) != 0){
            return new ResultTemplate("报名信息有误");
        }
        demandServicesI.saveConfirmUseOk(orderOfferParam,getUser().getRealName());
        return new ResultTemplate();
    }

    /**
     * 后台—确认使用前修改价格(定制内容/营销分发)
     * @param offer
     * @return
     */
    @RequestMapping(value = "demand_updatePrice",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate updatePrice(OrderInfoOffer offer){
        if(offer.getOrderInfoOfferId() == null || offer.getExecutePrice() == null){
            return new ResultTemplate("价格项Id或价格有误");
        }
        OrderInfoOffer infoOffer = orderInfoOfferServicesI.getById(offer.getOrderInfoOfferId());
        if(infoOffer == null){
            return new ResultTemplate("价格项信息不存在");
        }
        orderInfoOfferServicesI.updateOrderOffer(offer,getUser().getRealName());
        return new ResultTemplate();
    }

    /**
     * 后台—修改订单价格(已经生成订单)
     * (定制内容/营销分发/原创征稿)
     * @param orderBuyerId
     * @param orderPrice
     * @return
     */
    @RequestMapping(value = "demand_updateOrderPrice",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate updateOrderPrice(Integer orderBuyerId, BigDecimal orderPrice){
        if(orderBuyerId == null){
            return new ResultTemplate("订单Id不能为空");
        }
        OrderInfoBuyer infoBuyer = orderInfoBuyerServicesI.getById(orderBuyerId);
        if(infoBuyer == null){
            return new ResultTemplate("订单信息不存在");
        }
        if(infoBuyer.getOrderStatusValue().compareTo(EnumOrderBuyerStatus.已取消.getIndex()) == 0
                || infoBuyer.getOrderStatusValue().compareTo(EnumOrderBuyerStatus.已完成.getIndex()) == 0
                || infoBuyer.getPayStatusValue().compareTo(EnumPayStatus.已失效.getIndex()) == 0
                || infoBuyer.getPayStatusValue().compareTo(EnumPayStatus.已支付.getIndex()) == 0){//订单状态已完成、已取消；支付状态已失效、已支付
            return new ResultTemplate("订单信息不可操作");
        }
        if (orderPrice == null || orderPrice.compareTo(BigDecimal.ZERO) < 0){
            return new ResultTemplate("订单金额有误");
        }
        orderInfoBuyerServicesI.updateOrderPrice(orderBuyerId,orderPrice,getUser().getRealName());
        return new ResultTemplate();
    }

    /**
     * 后台—修改订单价格查询(营销分发/定制内容/原创征稿)
     * 已经生成订单
     * @param orderBuyerId
     * @return
     */
    @RequestMapping(value = "demand_findOrderPrice",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate findOrderPrice(Integer orderBuyerId){
        if(orderBuyerId == null){
            return new ResultTemplate("订单Id不能为空");
        }
        if(orderInfoBuyerServicesI.getById(orderBuyerId) == null){
            return new ResultTemplate("订单信息不存在");
        }
        OrderInfoBuyer orderInfoBuyer =orderInfoBuyerServicesI.findOrderPrice(orderBuyerId);
        JSONObject result = new JSONObject();
        if(orderInfoBuyer != null){
            Integer orderType = orderInfoBuyer.getOrderInfoType();
            //买家订单Id
            result.put("orderInfoBuyerId",orderInfoBuyer.getOrderInfoBuyerId());
            //报价
            result.put("basePrice",orderInfoBuyer.getOrderDetail() == null ? "" : orderInfoBuyer.getOrderDetail().getBasePrice());
            //订单金额
            result.put("executePrice",orderInfoBuyer.getOrderDetail() == null ? "" : orderInfoBuyer.getOrderDetail().getPrice());
            //买家服务费率
            result.put("buyerServiceRate",orderInfoBuyer.getBuyerServiceRate());
            //订单总计
            result.put("sumMoney",orderInfoBuyer.getReceivableMoney());
            //发票税率
            result.put("invoiceRate", orderInfoBuyer.getOrderInfoSeller() == null ? 0 : orderInfoBuyer.getOrderInfoSeller().getInvoiceRate());
            if(orderType.compareTo(EnumOrderSellerType.原创征稿.getIndex()) == 0){//原创征稿
                result.put("productTitle",orderInfoBuyer.getSnapshotYrProduction() == null ? "" : orderInfoBuyer.getSnapshotYrProduction().getTitle());
            }else {
                if(orderType.compareTo(EnumOrderSellerType.营销分发.getIndex()) == 0){//营销分发
                    result.put("accountName",orderInfoBuyer.getSnapshotAccount() == null ? "" : orderInfoBuyer.getSnapshotAccount().getName());
                }else if(orderType.compareTo(EnumOrderSellerType.定制内容.getIndex()) == 0){//定制内容
                    result.put("authorName",orderInfoBuyer.getSnapshotYrAuthor() == null ? "" : orderInfoBuyer.getSnapshotYrAuthor().getAuthorNickname());
                }
                //订单号
                result.put("orderSn",orderInfoBuyer.getOrderSn());
                //价格项名字
                result.put("offerPriceName",orderInfoBuyer.getOrderDetail() == null ? "" : orderInfoBuyer.getOrderDetail().getProduction());
            }
            //税额
            result.put("invoiceFree",0);
            if(CollectionUtil.size(orderInfoBuyer.getOrderCostInfoList()) > 0){
                for (OrderCostInfo costInfo :orderInfoBuyer.getOrderCostInfoList()){
                    if(costInfo.getCostTypeIndex().compareTo(EnumCostType.作品买家订单.getIndex()) == 0 && costInfo.getCostId() == 242){
                        result.put("invoiceFree",costInfo.getMoney());
                    }
                }
            }
        }
        return new ResultTemplate(result);
    }

    /**
     * 后台—前端实时计算价格(营销分发/定制内容)
     * 报名记录
     * @param offerParam
     * @return
     */
    @RequestMapping("demand_calcOfferPrice")
    @ResponseBody
    public ResultTemplate getOfferPrice(OrderOfferParam offerParam){
        if(offerParam == null || offerParam.getOfferIds() == null || offerParam.getOfferIds().length <= 0){
            return new ResultTemplate("价格项Id不能为空");
        }
        for (Integer id: offerParam.getOfferIds()){
            if(id == null){
                return new ResultTemplate("价格项Id不能为空");
            }
        }
        if(offerParam.getCostMoney() == null || offerParam.getCostMoney().length <=0){
            return new ResultTemplate("应约价不能为空");
        }
        for (BigDecimal price : offerParam.getCostMoney()){
            if (price == null){
                return new ResultTemplate("应约价不能为空");
            }
        }
        if(offerParam.getCostMoney().length != offerParam.getOfferIds().length){
            return new ResultTemplate("参数有误");
        }
        if(offerParam.getInvoiceRate() == null){
            return new ResultTemplate("发票参数有误");
        }
        if(offerParam.getBuyerServiceRate() !=null && offerParam.getExecutePrice() != null){
            return new ResultTemplate("参数有误");
        }
        JSONObject jsonObject = new JSONObject();
        if(offerParam.getFlag() == null){ //修改服务费
            if(offerParam.getBuyerServiceRate() == null || offerParam.getBuyerServiceRate().compareTo(BigDecimal.ZERO) < 0){
                return new ResultTemplate("服务费率有误");
            }
            jsonObject.put("priceDetail",getPriceByServiceRate(offerParam));
            jsonObject.put("buyerServiceRate",offerParam.getBuyerServiceRate());
        }else {//修改订单金额
            if(offerParam.getExecutePrice() == null || offerParam.getExecutePrice().compareTo(BigDecimal.ZERO) < 0){
                return new ResultTemplate("订单金额有误");
            }
            jsonObject =getPriceByOrderPrice(offerParam);
        }
        return new ResultTemplate(jsonObject);
    }

    /**
     * 通过订单金额计算订单费用、服务费率
     * @param offerParam
     * @return
     */
    private JSONObject getPriceByOrderPrice(OrderOfferParam offerParam) {
        JSONObject jsonObject = new JSONObject();
        JSONArray result = new JSONArray();
        BigDecimal[] costMoney = offerParam.getCostMoney();
        BigDecimal service = new BigDecimal("0");//计算服务费率
        for (int i = 0; i < offerParam.getOfferIds().length ; i++){
            if (offerParam.getOfferIds()[i].compareTo(offerParam.getFlag()) == 0){
                service = offerParam.getExecutePrice().subtract(costMoney[i]).divide(costMoney[i],3,BigDecimal.ROUND_UP);
            }
        }
        for (int i = 0; i < offerParam.getOfferIds().length ; i++){
            JSONObject object = new JSONObject();
            object.put("orderInfoOfferId",offerParam.getOfferIds()[i]);
            if (offerParam.getOfferIds()[i].compareTo(offerParam.getFlag()) == 0){
//                service = offerParam.getExecutePrice().subtract(costMoney[i]).divide(costMoney[i],3,BigDecimal.ROUND_UP);
                //订单金额
                object.put("executePrice",offerParam.getExecutePrice());
                //税额
                BigDecimal invoice =offerParam.getExecutePrice().multiply(offerParam.getInvoiceRate().divide(new BigDecimal("100"))).setScale(2,BigDecimal.ROUND_UP);
                object.put("invoiceFree",invoice);
                //订单总计
                object.put("sumMoney", invoice.add(offerParam.getExecutePrice()));
            }else {
                //订单金额
                BigDecimal orderMoney = costMoney[i].add(costMoney[i].multiply(service));
                object.put("executePrice",orderMoney);
                //税额
                BigDecimal invoice =orderMoney.multiply(offerParam.getInvoiceRate().divide(new BigDecimal("100"))).setScale(2,BigDecimal.ROUND_UP);
                object.put("invoiceFree",invoice);
                //订单总计
                object.put("sumMoney", invoice.add(orderMoney));
            }
            result.add(object);
        }
        jsonObject.put("priceDetail",result);
        jsonObject.put("buyerServiceRate",service.multiply(new BigDecimal("100")));
        return jsonObject;
    }

    /**
     * 通过服务费率计算订单金额
     * @param offerParam
     * @return
     */
    private JSONArray getPriceByServiceRate(OrderOfferParam offerParam) {
        JSONArray result = new JSONArray();
        BigDecimal[] costMoney = offerParam.getCostMoney();
        for (int i = 0; i < offerParam.getOfferIds().length ; i++){
            JSONObject object = new JSONObject();
            object.put("orderInfoOfferId",offerParam.getOfferIds()[i]);
            BigDecimal serviceRate = offerParam.getBuyerServiceRate().divide(new BigDecimal("100"));
            BigDecimal orderMoney = OrderTotalAmountRoundUtil.getRoundAmount(costMoney[i].add(costMoney[i].multiply(serviceRate)));
            //订单金额
            object.put("executePrice",orderMoney);
            //税额
            BigDecimal invoice =orderMoney.multiply(offerParam.getInvoiceRate().divide(new BigDecimal("100"))).setScale(2,BigDecimal.ROUND_UP);
            object.put("invoiceFree",invoice);
            //订单总计
            object.put("sumMoney", invoice.add(orderMoney));
            result.add(object);
        }
        return result;
    }

    /**
     * 后台—实时计算价格(营销分发/定制内容)
     * 订单列表
     * @param orderPrice
     * @param price
     * @param invoiceRate
     * @param flag
     * @return
     */
    @RequestMapping("exs_calcOrderPrice")
    @ResponseBody
    public ResultTemplate getByServiceRate(BigDecimal orderPrice,BigDecimal price,BigDecimal invoiceRate,Integer flag) {
        JSONObject result = new JSONObject();
        if(price == null || price.compareTo(BigDecimal.ZERO) < 0){
            return new ResultTemplate("报价有误");
        }
        if(orderPrice == null || flag == null || invoiceRate == null || orderPrice.compareTo(BigDecimal.ZERO) < 0 || invoiceRate.compareTo(BigDecimal.ZERO) < 0){
            return new ResultTemplate("参数有误");
        }
        if(flag == 1){
            //订单金额
            result.put("executePrice",orderPrice);
            //税额
            BigDecimal invoice =orderPrice.multiply(invoiceRate.divide(new BigDecimal("100"))).setScale(2,BigDecimal.ROUND_UP);
            result.put("invoiceFree",invoice);
            //订单总计
            result.put("sumMoney", invoice.add(orderPrice));
            //服务费率
            if(price.compareTo(BigDecimal.ZERO) ==0){
                result.put("buyerServiceRate",0);
            }else {
                result.put("buyerServiceRate", orderPrice.subtract(price).divide(price, 3, BigDecimal.ROUND_UP).multiply(new BigDecimal("100")));
            }
        }else {
            BigDecimal serviceRate = orderPrice.divide(new BigDecimal("100"));
            BigDecimal orderMoney = OrderTotalAmountRoundUtil.getRoundAmount(price.add(price.multiply(serviceRate)));
            //订单金额
            result.put("executePrice",orderMoney);
            //税额
            BigDecimal invoice =orderMoney.multiply(invoiceRate.divide(new BigDecimal("100"))).setScale(2,BigDecimal.ROUND_UP);
            result.put("invoiceFree",invoice);
            //订单总计
            result.put("sumMoney", invoice.add(orderMoney));
            //服务费率
            result.put("buyerServiceRate",orderPrice);
        }
        return new ResultTemplate(result);
    }

    /**
     * 后台—实时计算价格(原创征稿)
     * @param price
     * @param orderPrice
     * @param invoiceRate
     * @return
     */
    @RequestMapping("demand_calculatePrice")
    @ResponseBody
    public ResultTemplate calculatePrice(BigDecimal price, BigDecimal orderPrice, BigDecimal invoiceRate){
        if(price == null || price.compareTo(BigDecimal.ZERO) < 0){
            return new ResultTemplate("报价有误");
        }
        if(orderPrice == null || invoiceRate == null || orderPrice.compareTo(BigDecimal.ZERO) < 0 || invoiceRate.compareTo(BigDecimal.ZERO) < 0){
            return new ResultTemplate("参数有误");
        }
        JSONObject result = new JSONObject();
        //订单金额
        result.put("executePrice",orderPrice);
        //税额
        BigDecimal invoice =orderPrice.multiply(invoiceRate.divide(new BigDecimal("100"))).setScale(2,BigDecimal.ROUND_UP);
        result.put("invoiceFree",invoice);
        //订单总计
        result.put("sumMoney", invoice.add(orderPrice));
        //服务费率
        if(price.compareTo(BigDecimal.ZERO) == 0){
            result.put("buyerServiceRate",0);
        }else {
            result.put("buyerServiceRate",orderPrice.subtract(price).divide(price,3,BigDecimal.ROUND_UP).multiply(new BigDecimal("100")));
        }
        return new ResultTemplate(result);
    }

    /**
     * 后台——价格信息查询(原创征稿)
     * @param orderSignId
     * @return
     */
    @RequestMapping("getProPriceInfo")
    @ResponseBody
    public ResultTemplate getProPriceInfo(Integer orderSignId){
        if(orderSignId == null){
            return new ResultTemplate("报名信息Id不能为空");
        }
        OrderInfoSeller infoSeller = orderInfoSellerServicesI.getById(orderSignId);
        if(infoSeller == null){
            return new ResultTemplate("报名信息不存在");
        }
        JSONObject result = new JSONObject();
        result.put("orderSignId",orderSignId);
        result.put("title",infoSeller.getProduction());//作品标题
        result.put("invoiceRate",infoSeller.getInvoiceRate());//发票费率
        result.put("buyerServiceRate",infoSeller.getBuyerServiceRate());//服务费率
        result.put("executePrice",infoSeller.getBuyerOrderPrice());//订单金额
        result.put("referPrice",infoSeller.getReferPrice());//卖家报价
        BigDecimal invoiceFree = infoSeller.getBuyerOrderPrice().multiply(infoSeller.getInvoiceRate().divide(new BigDecimal("100"))).setScale(2,BigDecimal.ROUND_UP);
        result.put("invoiceFree",invoiceFree);//税额
        result.put("sumMoney",infoSeller.getBuyerOrderPrice().add(invoiceFree));//订单总计
        return new ResultTemplate(result);
    }

    /**
     * 后台——原创征稿
     * 报名记录 * 修改价格
     * @param orderSignId
     * @param executePrice
     * @return
     */
    @RequestMapping("updateProPriceInfo")
    @ResponseBody
    public ResultTemplate updateProPriceInfo(Integer orderSignId, BigDecimal executePrice){
        if(orderSignId == null || orderInfoSellerServicesI.getById(orderSignId) == null){
            return new ResultTemplate("报名信息有误");
        }
        if(executePrice == null || executePrice.compareTo(BigDecimal.ZERO) <0){
            return new ResultTemplate("订单金额有误");
        }
        orderInfoSellerServicesI.updateProPriceInfo(orderSignId,executePrice,getUser().getRealName());
        return new ResultTemplate();
    }

    /**
     * 后台—原创征稿生成订单
     * @param orderInfoSellerId
     * @return
     */
    @RequestMapping(value = "demand_saveProOrder",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate saveProOrder(Integer orderInfoSellerId){
        if(orderInfoSellerId == null || orderInfoSellerServicesI.getById(orderInfoSellerId) == null){
            return new ResultTemplate("报名信息不存在");
        }
        demandServicesI.saveProOrder(orderInfoSellerId,getUser().getRealName());
        return new ResultTemplate();
    }

    /**
     * 后台—确认执行 修改订单状态已完成
     * 营销分发/定制内容
     * @param orderBuyerId
     * @return
     */
    @RequestMapping(value = "demand_confirmResult",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate confirmResult(Integer orderBuyerId){
        if(orderBuyerId == null){
           return new ResultTemplate("订单信息有误");
        }
        OrderInfoBuyer infoBuyer = orderInfoBuyerServicesI.getById(orderBuyerId);
        if(infoBuyer == null || infoBuyer.getOrderStatusValue().compareTo(EnumOrderBuyerStatus.待买家验收.getIndex()) != 0){
            return new ResultTemplate("订单信息有误");
        }
        orderInfoBuyerServicesI.updateOrderBuyerStatus(orderBuyerId,getUser().getRealName());
        return new ResultTemplate();
    }

    /**
     * 后台—原创征稿下载
     * 报名列表——0
     * 订单列表——1
     * @param orderInfoId
     * @param flag
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("downloadPro")
    public void downloadPro(Integer orderInfoId,Integer flag, HttpServletRequest request , HttpServletResponse response) throws Exception {
        if(orderInfoId == null || flag == null){
            return ;
        }
        String localContent = "";
        String fileName = "";
        if(flag == 1){//订单列表下载
            OrderInfoBuyer infoBuyer = orderInfoBuyerServicesI.getById(orderInfoId);
            if(infoBuyer == null || (infoBuyer.getOrderInfoType().compareTo(EnumOrderSellerType.原创征稿.getIndex()) != 0
                    && infoBuyer.getOrderInfoType().compareTo(EnumOrderSellerType.作品订单.getIndex()) != 0)){
                return ;
            }
            SnapshotYrProduction snapshotYrProduction = snapshotYrProductionServicesI.getDownloadInfo(infoBuyer.getOrderSn());
            if(snapshotYrProduction == null){
                return ;
            }
            localContent = snapshotYrProduction.getLocalcontent();
            fileName = org.apache.commons.lang3.StringUtils.isBlank(snapshotYrProduction.getTitle()) ? "作品内容" :snapshotYrProduction.getTitle();
        }else if(flag == 0){//报名列表下载
            OrderInfoSeller infoSeller = orderInfoSellerServicesI.getById(orderInfoId);
            if(infoSeller == null ){
                return;
            }
            YRProduction yrProduction = yRProductionServicesI.getById(infoSeller.getReferId());
            if(yrProduction == null ){
                return ;
            }
            localContent = yrProduction.getLocalcontent();
            fileName = StringUtil.isBlank(yrProduction.getTitle()) ? "作品内容" : yrProduction.getTitle();
        }else {
            return;
        }
        if(StringUtil.isNotBlank(localContent) && localContent.length()>0) {
            download(fileName + ".doc",Html2WordUtil.html2Word(localContent,fileName,request,response));
        }
    }

}

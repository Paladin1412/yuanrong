package com.yuanrong.admin.web.controller.demand;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.result.DemandListResult;
import com.yuanrong.admin.result.PlatformIPAccountResult;
import com.yuanrong.admin.seach.CommonSearchClass;
import com.yuanrong.admin.seach.DemandListParamSearch;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.common.util.SystemParam;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 基本需求信息的controller
 * Created MDA
 */
@Controller
@RequestMapping("demand")
public class DemandController extends BaseController {
    private static final Logger logger = Logger.getLogger(DemandController.class);

    /**
     * 买家中心需求列表
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/7/6 14:24
     */
    @RequestMapping(value = "demand_list", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(DemandListParamSearch data, BaseModel baseModel, HttpSession session) {
        data.setWebUser(getWebUser(session));
        PageInfo<DemandListResult> demandPageInfo = demandServicesI.demandList(data, baseModel);
        JSONArray result = new JSONArray();
        if (CollectionUtil.size(demandPageInfo.getList()) > 0) {
            for (DemandListResult demand : demandPageInfo.getList()) {
                result.add(DemandListResult.packageDemandBuyCenter(demand));
            }
        }
        return new ResultTemplate(demandPageInfo, result);
    }

    /**
     * 买家中心查询需求
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/7/6 14:24
     */
    @RequestMapping("demand_select")
    @ResponseBody
    public ResultTemplate select(Demand demand,HttpSession session) {
        if (demand.getDemandSn() == null) {
            return new ResultTemplate("参数错误，没有传需求ID！", null);
        }
        demand.setWebUser(getWebUser(session));
        Demand retObject = demandServicesI.getDetailByDemand(demand);
        //封装需求基本信息
        JSONObject ele = Demand.packageDemandMy(retObject, true);
        if (ele != null) {
            return new ResultTemplate("", ele);
        } else {
            return new ResultTemplate("数据错误，无此需求或需求类型", null);
        }
    }

    /**
     * 获取需求类型
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/7/10 9:34
     */
    @RequestMapping(value = "demand_getType", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getDemandType() {
        return new ResultTemplate("", EnumDemandType.getMapInfo());
    }

    /**
     * 判断账号是否有快速需求
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/7/10 9:34
     */
    @RequestMapping(value = "demand_isHaveDemandFast", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate isHaveDemandFast(HttpSession session) {
        JSONObject ele = new JSONObject();
        if (demandFastServicesI.isHaveDemandFast(getWebUser(session).getRecID())) {
            ele.put("isHaveDemandFast", 1);
            return new ResultTemplate("", ele);
        } else {
            ele.put("isHaveDemandFast", 0);
            return new ResultTemplate("", ele);
        }

    }

    /**
     * 取消需求
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/7/10 9:34
     */
    @RequestMapping(value = "demand_updateDemandFront", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate updateDemandFront(Demand demand,HttpSession session) {
        if (demand.getDemandSn() == null) {
            return new ResultTemplate("需求id为空", null);

        }
        if (StringUtil.isBlank(demand.getCancelReason())) {
            return new ResultTemplate("取消原因为null", null);
        }
        demand.setWebUser(getWebUser(session));
        demand.setDemandStatusIndex(EnumDemandStatus.已取消.getIndex());
        Demand demand1 = demandServicesI.getByDemandSn(demand.getDemandSn());
        demand.setDemandId(demand1.getDemandId());
        demandServicesI.updateDemandStatus(demand , getWebUser(session).getMobile());
        return new ResultTemplate("", null);
    }


    /**
     * 买家中心查询需求——订单详情
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/7/6 14:24
     */
    @RequestMapping("orderList")
    @ResponseBody
    public ResultTemplate orderList(Demand demand,BaseModel baseModel,HttpSession session) {
        if (demand.getDemandSn() == null) {
            return new ResultTemplate("参数错误，没有传需求ID！", null);
        }
        demand.setWebUser(getWebUser(session));
        PageInfo<OrderInfoBuyer> orderInfoBuyerPageInfo = demandServicesI.getOrderList(demand,baseModel);
        JSONArray jsonArray = new JSONArray();
        //封装交易记录
        if(orderInfoBuyerPageInfo.getList().size()>0){
            for (OrderInfoBuyer orderInfoBuyer : orderInfoBuyerPageInfo.getList()) {
                JSONObject object = OrderInfoBuyer.packageToMy(orderInfoBuyer);
                object.put("num",orderInfoBuyerPageInfo.getList().size());
                jsonArray.add(object);
            }
        }
        return  new ResultTemplate(orderInfoBuyerPageInfo , jsonArray);
    }
    /**
    * 买家中心需求详情报名列表
    * @author      ShiLinghuai
    * @param
    * @return      
    * @exception   
    * @date        2018/10/11 10:17
    */
    @RequestMapping("applyList")
    @ResponseBody
    public ResultTemplate applyList(Demand demand,BaseModel baseModel,HttpSession session) {
        if(demand.getDemandSn()==null){
            return new ResultTemplate("参数错误，没有传需求ID！", null);
        }
        demand.setWebUser(getWebUser(session));
        //买家征稿服务费率
        String collectionServer = configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_BUYER_COLLECTION);
        //获取买家营销账号服务费率
        String accountServer = configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_BUYER_ACCOUNT);
        //获取买家原创内容服务费率
        String authorServer = configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_BUYER_AUTHOR);
        //获取发票费率
        String invoiceCOLLECTION = configurationServicesI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_ACCOUNT);
        String invoiceAuthor = configurationServicesI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_AUTHOR);
        String invoiceAccount = configurationServicesI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_COLLECTION);
        CommonSearchClass commonSearchClass = new CommonSearchClass(collectionServer,accountServer,authorServer,invoiceCOLLECTION,invoiceAuthor,invoiceAccount);
        Demand demandS = demandServicesI.getByDemandSn(demand.getDemandSn());
        if(demandS.getDemandTypeIndex() == EnumDemandType.原创征稿.getIndex()){
            //待审核审核失败不返回
            Integer[] ia = {EnumYRProductionStatus.待审核.getIndex(),EnumYRProductionStatus.审核不通过.getIndex()};
            demand.setYrProductStatus(ia);
        }
        PageInfo<OrderInfoSeller> orderInfoSellers = demandServicesI.getApplyList(demand,baseModel);
        JSONArray jsonArray = new JSONArray();
        if(CollectionUtil.size(orderInfoSellers.getList()) > 0){
            for(OrderInfoSeller orderInfoSeller : orderInfoSellers.getList()){
                JSONObject object = OrderInfoSeller.packageToMy(orderInfoSeller,commonSearchClass);
                object.put("num",orderInfoSellers.getList().size());
                jsonArray.add(object);
            }
        }
        return  new ResultTemplate(orderInfoSellers , jsonArray);
    }
    /**
    * 买家中心订单状态
    * @author      ShiLinghuai
    * @param
    * @return      
    * @exception   
    * @date        2018/10/10 16:08
    */
    @RequestMapping(value = "demand_getOrderStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getOrderStatus(Demand demand) {
        if(demand.getDemandSn()==null){
            return new ResultTemplate("参数错误，没有传需求ID！", null);
        }
        Demand demandS = demandServicesI.getByDemandSn(demand.getDemandSn());
        boolean isCallForParper = false;
        if(demandS.getDemandTypeIndex()==EnumDemandType.原创征稿.getIndex()){
            isCallForParper = true;
        }
        return new ResultTemplate("", EnumOrderBuyerStatus.getMapInfoMy(isCallForParper));
    }
    /**
     * 买家中心报名状态
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/10/10 16:08
     */
    @RequestMapping(value = "demand_getApplyStatus", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getApplyStatus(Demand demand) {
        if(demand.getDemandSn()==null){
            return new ResultTemplate("参数错误，没有传需求ID！", null);
        }
        Demand demandS = demandServicesI.getByDemandSn(demand.getDemandSn());
        boolean isCallForParper = false;
        if(demandS.getDemandTypeIndex()==EnumDemandType.原创征稿.getIndex()){
            isCallForParper = true;
        }
        return new ResultTemplate("", EnumOrderSellerStatus.getMapInfoMy(isCallForParper));
    }
    /**
     * 拒绝购买
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/10/10 16:08
     */
    @RequestMapping(value = "demand_updateStauts", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate updateStauts(OrderInfoSeller orderInfoSeller,HttpSession session) {
        if (orderInfoSeller.getOrderSn() == null||orderInfoSeller.getOrderStatusValue() == null) {
            return new ResultTemplate("传参错误", null);
        }
        orderInfoSeller.setWebUser(getWebUser(session));
        orderInfoSellerServicesI.updateEntity(orderInfoSeller);
        return new ResultTemplate("", null);
    }
}
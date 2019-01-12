package com.yuanrong.admin.server.controller.order;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumPayStatus;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.result.OrderInfoBuyerDetailResult;
import com.yuanrong.admin.result.OrderInfoBuyerListResult;
import com.yuanrong.admin.seach.OrderInfoBuyerListSearch;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.common.util.SystemParam;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

/**
 * 买家订单的controller
 * Created MDA
 */
@Controller
@RequestMapping("order")
public class OrderInfoBuyerController extends BaseController {
    private static final Logger logger = Logger.getLogger(OrderInfoBuyerController.class);

    /**
     * 后台—作品购买列表
     * @param data
     * @param baseModel
     * @return
     */
    @RequestMapping( value = "orderInfoBuyer_list" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(OrderInfoBuyerListSearch data , BaseModel baseModel){
        PageInfo<OrderInfoBuyerListResult> orderInfoBuyerPageInfo = orderInfoBuyerServicesI.buyOrderList(data , baseModel);
        JSONArray result = new JSONArray();
        JSONObject jsonNum = new JSONObject();
        if(CollectionUtil.size(orderInfoBuyerPageInfo.getList()) > 0){
            for (OrderInfoBuyerListResult orderInfo : orderInfoBuyerPageInfo.getList()){
                JSONObject jsonObject = OrderInfoBuyerListResult.packageOrderBuyInfo(orderInfo);
                result.add(jsonObject);
            }
        }
        //金额统计
        List<OrderInfoBuyerListResult> orderList = orderInfoBuyerServicesI.buyOrderList(data);
        if(CollectionUtil.size(orderList) > 0){
            BigDecimal costMoney = new BigDecimal("0");
            BigDecimal invoiceMoney = new BigDecimal("0");
            for (OrderInfoBuyerListResult infoResult : orderList){
                //订单金额累加
                BigDecimal costPrice = infoResult.getOrderDetail() == null ? new BigDecimal("0") : infoResult.getOrderDetail().getPrice();
                costMoney = costMoney.add(costPrice);
                //税额累加
                if(StringUtil.isNotBlank(infoResult.getMoneyDetail())){
                    String[] split = infoResult.getMoneyDetail().split("-_-");
                    for (int i =0;i<split.length;i++){
                        if(split[i].split("_-_").length > 0){
                            String[] cost = split[i].split("_-_");
                            Integer costId = Integer.valueOf(cost[1]);
                            if("1".equals(cost[0])){//买家
                                if(costId == 242){//税额
                                    invoiceMoney = invoiceMoney.add(new BigDecimal(cost[2]));
                                }
                            }
                        }
                    }
                }
            }
            jsonNum.put("costMoney",costMoney);
            jsonNum.put("invoiceMoney",invoiceMoney);
            jsonNum.put("sumMoney",costMoney.add(invoiceMoney));
        }
        return new ResultTemplate(orderInfoBuyerPageInfo,result,jsonNum);
    }

    /**
     * 获取支付状态
     */
    @RequestMapping("exs_getPayStatus")
    @ResponseBody
    public ResultTemplate getPayStatus(){
        return new ResultTemplate("", EnumPayStatus.getMapInfo());
    }

    /**
     * 后台—买家订单详情（作品购买）
     * @param orderInfoBuyerId -(订单号)
     * @param baseModel
     * @return
     */
    @RequestMapping( value = "orderInfoBuyer_detail" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate buyerDetailList(Integer orderInfoBuyerId, BaseModel baseModel){

        if(orderInfoBuyerId == null){
            return new ResultTemplate("作品订单有误",null);
        }
        OrderInfoBuyer buyerOrderInfo = orderInfoBuyerServicesI.findById(orderInfoBuyerId);
        if(buyerOrderInfo == null){
            return new ResultTemplate("作品订单信息不存在",null);
        }
        PageInfo<OrderInfoBuyerDetailResult> orderInfoSellerPageInfo = orderInfoSellerServicesI.orderInfoBuyerDetailList(orderInfoBuyerId , baseModel);
        JSONObject result = new JSONObject();
        if(CollectionUtil.size(orderInfoSellerPageInfo.getList()) > 0 ){
            String invoicePercent = configurationServicesI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER);
            String serviceFee = configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_SELLER_PERCENT);
            result =OrderInfoBuyerDetailResult.packageOrderSellerInfo(orderInfoSellerPageInfo.getList(),invoicePercent);
        }

        result.put("buyerOrderInfo",OrderInfoBuyer.packageOrderBuyerInfo(buyerOrderInfo));
        return new ResultTemplate(orderInfoSellerPageInfo , result);
    }

    /**
     * 删除买家订单—假删
     * @param orderInfoBuyerId
     * @return
     */
    @RequestMapping(value = "deleteBuyOrder",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate deleteBuyOrder(Integer orderInfoBuyerId){
        if(orderInfoBuyerId == null){
            return new ResultTemplate("订单Id不能为空");
        }
        if(orderInfoBuyerServicesI.getById(orderInfoBuyerId) == null){
            return new ResultTemplate("订单信息不存在");
        }
        orderInfoBuyerServicesI.deleteBuyOrder(orderInfoBuyerId);
        return new ResultTemplate();
    }
}

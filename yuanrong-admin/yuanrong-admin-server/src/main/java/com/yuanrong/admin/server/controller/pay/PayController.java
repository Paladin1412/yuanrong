package com.yuanrong.admin.server.controller.pay;

import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.parameter.MergePay;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.RequestUtil;
import com.yuanrong.common.util.ResultTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhonghang on 2018/10/11.
 */
@Controller
@RequestMapping("pay")
public class PayController extends BaseController {

    @RequestMapping(value = "mergePayOrder/qrCode",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate mergePayOrder(OrderInfoBuyer orderInfoBuyer){
        if(CollectionUtil.size(orderInfoBuyer.getOrderSns()) == 0){
            new ResultTemplate("订单号错误，请选择订单后再支付");
        }
        MergePay mergePay = orderInfoBuyerServicesI.saveMergePayOrder(orderInfoBuyer.getOrderSns(), RequestUtil.getIpAddress(request),null);
        try {
            WxPayUnifiedOrderResult wxPayUnifiedOrderResult = payServiceI.unifiedOrder(mergePay.getOrderRequest());
            JSONObject object = new JSONObject();
            object.put("codeUrl" , wxPayUnifiedOrderResult.getCodeURL());
            object.put("paySn",mergePay.getOrderRequest().getOutTradeNo());
            object.put("totalMoney" , mergePay.getTotalMoney());
            object.put("systemNow" , DateUtil.getNowDateTime());
            return new ResultTemplate(object);
        } catch (WxPayException e) {
            e.printStackTrace();
            return new ResultTemplate(e.getErrCodeDes());
        }

    }
}

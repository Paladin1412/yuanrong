package com.yuanrong.admin.mall.controller.pay;

import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.yuanrong.admin.Enum.EnumPayStatus;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.bean.pay.PayWater;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.parameter.MergePay;
import com.yuanrong.admin.result.wxPay.WxPayOrder;
import com.yuanrong.admin.rpc.exception.YRParamterException;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.RequestUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhonghang on 2018/7/11.
 */
@Controller
@RequestMapping("pay")
public class PayController extends BaseController {
    private static final Logger logger = Logger.getLogger(PayController.class);
    @RequestMapping("wx/notify")
    @ResponseBody
    public String wxNotify(){
        logger.info("==========微信支付回调=========");
        try {
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            WxPayOrderNotifyResult result = payServiceI.parseOrderNotifyResult(xmlResult);
//            WxPayOrderNotifyResult result = new WxPayOrderNotifyResult();
//            result.setOutTradeNo("185971734648451072");
//            result.setTotalFee(200);
            try {
                payWaterServicesI.savePayWater(result);
            }catch (com.yuanrong.admin.rpc.exception.WxPayException e){
                if(e.getCode().equals("500")){
                    logger.error("微信回调结果异常,异常原因{}", e);
                    return WxPayNotifyResponse.fail(e.getMessage());
                }
            }
            return WxPayNotifyResponse.success("处理成功!");
        } catch (Exception e) {
            logger.error("微信回调结果异常,异常原因{}", e);
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }
    /**
     * <pre>
     * 查询订单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2)
     * 该接口提供所有微信支付订单的查询，商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。
     * 需要调用查询接口的情况：
     * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
     * ◆ 调用支付接口后，返回系统错误或未知交易状态情况；
     * ◆ 调用被扫支付API，返回USERPAYING的状态；
     * ◆ 调用关单或撤销接口API之前，需确认支付状态；
     * 接口地址：https://api.mch.weixin.qq.com/pay/orderquery
     * </pre>
     *
     * @param transactionId 微信订单号
     * @param orderSn    商户系统内部的订单号，当没提供transactionId时需要传这个。
     */
    @RequestMapping("queryOrder")
    @ResponseBody
    public ResultTemplate queryOrder(String transactionId, String orderSn){
        if(StringUtil.isBlank(orderSn)){
            return new ResultTemplate("订单号为空" , null);
        }
        try {
            WxPayOrder result = this.payServiceI.queryOrder(transactionId, orderSn);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("outTradeNo" , result.getOutTradeNo());
            jsonObject.put("tradeState" , result.getTradeState()); //NOTPAY  SUCCESS
            return new ResultTemplate("",jsonObject);
        } catch (WxPayException e) {
            e.printStackTrace();
            return new ResultTemplate(e.getErrCodeDes(),null);
        }

    }

    /**
     * <pre>
     * 扫码支付模式二生成二维码的方法
     * 对应链接格式：weixin：//wxpay/bizpayurl?sr=XXXXX。请商户调用第三方库将code_url生成二维码图片。
     * 该模式链接较短，生成的二维码打印到结账小票上的识别率较高。
     * 文档详见: https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_5
     * </pre>
     *商户logo图片的文件对象，可以为空
     * @param codeUrl    微信返回的交易会话的二维码链接
     * @param sideLength 要生成的二维码的边长，如果为空，则取默认值400
     * @return 生成的二维码的字节数组
     */
    @RequestMapping("wx/createScanPayQrcodeMode")
    public void createScanPayQrcodeMode2(String codeUrl, Integer sideLength) {
        byte[] bytes = this.payServiceI.createScanPayQrcodeMode(codeUrl, sideLength);
        try {
            response.setContentType("image/png");
            OutputStream stream = response.getOutputStream();
            stream.write(bytes);
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 统一下单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1)
     * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
     * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
     *请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
     */
    @RequestMapping("/unifiedOrder.html")
    public String unifiedOrder(OrderInfoBuyer orderInfoBuyer, HttpServletRequest request) {
        if(CollectionUtil.size(orderInfoBuyer.getOrderSns()) == 0){
            return errorPage("订单号错误，请选择订单后再支付");
        }

        try {
            MergePay mergePay = orderInfoBuyerServicesI.saveMergePayOrder(orderInfoBuyer.getOrderSns(), RequestUtil.getIpAddress(request),getWebUser().getRecID());
            WxPayUnifiedOrderResult wxPayUnifiedOrderResult = payServiceI.unifiedOrder(mergePay.getOrderRequest());
            request.setAttribute("wxPayUnifiedOrderResult" , wxPayUnifiedOrderResult);
            request.setAttribute("mergePay" , mergePay);
            request.setAttribute("systemNow" , DateUtil.getNowDateTime());
        } catch (WxPayException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            return errorPage(e.getErrCodeDes());
        }catch (YRParamterException e){
            return errorPage(e.getMessage());
        }

        return "pay/unifiedOrder";
    }

    @RequestMapping("paySuccess")
    public String paySuccess(String orderSn ,HttpServletRequest request){
//        List<OrderInfoBuyer>  orderInfoBuyers = StringUtil.isBlank(orderSn) ? null : orderInfoBuyerServicesI.getByPayOrderSn(orderSn);
        PayWater payWater =  payWaterServicesI.getByOutTradeNo(orderSn);
        if(payWater == null){
            return errorPage("支付失败。如果已支付，请稍后在订单中心查询支付状态");
        }
        request.setAttribute("payWater",payWater);
        return "pay/paySuccess";
    }
    @RequestMapping("orderConfirm.html")
    public String orderConfirm(){
        return "pay/orderConfirm";
    }
}

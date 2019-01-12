package com.yuanrong.admin.rpc.api.pay;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.result.wxPay.WxPayOrder;

/**
 * Created by zhonghang on 2018/7/11.
 * 微信支付
 */
public interface PayServiceI {
    /**
     * 获取微信的支付类
     * @return
     */
    public WxPayService getWxPayService();

    /**
     * 下单接口
     * @param orderRequest
     */
    WxPayUnifiedOrderResult unifiedOrder(WxPayUnifiedOrderRequest orderRequest) throws WxPayException;

    /**
     * 生成二维码
     * @param codeUrl
     * @param sideLength
     * @return
     */
    byte[] createScanPayQrcodeMode(String codeUrl, Integer sideLength);
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
     * @param outTradeNo    商户系统内部的订单号，当没提供transactionId时需要传这个。
     */
    WxPayOrder queryOrder(String transactionId , String outTradeNo) throws WxPayException;

    /**
     * 微信回调，转换
     * @param xmlResult
     * @return
     */
    WxPayOrderNotifyResult parseOrderNotifyResult(String xmlResult) throws WxPayException;
}

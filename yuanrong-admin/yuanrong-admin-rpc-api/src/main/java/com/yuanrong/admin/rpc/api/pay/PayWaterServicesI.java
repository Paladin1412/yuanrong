package com.yuanrong.admin.rpc.api.pay;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.yuanrong.admin.bean.pay.PayWater;
import com.yuanrong.admin.rpc.BaseServicesI;
/**
 * 支付流水的services接口
 * Created MDA
 */
public interface PayWaterServicesI extends BaseServicesI<PayWater> {
    /**
     * 微信支付成功保存流水信息
     */
    void savePayWater(WxPayOrderNotifyResult result);

    /**
     * 通过业务号查询支付流水
     * @param outTradeNo
     * @return
     */
    PayWater getByOutTradeNo(String outTradeNo);
}

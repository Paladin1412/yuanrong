package com.yuanrong.admin.rpc.service.impl.pay;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.yuanrong.admin.result.wxPay.WxPayOrder;
import com.yuanrong.admin.rpc.api.pay.PayServiceI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.rpc.service.impl.config.ConfigProfile;
import org.springframework.stereotype.Service;


/**
 * Created by zhonghang on 2018/7/11.
 */
@Service
public class PayServicesImpl extends BaseServicesAbstract implements PayServiceI {

    @Override
    public WxPayService getWxPayService() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(ConfigProfile.WX_PAY_APP_ID);
        payConfig.setMchId(ConfigProfile.WX_PAY_MCH_ID);
        payConfig.setNotifyUrl(ConfigProfile.WX_PAY_NOTIFY_URL);
//        payConfig.setKeyPath(WXConfig.WX_PAY_API_KEY);
        payConfig.setMchKey(ConfigProfile.WX_PAY_API_KEY);
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }

    @Override
    public WxPayUnifiedOrderResult unifiedOrder(WxPayUnifiedOrderRequest orderRequest) throws WxPayException {
        return getWxPayService().unifiedOrder(orderRequest);
    }

    @Override
    public byte[] createScanPayQrcodeMode(String codeUrl, Integer sideLength) {
//        String logoPath = this.getClass().getClassLoader().getResource("logo.jpg").getPath();
//        File logo = new File(logoPath);
        return getWxPayService().createScanPayQrcodeMode2(codeUrl, null, sideLength);
    }

    @Override
    public WxPayOrder queryOrder(String transactionId, String outTradeNo) throws WxPayException {
        WxPayOrderQueryResult result = getWxPayService().queryOrder(transactionId, outTradeNo);
        return new WxPayOrder(result.getOutTradeNo(),result.getTradeState());
    }

    @Override
    public WxPayOrderNotifyResult parseOrderNotifyResult(String xmlResult) throws WxPayException {
        return getWxPayService().parseOrderNotifyResult(xmlResult);
    }
}

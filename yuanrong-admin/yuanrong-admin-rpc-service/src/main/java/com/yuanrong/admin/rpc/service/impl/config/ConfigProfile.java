package com.yuanrong.admin.rpc.service.impl.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by zhonghang on 2018/8/3.
 * 自定义配置项
 */
@Component
public class ConfigProfile  implements InitializingBean {
    Logger logger = Logger.getLogger(ConfigProfile.class);
    @Value("${wx.corpId}")
    private String wxCorpId;
    /**
     *  程序通知应用
     */
    @Value("${wx.notice.AgentId}")
    private Integer wxNoticeAgentId;
    @Value("${wx.notice.Secret}")
    private String wxNoticeSecret;
    /**
     * 正式环境BUG应用
     */
    @Value("${wx.bug.AgentId}")
    private Integer wxBugAgentId;
    @Value("${wx.bug.Secret}")
    private String wxBugSecret;

    //支付信息
    @Value("${wx.pay.appid}")
    private String wxPayAPPID;
    @Value("${wx.pay.app.secret}")
    private String wxPayAppSecret;
    @Value("${wx.pay.mch.id}")
    private String wxPayMchId;
    @Value("${wx.pay.api.key}")
    private String wxPayApiKey;
    @Value("${wx.pay.notify.url}")
    private String wxPayNotifyUrl;

    public static String WX_CROP_ID;
    public static Integer WX_NOTICE_AGENTID;
    public static String WX_NOTICE_SECRET;
    public static Integer WX_BUG_AGENTID;
    public static String WX_BUG_SECRET;

    public static String WX_PAY_APP_ID;
    public static String WX_PAY_APP_SECRET;
    public static String WX_PAY_MCH_ID; //商户号（改为自己实际的）
    public static String WX_PAY_API_KEY; //（改为自己实际的）key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
    public static String WX_PAY_NOTIFY_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("初始化自定义配置文件");
        WX_CROP_ID = wxCorpId;
        WX_NOTICE_AGENTID = wxNoticeAgentId;
        WX_NOTICE_SECRET = wxNoticeSecret;
        WX_BUG_AGENTID = wxBugAgentId;
        WX_BUG_SECRET = wxBugSecret;

        //支付
        WX_PAY_APP_ID = wxPayAPPID;
        WX_PAY_APP_SECRET = wxPayAppSecret;
        WX_PAY_MCH_ID = wxPayMchId;
        WX_PAY_API_KEY = wxPayApiKey;
        WX_PAY_NOTIFY_URL = wxPayNotifyUrl;
    }




}

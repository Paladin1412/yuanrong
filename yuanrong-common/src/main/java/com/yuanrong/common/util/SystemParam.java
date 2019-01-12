package com.yuanrong.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhonghang on 2018/5/8.
 * 系统变量
 */
public final class SystemParam {
    /**
     * 请求平台数据的URL
     */
    public static String  GET_PLATFORMIPACCOUNT_INFO_URL = "GET_PLATFORMIPACCOUNT_INFO_URL";
    /**
     * 请求抓文章的代码形式url
     */
    public static String  PRODUCTION_CODE_URL = "http://192.168.121.110:8080/getDetailContent/";
    /**
     * 随机分配的销售id
     */
    public static String SALE_ID= "SALE_ID";
    /**
     * 随机分配的媒介id
     */
    public static String MEDIA_ID = "MEDIA_ID";
    /**
     * 微信平台名称
     */
    public static String WeChat_Platform ="weixin";
    /**
     * 暂时配置的图文的形式id
     */
    public static Integer CONTENTFORMARTICLE = 1;

    /**
     * bug访问地址
     */
    public static String BUG_SERVER_URL = "BUG_SERVER_URL";

    public static String QINIU_DOMAIN = "QINIU_DOMAIN";
    public static String QINIU_ACCESSKEY = "QINIU_ACCESSKEY";
    public static String QINIU_SECRETKEY = "QINIU_SECRETKEY";
    public static String QINIU_BUCKET = "QINIU_BUCKET";

    /**
     * 买家发票税率-作品
     */
    public static String INVOICE_PERCENT_BUYER = "INVOICE_PERCENT_BUYER";

    /**
     * 卖家开发票比例
     */
    public static String INVOICE_PERCENT_SELLER = "INVOICE_PERCENT_SELLER";

    /**
     * 买家服务费比例-作品
     */
    public static String SERVICES_FEE_BUYER_PERCENT = "SERVICES_FEE_BUYER_PERCENT";

    /**
     * 卖家服务费比例-作品
     */
    public static String SERVICES_FEE_SELLER_PERCENT = "SERVICES_FEE_SELLER_PERCENT";
    /**
     * 订单支付有效时间（分钟）
     */
    public static String BUYER_ORDER_PAY_TEIM = "BUYER_ORDER_PAY_TEIM";

    /**
     * 重力因子（作者，作品计算排序分数是用到的）
     */
    public static String GRAVITYTH_POWER = "GRAVITYTH_POWER";
    /**
     * 买家定制内容服务费
     */
    public static String SERVICES_FEE_BUYER_AUTHOR = "SERVICES_FEE_BUYER_AUTHOR";
    /**
     * 买家营销账号服务费
     */
    public static  String SERVICES_FEE_BUYER_ACCOUNT = "SERVICES_FEE_BUYER_ACCOUNT";

    /**
     * 购物车最大加入数量
     */
    public static String SHOPPING_CART_MAX_NUM = "SHOPPING_CART_MAX_NUM";
    /**
     * 卖家营销账号服务费
     */
    public static String SERVICES_FEE_SELLER_ACCOUNT = "SERVICES_FEE_SELLER_ACCOUNT";
    /**
     * 卖家定制内容服务费
     */
    public static String SERVICES_FEE_SELLER_AUTHOR = "SERVICES_FEE_SELLER_AUTHOR";
    /**
     * 买家营销账号发票税率
     */
    public static String INVOICE_PERCENT_BUYER_ACCOUNT = "INVOICE_PERCENT_BUYER_ACCOUNT";
    /**
     * 买家定制内容发票税率
     */
    public static String INVOICE_PERCENT_BUYER_AUTHOR = "INVOICE_PERCENT_BUYER_AUTHOR";
    /**
     * 卖家征稿服务费
     */
    public static String SERVICES_FEE_SELLER_COLLECTION = "SERVICES_FEE_SELLER_COLLECTION";
    /**
     * 买家征稿服务费
     */
    public static String SERVICES_FEE_BUYER_COLLECTION = "SERVICES_FEE_BUYER_COLLECTION";
    /**
     * 买家征稿发票税率
     */
    public static String INVOICE_PERCENT_BUYER_COLLECTION = "INVOICE_PERCENT_BUYER_COLLECTION";
}

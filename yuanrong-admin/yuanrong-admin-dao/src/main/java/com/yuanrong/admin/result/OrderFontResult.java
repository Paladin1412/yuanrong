package com.yuanrong.admin.result;

import com.yuanrong.admin.bean.order.OrderInfoSeller;

import java.io.Serializable;

public class OrderFontResult extends OrderInfoSeller implements Serializable {
    /**
     * 账号名称，中文
     * 账号名称，中文
     */
    private String name;

    /**
     * 账号名id-微信号，微博号
     * 账号名id-微信号，微博号
     */
    private String accountID;

    /**
     * 平台名字
     * 平台名字
     */
    private String platformName;

    /**
     * 平台logo
     * 平台logo
     */
    private String platformLogo;

    /**
     * 头像
     * 头像
     */
    private String headImageUrlLocal;

    /**
     * 二维码
     * 二维码
     */
    private String qRcodeImageUrlLocal;

    /**
     * 账号简介
     * 账号简介
     */
    private String introduction;

    /**
     * 账号分类
     * 账号分类
     */
    private String categoryName;

    /**
     * 账号报价
     * 账号报价
     */
    private String priceInfo;

    /**
     * 有效期
     * 有效期
     */
    private String invalidTime;

    /**
     * 平台唯一标识
     * 平台唯一标识
     */
    private String pid;

    /**
     * 作者昵称
     * 作者昵称
     */
    private String authorNickname;

    /**
     * 作者头像
     * 作者头像
     */
    private String authorImg;



    /**
     * 作者Id
     * 作者Id
     */
    private Integer yrAuthorId;
}

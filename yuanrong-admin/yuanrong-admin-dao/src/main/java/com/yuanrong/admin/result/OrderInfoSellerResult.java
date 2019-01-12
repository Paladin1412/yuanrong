package com.yuanrong.admin.result;

import com.yuanrong.admin.bean.order.OrderInfoSeller;

/**
 * Created by zhonghang on 2018/7/20.
 */
public class OrderInfoSellerResult extends OrderInfoSeller {
    /**
     * 投稿报价项
     */
    private String enrollOfferPrice;

    public String getEnrollOfferPrice() {
        return enrollOfferPrice;
    }

    public void setEnrollOfferPrice(String enrollOfferPrice) {
        this.enrollOfferPrice = enrollOfferPrice;
    }
}

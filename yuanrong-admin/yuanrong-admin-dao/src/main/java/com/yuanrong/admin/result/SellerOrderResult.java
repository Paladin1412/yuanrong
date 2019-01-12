package com.yuanrong.admin.result;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.bean.order.SellerOrder;
import com.yuanrong.common.util.StringUtil;

/**
 * Created by zhonghang on 2018/10/10.
 */
public class SellerOrderResult extends SellerOrder {

    /**
     * 报价项
     */
    private String offerPrice;

    public String getOfferPrice() {
        return offerPrice;
    }

    public JSONArray getJSONOfferPrice() {
        JSONArray jsonArray = new JSONArray();
        if(StringUtil.isNotBlank(offerPrice)){
            String[] priceInfos = offerPrice.split("_\\*_");
            for(String priceInfo : priceInfos){
                String[] priceDetail = priceInfo.split("_-_");
                JSONObject price = new JSONObject();
                price.put("price" ,priceDetail[1] );
                price.put("priceName" ,priceDetail[0] );
                jsonArray.add(price);
            }
        }
        return jsonArray;
    }

    public static JSONArray getJSONOfferPriceByString( String offerPrice){
        SellerOrderResult sellerOrderResult = new SellerOrderResult();
        sellerOrderResult.setOfferPrice(offerPrice);
        return sellerOrderResult.getJSONOfferPrice();
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }
}

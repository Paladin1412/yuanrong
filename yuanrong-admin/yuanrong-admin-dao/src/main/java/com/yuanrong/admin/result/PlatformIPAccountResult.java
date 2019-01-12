package com.yuanrong.admin.result;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumYesOrNo;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.StringUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zhonghang on 2018/6/1.
 * 结果集类
 */
public class PlatformIPAccountResult extends PlatformIPAccount implements Serializable{
    /**
     * 报价信息
     */
    private String priceInfo;
    /**
     * 是否加入购物车
     */
    private Integer isAddCart;
    private EnumYesOrNo isAddCartEnum;

    public PlatformIPAccountResult() {
    }
    public PlatformIPAccountResult(String priceInfo) {
        this.priceInfo = priceInfo;
    }

    public String getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(String priceInfo) {
        this.priceInfo = priceInfo;
    }

    public JSONArray getPriceInfoJSONArray(){
        Map<String , JSONArray> priceInfoMap = new TreeMap<String, JSONArray>();
        if(StringUtil.isNoneBlank(this.priceInfo)){
            String[] price = this.priceInfo.split("-_-");
            for(String pr : price){
                String[] priceTemp = pr.split("_-_");
                JSONObject prInfo = new JSONObject();
                //价格名称
                prInfo.put("position" ,priceTemp[0] );
                //价格
                prInfo.put("Price" ,priceTemp[1] );
                //是否原创
                prInfo.put("isOriginal" ,priceTemp[2]);
                if(priceInfoMap.containsKey(priceTemp[5])){
                    priceInfoMap.get(priceTemp[5]).add(prInfo);
                } else{
                    JSONArray pricesPlat = new JSONArray();
                    pricesPlat.add(prInfo);
                    priceInfoMap.put(priceTemp[5] , pricesPlat);
                }

            }
        }
        JSONArray result = new JSONArray();
        for(Map.Entry ele : priceInfoMap.entrySet()){
            JSONObject object = new JSONObject();
            object.put("platformName" ,ele.getKey());
            object.put("positionPrice" , ele.getValue());
            result.add(object);
        }

        return result;
    }

    public Integer getIsAddCart() {
        return isAddCart;
    }

    public void setIsAddCart(Integer isAddCart) {
        this.isAddCart = isAddCart;
        this.isAddCartEnum = (EnumYesOrNo) EnumUtil.valueOf(EnumYesOrNo.class , isAddCart);
    }

    public EnumYesOrNo getIsAddCartEnum() {
        return isAddCartEnum;
    }

    public void setIsAddCartEnum(EnumYesOrNo isAddCartEnum) {
        this.isAddCartEnum = isAddCartEnum;
        this.isAddCart = isAddCartEnum.getIndex();
    }
    /**
     * 包账号
     * @param ele
     * @return
     */
    public static JSONObject mapParerPlatformAgent(PlatformIPAccountResult ele){
        JSONObject jsonObject = new JSONObject();
        //账号-系统ID
        jsonObject.put("id" , ele.getId());
        //账号-账号名
        jsonObject.put("name" , ele.getName());
        //账号-账号名id
        jsonObject.put("accountID" , ele.getAccountID());
        //平台id
        jsonObject.put("platformID" , ele.getPlatformID());
        //平台图标
        jsonObject.put("icoUrl" , ele.getShortVideoPlatformInfo()==null?null:ele.getShortVideoPlatformInfo().getIcoUrl());
        jsonObject.put("headImageUrlLocal" , ele.getHeadImageUrlLocal());
        //行业分类
        jsonObject.put("categoryName" , ele.getDictInfoCategory()==null?null:ele.getDictInfoCategory().getName());
        //数据展示
        jsonObject.put("avgReadCount" , ele.getAvgReadCount());
        //	粉丝数
        jsonObject.put("fans" , ele.getFans());
        //类别
        jsonObject.put("categoryID" , ele.getCategoryID());
        jsonObject.put("agentCoopBrand" , ele.getAgentCoopBrand());
        jsonObject.put("introduction" , ele.getIntroduction());
        jsonObject.put("avgForwardCount" , ele.getAvgForwardCount());
        jsonObject.put("avgPlayCount" , ele.getAvgPlayCount());
        jsonObject.put("avgLikeCount" , ele.getAvgLikeCount());
        return jsonObject;
    }
}

package com.yuanrong.admin.Enum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 *@author songwq
 *@date 2018/6/28
 *@time 17:55
 *@description
 */
public enum EnumOrderSellerStatus implements IntegerValuedEnum{
    卖家拒约("卖家拒约",1,"卖家拒约"),待卖家响应("待卖家响应",2,"待卖家响应"),待买家确认("待买家确认",3, "待买家确认"),
    买家拒绝("买家拒绝",8,"买家拒绝"),已生成订单("已生成订单",9,"已生成订单");

    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumOrderSellerStatus(String name, int index, String description) {
        this.name = name;
        this.index = index;
        this.description = description;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<Map<String , Object>> getMapInfo(){
        List<Map<String , Object>> result = new ArrayList();

        for(EnumOrderSellerStatus type : EnumOrderSellerStatus.values()){
            Map<String , Object> ele = new TreeMap<String, Object>();
            ele.put("id" , type.getIndex());
            ele.put("name" , type.getName());
            result.add(ele);
        }
        return result;
    }


    public static List<Map<String , Object>> getPartMapInfo(){
        List<Map<String , Object>> result = new ArrayList();
        for(EnumOrderSellerStatus type : EnumOrderSellerStatus.values()){
            if(type.getIndex()>=1 && type.getIndex()<=7){
                Map<String , Object> ele = new TreeMap<String, Object>();
                ele.put("id" , type.getIndex());
                ele.put("name" , type.getName());
                result.add(ele);
            }
        }
        return result;
    }
    public static List<Map<String , Object>> getMapInfoMy(boolean isCallForParper){
        List<Map<String, Object>> result = new ArrayList();
        for (EnumOrderSellerStatus type : EnumOrderSellerStatus.values()) {
            if ((type.getIndex() >= 1 && type.getIndex() <= 3) || (type.getIndex() >= 8 && type.getIndex() <= 9)) {
                if(isCallForParper&&(type.getIndex()==2||type.getIndex() ==1)){

                }else {
                    Map<String, Object> ele = new TreeMap<String, Object>();
                    ele.put("id", type.getIndex());
                    ele.put("name", type.getName());
                    result.add(ele);
                }
            }
        }
        return result;
    }
}

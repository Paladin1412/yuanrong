package com.yuanrong.admin.Enum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 *@author songwq
 *@date 2018/6/28
 *@time 16:06
 *@description
 */
public enum EnumOrderBuyerStatus implements IntegerValuedEnum{

    待付款("待付款",1, "待付款"), 已完成("已完成",2,"已完成"), 已取消("已取消",3, "已取消"),
    待买家验收("待买家验收",4,"待买家验收"),待卖家执行("待卖家执行", 5, "待卖家执行");
    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumOrderBuyerStatus(String name, int index, String description) {
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

        for(EnumOrderBuyerStatus type : EnumOrderBuyerStatus.values()){
            Map<String , Object> ele = new TreeMap<String, Object>();
            ele.put("id" , type.getIndex());
            ele.put("name" , type.getName());
            result.add(ele);
        }
        return result;
    }
    public static List<Map<String , Object>> getMapInfoMy(Boolean isCallForParper){
        List<Map<String , Object>> result = new ArrayList();

        for(EnumOrderBuyerStatus type : EnumOrderBuyerStatus.values()){
            if(isCallForParper&&type.getIndex()==4){

            }else {
                Map<String , Object> ele = new TreeMap<String, Object>();
                ele.put("id" , type.getIndex());
                ele.put("name" , type.getName());
                result.add(ele);
            }

        }
        return result;
    }
}

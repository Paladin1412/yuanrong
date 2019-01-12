package com.yuanrong.admin.Enum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 卖家订单状态
 */
public enum EnumSellerOrderStatus implements IntegerValuedEnum  {
    待卖家执行("待卖家执行", 1, "待卖家执行"),已完成 ("已完成", 2, "已完成"),已取消("已取消",3,"已取消"),
    待买家验收("待买家验收",4,"待买家验收"),待买家付款("待买家付款", 5, "待买家付款");

    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumSellerOrderStatus(String name, int index, String description) {
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

        for(EnumSellerOrderStatus type : EnumSellerOrderStatus.values()){
            Map<String , Object> ele = new TreeMap<String, Object>();
            ele.put("id" , type.index);
            ele.put("name" , type.name);
            result.add(ele);
        }
        return result;
    }
}

package com.yuanrong.admin.Enum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 基本枚举类型，确认订单来源类型
 */
public enum EnumOrderSure implements IntegerValuedEnum {
    从报名表("从报名表",1,"从报名表"),从立即下单("从立即下单",2,"从立即下单"),从购物车("从购物车",3,"从购物车");

    // 成员变量
    private String name;

    private int index;

    private String description;

    EnumOrderSure(String name, int index, String description) {
        this.name = name;
        this.index = index;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
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

    public static List<Map<String, Object>> getMapInfo(){
        List<Map<String, Object>> result = new ArrayList();

        for(EnumOrderSure type: EnumOrderSure.values()){
            Map<String , Object> ele = new TreeMap<String, Object>();
            ele.put("id" , type.index);
            ele.put("name" , type.name);
            result.add(ele);
        }
        return result;
    }
}

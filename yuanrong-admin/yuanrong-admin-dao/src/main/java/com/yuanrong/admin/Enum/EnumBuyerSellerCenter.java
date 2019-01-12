package com.yuanrong.admin.Enum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public enum EnumBuyerSellerCenter implements IntegerValuedEnum  {
    买家中心("买家中心", 0, "买家中心"),卖家中心 ("卖家中心", 1, "卖家中心");

//    账号停用("账号停用", 0, "账号停用"),账号可用("账号可用", 1, "账号可用");

    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumBuyerSellerCenter(String name, int index, String description) {
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

        for(EnumBuyerSellerCenter type : EnumBuyerSellerCenter.values()){
            Map<String , Object> ele = new TreeMap<String, Object>();
            ele.put("id" , type.index);
            ele.put("name" , type.name);
            result.add(ele);
        }
        return result;
    }
}

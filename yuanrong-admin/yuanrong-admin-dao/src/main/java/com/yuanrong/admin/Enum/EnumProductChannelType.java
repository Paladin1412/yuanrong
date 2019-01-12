package com.yuanrong.admin.Enum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 *@author songwq
 *@date 2018/6/28
 *@time 17:54
 *@description
 */
public enum EnumProductChannelType implements IntegerValuedEnum{
    作品订单("作品订单",1,"作品订单"),原创征稿("原创征稿",4,"原创征稿");
    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumProductChannelType(String name, int index, String description) {
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

        for(EnumProductChannelType type : EnumProductChannelType.values()){
            Map<String , Object> ele = new TreeMap<String, Object>();
            ele.put("id" , type.getIndex());
            ele.put("name" , type.getName());
            result.add(ele);
        }
        return result;
    }
}

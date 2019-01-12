package com.yuanrong.admin.Enum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public enum EnumDemandFastStatus implements IntegerValuedEnum  {
    待处理("待处理", 1, "待处理"),已接收("已接收", 2, "已接收"),
    已拒绝("已拒绝", 3, "已拒绝");

    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumDemandFastStatus(String name, int index, String description) {
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

        for(EnumDemandFastStatus type : EnumDemandFastStatus.values()){
            Map<String , Object> ele = new TreeMap<String, Object>();
            ele.put("id" , type.index);
            ele.put("name" , type.name);
            result.add(ele);
        }
        return result;
    }
}

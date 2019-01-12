package com.yuanrong.admin.Enum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public enum EnumDemandStatus implements IntegerValuedEnum  {
    待审核("待审核", 1, "待审核"),处理中("处理中", 2, "处理中"),
    待推荐("待推荐", 3, "待推荐"),已完成("已完成", 4, "已完成"),已取消("已取消", 5, "已取消"),
    /*拒绝需求("拒绝需求",7 , "拒绝需求"),*/完成推荐("完成推荐",6, "完成推荐");

    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumDemandStatus(String name, int index, String description) {
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

        for(EnumDemandStatus type : EnumDemandStatus.values()){
            Map<String , Object> ele = new TreeMap<String, Object>();
            ele.put("id" , type.index);
            ele.put("name" , type.name);
            result.add(ele);
        }
        return result;
    }
}

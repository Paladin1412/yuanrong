package com.yuanrong.admin.Enum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 *@author songwq
 *@date 2018/6/28
 *@time 16:15
 *@description
 */
public enum EnumPayStatus implements IntegerValuedEnum{
    待支付("待支付",1,"待支付"),已支付("已支付",2,"已支付"),支付失败("支付失败",3,"付失败"),已失效("已失效",4,"已失效");

    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumPayStatus(String name, int index, String description) {
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

        for(EnumPayStatus type : EnumPayStatus.values()){
            Map<String , Object> ele = new TreeMap<String, Object>();
            ele.put("id" , type.index);
            ele.put("name" , type.name);
            result.add(ele);
        }
        return result;
    }
}

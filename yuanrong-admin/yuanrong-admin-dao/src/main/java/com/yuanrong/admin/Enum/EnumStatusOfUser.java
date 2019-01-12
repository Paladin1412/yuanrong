package com.yuanrong.admin.Enum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public enum EnumStatusOfUser implements IntegerValuedEnum  {
    /*审核失败("审核失败", 0, "审核失败"),待审核("待审核", 2, "待审核"),
    审核成功("审核成功", 1, "审核成功"),注册成功("注册成功", 3, "注册成功");*/
    账号停用("账号停用", 0, "账号停用"),账号可用("账号可用", 1, "账号可用");

    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumStatusOfUser(String name, int index, String description) {
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

        for(EnumStatusOfUser type : EnumStatusOfUser.values()){
            Map<String , Object> ele = new TreeMap<String, Object>();
            ele.put("id" , type.index);
            ele.put("name" , type.name);
            result.add(ele);
        }
        return result;
    }
}

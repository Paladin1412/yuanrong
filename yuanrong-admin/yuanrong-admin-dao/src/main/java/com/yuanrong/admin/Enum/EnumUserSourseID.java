package com.yuanrong.admin.Enum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
* @Description:    当前角色分辨是否提交审核信息
* @Author:         ShiLinghuai
* @CreateDate:     2018/7/16 11:13
* @UpdateUser:     ShiLinghuai
* @UpdateDate:     2018/7/16 11:13
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public enum EnumUserSourseID implements IntegerValuedEnum{
    前台创建("前台创建", 1, "前台创建"),后台创建("后台创建", 2, "后台创建"),系统创建("系统创建",3,"系统创建");
    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumUserSourseID(String name, int index, String description) {
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
        for(EnumUserSourseID type : EnumUserSourseID.values()){
            Map<String , Object> ele = new TreeMap<String, Object>();
            ele.put("id" , type.index);
            ele.put("name" , type.name);
            result.add(ele);
        }
        return result;
    }
}

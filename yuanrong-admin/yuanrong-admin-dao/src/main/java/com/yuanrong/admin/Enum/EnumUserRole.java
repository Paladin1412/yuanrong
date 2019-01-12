package com.yuanrong.admin.Enum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
* @Description:    新增用户角色
* @Author:         ShiLinghuai
* @CreateDate:     2018/7/16 11:12
* @UpdateUser:     ShiLinghuai
* @UpdateDate:     2018/7/16 11:12
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public enum EnumUserRole implements IntegerValuedEnum{
    买家("买家", 0, "买家"),卖家("卖家", 1, "卖家");
    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumUserRole(String name, int index, String description) {
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
        for(EnumUserRole type : EnumUserRole.values()){
            Map<String , Object> ele = new TreeMap<String, Object>();
            ele.put("id" , type.index);
            ele.put("name" , type.name);
            result.add(ele);
        }
        return result;
    }
}

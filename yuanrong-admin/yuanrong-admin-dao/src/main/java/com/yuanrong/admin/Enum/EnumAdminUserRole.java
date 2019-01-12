package com.yuanrong.admin.Enum;

public enum EnumAdminUserRole implements IntegerValuedEnum {
    媒介经理("媒介经理", 1, "媒介经理"),销售经理("销售经理", 2, "销售经理"),产品运营("产品运营", 3, "产品运营");

    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumAdminUserRole(String name, int index, String description) {
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
}

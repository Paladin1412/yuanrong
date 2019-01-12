package com.yuanrong.admin.Enum;

public enum EnumPayType implements IntegerValuedEnum {
    微信("微信", 1, "微信"),支付宝("支付宝", 2, "支付宝");

    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumPayType(String name, int index, String description) {
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

package com.yuanrong.admin.Enum;

public enum EnumWeChartType implements IntegerValuedEnum {
    bug("bug", 1, "bug"),通知("通知", 2, "通知");

    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumWeChartType(String name, int index, String description) {
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

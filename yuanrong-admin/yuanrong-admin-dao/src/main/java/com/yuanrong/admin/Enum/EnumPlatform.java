package com.yuanrong.admin.Enum;

public enum EnumPlatform implements IntegerValuedEnum {
    微信("微信", 1, "微信"),微博("微博", 2, "微博"),短视频("短视频", 3, "短视频");

    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumPlatform(String name, int index, String description) {
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

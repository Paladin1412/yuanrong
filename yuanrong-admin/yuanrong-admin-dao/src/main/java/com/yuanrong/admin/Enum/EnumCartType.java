package com.yuanrong.admin.Enum;

public enum EnumCartType implements IntegerValuedEnum {
    创作者("创作者", 1, "创作者"),作品("作品", 2, "作品"),营销分发("营销分发", 3, "营销分发");

    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumCartType(String name, int index, String description) {
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

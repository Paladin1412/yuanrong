package com.yuanrong.admin.Enum;

public enum EnumReferType implements IntegerValuedEnum {
    作品收入("作品收入", 1, "作品收入"),提现("提现", 2, "提现"),需求收入("需求收入", 3, "需求收入");

    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumReferType(String name, int index, String description) {
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

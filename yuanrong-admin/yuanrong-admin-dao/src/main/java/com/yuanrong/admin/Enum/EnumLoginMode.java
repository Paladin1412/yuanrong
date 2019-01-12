package com.yuanrong.admin.Enum;

public enum EnumLoginMode implements IntegerValuedEnum {
    PC_账号密码("PC_账号密码", 1, "PC_账号密码"), PC_短息验证码("PC_短息验证码", 1, "PC_短息验证码");

    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumLoginMode(String name, int index, String description) {
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

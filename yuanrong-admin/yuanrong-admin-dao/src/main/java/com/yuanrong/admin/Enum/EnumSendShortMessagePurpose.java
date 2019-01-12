package com.yuanrong.admin.Enum;

public enum EnumSendShortMessagePurpose implements IntegerValuedEnum {
    用户注册("用户注册",1,"用户注册"),用户动态登录("用户动态登录",2,"用户动态登录"),
    用户修改密码("用户修改密码",3,"用户修改密码"),爬虫调用("爬虫调用",4,"爬虫调用"),
    注册信息审核("注册信息审核",5,"注册信息审核"),后台用户修改密码("后台用户修改密码",6,"后台用户修改密码"),
    C端发布需求("C端发布需求",7,"C端发布需求");

    //定义变量
    private String name;

    private int index;

    private  String description;

    EnumSendShortMessagePurpose(String name, int index, String description) {
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

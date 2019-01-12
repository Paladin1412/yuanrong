package com.yuanrong.admin.Enum;
/**
* @Description:    更新时的买家卖家类型
* @Author:         ShiLinghuai
* @CreateDate:     2018/7/16 11:14
* @UpdateUser:     ShiLinghuai
* @UpdateDate:     2018/7/16 11:14
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public enum EnumUserSellerAndBuyerUserType implements IntegerValuedEnum {
    个人("个人", 1, "个人"),企业("企业", 2, "企业");

    // 成员变量
    private String name;

    private int index;

    private String description;
    private EnumUserSellerAndBuyerUserType(String name, int index, String description) {
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

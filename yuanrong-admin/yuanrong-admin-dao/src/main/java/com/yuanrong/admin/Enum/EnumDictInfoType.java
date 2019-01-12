package com.yuanrong.admin.Enum;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zhonghang on 2018/4/28.
 */
public enum EnumDictInfoType  implements IntegerValuedEnum  {
    客户公司职位名称("客户公司职位名称", 1, "客户公司职位名称"),微信分类("微信分类", 2, "微信分类")
    ,短视频分类("短视频分类", 4, "短视频分类"),微博分类("微博分类", 3, "微博分类"),
    未上架原因("未上架原因", 5, "未上架原因"),平台来源("平台来源", 6, "平台来源"),
    需求状态("需求状态",7,"需求状态"),企业用户审核失败原因("企业用户审核失败原因",8,"企业用户审核失败原因"),
    个人用户审核失败原因("个人用户审核失败原因",9,"个人用户审核失败原因")
    ,作品审核失败原因("作品审核失败原因",10,"作品审核失败原因")
    ,创作者审核失败原因("创作者审核失败原因",11,"创作者审核失败原因")
    ,作者作品下架原因("作者作品下架原因",12,"作者作品下架原因")
    ,排行榜分类("排行榜分类",13,"排行榜分类")
    ,圆融分类("圆融分类",14,"圆融分类")
    ,客户所属行业("客户所属行业",15,"客户所属行业")
    ,商品费用明细("商品费用明细",16,"商品费用明细")
    ,需求拒绝原因("需求拒绝原因",17,"需求拒绝原因")
    ,订单执行终止原因("订单执行终止原因",18,"订单执行终止原因")
    ,订单拒约原因("订单拒约原因",19,"订单拒约原因")
    ,作品下架原因("作品下架原因",20,"作品下架原因");

    // 成员变量
    private String name;

    private int index;

    private String description;

    //构造方法
    private EnumDictInfoType(String name, int index, String description) {
        this.name = name;
        this.index = index;
        this.description = description;
    }

    // get set 方法
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

    public static Map<String , Object> getMapInfo(){
        Map<String , Object> result = new TreeMap<String, Object>();
        for(EnumDictInfoType type : EnumDictInfoType.values()){
            result.put("id" , type.index);
            result.put("name" , type.name);
        }
        return result;
    }
}

package com.yuanrong.admin.bean.base;
import java.util.*;
import java.io.Serializable;
import java.math.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.bean.BaseBean;

/**
 * 标签的实体类
 *
 * @author MDA
 *
 */
public class Lable extends BaseBean implements java.io.Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private Integer Id;
    /*****自定义属性区域begin******/
   
   
    /**
     * 标签名称
     * 标签名称
     */
    private String lableName;
   
    /**
     * 创建时间
     * 创建时间
     */
    private String createTime;
   
    /**
     * 使用状态
     * 使用状态
     */
    private Integer statusValue;
    public Integer getId() {
        return this.Id;
    }
    public void setId(Integer Id) {
        this.Id = Id;
    }
    /*****自定义属性区域begin.get/set******/
   
    public String getLableName() {
        return this.lableName;
    }
    public void setLableName(String lableName) {
        this.lableName = lableName;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public Integer getStatusValue() {
        return this.statusValue;
    }
    public void setStatusValue(Integer statusValue) {
        this.statusValue = statusValue;
    }

    /**
     * 封装数据——标签
     * @param lable
     * @return JSONObject
     */
    public static JSONObject packageLable(Lable lable){
        JSONObject json = new JSONObject();
        json.put("id",lable.getId());
        json.put("name",lable.getLableName());
        return json;
    }

    /**
     * 封装数据——标签
     * @param lableList
     * @return JSONArray
     */
    public static JSONArray packageLable(List<Lable> lableList) {
        JSONArray result = new JSONArray();
        for (Lable cont : lableList){
            result.add(packageLable(cont));
        }
        return result;
    }
}

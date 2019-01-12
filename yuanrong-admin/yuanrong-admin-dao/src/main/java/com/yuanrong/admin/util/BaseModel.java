package com.yuanrong.admin.util;

import java.io.Serializable;

/**
 * Created by zhonghang on 2018/4/11.
 */
public class BaseModel  implements Serializable {
    protected Integer cp = 0;// 当前页
    protected Integer rows = 10;// 每页显示记录数
    protected String sort;// 排序字段
    protected String order = "asc";// asc/desc
    protected String q;// easyui的combo和其子类过滤时使用

    protected String ids;// 主键集合，逗号分割

    private String fields; //需要调取的字段信息，用逗号分隔

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        if(cp != null)
            this.cp = cp;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }
}

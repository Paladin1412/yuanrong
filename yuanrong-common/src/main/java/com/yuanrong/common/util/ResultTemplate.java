package com.yuanrong.common.util;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

/**
 * Created by zhonghang on 2018/7/23.
 * 返回结果信息
 */
public class ResultTemplate {
    private int status;
    private String msg = "";
    private Object data = new JSONObject();

    public ResultTemplate(){
        this.status = 1;
    }
    public ResultTemplate(String msg){
        this.status = 0;
        this.msg = msg;
    }
    public ResultTemplate(Object data){
        this.status = 1;
        this.data = data;
    }

    public ResultTemplate(String msg , Object data){
        if(StringUtil.isBlank(msg)){
            this.status = 1;
        }
        this.msg = msg;
        this.data = data == null ? new JSONObject() : data;
    }

    public ResultTemplate(PageInfo pageInfo , Object data){
        this.status = 1;
        JSONObject result = getPageInfoJSON(pageInfo);
        result.put("data" , data);
        this.data = result;
    }

    public ResultTemplate(PageInfo pageInfo , Object data, Object data1){
        this.status = 1;
        JSONObject result = getPageInfoJSON(pageInfo);
        result.put("data" , data);
        result.put("countData" , data1);
        this.data = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private JSONObject getPageInfoJSON(PageInfo pageInfo){
        JSONObject result = new JSONObject();
        //总页数
        result.put("pages" , pageInfo.getPages());
        //每页的数量
        result.put("pageSize" , pageInfo.getPageSize());
        //总页数
        result.put("total" , pageInfo.getTotal());
        //当前页
        result.put("cp" , pageInfo.getPageNum());
        //当前页的数量
        result.put("size" , pageInfo.getSize());
        return result;
    }
}

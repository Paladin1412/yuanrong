package com.yuanrong.admin.exception;

import java.io.Serializable;

public class ParmException extends RuntimeException implements Serializable{
    /**
     * 出错用户id
     */
    private Integer id;
    private String name;
    private ErrorMessageData data;

    public ParmException(ErrorMessageData data) {
        super(data.getMsg());
        this.data = data;
    }
    public ParmException(String name, ErrorMessageData data) {
        super(name+":"+data.getMsg());
        this.name = name;
        this.data = data;
    }
    public ParmException(Integer id,String name, ErrorMessageData data) {
        super(name+":"+data.getMsg());
        this.name = name;
        this.data = data;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ErrorMessageData getData() {
        return data;
    }
    public void setData(ErrorMessageData data) {
        this.data = data;
    }
}

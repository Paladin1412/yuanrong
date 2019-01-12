package com.yuanrong.admin.bean.base;
import java.util.*;
import java.io.Serializable;
import java.math.*;
import com.yuanrong.admin.bean.BaseBean;

/**
 * 系统日志的实体类
 *
 * @author MDA
 *
 */
public class SystemLog extends BaseBean implements java.io.Serializable {
    // Fields
    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增类型
     */
    private int systemLogId;
    /*****自定义属性区域begin******/


    /**
     * 类路径
     * 类路径
     */
    private String classPath;

    /**
     * 记录ID
     * 记录ID
     */
    private int id;

    /**
     * 日志信息
     * 日志信息
     */
    private String msg;

    /**
     * 操作人
     */
    private String operator;

    public int getSystemLogId() {
        return this.systemLogId;
    }
    public void setSystemLogId(int systemLogId) {
        this.systemLogId = systemLogId;
    }
    /*****自定义属性区域begin.get/set******/

    public String getClassPath() {
        return this.classPath;
    }
    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMsg() {
        return this.msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}

package com.yuanrong.admin.bean.config;
import java.util.*;
import java.io.Serializable;
import java.math.*;
import com.yuanrong.admin.bean.BaseBean;

/**
 * 系统配置的实体类
 *
 * @author MDAConfiguration
 *
 */
public class Configuration extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private int configurationId;
    /*****自定义属性区域begin******/
   
   
    /**
     * 配置名
     * 配置名
     */
    private String cName;
   
    /**
     * 配置值
     * 配置值
     */
    private String cValue;
    public int getConfigurationId() {
        return this.configurationId;
    }
    public void setConfigurationId(int configurationId) {
        this.configurationId = configurationId;
    }
    /*****自定义属性区域begin.get/set******/
   
    public String getCName() {
        return this.cName;
    }
    public void setCName(String cName) {
        this.cName = cName;
    }
    public String getCValue() {
        return this.cValue;
    }
    public void setCValue(String cValue) {
        this.cValue = cValue;
    }
}

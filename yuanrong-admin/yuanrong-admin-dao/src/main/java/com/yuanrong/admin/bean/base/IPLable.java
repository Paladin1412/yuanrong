package com.yuanrong.admin.bean.base;
import java.util.*;
import java.io.Serializable;
import java.math.*;
import com.yuanrong.admin.bean.BaseBean;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * IP标签的实体类
 *
 * @author MDA
 *
 */
public class IPLable extends BaseBean implements java.io.Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private Integer recID;
    /*****自定义属性区域begin******/
   
   
    /**
     * 父级ID
     * 父级ID
     */
    private Integer parentID;
   
    /**
     * 标签名称
     * 标签名称
     */
    private String tagName;
   
    /**
     * 使用状态
     * 使用状态
     */
    private Integer statusValue;
    public Integer getRecID() {
        return this.recID;
    }
    public void setRecID(Integer recID) {
        this.recID = recID;
    }
    /*****自定义属性区域begin.get/set******/
   
    public Integer getParentID() {
        return this.parentID;
    }
    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }
   
    public String getTagName() {
        return this.tagName;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    public Integer getStatusValue() {
        return this.statusValue;
    }
    public void setStatusValue(Integer statusValue) {
        this.statusValue = statusValue;
    }
   
}

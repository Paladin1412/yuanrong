package com.yuanrong.admin.bean.base;

import java.util.Date;

public class SmsRecord   implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private Integer RecID;

    private String Mobile;

    private Date CreateTime;

    private Integer Status;

    private String Source;
    /**
     * 内容
     */
    private String content;
    public SmsRecord() {
    }

    public SmsRecord(Integer recID, String mobile, Date createTime, Integer status, String source) {
        RecID = recID;
        Mobile = mobile;
        CreateTime = createTime;
        Status = status;
        Source = source;
    }

    public Integer getRecID() {
        return RecID;
    }

    public void setRecID(Integer recID) {
        RecID = recID;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

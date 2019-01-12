package com.yuanrong.admin.seach;

import java.io.Serializable;
import java.util.List;

public class BatchUpdateSaleOrMedia implements Serializable {
    private String id;
    /**
     * 两种解析方式
     */
    private Integer[] ids;
    private List<Integer> idss;
    /**
     * 销售id
     */
    private Integer saleID;
    /**
     * 媒介id
     */
    private Integer mediaID;
    /**
     * 失败原因id
     */
    private Integer checkFailedReasonID;

    private String resetPassword;

    public String getResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(String resetPassword) {
        this.resetPassword = resetPassword;
    }

    public Integer getCheckFailedReasonID() {
        return checkFailedReasonID;
    }

    public void setCheckFailedReasonID(Integer checkFailedReasonID) {
        this.checkFailedReasonID = checkFailedReasonID;
    }

    public List<Integer> getIdss() {
        return idss;
    }

    public void setIdss(List<Integer> idss) {
        this.idss = idss;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public Integer getSaleID() {
        return saleID;
    }

    public void setSaleID(Integer saleID) {
        this.saleID = saleID;
    }

    public Integer getMediaID() {
        return mediaID;
    }

    public void setMediaID(Integer mediaID) {
        this.mediaID = mediaID;
    }
}

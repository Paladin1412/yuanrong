package com.yuanrong.admin.bean.rank;

import com.yuanrong.admin.Enum.EnumYesOrNo;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.util.EnumUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * IP创造力排行榜单
 */
public class ListIPCreativity  extends BaseBean implements Serializable {
    private static final long serialVersionUID = 921912668025850975L;
    /**
     *主键ID
     */
    private  Integer  recID ;

    /**
     *头像
     */
    private  String  headImg  ;

    /**
     *名称
     */
    private  String  name  ;

    /**
     *昵称
     */
    private  String   weixinId ;

    /**
     *稳定性（持续力）
     */
    private  Double   stability ;

    /**
     *内容质量
     */
    private  Double   contentQuality ;

    /**
     *原创比例（原创力）
     */
    private  Double   originalProportion ;

    /**
     *传播性（传播力）
     */
    private  Double   transmissible ;

    /**
     *粉丝数
     */
    private  Integer   fans ;

    /**
     *综合指数
     */
    private  Double   totalIndex ;

    /**
     *微博易指数
     */
    private  Double weiboyiIndex ;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *状态值 0 不可用 ，1 可用
     */
    private  Integer statusValue;
    private EnumYesOrNo enumYesOrNo;

    /**
     * 时间段
     */
    private  String rangeTime;

    public ListIPCreativity() {
    }

    public ListIPCreativity(Integer recID, String headImg, String name, String weixinId, Double stability, Double contentQuality, Double originalProportion, Double transmissible, Integer fans, Double totalIndex, Double weiboyiIndex, Date createTime, Integer statusValue, String rangeTime) {
        this.recID = recID;
        this.headImg = headImg;
        this.name = name;
        this.weixinId = weixinId;
        this.stability = stability;
        this.contentQuality = contentQuality;
        this.originalProportion = originalProportion;
        this.transmissible = transmissible;
        this.fans = fans;
        this.totalIndex = totalIndex;
        this.weiboyiIndex = weiboyiIndex;
        this.createTime = createTime;
        this.statusValue = statusValue;
        this.rangeTime = rangeTime;
    }

    public Integer getRecID() {
        return recID;
    }

    public void setRecID(Integer recID) {
        this.recID = recID;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public Double getStability() {
        return stability;
    }

    public void setStability(Double stability) {
        this.stability = stability;
    }

    public Double getContentQuality() {
        return contentQuality;
    }

    public void setContentQuality(Double contentQuality) {
        this.contentQuality = contentQuality;
    }

    public Double getOriginalProportion() {
        return originalProportion;
    }

    public void setOriginalProportion(Double originalProportion) {
        this.originalProportion = originalProportion;
    }

    public Double getTransmissible() {
        return transmissible;
    }

    public void setTransmissible(Double transmissible) {
        this.transmissible = transmissible;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Double getTotalIndex() {
        return totalIndex;
    }

    public void setTotalIndex(Double totalIndex) {
        this.totalIndex = totalIndex;
    }

    public Double getWeiboyiIndex() {
        return weiboyiIndex;
    }

    public void setWeiboyiIndex(Double weiboyiIndex) {
        this.weiboyiIndex = weiboyiIndex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(Integer statusValue) {
        this.statusValue = statusValue;
        this.enumYesOrNo = (EnumYesOrNo) EnumUtil.valueOf(EnumYesOrNo.class,statusValue);
    }

    public EnumYesOrNo getEnumYesOrNo() {
        return enumYesOrNo;
    }

    public void setEnumYesOrNo(EnumYesOrNo enumYesOrNo) {
        this.enumYesOrNo = enumYesOrNo;
        this.statusValue = enumYesOrNo.getIndex();
    }

    public String getRangeTime() {
        return rangeTime;
    }

    public void setRangeTime(String rangeTime) {
        this.rangeTime = rangeTime;
    }
}

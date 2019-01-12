package com.yuanrong.admin.bean.demand;

import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Tangzheng on 2018/5/23.
 */
public class AdvertiserReleaseRequirement extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer recid;

    /**
     * 项目类型
     */
    private Integer protype;

    /**
     * 项目名称
     */
    private String proname;

    /**
     * 项目描述
     */
    private String prodescription;

    /**
     * 项目预算
     */
    private BigDecimal probudget;

    /**
     * 完成天数
     */
    private Integer validity;

    /**
     * 手机号
     */
    private String iphonenum;

    /**
     * 验证码
     */
    private String vercode;

    /**
     * 使用场景id
     */
    private  Integer scenesid;

    /**
     * 分类id
     */
    private  Integer categoryid;

    /**
     * 修改次数
     */
    private  Integer modifynum;

    /**
     * 作者
     */
    private  String author;

    /**
     * 作者id
     */
    private Integer authorid;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 最后修改时间
     */
    private Date lastmodifytime;

    /**
     * 状态值
     */
    private Integer statusvalue;

    /**
     * 结束时间
     */
    private Date endtime;

    /**
     * 参观人数
     */
    private Integer attendnum;

    /**
     * 围观人数
     */
    private Integer onlookernum;

    /**
     *字典id
     */
    private Integer dictinfoid;

    /**
     * 需求图片行img
     */
    private String RequireImg;

    /**
     * 注册用户ID
     */
    private Integer registeredUserInfoID;

    public AdvertiserReleaseRequirement() {
    }

    public AdvertiserReleaseRequirement(Integer recid, Integer protype, String proname, String prodescription, BigDecimal probudget, Integer validity, String iphonenum, String vercode, Integer scenesid, Integer categoryid, Integer modifynum, String author, Integer authorid, Date createtime, Date lastmodifytime, Integer statusvalue, Date endtime, Integer attendnum, Integer onlookernum, Integer dictinfoid, String requireImg,Integer registeredUserInfoID) {
        this.recid = recid;
        this.protype = protype;
        this.proname = proname;
        this.prodescription = prodescription;
        this.probudget = probudget;
        this.validity = validity;
        this.iphonenum = iphonenum;
        this.vercode = vercode;
        this.scenesid = scenesid;
        this.categoryid = categoryid;
        this.modifynum = modifynum;
        this.author = author;
        this.authorid = authorid;
        this.createtime = createtime;
        this.lastmodifytime = lastmodifytime;
        this.statusvalue = statusvalue;
        this.endtime = endtime;
        this.attendnum = attendnum;
        this.onlookernum = onlookernum;
        this.dictinfoid = dictinfoid;
        RequireImg = requireImg;
        this.registeredUserInfoID = registeredUserInfoID;
    }

    public Integer getRecid() {
        return recid;
    }

    public void setRecid(Integer recid) {
        this.recid = recid;
    }

    public Integer getProtype() {
        return protype;
    }

    public void setProtype(Integer protype) {
        this.protype = protype;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getProdescription() {
        return prodescription;
    }

    public void setProdescription(String prodescription) {
        this.prodescription = prodescription;
    }

    public BigDecimal getProbudget() {
        return probudget;
    }

    public void setProbudget(BigDecimal probudget) {
        this.probudget = probudget;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public String getIphonenum() {
        return iphonenum;
    }

    public void setIphonenum(String iphonenum) {
        this.iphonenum = iphonenum;
    }

    public String getVercode() {
        return vercode;
    }

    public void setVercode(String vercode) {
        this.vercode = vercode;
    }

    public Integer getScenesid() {
        return scenesid;
    }

    public void setScenesid(Integer scenesid) {
        this.scenesid = scenesid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getModifynum() {
        return modifynum;
    }

    public void setModifynum(Integer modifynum) {
        this.modifynum = modifynum;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    public Integer getStatusvalue() {
        return statusvalue;
    }

    public void setStatusvalue(Integer statusvalue) {
        this.statusvalue = statusvalue;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getAttendnum() {
        return attendnum;
    }

    public void setAttendnum(Integer attendnum) {
        this.attendnum = attendnum;
    }

    public Integer getOnlookernum() {
        return onlookernum;
    }

    public void setOnlookernum(Integer onlookernum) {
        this.onlookernum = onlookernum;
    }

    public Integer getDictinfoid() {
        return dictinfoid;
    }

    public void setDictinfoid(Integer dictinfoid) {
        this.dictinfoid = dictinfoid;
    }

    public String getRequireImg() {
        return RequireImg;
    }

    public void setRequireImg(String requireImg) {
        RequireImg = requireImg;
    }

    public Integer getRegisteredUserInfoID() {
        return registeredUserInfoID;
    }

    public void setRegisteredUserInfoID(Integer registeredUserInfoID) {
        this.registeredUserInfoID = registeredUserInfoID;
    }
}

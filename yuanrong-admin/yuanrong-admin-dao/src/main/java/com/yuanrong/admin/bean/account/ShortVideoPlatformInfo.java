package com.yuanrong.admin.bean.account;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumBaseMethodParam;
import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 短视频平台信息
 */
public class ShortVideoPlatformInfo extends BaseBean implements Serializable {
    /**
     * 主键
     */
    private Integer recid;

    /**
     * 平台ID
     */
    private Integer platformid;

    /**
     * 平台名称
     */
    private String platformname;

    /**
     * 父级
     */
    private Integer preplatform;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 状态值
     */
    private Integer statusvalue;

    /**
     * 平台图标
     */
    private String icoUrl;
    /**
     * category=1,微信；2，微博； 3 视频
     */
    private Integer category;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getRecid() {
        return recid;
    }

    public void setRecid(Integer recid) {
        this.recid = recid;
    }

    public Integer getPlatformid() {
        return platformid;
    }

    public void setPlatformid(Integer platformid) {
        this.platformid = platformid;
    }

    public String getPlatformname() {
        return platformname;
    }

    public void setPlatformname(String platformname) {
        this.platformname = platformname == null ? null : platformname.trim();
    }

    public Integer getPreplatform() {
        return preplatform;
    }

    public void setPreplatform(Integer preplatform) {
        this.preplatform = preplatform;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getStatusvalue() {
        return statusvalue;
    }

    public void setStatusvalue(Integer statusvalue) {
        this.statusvalue = statusvalue;
    }

    public String getIcoUrl() {
        return icoUrl;
    }

    public void setIcoUrl(String icoUrl) {
        this.icoUrl = icoUrl;
    }

    public static JSONArray getArrrayByList(List<ShortVideoPlatformInfo> infoList){
        JSONArray jsonArray = new JSONArray();
        for(ShortVideoPlatformInfo shortVideoPlatformInfo : infoList){
            JSONObject object = new JSONObject();
            object.put("id" , shortVideoPlatformInfo.getRecid());
            object.put("name" , shortVideoPlatformInfo.getPlatformname());
            jsonArray.add(object);
        }
        return  jsonArray;
    }
    public static JSONArray packToTrading(List<ShortVideoPlatformInfo> infoList){
        JSONArray jsonArray = new JSONArray();
        for(ShortVideoPlatformInfo shortVideoPlatformInfo : infoList){
            JSONObject object = new JSONObject();
            object.put("name" , shortVideoPlatformInfo.getPlatformname());
            jsonArray.add(object);
        }
        JSONObject object1 = new JSONObject();
        object1.put("name" , EnumBaseMethodParam.其他.getName());
        jsonArray.add(object1);
        return  jsonArray;
    }
}
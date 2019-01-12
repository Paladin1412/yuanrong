package com.yuanrong.admin.result;

import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumAuthorStatus;
import com.yuanrong.admin.Enum.EnumUserRoleLicenseStatus;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.util.EnumUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName YRAuthorInfoResult
 * @Author Jss
 * @Date 2018/6/30
 */
public class YRAuthorInfoResult extends YRAuthor implements Serializable {

    /**
     * 使用场景
     */
    private String scenesName;
    /**
     * 分类(擅长领域)
     */
    private String CategoryName;
    /**
     * 分类Ids
     */
    private String CategoryIds;
    /**
     * 内容形式
     */
    private String contentFormName;
    /**
     * 创作风格(标签)
     */
    private String lableName;
    /**
     * 购物车标记0-没有加入购物车|| 1-加入购物车
     */
    private Integer flag;
    /**
     * 上架作品数
     */
    private Integer proUpNum;

    public String getScenesName() {
        return scenesName;
    }

    public void setScenesName(String scenesName) {
        this.scenesName = scenesName;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCategoryIds() {
        return CategoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        CategoryIds = categoryIds;
    }

    public String getContentFormName() {
        return contentFormName;
    }

    public void setContentFormName(String contentFormName) {
        this.contentFormName = contentFormName;
    }

    public String getLableName() {
        return lableName;
    }

    public void setLableName(String lableName) {
        this.lableName = lableName;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getProUpNum() {
        return proUpNum;
    }

    public void setProUpNum(Integer proUpNum) {
        this.proUpNum = proUpNum;
    }

    /**
     * 封装创作者参数—详情
     * @param author
     * @return
     */
    public static JSONObject packageParam(YRAuthorInfoResult author){
        JSONObject authorInfo = new JSONObject();
        /*//创作者Id
        authorInfo.put("recId",author.getRecId());
        //创作者头像
        authorInfo.put("authorImg",author.getAuthorImg());
        //创作者名称
        authorInfo.put("authorNickname",author.getAuthorNickname());
        //创作者原创报价
        authorInfo.put("createdPrice",author.getCreatedPrice());
        //创作者创作用时
        authorInfo.put("authorCreationTime",author.getAuthorCreationTime());
        //创作者简介
        authorInfo.put("Introduction",author.getIntroduction());
        //创作者使用场景
        authorInfo.put("scenesName",author.getScenesName());
        //创作者擅长领域
        authorInfo.put("CategoryName",author.getCategoryName());
        //创作者内容形式
        authorInfo.put("contentFormName",author.getContentFormName());
        //创作者标签
        authorInfo.put("lableName",author.getLableName());*/
        //购物车标记
        authorInfo.put("flag",author.getFlag());
        return authorInfo;
    }
}

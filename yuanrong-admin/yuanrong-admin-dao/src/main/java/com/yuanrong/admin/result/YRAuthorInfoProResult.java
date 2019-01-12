package com.yuanrong.admin.result;

import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;

import java.io.Serializable;

/**
 * @ClassName YRAuthorInfoResult
 * @Author Jss
 * @Date 2018/6/30
 */
public class YRAuthorInfoProResult extends YRProduction implements Serializable {

    /**
     * 使用场景
     */
    private String scenesName;
    /**
     * 分类(擅长领域)
     */
    private String CategoryName;
    /**
     * 内容形式
     */
    private String contentFormName;
    /**
     * 营销场景
     */
    private String calendarName;

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

    public String getContentFormName() {
        return contentFormName;
    }

    public void setContentFormName(String contentFormName) {
        this.contentFormName = contentFormName;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }
}

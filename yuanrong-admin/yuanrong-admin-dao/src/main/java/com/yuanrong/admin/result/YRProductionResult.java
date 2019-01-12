package com.yuanrong.admin.result;

import com.yuanrong.admin.Enum.EnumYesOrNo;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.util.EnumUtil;

/**
 * Created by zhonghang on 2018/7/9.
 */
public class YRProductionResult extends YRProduction {
    /**
     * 营销场景
     */
    private String marketingScene;

    /**
     * 交易次数
     */
    private int sellcount;

    /**
     * 擅长领域
     */
    private String yrAuthorCategory;

    /**
     * 内容形式
     */
    private String contentform;

    /**
     * 作品数量
     */
    private Integer prnum;


    public String getMarketingScene() {
        return marketingScene;
    }

    public void setMarketingScene(String marketingScene) {
        this.marketingScene = marketingScene;
    }

    public int getSellcount() {
        return sellcount;
    }

    public void setSellcount(int sellcount) {
        this.sellcount = sellcount;
    }

    public String getYrAuthorCategory() {
        return yrAuthorCategory;
    }

    public void setYrAuthorCategory(String yrAuthorCategory) {
        this.yrAuthorCategory = yrAuthorCategory;
    }

    public String getContentform() {
        return contentform;
    }

    public void setContentform(String contentform) {
        this.contentform = contentform;
    }

    public Integer getPrnum() {
        return prnum;
    }

    public void setPrnum(Integer prnum) {
        this.prnum = prnum;
    }

}

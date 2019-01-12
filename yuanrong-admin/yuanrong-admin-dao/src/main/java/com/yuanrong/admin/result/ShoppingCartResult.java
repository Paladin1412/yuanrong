package com.yuanrong.admin.result;

import com.yuanrong.admin.Enum.EnumPlatform;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.cart.ShoppingCart;
import com.yuanrong.admin.util.EnumUtil;

/**
 * Created by zhonghang on 2018/7/5.
 */
public class ShoppingCartResult extends ShoppingCart {
    private YRAuthor yrAuthor;
    private YRProduction yrProduction;
    private PlatformIPAccount platformIPAccount;

    /**
     * 账号报价信息
     */
    private String priceInfo;

    /**
     * 账号上架数量
     */
    private int shelvesNum;


    public YRAuthor getYrAuthor() {
        return yrAuthor;
    }

    public void setYrAuthor(YRAuthor yrAuthor) {
        this.yrAuthor = yrAuthor;
    }

    public YRProduction getYrProduction() {
        return yrProduction;
    }

    public void setYrProduction(YRProduction yrProduction) {
        this.yrProduction = yrProduction;
    }

    public PlatformIPAccount getPlatformIPAccount() {
        return platformIPAccount;
    }

    public void setPlatformIPAccount(PlatformIPAccount platformIPAccount) {
        this.platformIPAccount = platformIPAccount;
    }

    public int getShelvesNum() {
        return shelvesNum;
    }

    public void setShelvesNum(int shelvesNum) {
        this.shelvesNum = shelvesNum;
    }

    public String getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(String priceInfo) {
        this.priceInfo = priceInfo;
    }


}

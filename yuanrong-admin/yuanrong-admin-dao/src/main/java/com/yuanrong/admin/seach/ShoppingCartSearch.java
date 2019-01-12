package com.yuanrong.admin.seach;

import com.yuanrong.admin.Enum.EnumPlatform;
import com.yuanrong.admin.bean.cart.ShoppingCart;
import com.yuanrong.admin.util.EnumUtil;

/**
 * Created by zhonghang on 2018/7/5.
 */
public class ShoppingCartSearch extends ShoppingCart {
    /**
     * 发布状态
     */
    private Integer publishStatus;
    /**
     * 平台
     */
    private EnumPlatform platform;
    private Integer platformIndex;

    private Integer[] shoppingCartIds;

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public EnumPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(EnumPlatform platform) {
        this.platform = platform;
        this.platformIndex = platform.getIndex();
    }

    public Integer getPlatformIndex() {
        return platformIndex;
    }

    public void setPlatformIndex(Integer platformIndex) {
        this.platformIndex = platformIndex;
        this.platform = (EnumPlatform) EnumUtil.valueOf(EnumPlatform.class , platformIndex);
    }

    public Integer[] getShoppingCartIds() {
        return shoppingCartIds;
    }

    public void setShoppingCartIds(Integer[] shoppingCartIds) {
        this.shoppingCartIds = shoppingCartIds;
    }
}

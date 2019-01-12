package com.yuanrong.admin.bean.cart;
import java.util.*;
import java.io.Serializable;
import java.math.*;

import com.yuanrong.admin.Enum.EnumCartType;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.util.EnumUtil;

/**
 * 购物车的实体类
 *
 * @author MDA
 *
 */
public class ShoppingCart extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private int shoppingCartId;
    /*****自定义属性区域begin******/
   
   
    /**
     * 购物车类别
     * 购物车类别
     */
    private EnumCartType cartType;
    /*
    * 用于接收前台数据
    */
    private Integer cartTypeIndex;
   
    /**
     * 目标商品id
     * 目标商品id
     */
    private Integer productId;
   
    /**
     * 商品数量默认1
     * 商品数量默认1
     */
    private BigDecimal num;
   
    /**
     * 买家
     * 买家
     */
    private RegisteredUserInfo registeredUserInfo;
    private Integer registeredUserInfoId;

    public int getShoppingCartId() {
        return this.shoppingCartId;
    }
    public void setShoppingCartId(int shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }
    /*****自定义属性区域begin.get/set******/
   
    public EnumCartType getCartType() {
    return cartType;
}
public void setCartType(EnumCartType cartType) {
        cartTypeIndex = cartType.getIndex();
    this.cartType = cartType;
}
public void setCartType(String cartType) {
    this.cartType = (EnumCartType) EnumUtil.valueOf(EnumCartType.class,cartType);
}
    public Integer getCartTypeIndex() {
        return cartTypeIndex;
    }
    public void setCartTypeIndex(Integer cartTypeIndex) {
        this.cartTypeIndex = cartTypeIndex;
        this.cartType = (EnumCartType) EnumUtil.valueOf(EnumCartType.class, cartTypeIndex);
    }
    public Integer getProductId() {
        return this.productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
   
    public BigDecimal getNum() {
        return this.num;
    }
    public void setNum(BigDecimal num) {
        this.num = num;
    }
    public RegisteredUserInfo getRegisteredUserInfo() {
        return this.registeredUserInfo;
    }
    public void setRegisteredUserInfo(RegisteredUserInfo registeredUserInfo) {
        this.registeredUserInfo = registeredUserInfo;
    }

    public Integer getRegisteredUserInfoId() {
        return registeredUserInfoId;
    }

    public void setRegisteredUserInfoId(Integer registeredUserInfoId) {
        this.registeredUserInfoId = registeredUserInfoId;
    }
}

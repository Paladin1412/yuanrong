package com.yuanrong.admin.rpc.api.cart;
import com.yuanrong.admin.bean.cart.ShoppingCart;
import com.yuanrong.admin.result.ShoppingCartResult;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.seach.ShoppingCartSearch;

import java.util.List;
import java.util.Map;

/**
 * 购物车的services接口
 * Created MDA
 */
public interface ShoppingCartServicesI extends BaseServicesI<ShoppingCart> {
    /**
     * 查看购物车是否存在该商品
     * @param shoppingCart
     * @return
     */
    ShoppingCart isExistProduct (ShoppingCart shoppingCart);

    /**
     * 搜索买家创作者购物车
     * @param data
     * @return
     */
    List<ShoppingCartResult> listAuthorShoppingCart (ShoppingCartSearch data);

    /**
     * 搜索买家作品购物车
     * @param data
     * @return
     */
    List<ShoppingCartResult> listProductShoppingCart (ShoppingCartSearch data);

    /**
     * 搜索买家账号购物车
     * @param data
     * @return
     */
    List<ShoppingCartResult> listAccountShoppingCart (ShoppingCartSearch data);

    /**
     * 删除无效作者
     * @param data
     */
    void deleteFailureYrAuthor(ShoppingCartSearch data);

    /**
     * 删除无效作品
     * @param data
     */
    void deleteFailureProduction(ShoppingCartSearch data);

    /**
     * 删除无效账号
     * @param data
     */
    void deleteFailureAccount(ShoppingCartSearch data);

    /**
     * 批量删除某注册用户下购物车信息
     * @param shoppingCartids
     * @param registeredUserInfoId
     */
    void batchDeleteByIds(String shoppingCartids , int registeredUserInfoId);

    /**
     * 获取指定用户购物车中的数量
     * @param registeredUserInfoId
     * @return
     */
    List<Map> shoppingCartNum(Integer registeredUserInfoId);

    /**
     * 批量删除某个用户下的购物车
     * @param registeredUserInfoId
     * @param ids
     */
    void batchDelete(Integer registeredUserInfoId ,Integer[] ids);

    /**
     * 获取购物车账号类别的微信，微博，短视频的条数
     * @param registeredUserInfoId
     * @return
     */
    List<Map> getAccountTypeCount(Integer registeredUserInfoId);

    /**
     * 清空某个注册用户购物车
     * @param registeredUserInfoId
     */
    void removeAllByRegisterUserId(Integer registeredUserInfoId);

    /**
     * 保存选购车
     * @param data
     * @return
     */
    ShoppingCart saveShoppingCart(ShoppingCart data);

    /**
     * 获取注册用户下分类的购物车数量
     * @param data
     * @return
     */
    Integer cartNumByType(ShoppingCart data);
}

package com.yuanrong.admin.dao.cart;
import com.yuanrong.admin.bean.cart.ShoppingCart;
import com.yuanrong.admin.dao.BaseDaoI;
import com.yuanrong.admin.result.ShoppingCartResult;
import com.yuanrong.admin.seach.ShoppingCartSearch;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 购物车的dao
 * Created MDA
 */
@Repository
public interface ShoppingCartDaoI extends BaseDaoI<ShoppingCart> {
    /**
     * 查看购物车是否存在该商品
     * @param shoppingCart
     * @return
     */
    ShoppingCart isExistProduct (@Param("data") ShoppingCart shoppingCart);

    /**
     * 用户购物车 -- 作者
     * @param ShoppingCartSearch
     * @return
     */
    List<ShoppingCartResult> listAuthorShoppingCart(@Param("data") Object ShoppingCartSearch);

    /**
     * 用户购物车 -- 作品
     * @param data
     * @return
     */
    List<ShoppingCartResult> listProductShoppingCart(@Param("data")ShoppingCartSearch data);

    /**
     * 用户购物车 -- 账号
     * @param data
     * @return
     */
    List<ShoppingCartResult> listAccountShoppingCart(@Param("data")ShoppingCartSearch data);

    /**
     * 删除无效作者
     * @param data
     */
    void deleteFailureYrAuthor(@Param("data")ShoppingCartSearch data);

    /**
     * 删除无效作品
     * @param data
     */
    void deleteFailureProduction(@Param("data")ShoppingCartSearch data);

    /**
     * 删除无效账号
     * @param data
     */
    void deleteFailureAccount(@Param("data")ShoppingCartSearch data);

    /**
     * 批量删除某注册用户下购物车信息
     * @param shoppingCartids
     * @param registeredUserInfoId
     */
    void batchDeleteByIds(@Param("ids")Integer[] shoppingCartids , @Param("registeredUserInfoId") int registeredUserInfoId);

    /**
     * 提交订单 - 查询无效商品
     * @param data
     * @return
     */
    int countInvalidProduction(@Param("data")ShoppingCartSearch data);

    /**
     * 获取指定用户、购物车id，获取产品信息
     * @param data
     * @return
     */
    List<ShoppingCartResult> getProductByShppingCartIdssAndUserId (@Param("data")ShoppingCartSearch data);

    /**
     * 获取指定用户购物车中的数量
     * @param registeredUserInfoId
     * @return
     */
    List<Map> shoppingCartNum(@Param("registeredUserInfoId")Integer registeredUserInfoId);

    /**
     * 批量删除某个用户下的购物车
     * @param registeredUserInfoId
     * @param ids
     */
    void batchDelete(@Param("registeredUserInfoId")Integer registeredUserInfoId , @Param("ids")Integer[] ids);

    /**
     * 获取购物车账号类别的微信，微博，短视频的条数
     * @param registeredUserInfoId
     * @return
     */
    List<Map> getAccountTypeCount(@Param("registeredUserInfoId")Integer registeredUserInfoId);

    /**
     * 清空某个注册用户购物车
     * @param registeredUserInfoId
     */
    void removeAllByRegisterUserId(@Param("registeredUserInfoId")Integer registeredUserInfoId);

    /**
     * 获取注册用户下分类的购物车数量
     * @param data
     * @return
     */
    Integer cartNumByType(@Param("data")ShoppingCart data);
}

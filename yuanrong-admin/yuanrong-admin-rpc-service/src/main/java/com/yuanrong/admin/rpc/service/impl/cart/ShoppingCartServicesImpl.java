package com.yuanrong.admin.rpc.service.impl.cart;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.cart.ShoppingCart;
import com.yuanrong.admin.result.ShoppingCartResult;
import com.yuanrong.admin.rpc.api.cart.ShoppingCartServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.seach.ShoppingCartSearch;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * 购物车的services实现类
 * Created MDA
 */
@Service
public class ShoppingCartServicesImpl extends BaseServicesAbstract<ShoppingCart> implements ShoppingCartServicesI {
    @Override
    public ShoppingCart getById(Integer id) {
        return shoppingCartDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        shoppingCartDaoI.deleteById(id);
    }
    @Override
    public void save(ShoppingCart object) {
        shoppingCartDaoI.save(object);
    }
    @Override
    public List<ShoppingCart> getAll() {
        return shoppingCartDaoI.getAll();
    }
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , 0 , "shoppingCartId desc");
        List<ShoppingCartResult> result = shoppingCartDaoI.listAuthorShoppingCart(data);
        return new PageInfo(result);
    }



    @Override
    public ShoppingCart isExistProduct(ShoppingCart shoppingCart) {
        return shoppingCartDaoI.isExistProduct(shoppingCart);
    }

    @Override
    public List<ShoppingCartResult> listAuthorShoppingCart(ShoppingCartSearch data) {
        return shoppingCartDaoI.listAuthorShoppingCart(data);
    }

    @Override
    public List<ShoppingCartResult> listProductShoppingCart(ShoppingCartSearch data) {
        return shoppingCartDaoI.listProductShoppingCart(data);
    }

    @Override
    public List<ShoppingCartResult> listAccountShoppingCart(ShoppingCartSearch data) {
        return shoppingCartDaoI.listAccountShoppingCart(data);
    }

    @Override
    public void deleteFailureYrAuthor(ShoppingCartSearch data) {
        shoppingCartDaoI.deleteFailureYrAuthor(data);
    }

    @Override
    public void deleteFailureProduction(ShoppingCartSearch data) {
        shoppingCartDaoI.deleteFailureProduction(data);
    }

    @Override
    public void deleteFailureAccount(ShoppingCartSearch data) {
        shoppingCartDaoI.deleteFailureAccount(data);
    }

    @Override
    public void batchDeleteByIds(String shoppingCartids, int registeredUserInfoId) {
        Integer[] shoppingCartidsArray = null;
        String [] shoppingCartidsArray_Str = shoppingCartids.split(",");
        shoppingCartidsArray = new Integer[shoppingCartidsArray_Str.length];
        for (int i=0; i<shoppingCartidsArray_Str.length; i++){
            shoppingCartidsArray[i] = Integer.parseInt( shoppingCartidsArray_Str[i]);
        }
        shoppingCartDaoI.batchDeleteByIds(shoppingCartidsArray,registeredUserInfoId);
    }

    @Override
    public List<Map> shoppingCartNum(Integer registeredUserInfoId) {
        return shoppingCartDaoI.shoppingCartNum(registeredUserInfoId);
    }

    @Override
    public void batchDelete(Integer registeredUserInfoId, Integer[] ids) {
        shoppingCartDaoI.batchDelete(registeredUserInfoId , ids);
    }

    @Override
    public List<Map> getAccountTypeCount(Integer registeredUserInfoId) {
        return shoppingCartDaoI.getAccountTypeCount(registeredUserInfoId);
    }

    @Override
    public void removeAllByRegisterUserId(Integer registeredUserInfoId) {
        shoppingCartDaoI.removeAllByRegisterUserId(registeredUserInfoId);
    }

    @Override
    public ShoppingCart saveShoppingCart(ShoppingCart data) {
         shoppingCartDaoI.save(data);
         return data;
    }

    @Override
    public Integer cartNumByType(ShoppingCart data) {
        return shoppingCartDaoI.cartNumByType(data);
    }
}

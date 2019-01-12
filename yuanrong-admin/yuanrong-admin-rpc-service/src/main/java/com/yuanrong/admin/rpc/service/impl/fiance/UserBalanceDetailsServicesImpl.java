package com.yuanrong.admin.rpc.service.impl.fiance;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.fiance.UserBalanceDetails;
import com.yuanrong.admin.rpc.api.fiance.UserBalanceDetailsServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
/**
 * 用户余额流水的services实现类
 * Created MDA
 */
@Service
public class UserBalanceDetailsServicesImpl extends BaseServicesAbstract<UserBalanceDetails> implements UserBalanceDetailsServicesI {
    @Override
    public UserBalanceDetails getById(Integer id) {
        return userBalanceDetailsDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        userBalanceDetailsDaoI.deleteById(id);
    }
    @Override
    public void save(UserBalanceDetails object) {
        userBalanceDetailsDaoI.save(object);
    }
    @Override
    public List<UserBalanceDetails> getAll() {
        return userBalanceDetailsDaoI.getAll();
    }
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "userBalanceDetailsId desc");
        List<UserBalanceDetails> result = userBalanceDetailsDaoI.list(data);
        return new PageInfo(result);
    }

    @Override
    public BigDecimal getBalance(Integer registeredUserInfoId) {
        return userBalanceDetailsDaoI.getBalance(registeredUserInfoId);
    }
}

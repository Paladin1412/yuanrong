package com.yuanrong.admin.rpc.api.fiance;
import com.yuanrong.admin.bean.fiance.UserBalanceDetails;
import com.yuanrong.admin.rpc.BaseServicesI;

import java.math.BigDecimal;

/**
 * 用户余额流水的services接口
 * Created MDA
 */
public interface UserBalanceDetailsServicesI extends BaseServicesI<UserBalanceDetails> {
    /**
     * 获取用户余额
     * @param registeredUserInfoId
     * @return
     */
    BigDecimal getBalance(Integer registeredUserInfoId);
}

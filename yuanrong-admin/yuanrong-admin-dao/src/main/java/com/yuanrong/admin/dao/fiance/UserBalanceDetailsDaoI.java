package com.yuanrong.admin.dao.fiance;
import com.yuanrong.admin.bean.fiance.UserBalanceDetails;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * 用户余额流水的dao
 * Created MDA
 */
@Repository
public interface UserBalanceDetailsDaoI extends BaseDaoI<UserBalanceDetails> {
    /**
     * 获取用户余额
     * @param registeredUserInfoId
     * @return
     */
    BigDecimal getBalance(@Param("registeredUserInfoId") Integer registeredUserInfoId);
}

package com.yuanrong.admin.dao.pay;
import com.yuanrong.admin.bean.pay.PayWater;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 支付流水的dao
 * Created MDA
 */
@Repository
public interface PayWaterDaoI extends BaseDaoI<PayWater> {

    /**
     * 根据微信交易订单号，获取微信流水
     * @param outTradeNo
     * @return
     */
    PayWater getByOutTradeNo (@Param("outTradeNo") String outTradeNo);
}

package com.yuanrong.admin.dao.order;
import com.yuanrong.admin.bean.order.OrderInfoOffer;
import com.yuanrong.admin.dao.BaseDaoI;
import com.yuanrong.admin.seach.OrderPriceParamSearch;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 卖家订单报价的dao
 * Created MDA
 */
@Repository
public interface OrderInfoOfferDaoI extends BaseDaoI<OrderInfoOffer> {
    /**
     *@author songwq
     *@param
     *@date 2018/8/23
     *@description 根据ID修改价格
    */
    void updatePricesById(@Param(value="data")OrderInfoOffer orderInfoOffer);

    /**
     * 根据报名Id获取报价项
     * @param orderInfoSellerId
     * @return
     */
    List<OrderInfoOffer> getByOrderInfoSellerId(@Param(value = "orderInfoSellerId") Integer orderInfoSellerId);

    /**
     *@author songwq
     *@param
     *@date 2018/8/23
     *@description 批量修改应约价、报价、金额等
     */
    void updateOrderInfoOffer(@Param(value="data")OrderInfoOffer orderInfoOffer);
}

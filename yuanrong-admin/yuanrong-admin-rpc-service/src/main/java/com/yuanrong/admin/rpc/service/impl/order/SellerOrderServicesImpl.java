package com.yuanrong.admin.rpc.service.impl.order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.bean.order.SellerOrder;
import com.yuanrong.admin.result.SellerOrderResult;
import com.yuanrong.admin.rpc.api.order.SellerOrderServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.seach.OrderPriceParamSearch;
import com.yuanrong.admin.seach.SellerOrderSearch;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.ResultTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 卖家订单的services实现类
 * Created MDA
 */
@Service
public class SellerOrderServicesImpl extends BaseServicesAbstract<SellerOrder> implements SellerOrderServicesI {
    @Override
    public SellerOrder getById(Integer id) {
        return sellerOrderDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        sellerOrderDaoI.deleteById(id);
    }
    @Override
    public void save(SellerOrder object) {
        sellerOrderDaoI.save(object);
    }
    @Override
    public List<SellerOrder> getAll() {
        return sellerOrderDaoI.getAll();
    }
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() );
        List<SellerOrder> result = sellerOrderDaoI.list(data);

        /*for (SellerOrder sellerOrder : result) {
            //查询应约回复
            Integer orderInfoSellerId = sellerOrder.getOrderInfoSeller()==null?0:sellerOrder.getOrderInfoSeller().getOrderInfoSellerId();
            if(orderInfoSellerId!=0){
                sellerOrder.setOrderInfoOfferList(orderInfoSellerDaoI.getOrderInfoOffer(orderInfoSellerId));
            }
        }*/
        return new PageInfo(result);
    }

    @Override
    public List<SellerOrder> getByEntity(SellerOrder sellerOrder) {
        return null;
    }

    @Override
    public SellerOrder getByOrderSn(String orderSn) {
        return sellerOrderDaoI.getByOrderSn(orderSn);
    }
    /**
     *@author songwq
     *@param
     *@date 2018/9/19
     *@description 修改价格
     */
    @Override
    public void updateProductPrice(OrderPriceParamSearch orderPriceParamSearch,SellerOrder sellerOrder) {
        //修改卖家订单（OrderPriceParamSearch存放的是集合，需要改为单个值）
        sellerOrder.setPrice(orderPriceParamSearch.getSellerPrice());
        sellerOrder.setPayable(orderPriceParamSearch.getPayAble());
        sellerOrder.setSellerServiceRate(orderPriceParamSearch.getSellerServiceRate());
        sellerOrderDaoI.updateSellerOrder(sellerOrder);
        //修改报名表的卖家佣金
        OrderInfoSeller seller = orderInfoSellerDaoI.getBySellerOrderSn(orderPriceParamSearch.getOrderSn());
        if(seller!=null && (seller.getSellerServiceRate()==null
                || seller.getSellerServiceRate().compareTo(orderPriceParamSearch.getSellerServiceRate())!=0)){
            seller.setSellerServiceRate(orderPriceParamSearch.getSellerServiceRate());
            orderInfoSellerDaoI.updateOrderSeller(seller);
        }
    }

    @Override
    public SellerOrderResult getDetailBySn(SellerOrderSearch sellerOrderSearch) {
        return sellerOrderDaoI.getDetailBySn(sellerOrderSearch);
    }

    @Override
    public List<Map<String, Object>> getOrderStatusCount(SellerOrderSearch data) {
        return sellerOrderDaoI.getOrderStatusCount(data);
    }
}

package com.yuanrong.admin.server.controller.order;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.order.OrderDetail;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 订单明细的controller
 * Created MDA
 */
@Controller
@RequestMapping("order")
public class OrderDetailController extends BaseController {
    private static final Logger logger = Logger.getLogger(OrderDetailController.class);
    @RequestMapping( value = "orderDetail_list" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(OrderDetail data , BaseModel baseModel){
        PageInfo orderDetailPageInfo = orderDetailServicesI.list(data , baseModel);
        JSONArray array = new JSONArray();
        if(CollectionUtil.size(orderDetailPageInfo.getList()) > 0){
        }
        return new ResultTemplate(orderDetailPageInfo , array);
    }
    @RequestMapping("orderDetail_save")
    public String save(OrderDetail user){
        
        return "order/systemUser_save";
    }
    @RequestMapping( value = "orderDetail_saveOk" , method = RequestMethod.POST)
    public void saveOk(OrderDetail theOrderDetail){
        orderDetailServicesI.save(theOrderDetail);
    }
    @RequestMapping("orderDetail_update")
    public String update(){
        return "order/orderDetail_update";
    }
    @RequestMapping( value = "orderDetail_updateOk"  , method = RequestMethod.POST)
    public void updateOk(){
    }
}

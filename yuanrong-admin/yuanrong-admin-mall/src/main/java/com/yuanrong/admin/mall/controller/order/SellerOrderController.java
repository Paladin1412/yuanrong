package com.yuanrong.admin.mall.controller.order;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.order.SellerOrder;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 卖家订单的controller
 * Created MDA
 */
@Controller
@RequestMapping("order")
public class SellerOrderController extends BaseController {
    private static final Logger logger = Logger.getLogger(SellerOrderController.class);
}

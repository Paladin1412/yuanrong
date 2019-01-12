package com.yuanrong.admin.server.controller.order;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.order.OrderCostInfo;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
/**
 * 买家订单附加费用的controller
 * Created MDA
 */
@Controller
@RequestMapping("order")
public class OrderCostInfoController extends BaseController {
    private static final Logger logger = Logger.getLogger(OrderCostInfoController.class);

}

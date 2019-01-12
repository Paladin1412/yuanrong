package com.yuanrong.admin.web.controller.pay;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.pay.PayWater;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 支付流水的controller
 * Created MDA
 */
@Controller
@RequestMapping("pay")
public class PayWaterController extends BaseController {
    private static final Logger logger = Logger.getLogger(PayWaterController.class);
}

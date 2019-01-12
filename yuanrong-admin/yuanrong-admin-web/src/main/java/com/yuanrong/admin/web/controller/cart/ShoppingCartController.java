package com.yuanrong.admin.web.controller.cart;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.cart.ShoppingCart;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
/**
 * 购物车的controller
 * Created MDA
 */
@Controller
@RequestMapping("cart")
public class ShoppingCartController extends BaseController {
    private static final Logger logger = Logger.getLogger(ShoppingCartController.class);

}

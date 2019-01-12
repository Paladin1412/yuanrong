package com.yuanrong.admin.mall.controller.base;

import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.common.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * SELECT `global_seq_nextval`('GLOBAL_CAT_ID');
 * <p>
 * SELECT `global_seq_nextval`('GLOBAL_ARTICLE_ID');
 * <p>
 * 访问应用首先走此controller
 *
 * @author: makaidong
 * @Date: 2013-06-05
 */
@Controller
public class DefaultController extends BaseController {

    protected transient Logger log = LoggerFactory.getLogger(getClass());

//    @RequestMapping(value = "/", method = {RequestMethod.GET})
//    public ModelAndView index(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession(true);
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("index");
//        String username = String.valueOf(session.getAttribute(Constant.USER_INFO));
//        return mv;
//
//    }

    @RequestMapping(value = "/contentBank/content_bank.html", method = RequestMethod.GET)
    public ModelAndView contentBank(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contentBank/content_bank");
        return mv;
    }

    @RequestMapping(value = "/admin/index.html", method = RequestMethod.GET)
    public ModelAndView admin_index(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/index");
        return mv;
    }

    @RequestMapping(value = "/ipTrade/ip_trade_distribution.html", method = RequestMethod.GET)
    public String ipTrade_ip_trade_distribution(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ipTrade/ip_trade_distribution");
        return "ipTrade/ip_trade_distribution";
    }
    @RequestMapping(value = "/ipEvaluation/ip_ranking_creation.html", method = RequestMethod.GET)
    public ModelAndView ipEvaluation_ip_ranking_creation(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ipEvaluation/ip_ranking_creation");
        return mv;
    }
    @RequestMapping(value = "/ipEvaluation/ip_data_description.html", method = RequestMethod.GET)
    public ModelAndView ipEvaluation_ip_data_description(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ipEvaluation/ip_data_description");
        return mv;
    }
    @RequestMapping(value = "/h5/login.html", method = RequestMethod.GET)
    public ModelAndView h5_login(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("h5/login");
        return mv;
    }
  
    @RequestMapping(value = "/ipEvaluation/ip_ranking_article.html", method = RequestMethod.GET)
    public ModelAndView ipEvaluation_ip_ranking_article(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ipEvaluation/ip_ranking_article");
        return mv;
    }

    @RequestMapping(value = "/admin/roleManage.html", method = RequestMethod.GET)
    public ModelAndView admin_roleManage(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/roleManage");
        return mv;
    }

	 @RequestMapping(value="/contentBank/graphics_writer_{recid}.html",method= RequestMethod.GET)
	 public ModelAndView graphics_writer(ModelMap model, HttpServletRequest request, HttpServletResponse response , @PathVariable String recid) {
         ModelAndView mv = new ModelAndView();
         Map<String, String> param = new HashMap<String, String>();
         param.put("recid", recid);
         mv.addAllObjects(param);
         mv.setViewName("contentBank/graphics_writer");
         return mv;
     }
    @RequestMapping(value = "/admin/userManage.html", method = RequestMethod.GET)
    public ModelAndView admin_userManage(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/userManage");
        return mv;

    }
    @RequestMapping(value = "/contentBank/demand_hall.html", method = RequestMethod.GET)
    public ModelAndView contentBank_demand_hall(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contentBank/demand_hall");
        return mv;

    }

    @RequestMapping(value = "/contentBank/work_detail_{recid}.html", method = RequestMethod.GET)
    public ModelAndView contentBank_work_detail(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable String recid) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contentBank/work_detail");
        Map<String, String> param = new HashMap<String, String>();
        param.put("recid", recid);
        mv.addAllObjects(param);
        return mv;

    }

    @RequestMapping(value = "/demandHall/demand_hall.html", method = RequestMethod.GET)
    public ModelAndView demandHall_demand_hall(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("demandHall/demand_hall");
        return mv;

    }

    @RequestMapping(value = "/demandHall/demand_publish.html", method = RequestMethod.GET)
    public ModelAndView demandHall_demand_publish(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("demandHall/demand_publish");
        return mv;

    }

    @RequestMapping(value = "/demandHall/demand_author1_{id}.html", method = RequestMethod.GET)
    public ModelAndView demand_author1(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        ModelAndView mv = new ModelAndView();
        Map<String, String> param = new HashMap<String, String>();
        param.put("id", id);
        mv.addAllObjects(param);
        mv.setViewName("demandHall/demand_author1");
        return mv;
    }

    @RequestMapping(value = "/demandHall/demand_author2_{id}.html", method = RequestMethod.GET)
    public ModelAndView demand_author2(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        ModelAndView mv = new ModelAndView();
        Map<String, String> param = new HashMap<String, String>();
        param.put("id", id);
        mv.addAllObjects(param);
        mv.setViewName("demandHall/demand_author2");
        return mv;
    }

    @RequestMapping(value = "/h5/index.html", method = RequestMethod.GET)
    public ModelAndView h5_index(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("h5/index");
        return mv;
    }

    @RequestMapping(value = "/admin/addUser.html", method = RequestMethod.GET)
    public ModelAndView admin_addUser(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/addUser");
        return mv;
    }


    @RequestMapping(value = "/admin/shouye.html", method = RequestMethod.GET)
    public ModelAndView admin_shouye(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/shouye");
        return mv;
    }

    @RequestMapping(value = "/admin/needList.html", method = RequestMethod.GET)
    public ModelAndView admin_needList(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/needList");
        return mv;
    }

    @RequestMapping(value = "/admin/userList.html", method = RequestMethod.GET)
    public ModelAndView admin_userList(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/userList");
        return mv;
    }

    @RequestMapping(value = "/admin/needsDetail.html", method = RequestMethod.GET)
    public ModelAndView admin_neddsDetail(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/needsDetail");
        return mv;
    }

    @RequestMapping(value = "/admin/login.html", method = RequestMethod.GET)
    public ModelAndView admin_login(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/login");
        return mv;
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public ModelAndView login(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }


    @RequestMapping(value = "/contentBank/publish_demand.html", method = RequestMethod.GET)
    public ModelAndView publish_demand(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contentBank/publish_demand");
        return mv;
    }

    @RequestMapping(value = "/contentBank/original_work.html", method = RequestMethod.GET)
    public ModelAndView original_work(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contentBank/original_work");
        return mv;
    }

    @RequestMapping(value = "/contentBank/author_images.html", method = RequestMethod.GET)
    public String author_images() {
        return "contentBank/author_images";
    }


    @RequestMapping(value = "/ipTrade/ip_trade.html", method = RequestMethod.GET)
    public ModelAndView ipTrade(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ipTrade/ip_trade");
        return mv;
    }

    @RequestMapping(value = "/ipEvaluation/ip_evaluation.html", method = RequestMethod.GET)
    public ModelAndView ipEvaluation(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ipEvaluation/ip_evaluation");
        return mv;
    }

    @RequestMapping(value = "/aboutUs/about_us.html", method = RequestMethod.GET)
    public ModelAndView aboutUs(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aboutUs/about_us");
        return mv;
    }



    @RequestMapping(value = "/contentBank/video_auther_{recid}.html", method = RequestMethod.GET)
    public ModelAndView video_auther(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable String recid) {
        ModelAndView mv = new ModelAndView();
        Map<String, String> param = new HashMap<String, String>();
        param.put("recid", recid);
        mv.addAllObjects(param);
        mv.setViewName("contentBank/video_auther");
        return mv;
    }

    @RequestMapping(value = "/cart/cart_author.html", method = RequestMethod.GET)
    public ModelAndView cart_cart_author(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cart/cart_author");
        return mv;
    }
    @RequestMapping(value = "/cart/cart_article.html", method = RequestMethod.GET)
    public ModelAndView cart_cart_article(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cart/cart_article");
        return mv;
    }
    @RequestMapping(value = "/cart/cart_distribution.html", method = RequestMethod.GET)
    public ModelAndView cart_cart_distribubtion(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cart/cart_distribution");
        return mv;
    }

    @RequestMapping(value = "/demandHall/demand_work1_{recid}.html", method = RequestMethod.GET)
    public ModelAndView demand_work1(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable String recid) {
        ModelAndView mv = new ModelAndView();
        Map<String, String> param = new HashMap<String, String>();
        param.put("recid", recid);
        mv.addAllObjects(param);
        mv.setViewName("demandHall/demand_work1");
        return mv;
    }
    @RequestMapping(value = "/demandHall/demand_work2_{recid}.html", method = RequestMethod.GET)
    public ModelAndView demand_work2(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable String recid) {
        ModelAndView mv = new ModelAndView();
        Map<String, String> param = new HashMap<String, String>();
        param.put("recid", recid);
        mv.addAllObjects(param);
        mv.setViewName("demandHall/demand_work2");
        return mv;
    }

    @RequestMapping(value = "/playAround/play_around.html", method = RequestMethod.GET)
    public ModelAndView playAround_play_around(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("playAround/play_around");
        return mv;
    }

    @RequestMapping(value = "/segmentAccount/segment_account.html", method = RequestMethod.GET)
    public ModelAndView segmentAccount_segment_account(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("segmentAccount/segment_account");
        return mv;
    }

    /*@RequestMapping(value="/contentBank/abnormal_author_detail_{recid}.html",method= RequestMethod.GET)
    public ModelAndView abnormal_author_detail(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable String recid) {
        ModelAndView mv = new ModelAndView();
        Map<String, String> param = new HashMap<String, String>();
        param.put("recid", recid);
        mv.addAllObjects(param);
        mv.setViewName("/contentBank/abnormal_author_detail");
        return mv;
    }
*/
    /**
     * 获得当的访问路径
     * <p>
     * HttpServletRequest.getRequestURL+"?"+HttpServletRequest.getQueryString
     *
     * @param request
     * @return
     */
    public static String getLocation(HttpServletRequest request) {
        UrlPathHelper helper = new UrlPathHelper();
        StringBuffer buff = request.getRequestURL();
        String uri = request.getRequestURI();
        String origUri = helper.getOriginatingRequestUri(request);
        buff.replace(buff.length() - uri.length(), buff.length(), origUri);
        String queryString = helper.getOriginatingQueryString(request);
        if (queryString != null) {
            buff.append("?").append(queryString);
        }
        return buff.toString();
    }


}

package com.yuanrong.admin.server.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.system.SystemMenu;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/4/19.
 */
@Controller
@RequestMapping("system")
public class SystemMenuController extends BaseController {
    @RequestMapping("list")
    @ResponseBody
    public ResultTemplate list(SystemMenu data , BaseModel baseModel){
        PageInfo<SystemMenu> systemMenuPageInfo = systemMenuServicesI.list(data,baseModel);
        return new ResultTemplate("",systemMenuPageInfo);
    }

    @RequestMapping("exs_selectMenu")
    @ResponseBody
    public ResultTemplate selectMenu( ){

        List<SystemMenu>   systemMenuList = (List<SystemMenu>)getSession().getAttribute("menus");

        //最后的结果https://blog.csdn.net/frankcheng5143/article/details/52958486
        List<SystemMenu> systemMenuListRnt = new ArrayList<SystemMenu>();
        JSONArray menuList = new JSONArray();
        //先找到所有的一级菜单
        for(SystemMenu  systemMenu_1 :systemMenuList){
            if(systemMenu_1.getParentMenuId()==null && systemMenu_1.getTypeIndex()==1){
                JSONObject menuInfo = new JSONObject();
                menuInfo.put("menuName",systemMenu_1.getMenuName());
                menuInfo.put("path",systemMenu_1.getPath());
                menuInfo.put("hasChildren",false);
//                systemMenuListRnt.add(systemMenu_1);
                JSONArray menuList_1 = new JSONArray();
                for(SystemMenu  systemMenu_2 : systemMenuList){
                    if (systemMenu_2.getParentMenuId() !=null && systemMenu_1.getSystemMenuId()==systemMenu_2.getParentMenuId() && systemMenu_2.getTypeIndex()==1) {
                        JSONObject menuInfo_1 = new JSONObject();
                        menuInfo_1.put("childMenuName",systemMenu_2.getMenuName());
                        menuInfo_1.put("path",systemMenu_2.getPath());
                        menuInfo_1.put("hasChildren",false);
//                systemMenuListRnt.add(systemMenu_1);
                        JSONArray menuList_2 = new JSONArray();
                        for(SystemMenu  systemMenu_3 : systemMenuList){
                            if (systemMenu_3.getParentMenuId() != null && systemMenu_2.getSystemMenuId()==systemMenu_3.getParentMenuId() && systemMenu_3.getTypeIndex()==1) {
                                JSONObject menuInfo_3 = new JSONObject();
                                menuInfo_3.put("childMenuName",systemMenu_3.getMenuName());
                                menuInfo_3.put("path",systemMenu_3.getPath());
                                menuInfo_3.put("hasChildren",false);
                                menuList_2.add(menuInfo_3);
                            }
                        }
                        menuInfo_1.put("childrenMenu2",menuList_2);
                        menuList_1.add(menuInfo_1);
//                        systemMenu_1.getChildMenus().add(systemMenu_2);
                    }
                }
                menuInfo.put("childrenMenu",menuList_1);
                menuList.add(menuInfo);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
//        for(SystemMenu systemMenu_2: systemMenuListRnt){
//            systemMenu_2.setChildMenus(getChild(systemMenu_2.getSystemMenuId(),systemMenuList));
//        }

        //获取首页数据
//        SystemMenu systemMenuHome = new SystemMenu();
//        for(SystemMenu systemMenu :systemMenuList ){
//            if(systemMenu.getParentMenuId()==0){
//                systemMenuHome.setMenuIco(systemMenu.getMenuIco());
//                systemMenuHome.setSystemMenuId(systemMenu.getSystemMenuId());
//                systemMenuHome.setMenuPath(systemMenu.getMenuPath());
//                systemMenuHome.setMenuName(systemMenu.getMenuName());
//                break;
//            }
//        }
//        systemMenuListRnt.add(systemMenuHome);

        return new ResultTemplate("",menuList);
    }

    /**
     * 递归查找子菜单
     *
     * @param id
     *            当前菜单id
     * @param rootMenu
     *            要查找的列表
     * @return
     */
    private  static List<SystemMenu> getChild(int id , List<SystemMenu> rootMenu){
        // 子菜单
        List<SystemMenu> childList = new ArrayList<>();
        for (SystemMenu systemMenu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            //   if (StringUtils.isNotBlank(menu.getParentId())) {
            if(systemMenu.getParentMenuId()>0){
                //if (systemMenu.getParentId().equals(id)) {
                if (systemMenu.getParentMenuId()==id) {
                    childList.add(systemMenu);
                }
            }
        }

        // 把子菜单的子菜单再循环一遍
        for (SystemMenu systemMenu : childList) {// 没有url子菜单还有子菜单
            if (systemMenu.getTypeIndex() == 0) {
                // 递归
                systemMenu.setChildMenus(getChild(systemMenu.getSystemMenuId(), rootMenu));
            }
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

}


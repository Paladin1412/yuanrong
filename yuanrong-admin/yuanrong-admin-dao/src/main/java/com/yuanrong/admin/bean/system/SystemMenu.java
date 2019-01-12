package com.yuanrong.admin.bean.system;

import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhonghang on 2018/4/19.
 * 菜单
 */
public class SystemMenu extends BaseBean implements Serializable {
    /**
     * ID
     */
    private int systemMenuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单路径
     */
    private String path;
    /**
     * 菜单获取数据地址
     */
    private String menuPath;
    /**
     * 菜单图标
     */
    private String menuIco;
    /**
     * 排序
     */
    private int sort;

    /**
     * 菜单类型
     */
    private int typeIndex;
    /**
     * 子菜单列表
     */
    private List<SystemMenu> childMenus;
    /**
     * 父菜单
     */
    private Integer parentMenuId;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<SystemMenu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<SystemMenu> childMenus) {
        this.childMenus = childMenus;
    }


    public int getSystemMenuId() {
        return systemMenuId;
    }

    public void setSystemMenuId(int systemMenuId) {
        this.systemMenuId = systemMenuId;
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getMenuIco() {
        return menuIco;
    }

    public void setMenuIco(String menuIco) {
        this.menuIco = menuIco;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(int typeIndex) {
        this.typeIndex = typeIndex;
    }
}

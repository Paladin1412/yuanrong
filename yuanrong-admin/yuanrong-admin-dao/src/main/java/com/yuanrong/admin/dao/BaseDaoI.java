package com.yuanrong.admin.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Vector;

/**
 * Created by zhonghang on 2018/4/11.
 */
public interface BaseDaoI<T> {

    /**
     * 通过ID搜索
     * @param id
     * @return
     */
    T getById(@Param("id") Integer id);
    /**
     * 通过ID集合搜索
     * @param
     * @return
     */
    List<T> getListByIds(@Param("ids") List<Integer> ids);

    /**
     * 删除指定ID记录
     * @param id
     */
    void deleteById(@Param("id") Integer id);

    /**
     * 新增
     * @param data
     */
    void save(@Param("data") T data);

    /**
     * 获取所有数据
     * @return
     */
    List<T> getAll();

    /**
     * 查询
     * @param data
     * @return
     */
    List<T> list(@Param("data") Object data);

}

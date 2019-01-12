package com.yuanrong.admin.rpc;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.util.BaseModel;

import java.util.List;

/**
 * Created by zhonghang on 2018/4/19.
 */
public interface BaseServicesI<T> {
    /**
     * 通过ID搜索
     * @param id
     * @return
     */
    T getById( Integer id);

    /**
     * 删除指定ID记录
     * @param id
     */
    void deleteById( Integer id);

    /**
     * 新增
     * @param object
     */
    void save( T object);

    /**
     * 获取所有数据
     * @return
     */
    List<T> getAll();

    /**
     * 分页查询
     * @param data
     * @param baseModel
     * @return
     */
    PageInfo list(Object data , BaseModel baseModel);
}

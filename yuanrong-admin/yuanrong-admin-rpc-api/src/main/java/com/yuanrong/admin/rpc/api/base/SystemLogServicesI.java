package com.yuanrong.admin.rpc.api.base;
import com.alibaba.fastjson.JSONArray;
import com.yuanrong.admin.bean.base.SystemLog;
import com.yuanrong.admin.rpc.BaseServicesI;

import java.util.List;

/**
 * 系统日志的services接口
 * Created MDA
 */
public interface SystemLogServicesI extends BaseServicesI<SystemLog> {
    /**
     * 根据类路径和目标ID获取日志
     * @param classPath
     * @param id
     * @return
     */
    List<SystemLog> getByClassPathAndId (String classPath , String id);

    /**
     * 根据类路径和目标ID获取日志 ，返回josnArray
     * @param classPath
     * @param id
     * @return
     */
    JSONArray getJSONArrayByClassPathAndId (String classPath , String id);

    /**
     * 增加日志
     * @param classPath 类路径
     * @param id 目标id
     * @param msg 操作信息
     * @param operator 操作人
     */
    void log(String classPath , int id , String msg , String operator);
}

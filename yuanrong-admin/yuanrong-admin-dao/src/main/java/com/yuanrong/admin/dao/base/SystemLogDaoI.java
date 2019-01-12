package com.yuanrong.admin.dao.base;
import com.yuanrong.admin.bean.base.SystemLog;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统日志的dao
 * Created MDA
 */
@Repository
public interface SystemLogDaoI extends BaseDaoI<SystemLog> {
    /**
     * 根据类路径和目标ID获取日志
     * @param classPath
     * @param id
     * @return
     */
    List<SystemLog> getByClassPathAndId (@Param("classPath") String classPath ,@Param("id") String id);

    /**
     * 增加日志
     * @param classPath
     * @param id
     * @param msg
     * @param operator
     */
    void log(@Param("classPath") String classPath , @Param("id") int id ,@Param("msg")  String msg , @Param("operator") String operator);
}

package com.yuanrong.admin.dao.config;
import com.yuanrong.admin.bean.config.Configuration;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 系统配置的dao
 * Created MDA
 */
@Repository
public interface ConfigurationDaoI extends BaseDaoI<Configuration> {
    /**
     * 通过key获取值
     * @param key
     * @return
     */
    public String getbyKey(@Param("key") String key);
}

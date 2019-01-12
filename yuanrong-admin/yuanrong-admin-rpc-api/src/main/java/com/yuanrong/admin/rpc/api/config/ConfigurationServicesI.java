package com.yuanrong.admin.rpc.api.config;
import com.yuanrong.admin.bean.config.Configuration;
import com.yuanrong.admin.rpc.BaseServicesI;
/**
 * 系统配置的services接口
 * Created MDA
 */
public interface ConfigurationServicesI extends BaseServicesI<Configuration> {
    /**
     * 通过key获取值
     * @param key
     * @return
     */
    public String getbyKey(String key);
}

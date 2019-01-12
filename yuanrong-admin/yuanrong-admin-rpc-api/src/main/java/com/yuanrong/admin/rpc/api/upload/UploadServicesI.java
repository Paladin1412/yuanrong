package com.yuanrong.admin.rpc.api.upload;

import com.yuanrong.common.oss.CloudStorageConfig;

/**
 * Created by zhonghang on 2018/7/5.
 */
public interface UploadServicesI {
    /**
     * 获取七牛上传token
     * @return
     */
    public String getUploadToken();

    /**
     * 获取云存储配置信息
     * @return
     */
    CloudStorageConfig getCloudStorageConfig();
}

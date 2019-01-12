package com.yuanrong.common.oss;

/**
 * Created by zhonghang on 2018-01-19.
 */
public final class CloudStorageFactory {
    private CloudStorageFactory(){}
    public static CloudStorageServiceAbstract build(CloudStorageConfig config){
        return new QiniuCloudStorageServiceImpl(config);
    }
}

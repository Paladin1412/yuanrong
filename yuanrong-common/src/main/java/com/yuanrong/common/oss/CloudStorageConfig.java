package com.yuanrong.common.oss;

import java.io.Serializable;

/**
 * Created by zhonghang on 2018-01-19.
 * 参数Bean
 */
public class CloudStorageConfig implements Serializable {
    /**
     * 存储平台ak
     */
    private String accessKey;
    /**
     * 存储平台sk
     */
    private String secretKey;
    /**
     * 存储平台域名
     */
    private String domain;
    /**
     * 存储平台，存储空间
     */
    private String csBucketName;

    /**
     * 路径前缀
     */
    private String csPrefix;
    /**
     * endPoint
     */
    private String endPoint;

    /**
     * 所属地区
     */
    private String region;


    public CloudStorageConfig(String accessKey, String secretKey, String domain, String csBucketName, String csPrefix, String endPoint, String region) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.domain = domain;
        this.csBucketName = csBucketName;
        this.csPrefix = csPrefix;
        this.endPoint = endPoint;
        this.region = region;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCsBucketName() {
        return csBucketName;
    }

    public void setCsBucketName(String csBucketName) {
        this.csBucketName = csBucketName;
    }

    public String getCsPrefix() {
        return csPrefix;
    }

    public void setCsPrefix(String csPrefix) {
        this.csPrefix = csPrefix;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}

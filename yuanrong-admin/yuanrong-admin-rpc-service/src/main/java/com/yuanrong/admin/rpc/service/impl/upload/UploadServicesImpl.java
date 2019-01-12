package com.yuanrong.admin.rpc.service.impl.upload;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.util.Auth;
import com.yuanrong.admin.rpc.api.upload.UploadServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.common.oss.CloudStorageConfig;
import com.yuanrong.common.util.SystemParam;
import org.springframework.stereotype.Service;

/**
 * Created by zhonghang on 2018/7/5.
 */
@Service
public class UploadServicesImpl extends BaseServicesAbstract implements UploadServicesI {
    @Override
    public String getUploadToken() {
        Auth auth = Auth.create(configurationDaoI.getbyKey(SystemParam.QINIU_ACCESSKEY),
                configurationDaoI.getbyKey(SystemParam.QINIU_SECRETKEY));
        String uptoken = auth.uploadToken(configurationDaoI.getbyKey(SystemParam.QINIU_BUCKET));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uptoken", uptoken);
        return jsonObject.toJSONString();
    }

    @Override
    public CloudStorageConfig getCloudStorageConfig() {
        return new CloudStorageConfig(configurationDaoI.getbyKey(SystemParam.QINIU_ACCESSKEY),
                configurationDaoI.getbyKey(SystemParam.QINIU_SECRETKEY),
                configurationDaoI.getbyKey(SystemParam.QINIU_DOMAIN),
                configurationDaoI.getbyKey(SystemParam.QINIU_BUCKET),
                null,null,null);
    }
}

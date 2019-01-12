package com.yuanrong.common.oss;

import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.yuanrong.common.util.StringUtil;
import org.apache.commons.io.IOUtils;
import org.apache.tools.ant.util.DateUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.UUID;

/**
 * Created by zhonghang on 2018/10/15.
 * 七牛云存储
 */
public class QiniuCloudStorageServiceImpl extends CloudStorageServiceAbstract {
    private UploadManager uploadManager;
    private String token;
    //密钥配置
    private Auth auth ;


    public QiniuCloudStorageServiceImpl(CloudStorageConfig config){
        this.config = config;
        uploadManager = new UploadManager(new Configuration(Zone.zone1()));
        token = Auth.create(config.getAccessKey(), config.getSecretKey()).
                uploadToken(config.getCsBucketName());
        auth = Auth.create(config.getAccessKey(), config.getSecretKey());
    }

    @Override
    public String upload(byte[] data, String path) {
        try {
            Response res = uploadManager.put(data, path, token);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("上传文件失败，请核对七牛配置信息", e);
        }
        return  config.getDomain()+ "/" + path;
    }

    @Override
    public String upload(byte[] data) {
        return upload(data, getPath(null));
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, path);
        } catch (IOException e) {
            throw new RuntimeException("上传文件失败", e);
        }
    }

    @Override
    public String upload(InputStream inputStream) {
        return upload(inputStream, getPath(null));
    }

    @Override
    public void down(OutputStream os, String path) throws IOException {
        InputStream is = null;
        try{
            String downLoadUrl = auth.privateDownloadUrl(path);
            URL url = new URL(downLoadUrl);
            URLConnection con = url.openConnection();
            is = con.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.flush();
        }catch (Exception e){

        }finally {
            is.close();
        }
    }

    /**
     * 文件路径
     * @param prefix 前缀
     * @return 返回上传路径
     */
    public String getPath(String prefix) {
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件路径
        String path = DateUtils.format(new Date(), "yyyyMMdd") + "/" + uuid;

        if(StringUtil.isNotBlank(prefix)){
            path = prefix + "/" + path;
        }

        return path;
    }
}

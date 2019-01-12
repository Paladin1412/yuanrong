package com.yuanrong.admin.rpc.api.notice;

import com.yuanrong.admin.Enum.EnumWeChartType;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhonghang on 2018/8/3.
 */
public interface WeChatServicesI {
    /**
     * 发送图文消息给指定用户
     * @param weChartType
     * @param title
     * @param description
     * @param picUrl
     * @param url
     * @param toUser
     * @param agentId
     * @throws WxErrorException
     * @throws WxErrorException
     */
    public  void sendNewArticle(EnumWeChartType weChartType, String title , String description , String picUrl , String url , String toUser , Integer agentId) throws WxErrorException, WxErrorException;

    /**
     * 发送图文消息给指定部门
     * @param weChartType
     * @param title
     * @param description
     * @param picUrl
     * @param url
     * @param parytID
     * @param agentId
     * @throws WxErrorException
     * @throws WxErrorException
     */
    public  void sendNewArticleToParty(EnumWeChartType weChartType , String title , String description , String picUrl , String url ,String parytID ,Integer agentId) throws WxErrorException, WxErrorException;

    /**
     * 发送文件给指定人
     * @param weChartType
     * @param agentId
     * @param mediaId
     * @throws WxErrorException
     */
    public  void sendMediaMsg(EnumWeChartType weChartType , Integer agentId , String mediaId, String toUser) throws WxErrorException;

    /**
     * 发送Bug给工程师
     * @param weChartType
     * @param title
     * @param description
     * @param picUrl
     * @param url
     */
    public  void sendBugToEngineer(EnumWeChartType weChartType , String title , String description , String picUrl , String url);

    /**
     * 发送文本给指定人
     * @param weChartType
     * @param msg
     * @param toUser
     * @throws WxErrorException
     */
    public  void sendText(EnumWeChartType weChartType , String msg , String toUser) throws WxErrorException;

    /**
     * 上传文件到微信
     * @param in
     * @return
     * @throws WxErrorException
     * @throws IOException
     */
    public  WxMediaUploadResult uploadFile(String mediaType, String fileType, InputStream in) throws WxErrorException, IOException;

    /**
     * 上传文件到微信
     * @param mediaType
     * @param file
     * @return
     */
    public  WxMediaUploadResult uploadFile(String mediaType , File file) throws WxErrorException;

}

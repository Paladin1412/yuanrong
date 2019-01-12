package com.yuanrong.admin.rpc.service.impl.notice;

import com.yuanrong.admin.Enum.EnumWeChartType;
import com.yuanrong.admin.rpc.api.notice.WeChatServicesI;
import com.yuanrong.admin.rpc.service.impl.config.ConfigProfile;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.config.WxCpInMemoryConfigStorage;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhonghang on 2018/8/3.
 */
@Service
public class WeChatServicesImpl implements WeChatServicesI {
    private static final Logger logger = Logger.getLogger(WeChatServicesImpl.class);
    private static final String DEV_USER = "|TangZheng|ZhongHang|MaKaiDong|JiaoShaoShao|ShiLingHuai";
    private static WxCpServiceImpl noticeWxCpService ;
    private static WxCpServiceImpl bugWxCpService;
    private WxCpServiceImpl getWxCpService(EnumWeChartType weChartType){
        if(noticeWxCpService == null){
            noticeWxCpService = new WxCpServiceImpl();
            WxCpInMemoryConfigStorage config = new WxCpInMemoryConfigStorage();
            config.setCorpId(ConfigProfile.WX_CROP_ID);
            config.setCorpSecret(ConfigProfile.WX_NOTICE_SECRET);
            config.setAgentId(ConfigProfile.WX_NOTICE_AGENTID);
            noticeWxCpService.setWxCpConfigStorage(config);
        }
        if(bugWxCpService == null){
            bugWxCpService = new WxCpServiceImpl();
            WxCpInMemoryConfigStorage config = new WxCpInMemoryConfigStorage();
            config.setCorpId(ConfigProfile.WX_CROP_ID);

            config.setCorpSecret(ConfigProfile.WX_BUG_SECRET);
            config.setAgentId(ConfigProfile.WX_BUG_AGENTID);
            bugWxCpService.setWxCpConfigStorage(config);
        }
        return weChartType.getIndex() == EnumWeChartType.通知.getIndex() ? noticeWxCpService : bugWxCpService;
    }

    public  void sendNewArticle(EnumWeChartType weChartType , String title , String description , String picUrl , String url , String toUser , Integer agentId) throws WxErrorException, WxErrorException {
        NewArticle newArticle = new NewArticle();
        newArticle.setUrl(url);
        newArticle.setTitle(title);
        newArticle.setDescription(description);
        newArticle.setPicUrl(picUrl);
        WxCpMessage message = WxCpMessage.NEWS().agentId(agentId).addArticle(newArticle).toUser(toUser+DEV_USER).build();
        getWxCpService(weChartType).messageSend(message);
    }
    public  void sendNewArticleToParty(EnumWeChartType weChartType , String title , String description , String picUrl , String url ,String parytID ,Integer agentId) throws WxErrorException, WxErrorException {
        NewArticle newArticle = new NewArticle();
        newArticle.setUrl(url);
        newArticle.setTitle(title);
        newArticle.setDescription(description);
        newArticle.setPicUrl(picUrl);
        WxCpMessage message = WxCpMessage.NEWS().agentId(agentId).addArticle(newArticle).toParty(parytID).build();
        getWxCpService(weChartType).messageSend(message);
    }

    public  void sendMediaMsg(EnumWeChartType weChartType , Integer agentId , String mediaId , String toUser) throws WxErrorException {
        WxCpMessage message = WxCpMessage.FILE().agentId(agentId).mediaId(mediaId).toUser(toUser+DEV_USER).build();
        getWxCpService(weChartType).messageSend(message);
    }

    public  void sendBugToEngineer(EnumWeChartType weChartType , String title , String description , String picUrl , String url){
        try {
            sendNewArticleToParty(weChartType ,title , description , picUrl , url , "2" , ConfigProfile.WX_BUG_AGENTID);
        } catch (WxErrorException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    public  void sendText(EnumWeChartType weChartType , String msg , String toUser) throws WxErrorException {
        WxCpMessage message = WxCpMessage.TEXT().content(msg).toUser(toUser+DEV_USER).build();
        getWxCpService(weChartType).messageSend(message);
    }

    /**
     * 上传文件到微信
     * @param in
     * @return
     * @throws WxErrorException
     * @throws IOException
     */
    public  WxMediaUploadResult uploadFile(String mediaType, String fileType, InputStream in) throws WxErrorException, IOException {
        return getWxCpService(EnumWeChartType.通知).getMediaService().upload(mediaType , fileType ,in);
    }

    @Override
    public WxMediaUploadResult uploadFile(String mediaType, File file) throws WxErrorException {
        return getWxCpService(EnumWeChartType.通知).getMediaService().upload(mediaType , file);
    }
}

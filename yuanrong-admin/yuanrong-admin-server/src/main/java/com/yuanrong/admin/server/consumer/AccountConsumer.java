package com.yuanrong.admin.server.consumer;

/**
 * Created by zhonghang on 2018/8/1.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.yuanrong.admin.Enum.EnumWeChartType;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.rpc.api.account.PlatformIPAccountServicesI;
import com.yuanrong.admin.rpc.api.base.SystemLogServicesI;
import com.yuanrong.admin.rpc.api.notice.WeChatServicesI;
import com.yuanrong.admin.util.CollectionUtil;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * 消息到达消费者监听类
 */
public class AccountConsumer implements ChannelAwareMessageListener {
    private static final Logger logger = Logger.getLogger(AccountConsumer.class);
    @Autowired
    PlatformIPAccountServicesI platformIPAccountServicesI;
    @Autowired
    SystemLogServicesI systemLogServicesI;

    @Autowired
    WeChatServicesI weChatServicesI;
    /**
     * 处理收到的rabbit消息的回调方法。
     * @param message AMQP封装消息对象
     * @param channel 信道对象，可以进行确认回复
     * @throws Exception Any.
     */
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(message.getBody());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            JSONObject queInfo = (JSONObject) objectInputStream.readObject();
            String toUser = queInfo.getString("cpWeiXinId");
            String msgTime = queInfo.getString("createdTime");
            logger.info("消费者收到消息："+queInfo.toJSONString());
            List<PlatformIPAccount> failAccount =  platformIPAccountServicesI.saveQueueMQ(queInfo);
            //失败账号生成Excel发送消息给创建人
            if(CollectionUtil.size(failAccount) > 0){
                weChatServicesI.sendText(EnumWeChartType.通知 , "您好：您在["+msgTime+"]批量导入的账号信息处理完毕，该文件3天内有效，问题文件如下：" , toUser);
                platformIPAccountServicesI.createdExcelSendMsg(failAccount , queInfo.getString("fileName") , toUser);
            }else{
                weChatServicesI.sendText(EnumWeChartType.通知 , "您好：您在["+msgTime+"]批量导入的账号信息已经全部导入完毕，未出现问题。" , toUser);
            }
            //成功应答
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
            //重新进入队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }

        //requeue，true重新进入队列
//      channel.basicNack(envelope.getDeliveryTag(), false, true);
        //requeue，true重新进入队列,与basicNack差异缺少multiple参数 当消费消息出现异常时，我们需要取消确认
        //抛弃此条消息
        //channel.basicNack(envelope.getDeliveryTag(), false, false);
//      channel.basicReject(envelope.getDeliveryTag(), true);

    }
}

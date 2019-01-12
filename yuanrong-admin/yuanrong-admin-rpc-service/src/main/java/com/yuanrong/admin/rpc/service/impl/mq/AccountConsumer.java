package com.yuanrong.admin.rpc.service.impl.mq;

/**
 * Created by zhonghang on 2018/8/1.
 */

import com.alibaba.fastjson.JSONArray;
import com.rabbitmq.client.Channel;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.rpc.api.account.PlatformIPAccountServicesI;
import com.yuanrong.admin.rpc.api.base.SystemLogServicesI;
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
@Component
public class AccountConsumer  implements ChannelAwareMessageListener {
    private static final Logger logger = Logger.getLogger(AccountConsumer.class);
    @Autowired
    PlatformIPAccountServicesI platformIPAccountServicesI;
    @Autowired
    SystemLogServicesI systemLogServicesI;
    /**
     * 处理收到的rabbit消息的回调方法。
     * @param message AMQP封装消息对象
     * @param channel 信道对象，可以进行确认回复
     * @throws Exception Any.
     */
    public void onMessage(Message message, Channel channel) throws Exception {
        systemLogServicesI.log(AccountConsumer.class.getName() , 0,"测试Rabbit消费，存储数据库","系统");
        Thread.sleep(4000);
       /* try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(message.getBody());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            JSONArray queInfo = (JSONArray) objectInputStream.readObject();
            logger.info("消费者收到消息："+queInfo.toJSONString());
            List<PlatformIPAccount> failAccount =  platformIPAccountServicesI.saveQueueMQ(queInfo);
            //失败账号生成Excel发送消息给创建人
            if(CollectionUtil.size(failAccount) > 0){
                platformIPAccountServicesI.createdExcelSendMsg(failAccount);
            }
            //成功应答
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
            //重新进入队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }*/

        //requeue，true重新进入队列
//      channel.basicNack(envelope.getDeliveryTag(), false, true);
        //requeue，true重新进入队列,与basicNack差异缺少multiple参数 当消费消息出现异常时，我们需要取消确认
        //抛弃此条消息
        //channel.basicNack(envelope.getDeliveryTag(), false, false);
//      channel.basicReject(envelope.getDeliveryTag(), true);

    }
}

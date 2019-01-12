package com.yuanrong.admin.rpc.service.impl.mq;

/**
 * Created by zhonghang on 2018/8/1.
 */

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.UnsupportedEncodingException;

/**
 * 实现此方法在basicpublish失败时回调
 * 相当于 ReturnListener的功能。
 * 在发布消息时设置mandatory等于true，
 * 监听消息是否有相匹配的队列，没有时ReturnCallback将执行returnedMessage方法，消息将返给发送者
 */
public class MyReturnCallback implements RabbitTemplate.ReturnCallback {

    public void returnedMessage(Message message, int replyCode, String replyText,
                                String exchange, String routingKey) {
        try {
            System.out.println("消息发送进指定队列失败：失败原因（+replyText）："+replyText
                    +";错误代码（replyCode）："+replyCode
                    +";消息对象："+new String(message.getBody(),"UTF-8")
                    +"exchange:"+exchange
                    +"routingKey:"+routingKey);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}


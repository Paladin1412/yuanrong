package com.yuanrong.admin.rpc.service.impl.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * Created by zhonghang on 2018/8/1.
 */
public class ConfirmReturnBack  implements RabbitTemplate.ConfirmCallback {
    /**
     * Confirmation callback.
     * @param correlationdata 回调的相关数据.
     * @param ack true for ack, false for nack
     * @param cause 专门给NACK准备的一个可选的原因，其他情况为null。
     */
    public void confirm(CorrelationData correlationdata, boolean ack, String cause) {
        System.out.println("Exchange接收是否成功（ack）: " + ack + "。 返回的用户参数（correlationData）: " + correlationdata + "。NACK原因（cause） : " + cause);
    }
}
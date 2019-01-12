package com.yuanrong.admin.rpc.service.impl.mq;

import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.rpc.api.mq.RabbitMQServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by zhonghang on 2018/8/1.
 */
@Service
public class RabbitMQServicesImpl extends BaseServicesAbstract implements RabbitMQServicesI {
    private final static Logger LOGGER = Logger.getLogger(RabbitMQServicesImpl.class);
    private final static String accountKey = "accountKey";
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void addIPAccountToMQ(JSONObject queue) {
        rabbitTemplate.convertAndSend(RabbitMQServicesImpl.accountKey , queue);
    }
}

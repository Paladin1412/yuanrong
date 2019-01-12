package com.yuanrong.admin.rpc.api.mq;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by zhonghang on 2018/8/1.
 */
public interface RabbitMQServicesI {

    /**
     * 将导入账号存储到队列，等待抓取入库
     * @param queue
     */
    public void addIPAccountToMQ(JSONObject queue);
}

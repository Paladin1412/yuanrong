package com.yuanrong.admin.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by zhonghang on 2018/4/16.
 */
public class YuanRongAdminRpcApp {
    protected transient static Logger log = LoggerFactory.getLogger(YuanRongAdminRpcApp.class);
    public void init() {
        log.error(">>>>> ------------------init 正在启动 <<<<<");
        log.info(">>>>> init techThink-admin-rpc-service 正在启动 <<<<<");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:springconfig/*.xml");
        log.info(">>>>> init techThink-admin-rpc-service 启动完成 <<<<<");
        context.start();
        System.out.println("init 按任意键退出");
        try {
             System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("init = " + spark_url + " " + spark_driver + " " + ROLE_MONITOR_ADMIN);
    }

    public void close() {
        log.error(">>>>> ------------------main close 启动完成 <<<<<");
        //System.out.println("close = " + spark_url + " " + spark_driver + " " + LOGS_FUNCTION_LOGIN);
    }
    public static void main(String[] args) {
        log.info(">>>>> main techThink-admin-rpc-service 正在启动 <<<<<");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:springconfig/*.xml");
        log.info(">>>>> main techThink-admin-rpc-service 启动完成 <<<<<");
        context.start();
        log.error("按任意键退出");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

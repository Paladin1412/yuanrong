package com.yuanrong.admin.rpc.api.quartz;


import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;

import java.util.Date;

public interface QuartzManagerI {
    /**
     * @Description: 添加一个定时任务
     *
     * @param jobName 任务名
     * @param jobGroupName  任务组名
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     * @param jobClass  任务
     * @param cron   时间设置，参考quartz说明文档
     */
    public  void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, String cron);
    /**
     * @Description: 添加一个定时任务在指定秒数后执行一次)
     *
     * @param jobName 任务名
     * @param jobGroupName  任务组名
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     * @param jobClass  任务
     * @param startTime   开始执行的时间
     */
    public  void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, Date startTime);

    /**
     * @Description: 修改一个任务的触发时间
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     * @param cron   时间设置，参考quartz说明文档
     */
    public  void modifyJobTime(String jobName,
                               String jobGroupName, String triggerName, String triggerGroupName, String cron);

    /**
     * @Description: 移除一个任务
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    public  void removeJob(String jobName, String jobGroupName,
                           String triggerName, String triggerGroupName);

    /**
     * @Description:启动所有定时任务
     */
    public  void startJobs();

    /**
     * @Description:关闭所有定时任务
     */
    public  void shutdownJobs();

    /**
     * 暂停所有任务
     */
    public void pauseAll();

    /**
     * 重启任务
     */
    public void resumeAll();

    /**
     * 添加一个订单支付超时的任务
     * @param orderInfoBuyer
     */
    public void addTimeoutOrderInfoJobs(OrderInfoBuyer orderInfoBuyer);
}

package com.yuanrong.admin.rpc.service.impl.quartz;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.rpc.api.quartz.QuartzManagerI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.common.util.SystemParam;
import org.quartz.*;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class QuartzManagerImpl   implements QuartzManagerI {
    @Autowired
    private  SchedulerFactoryBean schedulerFactory ;

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
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public  void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, String cron) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            // 任务名，任务组，任务执行类
            JobDetail jobDetail= JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();
            // 调度容器设置JobDetail和Trigger
            sched.scheduleJob(jobDetail, trigger);
            // 启动
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, Date startTime) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            // 任务名，任务组，任务执行类
            JobDetail jobDetail= JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            // 调度容器设置JobDetail和Trigger
            SimpleTriggerImpl simpleTrigger = new SimpleTriggerImpl(triggerName, triggerGroupName);
            //设置作业启动时间,马上启动
            simpleTrigger.setStartTime(startTime);

            //设置作业执行间隔
            simpleTrigger.setRepeatInterval(1);
            //设置作业执行次数
            simpleTrigger.setRepeatCount(0);
            //设置作业执行优先级默认为5
//            simpleTrigger.setPriority(10);
            sched.scheduleJob(jobDetail, simpleTrigger);

            // 启动
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 修改一个任务的触发时间
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     * @param cron   时间设置，参考quartz说明文档
     */
    @Override
    public  void modifyJobTime(String jobName, String jobGroupName, String triggerName, String triggerGroupName, String cron) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }

            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                /** 方式一 ：调用 rescheduleJob 开始 */
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                sched.rescheduleJob(triggerKey, trigger);
                /** 方式一 ：调用 rescheduleJob 结束 */

                /** 方式二：先删除，然后在创建一个新的Job  */
                //JobDetail jobDetail = sched.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
                //Class<? extends Job> jobClass = jobDetail.getJobClass();
                //removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
                //addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);
                /** 方式二 ：先删除，然后在创建一个新的Job */
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 移除一个任务
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    @Override
    public  void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();

            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

            sched.pauseTrigger(triggerKey);// 停止触发器
            sched.unscheduleJob(triggerKey);// 移除触发器
            sched.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:启动所有定时任务
     */
    @Override
    public  void startJobs() {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            sched.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:关闭所有定时任务
     */
    @Override
    public  void shutdownJobs() {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            if (!sched.isShutdown()) {
                sched.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void pauseAll() {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            sched.pauseAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void resumeAll() {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            sched.resumeAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addTimeoutOrderInfoJobs(OrderInfoBuyer orderInfoBuyer) {
        this.addJob("timeoutOrderInfoJob_"+orderInfoBuyer.getOrderInfoBuyerId() , "timeoutOrderInfoGroup_"+orderInfoBuyer.getOrderInfoBuyerId(),
                "timeoutOrderInfoTrigger_"+orderInfoBuyer.getOrderInfoBuyerId(),"timeoutOrderInfoTriggerGroup_"+orderInfoBuyer.getOrderInfoBuyerId(),TimeoutOrderInfoJobs.class , DateUtil.parseDateTime(orderInfoBuyer.getPayInvalidTime()));
    }
}

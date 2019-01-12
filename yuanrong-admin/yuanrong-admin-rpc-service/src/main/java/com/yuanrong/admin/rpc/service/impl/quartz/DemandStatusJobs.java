package com.yuanrong.admin.rpc.service.impl.quartz;

import com.yuanrong.admin.rpc.api.base.SystemLogServicesI;
import com.yuanrong.admin.rpc.api.demand.DemandServicesI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 更改需求状态为已完成：（截止日期-当前时间）< 0
 */
public class DemandStatusJobs extends QuartzJobBean {
    private static final Log LOG_RECORD = LogFactory.getLog(DemandStatusJobs.class);

    @Autowired
    private DemandServicesI demandServicesI;

    @Autowired
    private SystemLogServicesI systemLogServicesI;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG_RECORD.info("更改需求状态为已完成：（截止日期-当前时间）< 0，定时任务," + sdf.format(new Date())+"\t"
                +jobExecutionContext.getJobDetail().getKey().getName());
        demandServicesI.updateDemandStatusIndex();
        systemLogServicesI.log(DemandStatusJobs.class.getName() , 0 , "需求状态更改为已完成" , "系统");
    }
}

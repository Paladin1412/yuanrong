package com.yuanrong.admin.rpc.service.impl.quartz;

import com.yuanrong.admin.rpc.api.order.OrderInfoBuyerServicesI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeoutOrderInfoJobs extends QuartzJobBean {
    private static final Log LOG_RECORD = LogFactory.getLog(TimeoutOrderInfoJobs.class);
    /*
     * 这里就是因为有上文中的AutowiringSpringBeanJobFactory才可以使用像@Autowired的注解，当然还可以使用Spring的其他注解
     * 否则只能在配置文件中设置这属性的值，另一种方式下面说到
     */
    @Autowired
    private OrderInfoBuyerServicesI orderInfoBuyerServicesI;


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG_RECORD.info("订单支付超时执行," + sdf.format(new Date())+"\t"
                +jobExecutionContext.getJobDetail().getKey().getName());
        //我们真正要执行的任务
        int orderInfoBuyerId = Integer.parseInt(jobExecutionContext.getJobDetail().getKey().getName().split("_")[1]);
        orderInfoBuyerServicesI.updateTimeoutOrderInfo(orderInfoBuyerId);
    }
}

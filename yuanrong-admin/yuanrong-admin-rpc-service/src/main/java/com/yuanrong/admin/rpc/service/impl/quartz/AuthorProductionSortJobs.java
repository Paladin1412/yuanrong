package com.yuanrong.admin.rpc.service.impl.quartz;

import com.yuanrong.admin.rpc.api.author.YRAuthorServicesI;
import com.yuanrong.admin.rpc.api.author.YRProductionServicesI;
import com.yuanrong.admin.rpc.api.base.SystemLogServicesI;
import com.yuanrong.admin.rpc.api.order.OrderInfoBuyerServicesI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者，作品排序类
 */
public class AuthorProductionSortJobs extends QuartzJobBean {
    private static final Log LOG_RECORD = LogFactory.getLog(AuthorProductionSortJobs.class);
    /*
     * 这里就是因为有上文中的AutowiringSpringBeanJobFactory才可以使用像@Autowired的注解，当然还可以使用Spring的其他注解
     * 否则只能在配置文件中设置这属性的值，另一种方式下面说到
     */
    @Autowired
    private YRProductionServicesI yrProductionServicesI;

    @Autowired
    private YRAuthorServicesI yrAuthorServicesI;

    @Autowired
    private SystemLogServicesI systemLogServicesI;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG_RECORD.info("作品、作者排序定时任务," + sdf.format(new Date())+"\t"
                +jobExecutionContext.getJobDetail().getKey().getName());
        //将redis中，作者，作品访问次数刷入数据库中
        yrProductionServicesI.updateFlushRedisAccessTimeToDb();
        yrAuthorServicesI.updateFlushRedisAccessTimeToDb();

        //计算作者作品排序
        yrProductionServicesI.updateCalculateSortScore();
        yrAuthorServicesI.updateCalculateSortScore();
        systemLogServicesI.log(AuthorProductionSortJobs.class.getName() , 0 , "计算作者，作品排序" , "系统");
    }
}

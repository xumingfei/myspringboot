package com.practice.springbootquartz.taks;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component()
public class FirstTask extends QuartzJobBean {

    private static final Logger log = LoggerFactory.getLogger(FirstTask.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //这调用执行定时任务的逻辑
        log.info("定时任务执行。。"+new Date());
    }
}

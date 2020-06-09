package com.practice.springbootquartz.config;

import com.practice.springbootquartz.taks.FirstTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfig {

    @Bean
    public JobDetail firstJobDetail() {
        return JobBuilder.newJob(FirstTask.class).withIdentity("task","group1").storeDurably().build();
    }

    @Bean
    public SimpleTrigger firstSimpleTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever();

        SimpleTrigger trigger = TriggerBuilder.newTrigger().forJob(firstJobDetail())
                .withIdentity("firstSimpleTrigger")
                .withDescription("simple类型触发器")
                .withSchedule(scheduleBuilder).build();
        return trigger;
    }

    @Bean
    public CronTrigger cronTrigger(){
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(String.format("0 */1 * * * ?"))
                .withMisfireHandlingInstructionDoNothing()
                ;
        CronTrigger trigger = TriggerBuilder.newTrigger().forJob(firstJobDetail())
                .withIdentity("helloCronTrigger").withDescription("cron类型触发的定时器").withSchedule(cronScheduleBuilder).build();
        return trigger;
    }
}

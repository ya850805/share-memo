package com.sharememo.quartz;

import org.quartz.*;

public class TriggerComponent {
  public static Trigger cronTrigger(String cron) {
    CronTrigger cronTrigger =
        TriggerBuilder.newTrigger()
            .withSchedule(
                CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing())
            .build();
    return cronTrigger;
  }

  public static Trigger cronTrigger(String cron, JobDataMap jobDataMap) {
    CronTrigger cronTrigger =
        TriggerBuilder.newTrigger()
            .withSchedule(
                CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing())
            .usingJobData(jobDataMap)
            .build();
    return cronTrigger;
  }
}

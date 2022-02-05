package com.sharememo.controller;

import com.sharememo.entity.QuartzNotification;
import com.sharememo.quartz.QuartzNotificationJob;
import com.sharememo.quartz.TriggerComponent;
import com.sharememo.service.QuartzNotificationService;
import com.sharememo.util.DateUtil;
import com.sharememo.vo.R;
import com.sharememo.vo.quartzNotification.QuartzNotificationCreateVo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class QuartzNotificationController {
  @Autowired private QuartzNotificationService quartzNotificationService;
  @Autowired private Scheduler scheduler;

  @PostMapping("/quartz-notification")
  public R createQuartzNotification(@RequestBody QuartzNotificationCreateVo vo) {
    QuartzNotification quartzNotification = new QuartzNotification();
    BeanUtils.copyProperties(vo, quartzNotification);
    quartzNotification.setCron(DateUtil.parseCron(vo.getCron()));

    startJob(quartzNotification); // If error occurs, then the data will not insert.
    quartzNotificationService.create(quartzNotification);
    return R.ok();
  }

  R startJob(QuartzNotification quartzNotification) {
    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.put("key", "value");

    JobKey jobKey =
        JobKey.jobKey(quartzNotification.getJobName(), quartzNotification.getJobGroup());
    JobDetail jobDetail =
        JobBuilder.newJob(QuartzNotificationJob.class).withIdentity(jobKey).storeDurably().build();
    Trigger trigger = TriggerComponent.cronTrigger(quartzNotification.getCron(), jobDataMap);

    try {
      scheduler.scheduleJob(jobDetail, trigger);
      return R.ok();
    } catch (SchedulerException e) {
      log.error(e.getMessage());
    }

    return R.error();
  }
}

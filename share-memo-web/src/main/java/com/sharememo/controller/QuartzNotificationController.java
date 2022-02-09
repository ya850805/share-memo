package com.sharememo.controller;

import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.entity.QuartzNotification;
import com.sharememo.exception.ShareMemoException;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    startJob(
        quartzNotification, vo.getMemberIds()); // If error occurs, then the data will not insert.
    quartzNotificationService.create(quartzNotification, vo.getMemberIds());
    return R.ok();
  }

  void startJob(QuartzNotification quartzNotification, List<Integer> memberIds) {
    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.put(ShareMemoConstant.JOB_DATA_MAP_KEY_CONTENT, quartzNotification.getContent());
    jobDataMap.put(ShareMemoConstant.JOB_DATA_MAP_KEY_IDS, memberIds);

    JobKey jobKey =
        JobKey.jobKey(quartzNotification.getJobName(), quartzNotification.getJobGroup());
    JobDetail jobDetail =
        JobBuilder.newJob(QuartzNotificationJob.class).withIdentity(jobKey).storeDurably().build();
    Trigger trigger = TriggerComponent.cronTrigger(quartzNotification.getCron(), jobDataMap);

    try {
      scheduler.scheduleJob(jobDetail, trigger);
    } catch (SchedulerException e) {
      log.error(e.getMessage());
      throw new ShareMemoException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
  }
}

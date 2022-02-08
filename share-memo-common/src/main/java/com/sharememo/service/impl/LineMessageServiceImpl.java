package com.sharememo.service.impl;

import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.entity.QuartzNotification;
import com.sharememo.exception.ShareMemoException;
import com.sharememo.quartz.QuartzNotificationJob;
import com.sharememo.quartz.TriggerComponent;
import com.sharememo.service.LineMessageService;
import com.sharememo.service.QuartzNotificationService;
import com.sharememo.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class LineMessageServiceImpl implements LineMessageService {
  @Autowired private QuartzNotificationService quartzNotificationService;
  @Autowired private Scheduler scheduler;

  @Override
  public String handlePlainTextMessage(String text) {
    // TODO Handle the request that adding quartz notification by line message.
    if (StringUtils.EMPTY.equals(text.trim())) {
      return StringUtils.EMPTY;
    } else if (ShareMemoConstant.LINE_BOT_QUESTION.equals(text)) {
      return ShareMemoConstant.LINE_BOT_RESPONSE_QUESTION;
    } else if (ShareMemoConstant.LINE_BOT_COMMAND.equals(text)) {
      StringBuilder sb = new StringBuilder();
      sb.append(ShareMemoConstant.LINE_BOT_COMMAND_ONE);
      sb.append(StringUtils.LF);

      return sb.toString();
    } else if (text.startsWith(ShareMemoConstant.LINE_BOT_REMIND_ME)) {
      LocalDateTime noticeTimestamp =
          LocalDateTime.parse(text.substring(4, 23), ShareMemoConstant.DATE_TIME_FORMATTER);
      String content = text.substring(24);

      QuartzNotification quartzNotification = new QuartzNotification();
      quartzNotification.setJobName("jobName");
      quartzNotification.setJobGroup("jobGroup");
      quartzNotification.setSubject(content);
      quartzNotification.setContent(content);
      quartzNotification.setCron(DateUtil.parseCron(noticeTimestamp));

      startJob(quartzNotification); // If error occurs, then the data will not insert.
      quartzNotificationService.create(quartzNotification);
      return ShareMemoConstant.LINE_BOT_ACCEPT_COMMAND;
    } else {
      return ShareMemoConstant.LINE_BOT_DEFAULT_RESPONSE;
    }
  }

  private void startJob(QuartzNotification quartzNotification) {
    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.put("key", "value");

    JobKey jobKey =
        JobKey.jobKey(quartzNotification.getJobName(), quartzNotification.getJobGroup());
    JobDetail jobDetail =
        JobBuilder.newJob(QuartzNotificationJob.class).withIdentity(jobKey).storeDurably().build();
    Trigger trigger = TriggerComponent.cronTrigger(quartzNotification.getCron(), jobDataMap);

    try {
      scheduler.scheduleJob(jobDetail, trigger);
    } catch (SchedulerException e) {
      log.error(e.getMessage());
      throw new ShareMemoException(500, e.getMessage());
    }
  }
}

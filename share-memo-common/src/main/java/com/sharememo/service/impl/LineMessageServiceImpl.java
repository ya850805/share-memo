package com.sharememo.service.impl;

import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.entity.QuartzNotification;
import com.sharememo.exception.ShareMemoException;
import com.sharememo.quartz.QuartzNotificationJob;
import com.sharememo.quartz.TriggerComponent;
import com.sharememo.service.LineMessageService;
import com.sharememo.service.MemberService;
import com.sharememo.service.QuartzNotificationService;
import com.sharememo.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class LineMessageServiceImpl implements LineMessageService {
  @Autowired private QuartzNotificationService quartzNotificationService;
  @Autowired private MemberService memberService;
  @Autowired private Scheduler scheduler;

  //TODO Add transaction
  @Override
  public String handlePlainTextMessage(String text, String senderLineId) {
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

      if(LocalDateTime.now().isAfter(noticeTimestamp) || LocalDateTime.now().isEqual(noticeTimestamp)) {
        return ShareMemoConstant.LINE_NOT_MESSAGE_ERROR;
      }

      String content = text.substring(24);

      QuartzNotification quartzNotification = new QuartzNotification();
      quartzNotification.setJobName(RandomStringUtils.random(10, true, false));
      quartzNotification.setJobGroup(ShareMemoConstant.DEFAULT_JOB_GROUP);
      quartzNotification.setSubject(content);
      quartzNotification.setContent(content);
      quartzNotification.setCron(DateUtil.parseCron(noticeTimestamp));

      List<Integer> memberId = Arrays.asList(memberService.getByLineId(senderLineId).getId());

      quartzNotificationService.create(quartzNotification, memberId);
      startJob(quartzNotification, memberId);
      return ShareMemoConstant.LINE_BOT_ACCEPT_COMMAND;
    } else {
      return ShareMemoConstant.LINE_BOT_DEFAULT_RESPONSE;
    }
  }

  private void startJob(QuartzNotification quartzNotification, List<Integer> memberIds) {
    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.put(ShareMemoConstant.JOB_DATA_MAP_KEY_CONTENT, quartzNotification.getContent());
    jobDataMap.put(ShareMemoConstant.JOB_DATA_MAP_KEY_IDS, memberIds);
    jobDataMap.put(ShareMemoConstant.JOB_DATA_MAP_KEY_NOTIFICATION_ID, quartzNotification.getId());

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

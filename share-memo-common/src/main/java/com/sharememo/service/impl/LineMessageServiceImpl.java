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
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class LineMessageServiceImpl implements LineMessageService {
  @Autowired private QuartzNotificationService quartzNotificationService;
  @Autowired private MemberService memberService;
  @Autowired private Scheduler scheduler;
  @Autowired private StringRedisTemplate stringRedisTemplate;

  /**
   * Handle line bot received plan text message.
   *
   * @param text Send text.
   * @param senderLineId Sender's line id.
   * @return Line bot response text.
   */
  @Override
  @Transactional
  public String handlePlainTextMessage(String text, String senderLineId) {
    if (StringUtils.EMPTY.equals(text.trim())) {
      return StringUtils.EMPTY;
    } else if (ShareMemoConstant.LINE_BOT_QUESTION.equals(text)) {
      return ShareMemoConstant.LINE_BOT_RESPONSE_QUESTION;
    } else if (ShareMemoConstant.LINE_BOT_COMMAND.equals(text)) { //show all line bot commands
      StringBuilder sb = new StringBuilder();
      sb.append(ShareMemoConstant.LINE_BOT_COMMAND_ONE);
      sb.append(StringUtils.LF);
      sb.append(ShareMemoConstant.LINE_BOT_COMMAND_TWO);
      sb.append(StringUtils.LF);
      sb.append(ShareMemoConstant.LINE_BOT_COMMAND_THREE);
      sb.append(StringUtils.LF);
      sb.append(ShareMemoConstant.LINE_BOT_COMMAND_FOUR);
      sb.append(StringUtils.LF);

      return sb.toString();
    } else if (text.startsWith(ShareMemoConstant.LINE_BOT_REMIND_ME)) {
      LocalDateTime noticeTimestamp =
          LocalDateTime.parse(text.substring(4, 23), ShareMemoConstant.DATE_TIME_FORMATTER);

      if (LocalDateTime.now().isAfter(noticeTimestamp)
          || LocalDateTime.now().isEqual(noticeTimestamp)) {
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
    } else if (text.startsWith(ShareMemoConstant.LINE_BOT_INSERT_NOTE)) { // insert note
      String note = text.substring(ShareMemoConstant.LINE_BOT_INSERT_NOTE.length());
      if (StringUtils.isBlank(note)) return ShareMemoConstant.LINE_NOT_MESSAGE_ERROR;

      stringRedisTemplate.opsForList().rightPush(ShareMemoConstant.LINE_BOT_NOTE_REDIS_KEY, note);
      return ShareMemoConstant.LINE_BOT_ACCEPT_COMMAND;
    } else if (ShareMemoConstant.LINE_BOT_NOTE.equals(text)) { // show all note
      long size = stringRedisTemplate.opsForList().size(ShareMemoConstant.LINE_BOT_NOTE_REDIS_KEY);

      StringBuilder sb = new StringBuilder();
      List<String> notes =
          stringRedisTemplate
              .opsForList()
              .range(ShareMemoConstant.LINE_BOT_NOTE_REDIS_KEY, 0, (int) (size - 1));
      for (int i = 0; i < notes.size(); i++) {
        sb.append(i + 1).append(". ").append(notes.get(i)).append(StringUtils.LF);
      }
      return sb.toString();
    } else if (text.startsWith(ShareMemoConstant.LINE_BOT_DELETE_NOTE)) { // delete note
      Integer id;
      try {
        id = Integer.valueOf(text.substring(ShareMemoConstant.LINE_BOT_DELETE_NOTE.length()));
      } catch (NumberFormatException nfe) {
        return ShareMemoConstant.LINE_NOT_MESSAGE_ERROR;
      }
      long size = stringRedisTemplate.opsForList().size(ShareMemoConstant.LINE_BOT_NOTE_REDIS_KEY);
      List<String> notes =
          stringRedisTemplate
              .opsForList()
              .range(ShareMemoConstant.LINE_BOT_NOTE_REDIS_KEY, 0, size - 1);
      String deleted = notes.get(id - 1);

      stringRedisTemplate
          .opsForList()
          .remove(ShareMemoConstant.LINE_BOT_NOTE_REDIS_KEY, 1, deleted);
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

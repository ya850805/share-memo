package com.sharememo.vo.quartzNotification;

import lombok.Data;

@Data
public class QuartzNotificationCreateVo {
  private String jobName;

  private String jobGroup;

  private String subject;

  private String content;

  private String cron;
}

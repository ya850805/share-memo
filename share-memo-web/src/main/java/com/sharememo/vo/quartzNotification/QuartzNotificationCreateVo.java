package com.sharememo.vo.quartzNotification;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuartzNotificationCreateVo {
  private String jobName;

  private String jobGroup;

  private String subject;

  private String content;

  @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
  private LocalDateTime cron;

  /**
   * Members who need to send quartz notification.
   */
  private List<Integer> memberIds;
}

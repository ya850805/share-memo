package com.sharememo.vo.quartzNotification;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuartzNotificationCreateVo {
  @NotBlank(message = "jobName不得為空")
  private String jobName;

  @NotBlank(message = "jobGroup不得為空")
  private String jobGroup;

  @NotBlank(message = "請輸入通知主旨")
  private String subject;

  @NotBlank(message = "請輸入通知內文")
  private String content;

  @NotNull(message = "請輸入通知日期")
  @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
  private LocalDateTime cron;

  /**
   * Members who need to send quartz notification.
   */
  @NotEmpty(message = "請至少選擇一位member")
  private List<Integer> memberIds;
}

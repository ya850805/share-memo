package com.sharememo.vo.notification;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

/** @author Jason */
@Data
public class NotificationCreateVo {
  private String subject;

  private String content;

  @JsonFormat(pattern = "yyyy/MM/dd")
  private LocalDate notificationDate;
}

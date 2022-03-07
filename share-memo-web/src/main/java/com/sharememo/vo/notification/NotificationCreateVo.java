package com.sharememo.vo.notification;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/** @author Jason */
@Data
public class NotificationCreateVo {
  @NotBlank(message = "請輸入通知主旨")
  private String subject;

  @NotBlank(message = "請輸入通知內文")
  private String content;

  @NotNull(message = "請輸入通知日期")
  @JsonFormat(pattern = "yyyy/MM/dd")
  private LocalDate notificationDate;
}

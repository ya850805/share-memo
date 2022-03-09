package com.sharememo.vo.memberNotification;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/** @author Jason */
@Data
public class MemberNotificationCreateVo {
  @Min(value = 1, message = "memberId最小值為1")
  @NotNull(message = "請輸入memberId")
  private Integer memberId;

  @Min(value = 1, message = "notificationId最小值為1")
  @NotNull(message = "請輸入notificationId")
  private Integer notificationId;
}

package com.sharememo.service;

import com.sharememo.entity.QuartzMemberNotification;

public interface QuartzMemberNotificationService {
  void create(QuartzMemberNotification quartzMemberNotification);

  //TODO use this method.
  QuartzMemberNotification findByQuartzNotificationIdAndMemberId(Integer quartzNotificationId, Integer memberId);
  void updateIsSend(Integer quartzNotificationId, Integer memberId);
}

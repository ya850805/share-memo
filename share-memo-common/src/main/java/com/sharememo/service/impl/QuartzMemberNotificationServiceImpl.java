package com.sharememo.service.impl;

import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.constant.YNEnum;
import com.sharememo.entity.QuartzMemberNotification;
import com.sharememo.mapper.QuartzMemberNotificationMapper;
import com.sharememo.service.QuartzMemberNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class QuartzMemberNotificationServiceImpl implements QuartzMemberNotificationService {
  @Autowired private QuartzMemberNotificationMapper quartzMemberNotificationMapper;

  @Override
  @Transactional
  public void create(QuartzMemberNotification quartzMemberNotification) {
    // First time create haven't send.
    quartzMemberNotification.setIsSend(YNEnum.N.name());
    quartzMemberNotification.setCreateTimestamp(LocalDateTime.now());
    quartzMemberNotification.setCreateUser(ShareMemoConstant.SYS_USER);
    quartzMemberNotification.setUpdateTimestamp(LocalDateTime.now());
    quartzMemberNotification.setUpdateUser(ShareMemoConstant.SYS_USER);
    quartzMemberNotificationMapper.create(quartzMemberNotification);
  }

  @Override
  public QuartzMemberNotification findByQuartzNotificationIdAndMemberId(
      Integer quartzNotificationId, Integer memberId) {
    return quartzMemberNotificationMapper.findByQuartzNotificationIdAndMemberId(
        quartzNotificationId, memberId);
  }
}

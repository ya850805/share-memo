package com.sharememo.service.impl;

import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.constant.YNEnum;
import com.sharememo.entity.QuartzMemberNotification;
import com.sharememo.entity.QuartzNotification;
import com.sharememo.mapper.QuartzNotificationMapper;
import com.sharememo.service.QuartzMemberNotificationService;
import com.sharememo.service.QuartzNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class QuartzNotificationServiceImpl implements QuartzNotificationService {
  @Autowired private QuartzNotificationMapper quartzNotificationMapper;
  @Autowired private QuartzMemberNotificationService quartzMemberNotificationService;

  @Override
//  @Transactional
  public void create(QuartzNotification quartzNotification, List<Integer> memberIds) {
    quartzNotification.setCreateTimestamp(LocalDateTime.now());
    quartzNotification.setCreateUser(ShareMemoConstant.SYS_USER);
    quartzNotification.setUpdateTimestamp(LocalDateTime.now());
    quartzNotification.setUpdateUser(ShareMemoConstant.SYS_USER);

    quartzNotificationMapper.create(quartzNotification);
    for (Integer memberId : memberIds) {
      QuartzMemberNotification quartzMemberNotification = new QuartzMemberNotification();
      quartzMemberNotification.setQuartzNotificationId(quartzNotification.getId());
      quartzMemberNotification.setMemberId(memberId);
      quartzMemberNotificationService.create(quartzMemberNotification);
    }
  }
}

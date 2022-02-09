package com.sharememo.service.impl;

import com.sharememo.entity.QuartzMemberNotification;
import com.sharememo.mapper.QuartzMemberNotificationMapper;
import com.sharememo.service.QuartzMemberNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuartzMemberNotificationServiceImpl implements QuartzMemberNotificationService {
  @Autowired private QuartzMemberNotificationMapper quartzMemberNotificationMapper;

  @Override
  @Transactional
  public void create(QuartzMemberNotification quartzMemberNotification) {
    quartzMemberNotificationMapper.create(quartzMemberNotification);
  }
}

package com.sharememo.service.impl;

import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.entity.Notification;
import com.sharememo.mapper.NotificationMapper;
import com.sharememo.service.MemberNotificationService;
import com.sharememo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/** @author Jason */
@Service
public class NotificationServiceImpl implements NotificationService {
  @Autowired private NotificationMapper mapper;
  @Autowired private MemberNotificationService memberNotificationService;

  @Override
  @Transactional
  public void createNotification(Notification notification) {
    notification.setCreateTimestamp(LocalDateTime.now());
    notification.setCreateUser(ShareMemoConstant.SYS_USER);
    notification.setUpdateTimestamp(LocalDateTime.now());
    notification.setUpdateUser(ShareMemoConstant.SYS_USER);

    mapper.create(notification);
  }

  @Override
  @Transactional
  public void deleteNotification(Integer id) {
    memberNotificationService.deleteByNotificationId(id);
    mapper.delete(id);
  }
}

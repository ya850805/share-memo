package com.sharememo.service.impl;

import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.constant.YNEnum;
import com.sharememo.entity.Notification;
import com.sharememo.mapper.NotificationMapper;
import com.sharememo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/** @author Jason */
@Service
public class NotificationServiceImpl implements NotificationService {
  @Autowired private NotificationMapper mapper;

  @Override
  @Transactional
  public void createNotification(Notification notification) {
    notification.setIsSend(YNEnum.Y.name());
    notification.setCreateTimestamp(LocalDateTime.now());
    notification.setCreateUser(ShareMemoConstant.SYS_USER);
    notification.setUpdateTimestamp(LocalDateTime.now());
    notification.setUpdateUser(ShareMemoConstant.SYS_USER);

    mapper.create(notification);
  }
}

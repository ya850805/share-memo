package com.sharememo.service.impl;

import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.entity.QuartzNotification;
import com.sharememo.mapper.QuartzNotificationMapper;
import com.sharememo.service.QuartzNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
public class QuartzNotificationServiceImpl implements QuartzNotificationService {
  @Autowired private QuartzNotificationMapper quartzNotificationMapper;

  @Override
  @Transactional
  public void create(QuartzNotification quartzNotification) {
    quartzNotification.setCreateTimestamp(
        LocalDateTime.now().format(ShareMemoConstant.DATE_TIME_FORMATTER));
    quartzNotification.setCreateUser(ShareMemoConstant.SYS_USER);
    quartzNotification.setUpdateTimestamp(
        LocalDateTime.now().format(ShareMemoConstant.DATE_TIME_FORMATTER));
    quartzNotification.setUpdateUser(ShareMemoConstant.SYS_USER);
    quartzNotificationMapper.create(quartzNotification);
  }

}

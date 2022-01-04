package com.sharememo.service.impl;

import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.constant.YNEnum;
import com.sharememo.entity.MemberNotification;
import com.sharememo.mapper.MemberNotificationMapper;
import com.sharememo.service.MemberNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/** @author Jason */
@Service
public class MemberNotificationServiceImpl implements MemberNotificationService {
  @Autowired private MemberNotificationMapper mapper;

  @Override
  public void createMemberNotification(MemberNotification memberNotification) {
    // First time create haven't send.
    memberNotification.setIsSend(YNEnum.N.name());
    memberNotification.setCreateTimestamp(LocalDateTime.now());
    memberNotification.setCreateUser(ShareMemoConstant.SYS_USER);
    memberNotification.setUpdateTimestamp(LocalDateTime.now());
    memberNotification.setUpdateUser(ShareMemoConstant.SYS_USER);
    mapper.create(memberNotification);
  }
}

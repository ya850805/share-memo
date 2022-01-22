package com.sharememo.service;

import com.sharememo.constant.YNEnum;
import com.sharememo.entity.MemberNotification;
import com.sharememo.mapper.MemberNotificationMapper;
import com.sharememo.service.impl.MemberNotificationServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MemberNotificationServiceTest {

  @Mock private MemberNotificationMapper memberNotificationMapper;
  @InjectMocks private MemberNotificationServiceImpl memberNotificationServiceImpl;

  @Test
  public void createMemberNotification_AnyMemberNotification_HappenedOnceAndIsSendEqualsN() {
    MemberNotification createdMemberNotification = new MemberNotification();
    memberNotificationServiceImpl.createMemberNotification(createdMemberNotification);

    Mockito.verify(memberNotificationMapper, Mockito.times(1)).create(createdMemberNotification);
    Assert.assertEquals(createdMemberNotification.getIsSend(), YNEnum.N.name());
  }
}

package com.sharememo.service;

import com.sharememo.constant.YNEnum;
import com.sharememo.entity.QuartzMemberNotification;
import com.sharememo.mapper.QuartzMemberNotificationMapper;
import com.sharememo.service.impl.QuartzMemberNotificationServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QuartzMemberNotificationServiceTest {
  @InjectMocks private QuartzMemberNotificationServiceImpl quartzMemberNotificationService;
  @Mock private QuartzMemberNotificationMapper quartzMemberNotificationMapper;

  @Test
  public void create_AnyQuartzMemberNotification_HappenedOnceAndIsSendEqualsN() {
    QuartzMemberNotification quartzMemberNotification = new QuartzMemberNotification();
    quartzMemberNotificationService.create(quartzMemberNotification);
    Mockito.verify(quartzMemberNotificationMapper, Mockito.times(1))
        .create(quartzMemberNotification);
    Assert.assertEquals(quartzMemberNotification.getIsSend(), YNEnum.N.name());
  }

  @Test
  public void findByQuartzNotificationIdAndMemberId_AnyParameter_HappenedOnce() {
    Integer quartzNotificationId = Mockito.anyInt();
    Integer memberId = Mockito.anyInt();

    quartzMemberNotificationService.findByQuartzNotificationIdAndMemberId(
        quartzNotificationId, memberId);
    Mockito.verify(quartzMemberNotificationMapper, Mockito.times(1))
        .findByQuartzNotificationIdAndMemberId(quartzNotificationId, memberId);
  }

  @Test
  public void updateIsSend_AnyParameter_HappenedOnce() {
    Integer quartzNotificationId = Mockito.anyInt();
    Integer memberId = Mockito.anyInt();

    quartzMemberNotificationService.updateIsSend(quartzNotificationId, memberId);
    Mockito.verify(quartzMemberNotificationMapper, Mockito.times(1))
        .updateIsSend(quartzNotificationId, memberId);
  }
}

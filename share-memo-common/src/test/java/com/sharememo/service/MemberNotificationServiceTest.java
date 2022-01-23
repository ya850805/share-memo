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

import java.util.Arrays;
import java.util.List;

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

  @Test
  public void deleteByMemberId_AnyMemberId_HappenedOnce() {
    Integer deletedMemberId = Mockito.anyInt();
    memberNotificationServiceImpl.deleteByMemberId(deletedMemberId);

    Mockito.verify(memberNotificationMapper, Mockito.times(1)).deleteByMemberId(deletedMemberId);
  }

  @Test
  public void deleteByNotificationId_AnyNotificationId_HappenedOnce() {
    Integer deletedNotificationId = Mockito.anyInt();
    memberNotificationServiceImpl.deleteByNotificationId(deletedNotificationId);

    Mockito.verify(memberNotificationMapper, Mockito.times(1)).deleteByNotificationId(deletedNotificationId);
  }

  @Test
  public void findMemberIdsByNotificationId_AnyNotificationId_ResultLengthIs5() {
    Integer notificationId = Mockito.anyInt();
    Mockito.when(memberNotificationMapper.findMemberIdsByNotificationId(notificationId)).thenReturn(Arrays.asList(1 ,2, 3, 4, 5));

    List<Integer> memberIdsByNotificationId = memberNotificationServiceImpl.findMemberIdsByNotificationId(notificationId);
    Assert.assertEquals(memberIdsByNotificationId.size(), 5);
  }
}

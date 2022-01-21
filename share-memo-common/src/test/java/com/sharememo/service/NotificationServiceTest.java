package com.sharememo.service;

import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.entity.Notification;
import com.sharememo.mapper.NotificationMapper;
import com.sharememo.service.impl.NotificationServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest {
  @Mock private NotificationMapper notificationMapper;
  @Mock private MemberNotificationService memberNotificationService;

  @InjectMocks private NotificationServiceImpl notificationServiceImpl;

  @Test
  public void createNotification_AnyNotification_HappenedOnce() {
    Notification createdNotification = new Notification();
    notificationServiceImpl.createNotification(createdNotification);

    Mockito.verify(notificationMapper, Mockito.times(1)).create(createdNotification);
  }

  @Test
  public void deleteNotification_AnyInt_HappenedOnce() {
    Integer id = Mockito.anyInt();
    notificationServiceImpl.deleteNotification(id);

    Mockito.verify(notificationMapper, Mockito.times(1)).delete(id);
  }

  @Test
  public void findByNotificationDate_InquiryToday_ReturnTargetNotification() {
    String notificationDate = LocalDate.now().format(ShareMemoConstant.DATE_FORMATTER);

    Notification target = new Notification();
    Integer id = 1;
    target.setId(id);
    Mockito.when(notificationMapper.findByNotificationDate(notificationDate)).thenReturn(Arrays.asList(target));

    Assert.assertEquals(notificationServiceImpl.findByNotificationDate(LocalDate.now()).get(0).getId(), id);
  }
}

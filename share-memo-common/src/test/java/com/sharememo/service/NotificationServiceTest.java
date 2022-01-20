package com.sharememo.service;

import com.sharememo.entity.Notification;
import com.sharememo.mapper.NotificationMapper;
import com.sharememo.service.impl.NotificationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest {
  @Mock private NotificationMapper notificationMapper;

  @InjectMocks private NotificationServiceImpl notificationServiceImpl;

  @Test
  public void createNotification_anyNotification_happenedOnce() {
    Notification createdNotification = new Notification();
    notificationServiceImpl.createNotification(createdNotification);

    Mockito.verify(notificationMapper, Mockito.times(1)).create(createdNotification);
  }
}

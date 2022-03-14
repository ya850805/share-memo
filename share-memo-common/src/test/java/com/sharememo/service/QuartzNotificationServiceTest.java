package com.sharememo.service;

import com.sharememo.entity.QuartzMemberNotification;
import com.sharememo.entity.QuartzNotification;
import com.sharememo.mapper.QuartzNotificationMapper;
import com.sharememo.service.impl.QuartzNotificationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class QuartzNotificationServiceTest {
  @InjectMocks private QuartzNotificationServiceImpl quartzNotificationService;
  @Mock private QuartzNotificationMapper quartzNotificationMapper;
  @Mock private QuartzMemberNotificationService quartzMemberNotificationService;

  @Test
  public void create_5Members_Happened5Times() {
    QuartzNotification quartzNotification = new QuartzNotification();
    List<Integer> memberIds = Arrays.asList(1, 2, 3, 4, 5);
    quartzNotificationService.create(quartzNotification, memberIds);

    Mockito.verify(quartzNotificationMapper, Mockito.times(1)).create(quartzNotification);
    Mockito.verify(quartzMemberNotificationService, Mockito.times(memberIds.size()))
        .create(Mockito.any(QuartzMemberNotification.class));
  }
}

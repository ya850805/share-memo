package com.sharememo.service;

import com.sharememo.entity.QuartzMemberNotification;
import com.sharememo.entity.QuartzNotification;
import com.sharememo.mapper.QuartzMemberNotificationMapper;
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
  @Mock private QuartzMemberNotificationMapper quartzMemberNotificationMapper;

  @Test
  public void create_5Members_Happened5Times() {
    QuartzNotification quartzNotification = new QuartzNotification();
    List<Integer> memberIds = Arrays.asList(1, 2, 3, 4, 5);
    quartzNotificationService.create(quartzNotification, memberIds);

    Mockito.verify(quartzNotificationMapper, Mockito.times(1)).create(quartzNotification);
    Mockito.verify(quartzMemberNotificationService, Mockito.times(memberIds.size()))
        .create(Mockito.any(QuartzMemberNotification.class));
  }

  @Test
  public void findAllActive_3ActiveQuartzNotification_FindByIdHappened3Times() {
    // Return the id=1, 2, 3 QuartzNotification that haven't send.
    Mockito.when(quartzMemberNotificationMapper.findAllActiveNotificationId())
        .thenReturn(Arrays.asList(1, 2, 3));
    quartzNotificationService.findAllActive();

    Mockito.verify(quartzNotificationMapper, Mockito.times(3)).findById(Mockito.anyInt());
  }

  @Test
  public void delete_AnyQuartzNotificationId_HappenedOnce() {
    Integer quartzNotificationId = Mockito.anyInt();
    quartzNotificationService.delete(quartzNotificationId);

    Mockito.verify(quartzNotificationMapper, Mockito.times(1)).delete(quartzNotificationId);
    Mockito.verify(quartzMemberNotificationMapper, Mockito.times(1))
        .deleteByQuartzNotificationId(quartzNotificationId);
  }
}

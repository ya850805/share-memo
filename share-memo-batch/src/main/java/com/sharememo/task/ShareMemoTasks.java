package com.sharememo.task;

import com.sharememo.entity.Notification;
import com.sharememo.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/** @author Jason */
@Slf4j
@Component
public class ShareMemoTasks {
  @Autowired private NotificationService notificationService;

  /** Send daily notifications at 00:00. */
  //    @Scheduled(cron = "30 * * * * *")
  @Scheduled(cron = "* * 0 * * *")
  public void sendDailyNotification() {
    log.info("Execute Daily Tasks!!!");
    List<Notification> notifications = notificationService.findByNotificationDate(LocalDate.now());
    log.info(notifications.toString());

    // TODO Send notification email.
  }
}

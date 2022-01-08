package com.sharememo.service;

import com.sharememo.entity.Notification;

import java.time.LocalDate;
import java.util.List;

/** @author Jason */
public interface NotificationService {
    void createNotification(Notification notification);
    void deleteNotification(Integer id);
    List<Notification> findByNotificationDate(LocalDate notificationDate);
}

package com.sharememo.service;

import com.sharememo.entity.Notification;

/** @author Jason */
public interface NotificationService {
    void createNotification(Notification notification);
    void deleteNotification(Integer id);
}

package com.sharememo.service;

import com.sharememo.entity.QuartzNotification;

import java.util.List;

public interface QuartzNotificationService {
    void create(QuartzNotification quartzNotification, List<Integer> memberIds);

    //TODO Add unit test
    //Find all quartz_notification that haven't send.
    List<QuartzNotification> findAllActive();

    //TODO delete quartz_notification
}

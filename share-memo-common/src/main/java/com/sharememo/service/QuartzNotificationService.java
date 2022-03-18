package com.sharememo.service;

import com.sharememo.entity.QuartzNotification;

import java.util.List;

public interface QuartzNotificationService {
    void create(QuartzNotification quartzNotification, List<Integer> memberIds);

    //TODO find all active quartz_notification

    //TODO delete quartz_notification
}

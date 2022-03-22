package com.sharememo.service;

import com.sharememo.entity.QuartzNotification;

import java.util.List;

public interface QuartzNotificationService {
    void create(QuartzNotification quartzNotification, List<Integer> memberIds);

    //TODO use this method.
    //Find all quartz_notification that haven't send.
    List<QuartzNotification> findAllActive();

    //TODO delete quartz_notification
    void delete(Integer id);
}

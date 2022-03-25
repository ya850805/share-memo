package com.sharememo.service;

import com.sharememo.entity.QuartzNotification;

import java.util.List;

public interface QuartzNotificationService {
    void create(QuartzNotification quartzNotification, List<Integer> memberIds);

    //Find all quartz_notification that haven't send.
    List<QuartzNotification> findAllActive();

    //TODO use this method.
    void delete(Integer id);
}

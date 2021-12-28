package com.sharememo.controller;

import com.sharememo.entity.Notification;
import com.sharememo.service.NotificationService;
import com.sharememo.vo.R;
import com.sharememo.vo.notification.NotificationCreateVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** @author Jason */
@RestController
public class NotificationController {
  @Autowired private NotificationService notificationService;

  @PostMapping("/notification")
  public R createNotification(@RequestBody NotificationCreateVo vo) {
    Notification notification = new Notification();
    BeanUtils.copyProperties(vo, notification);

    notificationService.createNotification(notification);
    return R.ok();
  }
}

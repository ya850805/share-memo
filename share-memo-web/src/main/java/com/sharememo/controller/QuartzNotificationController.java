package com.sharememo.controller;

import com.sharememo.entity.QuartzNotification;
import com.sharememo.service.QuartzNotificationService;
import com.sharememo.util.DateUtil;
import com.sharememo.vo.R;
import com.sharememo.vo.quartzNotification.QuartzNotificationCreateVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuartzNotificationController {
  @Autowired private QuartzNotificationService quartzNotificationService;

  @PostMapping("/quartz-notification")
  public R createQuartzNotification(@RequestBody QuartzNotificationCreateVo vo) {
    QuartzNotification quartzNotification = new QuartzNotification();
    BeanUtils.copyProperties(vo, quartzNotification);
    quartzNotification.setCron(DateUtil.parseCron(vo.getCron()));

    quartzNotificationService.create(quartzNotification);
    return R.ok();
  }
}

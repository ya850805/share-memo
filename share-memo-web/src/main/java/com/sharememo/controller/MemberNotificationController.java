package com.sharememo.controller;

import com.sharememo.entity.MemberNotification;
import com.sharememo.service.MemberNotificationService;
import com.sharememo.vo.R;
import com.sharememo.vo.memberNotification.MemberNotificationCreateVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** @author Jason */
@RestController
public class MemberNotificationController {
  @Autowired
  private MemberNotificationService memberNotificationService;

  @PostMapping("/member-notification")
  public R createMemberNotification(@RequestBody MemberNotificationCreateVo vo) {
    MemberNotification memberNotification = new MemberNotification();
    BeanUtils.copyProperties(vo, memberNotification);
    memberNotificationService.createMemberNotification(memberNotification);
    return R.ok();
  }
}

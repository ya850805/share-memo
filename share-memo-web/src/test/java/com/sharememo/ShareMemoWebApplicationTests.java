package com.sharememo;

import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.service.MemberNotificationService;
import com.sharememo.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@Slf4j
@SpringBootTest
@ActiveProfiles("dev")
class ShareMemoWebApplicationTests {

  @Autowired
  private NotificationService notificationService;

  @Autowired
  private MemberNotificationService memberNotificationService;

  @Test
  void contextLoads() {
    log.info(notificationService.findByNotificationDate(
            LocalDate.parse("2022-02-05", ShareMemoConstant.DATE_FORMATTER)).toString());
  }

  @Test
  void testFindMemberIdsByNotificationId() {
    log.info(memberNotificationService.findMemberIdsByNotificationId(1).toString());
  }
}

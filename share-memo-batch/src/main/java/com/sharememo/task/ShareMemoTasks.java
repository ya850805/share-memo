package com.sharememo.task;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.sharememo.entity.Member;
import com.sharememo.entity.Notification;
import com.sharememo.service.MemberNotificationService;
import com.sharememo.service.MemberService;
import com.sharememo.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

/** @author Jason */
@Slf4j
@Component
public class ShareMemoTasks {
  @Autowired private MemberService memberService;
  @Autowired private NotificationService notificationService;
  @Autowired private MemberNotificationService memberNotificationService;
  @Autowired private LineMessagingClient lineMessagingClient;

  /** Send daily notifications at 00:00. */
//  @Scheduled(cron = "45 * * * * *")
  //  @Scheduled(cron = "* * 0 * * *")
  public void sendDailyNotification() {
    log.info("Execute Daily Tasks!!!");
    List<Notification> notifications = notificationService.findByNotificationDate(LocalDate.now());
    log.info(notifications.toString());

    // TODO Send notification email.
    //    lineMessagingClient.pushMessage(new PushMessage("U8fb87b06d107687799eae11f0ba0ba51", new
    // TextMessage("test message"), true));
  }

  @Scheduled(cron = "* * 9 * * *")
  public void sendLineMorningGreeting() {
    List<Member> members = memberService.findAllMembers();
    members.forEach(
        member -> {
          if (StringUtils.isNotBlank(member.getLineId())) {
            String userName = "";
            try {
              userName = lineMessagingClient.getProfile(member.getLineId()).get().getDisplayName();
            } catch (InterruptedException e) {
              e.printStackTrace();
            } catch (ExecutionException e) {
              e.printStackTrace();
            }
            lineMessagingClient.pushMessage(
                new PushMessage(
                    member.getLineId(), new TextMessage("早安！" + userName + " 今天又是美好的一天～😂"), true));
          }
        });
  }
}

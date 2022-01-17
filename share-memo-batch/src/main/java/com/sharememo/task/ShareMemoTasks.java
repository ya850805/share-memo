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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/** @author Jason */
@Slf4j
@Component
public class ShareMemoTasks {
  @Autowired private MemberService memberService;
  @Autowired private NotificationService notificationService;
  @Autowired private MemberNotificationService memberNotificationService;
  @Autowired private LineMessagingClient lineMessagingClient;

  @Autowired private JavaMailSender javaMailSender;

  @Value("${spring.mail.username}")
  private String sender;

  // TODO send daily email and Line message
  /** Send daily notifications at 00:00. */
  //  @Scheduled(cron = "* * 0 * * *")
  public void sendDailyNotification() {
    log.info("Execute Daily Tasks!!!");
    List<Notification> notifications = notificationService.findByNotificationDate(LocalDate.now());
    log.info(notifications.toString());

    for (Notification notification : notifications) {
      List<Member> members =
          memberNotificationService.findMemberIdsByNotificationId(notification.getId()).stream()
              .map(memberId -> memberService.getById(memberId))
              .collect(Collectors.toList());

      for (Member member : members) {
        // Push Line Message
        if (StringUtils.isNotBlank(member.getLineId())) {
          lineMessagingClient.pushMessage(
              new PushMessage(
                  member.getLineId(),
                  new TextMessage(
                      "【" + notification.getSubject() + "】：" + notification.getContent()),
                  true));
        }

        // Send Email
        if (StringUtils.isNotBlank(member.getEmail())) {
          SimpleMailMessage message = new SimpleMailMessage();
          message.setFrom(sender);
          message.setTo(member.getEmail());
          message.setSubject(notification.getSubject());
          message.setText(notification.getContent());
          javaMailSender.send(message);
        }
      }
    }
  }

//  @Scheduled(cron = "* * 9 * * *")
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

  /** Test for sending line message */
  //  @Scheduled(cron = "0 * * * * *")
  public void testLineMessage() {
    List<Member> members = memberService.findAllMembers();
    members.forEach(
        member -> {
          if (StringUtils.isNotBlank(member.getLineId())) {
            lineMessagingClient.pushMessage(
                new PushMessage(member.getLineId(), new TextMessage("測試訊息"), true));
          }
        });
  }

  /** Test for sending mail */
  //  @Scheduled(cron = "30 * * * * *")
  public void testMail() {
    List<Member> members = memberService.findAllMembers();
    members.forEach(
        member -> {
          if (StringUtils.isNotBlank(member.getEmail())) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(member.getEmail());
            message.setSubject("測試主旨");
            message.setText("測試內容");
            javaMailSender.send(message);
          }
        });
  }
}

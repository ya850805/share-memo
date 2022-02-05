package com.sharememo.controller;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.event.*;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.*;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.sharememo.entity.Member;
import com.sharememo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@LineMessageHandler
public class LineController {

  @Autowired
  private LineMessagingClient lineMessagingClient;

  @Autowired
  private MemberService memberService;

  @Autowired
  private QuartzNotificationController quartzNotificationController;

  @EventMapping
  public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
    //TODO Handle the request that adding quartz notification by line message.
    return new TextMessage("🤖: " + event.getMessage().getText());
  }

  @EventMapping
  public void handleFollowEvent(FollowEvent event) throws ExecutionException, InterruptedException {
    log.info("followed this bot: {}", event);
    Source source = event.getSource();
    UserProfileResponse user = lineMessagingClient.getProfile(source.getUserId()).get();

    Member member = new Member();
    member.setName(user.getDisplayName());
    member.setLineId(source.getUserId());
    memberService.createMember(member);
  }

  @EventMapping
  public void handleUnfollowEvent(UnfollowEvent event) {
    log.info("unfollowed this bot: {}", event);
    memberService.deleteByLineId(event.getSource().getUserId());
  }

  /**
   * Line default messages.
   * @param event event
   */
  @EventMapping
  public void handleDefaultMessageEvent(Event event) {
    log.info("event: {}", event);
  }

}

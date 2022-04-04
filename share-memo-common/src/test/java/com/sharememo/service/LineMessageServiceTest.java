package com.sharememo.service;

import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.entity.QuartzNotification;
import com.sharememo.service.impl.LineMessageServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.quartz.Scheduler;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class LineMessageServiceTest {
  @InjectMocks private LineMessageServiceImpl lineMessageService;
  @Mock private QuartzNotificationService quartzNotificationService;
  @Mock private MemberService memberService;
  @Mock private Scheduler scheduler;
  @Mock private StringRedisTemplate stringRedisTemplate;

  private static final String SENDER_LINE_ID = "123456";

  @Test
  public void handlePlainTextMessage_EmptyMessage_ReturnEmptyString() {
    String text = lineMessageService.handlePlainTextMessage(" ", SENDER_LINE_ID);
    Assert.assertEquals(text, StringUtils.EMPTY);
  }

  @Test
  public void handlePlainTextMessage_QuestionMessage_ReturnLineBotResponseQuestion() {
    String text = lineMessageService.handlePlainTextMessage(ShareMemoConstant.LINE_BOT_QUESTION, SENDER_LINE_ID);
    Assert.assertEquals(text, ShareMemoConstant.LINE_BOT_RESPONSE_QUESTION);

  }

  @Test
  public void handlePlainTextMessage_ShowAllLineBotCommand_ReturnNotEmpty() {
    String text = lineMessageService.handlePlainTextMessage(ShareMemoConstant.LINE_BOT_COMMAND, SENDER_LINE_ID);
    Assert.assertNotEquals(StringUtils.EMPTY, text);
  }

  @Test
  public void handlePlainTextMessage_FindAllActiveNoti_HappenedOnce() {
    lineMessageService.handlePlainTextMessage(ShareMemoConstant.LINE_BOT_ALL_NOTI, SENDER_LINE_ID);
    Mockito.verify(quartzNotificationService, Mockito.times(1)).findAllActive();
  }
}

package com.sharememo.service;

import com.sharememo.constant.ShareMemoConstant;
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
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

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
    String text =
        lineMessageService.handlePlainTextMessage(
            ShareMemoConstant.LINE_BOT_QUESTION, SENDER_LINE_ID);
    Assert.assertEquals(text, ShareMemoConstant.LINE_BOT_RESPONSE_QUESTION);
  }

  @Test
  public void handlePlainTextMessage_ShowAllLineBotCommand_ReturnNotEmpty() {
    String text =
        lineMessageService.handlePlainTextMessage(
            ShareMemoConstant.LINE_BOT_COMMAND, SENDER_LINE_ID);
    Assert.assertNotEquals(StringUtils.EMPTY, text);
  }

  @Test
  public void handlePlainTextMessage_FindAllActiveNoti_HappenedOnce() {
    lineMessageService.handlePlainTextMessage(ShareMemoConstant.LINE_BOT_ALL_NOTI, SENDER_LINE_ID);
    Mockito.verify(quartzNotificationService, Mockito.times(1)).findAllActive();
  }

  @Test
  public void handlePlainTextMessage_NoteIsBlank_ReturnErrorMessage() {
    String note = StringUtils.EMPTY;
    String returnMessage =
        lineMessageService.handlePlainTextMessage(
            ShareMemoConstant.LINE_BOT_INSERT_NOTE + note, SENDER_LINE_ID);
    Assert.assertEquals(returnMessage, ShareMemoConstant.LINE_NOT_MESSAGE_ERROR);
  }

  @Test
  public void handlePlainTextMessage_InsertNote_HappenedOnce() {
    String note = "note";
    ListOperations mock = Mockito.mock(ListOperations.class);

    Mockito.when(stringRedisTemplate.opsForList()).thenReturn(mock);

    lineMessageService.handlePlainTextMessage(
        ShareMemoConstant.LINE_BOT_INSERT_NOTE + note, SENDER_LINE_ID);
    Mockito.verify(mock, Mockito.times(1))
        .rightPush(ShareMemoConstant.LINE_BOT_NOTE_REDIS_KEY, note);
  }
}

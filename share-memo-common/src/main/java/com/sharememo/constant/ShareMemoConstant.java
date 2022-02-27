package com.sharememo.constant;

import java.time.format.DateTimeFormatter;

/**
 * Share-memo project common constant.
 *
 * @author Jason
 */
public class ShareMemoConstant {
  public static final String SYS_USER = "sysuser";
  public static final DateTimeFormatter DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  public static final String DEFAULT_JOB_GROUP = "ShareMemo JobGroup";
  public static final String JOB_DATA_MAP_KEY_CONTENT = "content";
  public static final String JOB_DATA_MAP_KEY_IDS = "memberIds";
  public static final String JOB_DATA_MAP_KEY_NOTIFICATION_ID = "notificationId";
  public static final String LINE_BOT_NOTE_REDIS_KEY = "SHARE_NOTE";

  /*********************Line Constant**************************/
  public static final String LINE_BOT_QUESTION = "?";

  public static final String LINE_BOT_COMMAND = "!指令";
  public static final String LINE_BOT_RESPONSE_QUESTION = "輸入「" + LINE_BOT_COMMAND + "」，查看我支援的指令！";

  /** Add line notification*/
  public static final String LINE_BOT_REMIND_ME = "提醒我：";
  public static final String LINE_BOT_COMMAND_ONE = "1. 新增定時提醒：「" + LINE_BOT_REMIND_ME + "yyyy-MM-dd HH:mm:ss 內容」";

  /** Show note*/
  public static final String LINE_BOT_NOTE = "筆記本";
  public static final String LINE_BOT_COMMAND_TWO = "2. " + LINE_BOT_NOTE;

  /** Insert note*/
  public static final String LINE_BOT_INSERT_NOTE = "新增筆記：";
  public static final String LINE_BOT_COMMAND_THREE = "3. " + LINE_BOT_INSERT_NOTE;

  /** Delete note*/
  public static final String LINE_BOT_DELETE_NOTE = "刪除筆記：";
  public static final String LINE_BOT_COMMAND_FOUR = "4. " + LINE_BOT_DELETE_NOTE;

  public static final String LINE_BOT_ACCEPT_COMMAND = "我收到指令了！";
  public static final String LINE_BOT_DEFAULT_RESPONSE = "我不請楚你在說什麼...";
  public static final String LINE_NOT_MESSAGE_ERROR = "輸入的內容有誤";
}

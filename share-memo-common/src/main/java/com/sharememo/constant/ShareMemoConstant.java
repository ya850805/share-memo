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

  /*********************Line Constant**************************/
  public static final String LINE_BOT_QUESTION = "?";

  public static final String LINE_BOT_COMMAND = "!指令";
  public static final String LINE_BOT_RESPONSE_QUESTION = "輸入「" + LINE_BOT_COMMAND + "」，查看我支援的指令！";
  public static final String LINE_BOT_REMIND_ME = "提醒我：";
  public static final String LINE_BOT_COMMAND_ONE = "1. 新增定時提醒：「" + LINE_BOT_REMIND_ME + "yyyy-MM-dd HH:mm:ss 內容」";

  public static final String LINE_BOT_ACCEPT_COMMAND = "我收到指令了！";
  public static final String LINE_BOT_DEFAULT_RESPONSE = "我不請楚你在說什麼...";
}

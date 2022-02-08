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

  /*********************Line Constant**************************/
  public static final String LINE_BOT_QUESTION = "?";

  public static final String LINE_BOT_COMMAND = "!指令";
  public static final String LINE_BOT_RESPONSE_QUESTION = "輸入「" + LINE_BOT_COMMAND + "」，查看我支援的指令！";
  public static final String LINE_BOT_COMMAND_ONE = "1. 新增定時提醒：「提醒我：yyyy-MM-dd HH:mm:ss 內容」";

  public static final String LINE_BOT_DEFAULT_RESPONSE = "我不請楚你在說什麼...";
}

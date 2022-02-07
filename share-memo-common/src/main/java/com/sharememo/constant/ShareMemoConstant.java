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
  public static final String LINE_COMMAND = "指令";
}

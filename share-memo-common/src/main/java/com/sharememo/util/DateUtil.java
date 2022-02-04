package com.sharememo.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

public class DateUtil {
  /**
   * LocalDateTime to cron expression.
   * @param localDateTime localDateTime
   * @return cron expression
   */
  public static String parseCron(LocalDateTime localDateTime) {
    StringBuffer sb = new StringBuffer();
    sb.append(fill(localDateTime.getSecond())).append(StringUtils.SPACE); // second
    sb.append(fill(localDateTime.getMinute())).append(StringUtils.SPACE); // minute
    sb.append(fill(localDateTime.getHour())).append(StringUtils.SPACE); // hour
    sb.append(fill(localDateTime.getDayOfMonth())).append(StringUtils.SPACE); // day of month
    sb.append(fill(localDateTime.getMonth().getValue())).append(StringUtils.SPACE); // month
    sb.append("?").append(StringUtils.SPACE); // day of week, set as ?
    sb.append(localDateTime.getYear()); // year
    return sb.toString();
  }

  /**
   * Fill time to 2-digit.
   * @param time original time fr
   * @return 2-digit String
   */
  private static String fill(int time) {
    if (time < 10) {
      return "0" + time;
    } else {
      return String.valueOf(time);
    }
  }
}

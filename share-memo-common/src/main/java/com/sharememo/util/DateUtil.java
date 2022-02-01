package com.sharememo.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

public class DateUtil {
  public static String parseCron(LocalDateTime localDateTime) {
    StringBuffer sb = new StringBuffer();
    sb.append(localDateTime.getSecond()).append(StringUtils.SPACE); // second
    sb.append(localDateTime.getMinute()).append(StringUtils.SPACE); // minute
    sb.append(localDateTime.getHour()).append(StringUtils.SPACE); // hour
    sb.append(localDateTime.getDayOfMonth()).append(StringUtils.SPACE); // day of month
    sb.append(localDateTime.getMonth().getValue()).append(StringUtils.SPACE); // month
    sb.append("*").append(StringUtils.SPACE); // day of week, set as *
    sb.append(localDateTime.getYear()); // year
    return sb.toString();
  }
}

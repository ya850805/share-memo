package com.sharememo.mapper;

import com.sharememo.entity.QuartzNotification;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuartzNotificationMapper {
  void create(QuartzNotification quartzNotification);
}

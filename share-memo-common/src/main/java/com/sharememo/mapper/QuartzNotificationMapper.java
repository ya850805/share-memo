package com.sharememo.mapper;

import com.sharememo.entity.QuartzNotification;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuartzNotificationMapper {
  Integer create(QuartzNotification quartzNotification);
  QuartzNotification findById(Integer id);
  void delete(Integer id);
}

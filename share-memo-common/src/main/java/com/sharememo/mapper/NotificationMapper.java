package com.sharememo.mapper;

import com.sharememo.entity.Notification;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NotificationMapper {
  void create(Notification notification);
}

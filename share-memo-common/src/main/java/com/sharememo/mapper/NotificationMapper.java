package com.sharememo.mapper;

import com.sharememo.entity.Notification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationMapper {
  void create(Notification notification);

  void delete(Integer id);

  List<Notification> findByNotificationDate(String notificationDate);
}

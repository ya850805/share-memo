package com.sharememo.mapper;

import com.sharememo.entity.QuartzMemberNotification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuartzMemberNotificationMapper {
  void create(QuartzMemberNotification quartzMemberNotification);
  QuartzMemberNotification findByQuartzNotificationIdAndMemberId(Integer quartzNotificationId, Integer memberId);
  void updateIsSend(Integer quartzNotificationId, Integer memberId);
  List<Integer> findAllActiveNotificationId();
  void deleteByQuartzNotificationId(Integer quartzNotificationId);
}

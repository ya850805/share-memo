package com.sharememo.mapper;

import com.sharememo.entity.QuartzMemberNotification;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuartzMemberNotificationMapper {
  void create(QuartzMemberNotification quartzMemberNotification);
  QuartzMemberNotification findByQuartzNotificationIdAndMemberId(Integer quartzNotificationId, Integer memberId);
}

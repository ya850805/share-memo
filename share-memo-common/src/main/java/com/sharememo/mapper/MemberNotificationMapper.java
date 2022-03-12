package com.sharememo.mapper;

import com.sharememo.entity.MemberNotification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberNotificationMapper {
    void create(MemberNotification memberNotification);
    void deleteByMemberId(Integer memberId);
    void deleteByNotificationId(Integer notificationId);
    List<Integer> findMemberIdsByNotificationId(Integer notificationId);
    void updateIsSend(Integer notificationId, Integer memberId);
}
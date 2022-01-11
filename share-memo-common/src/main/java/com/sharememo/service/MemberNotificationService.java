package com.sharememo.service;

import com.sharememo.entity.MemberNotification;

import java.util.List;

/**
 * @author Jason
 */
public interface MemberNotificationService {
    void createMemberNotification(MemberNotification memberNotification);
    void deleteByMemberId(Integer memberId);
    void deleteByNotificationId(Integer notificationId);
    List<Integer> findMemberIdsByNotificationId(Integer notificationId);
}

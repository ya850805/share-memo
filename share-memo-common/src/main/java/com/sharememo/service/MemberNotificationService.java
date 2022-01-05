package com.sharememo.service;

import com.sharememo.entity.MemberNotification;

/**
 * @author Jason
 */
public interface MemberNotificationService {
    void createMemberNotification(MemberNotification memberNotification);
    void deleteByMemberId(Integer memberId);
    void deleteByNotificationId(Integer notificationId);
}

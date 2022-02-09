package com.sharememo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * quartz_member_notification
 * @author 
 */
@Data
public class QuartzMemberNotification extends QuartzMemberNotificationKey implements Serializable {
    private String isSend;

    private LocalDateTime createTimestamp;

    private String createUser;

    private LocalDateTime updateTimestamp;

    private String updateUser;

    private static final long serialVersionUID = 1L;
}
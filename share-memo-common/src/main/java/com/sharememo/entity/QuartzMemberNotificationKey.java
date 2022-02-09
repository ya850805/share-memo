package com.sharememo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * quartz_member_notification
 * @author 
 */
@Data
public class QuartzMemberNotificationKey implements Serializable {
    private Integer memberId;

    private Integer quartzNotificationId;

    private static final long serialVersionUID = 1L;
}
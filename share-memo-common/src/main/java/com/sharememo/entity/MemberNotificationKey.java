package com.sharememo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * member_notification
 * @author 
 */
@Data
public class MemberNotificationKey implements Serializable {
    private Integer memberId;

    private Integer notificationId;

    private static final long serialVersionUID = 1L;
}
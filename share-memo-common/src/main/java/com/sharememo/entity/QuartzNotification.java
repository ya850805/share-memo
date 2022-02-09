package com.sharememo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * quartz_notification
 * @author 
 */
@Data
public class QuartzNotification implements Serializable {
    private Integer id;

    private String jobName;

    private String jobGroup;

    private String subject;

    private String content;

    private String cron;

    private LocalDateTime createTimestamp;

    private String createUser;

    private LocalDateTime updateTimestamp;

    private String updateUser;

    private static final long serialVersionUID = 1L;
}
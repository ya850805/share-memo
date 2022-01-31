package com.sharememo.entity;

import java.io.Serializable;
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

    private String createTimestamp;

    private String createUser;

    private String updateTimestamp;

    private String updateUser;

    private static final long serialVersionUID = 1L;
}
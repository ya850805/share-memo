package com.sharememo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * notification
 * @author 
 */
@Data
public class Notification implements Serializable {
    private Integer id;

    private String subject;

    private String content;

    private String isSend;

    private LocalDateTime createTimestamp;

    private String createUser;

    private LocalDateTime updateTimestamp;

    private String updateUser;

    private static final long serialVersionUID = 1L;
}
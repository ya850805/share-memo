package com.sharememo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * member
 * @author 
 */
@Data
public class Member implements Serializable {
    private Integer id;

    private String name;

    private String email;

    private LocalDateTime createTimestamp;

    private String createUser;

    private LocalDateTime updateTimestamp;

    private String updateUser;

    private static final long serialVersionUID = 1L;
}
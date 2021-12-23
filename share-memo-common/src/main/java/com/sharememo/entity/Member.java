package com.sharememo.entity;

import java.io.Serializable;
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

    private String createTimestamp;

    private String createUser;

    private String updateTimestamp;

    private String updateUser;

    private static final long serialVersionUID = 1L;
}
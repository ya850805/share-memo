package com.sharememo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.sharememo.constant.ShareMemoConstant;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * member
 * @author 
 */
@Data
public class Member implements Serializable {
    private Integer id;

    private String name;

    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTimestamp;

    private String createUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTimestamp;

    private String updateUser;

    private static final long serialVersionUID = 1L;
}
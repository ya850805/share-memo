package com.sharememo.vo;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * @author Jason
 *     <p>Return object
 */
public class R extends HashMap<String, Object> {

  public R(Integer code, String message) {
    put("code", code);
    put("message", message);
  }

  public static R ok() {
    return new R(HttpStatus.OK.value(), "success");
  }

  public static R error() {
    return new R(HttpStatus.FORBIDDEN.value(), "error");
  }
}

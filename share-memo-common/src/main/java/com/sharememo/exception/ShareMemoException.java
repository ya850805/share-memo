package com.sharememo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/** @author Jason */
@Data
public class ShareMemoException extends RuntimeException {
  private Integer statusCode;
  private String message;

  public ShareMemoException(Integer statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
  }
}

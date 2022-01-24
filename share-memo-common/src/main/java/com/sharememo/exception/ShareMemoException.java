package com.sharememo.exception;

import lombok.Data;

/**
 *  @author Jason
 *  Custom Exception extends RuntimeException.
 */
@Data
public class ShareMemoException extends RuntimeException {
  private Integer statusCode;
  private String message;

  public ShareMemoException(Integer statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
  }
}

package com.sharememo.controller;

import com.sharememo.exception.ShareMemoException;
import com.sharememo.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** @author Jason */
@Slf4j
@RestControllerAdvice
public class ShareMemoExceptionHandler {
  @ExceptionHandler(ShareMemoException.class)
  public R handleShareMemoException(ShareMemoException e) {
    log.error(e.getMessage());
    return new R(e.getStatusCode(), e.getMessage());
  }

  @ExceptionHandler(RuntimeException.class)
  public R handleRuntimeException(RuntimeException e) {
    log.error(e.toString());
    return R.error();
  }
}

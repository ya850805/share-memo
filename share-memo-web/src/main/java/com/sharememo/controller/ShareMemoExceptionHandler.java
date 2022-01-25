package com.sharememo.controller;

import com.sharememo.exception.ShareMemoException;
import com.sharememo.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Jason
 * Global exception handler.
 */
@Slf4j
@RestControllerAdvice
public class ShareMemoExceptionHandler {
  /**
   * Handle ShareMemoException
   * @param e ShareMemoException
   * @return R
   */
  @ExceptionHandler(ShareMemoException.class)
  public R handleShareMemoException(ShareMemoException e) {
    log.error(e.getMessage());
    return new R(e.getStatusCode(), e.getMessage());
  }

  /**
   * Handle MethodArgumentNotValidException
   * @param e MethodArgumentNotValidException
   * @return R
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public R handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    return new R(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getFieldError().getDefaultMessage());
  }

  /**
   * Handle RuntimeException(last)
   * @param e RuntimeException
   * @return R
   */
  @ExceptionHandler(RuntimeException.class)
  public R handleRuntimeException(RuntimeException e) {
    log.error(e.toString());
    return R.error();
  }
}

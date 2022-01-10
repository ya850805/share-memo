package com.sharememo.controller;

import com.sharememo.exception.ShareMemoException;
import com.sharememo.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Jason */
@Slf4j
@RestController
public class WelcomeController {

  @GetMapping("/welcome")
  public String welcome() {
    return "Hello World";
  }

  @GetMapping("/exception")
  public String testShareMemoException() {
    throw new ShareMemoException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Test Server Error!");
  }

  @PostMapping("post")
  public R testPost() {
    log.info("post!");
    return R.ok();
  }

}

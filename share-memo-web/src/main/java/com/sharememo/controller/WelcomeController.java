package com.sharememo.controller;

import com.sharememo.exception.ShareMemoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Jason */
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
}

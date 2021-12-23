package com.sharememo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Jason */
@RestController
public class WelcomeController {

  @GetMapping("/welcome")
  public String welcome() {
    return "Hello World";
  }
}

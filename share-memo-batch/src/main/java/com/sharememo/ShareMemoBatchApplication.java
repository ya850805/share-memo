package com.sharememo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShareMemoBatchApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShareMemoBatchApplication.class, args);
  }
}

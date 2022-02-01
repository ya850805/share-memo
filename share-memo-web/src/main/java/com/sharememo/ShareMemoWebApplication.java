package com.sharememo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShareMemoWebApplication {

  //TODO dynamic properties for org.quartz.dataSource.qzDS.password
  public static void main(String[] args) {
    SpringApplication.run(ShareMemoWebApplication.class, args);
  }
}

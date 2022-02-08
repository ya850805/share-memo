package com.sharememo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShareMemoWebApplication {

  //TODO dynamic properties for org.quartz.dataSource.qzDS.password
  //TODO add member_quartz_notification table and following code.
  public static void main(String[] args) {
    SpringApplication.run(ShareMemoWebApplication.class, args);
  }
}

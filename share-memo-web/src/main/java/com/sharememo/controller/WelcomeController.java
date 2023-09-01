package com.sharememo.controller;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.sharememo.exception.ShareMemoException;
import com.sharememo.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.net.URI;

/** @author Jason */
@Slf4j
@RestController
public class WelcomeController {

  @Autowired
  private LineMessagingClient lineMessagingClient;

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

  @GetMapping("/sendText")
  public void sendLineTextMessage(@RequestParam("msg") String msg) {
    TextMessage textMessage = new TextMessage(msg);
    String to = "";
    PushMessage pushMessage = new PushMessage(to, textMessage);
    lineMessagingClient.pushMessage(pushMessage);
  }

  @GetMapping("/sendImage")
  public void sendLineImageMessage() {
    URI uri = URI.create("https://thumb.photo-ac.com/e4/e4ae7fe69ff6932b8ea1d94b9eeecde8_w.jpeg");
    ImageMessage imageMessage = new ImageMessage(uri, uri);
    String to = "";
    PushMessage pushMessage = new PushMessage(to, imageMessage);
    lineMessagingClient.pushMessage(pushMessage);
  }
}

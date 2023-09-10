package com.sharememo.controller;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Button;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.container.BubbleStyles;
import com.linecorp.bot.model.message.flex.container.FlexContainer;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.sharememo.exception.ShareMemoException;
import com.sharememo.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.net.URI;
import java.util.Arrays;

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

  @GetMapping("/sendFlexMessage")
  public void sendFlexMessage() {
    FlexContainer flexContainer = Bubble.builder()
            .styles(BubbleStyles.builder().build())
            .body(Box.builder()
                    .layout(FlexLayout.VERTICAL)
                    .contents(Arrays.asList(
                            Text.builder()
                                    .text("Hello, Flex Message!")
                                    .weight(Text.TextWeight.REGULAR)
                                    .size(FlexFontSize.Md)
                                    .build(),
                            Button.builder()
                                    .style(Button.ButtonStyle.PRIMARY)
                                    .action(new PostbackAction("Click me!", "button_clicked"))
                                    .build()
                    ))
                    .build())
            .build();
    FlexMessage flexMessage = new FlexMessage("this is flex message alter text", flexContainer);

    String to = "";
    PushMessage pushMessage = new PushMessage(to, flexMessage);
    lineMessagingClient.pushMessage(pushMessage);
  }
}

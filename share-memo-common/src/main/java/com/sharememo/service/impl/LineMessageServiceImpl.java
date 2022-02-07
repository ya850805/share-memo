package com.sharememo.service.impl;

import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.service.LineMessageService;
import org.springframework.stereotype.Service;

@Service
public class LineMessageServiceImpl implements LineMessageService {
  @Override
  public String handlePlainTextMessage(String text) {
    //TODO Add text message logic.
    //TODO Handle the request that adding quartz notification by line message.
    return ShareMemoConstant.LINE_COMMAND + text;
  }
}

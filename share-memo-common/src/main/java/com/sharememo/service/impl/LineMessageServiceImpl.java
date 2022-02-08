package com.sharememo.service.impl;

import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.service.LineMessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class LineMessageServiceImpl implements LineMessageService {
  @Override
  public String handlePlainTextMessage(String text) {
    // TODO Add text message logic.
    // TODO Handle the request that adding quartz notification by line message.
    if (ShareMemoConstant.LINE_BOT_QUESTION.equals(text)) {
      return ShareMemoConstant.LINE_BOT_RESPONSE_QUESTION;
    } else if (ShareMemoConstant.LINE_BOT_COMMAND.equals(text)) {
      StringBuilder sb = new StringBuilder();
      sb.append(ShareMemoConstant.LINE_BOT_COMMAND_ONE);
      sb.append(StringUtils.LF);

      return sb.toString();
    } else {
      return ShareMemoConstant.LINE_BOT_DEFAULT_RESPONSE;
    }
  }
}

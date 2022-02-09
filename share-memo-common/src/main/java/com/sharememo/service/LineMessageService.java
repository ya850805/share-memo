package com.sharememo.service;

public interface LineMessageService {
  String handlePlainTextMessage(String text, String senderLineId);
}

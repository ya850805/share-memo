package com.sharememo.controller;

import com.sharememo.entity.Member;
import com.sharememo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** @author Jason */
@RestController
public class MemberController {
  @Autowired private MemberService memberService;

  @GetMapping("/member")
  public Member getById(@RequestParam("id") Integer id) {
    return memberService.getById(id);
  }
}

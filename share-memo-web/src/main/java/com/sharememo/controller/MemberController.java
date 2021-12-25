package com.sharememo.controller;

import com.sharememo.entity.Member;
import com.sharememo.service.MemberService;
import com.sharememo.vo.R;
import com.sharememo.vo.member.MemberCreateVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** @author Jason */
@RestController
public class MemberController {
  @Autowired private MemberService memberService;

  @GetMapping("/member")
  public Member getById(@RequestParam("id") Integer id) {
    return memberService.getById(id);
  }

  @PostMapping("/member")
  public R createMember(@RequestBody MemberCreateVo vo) {
    Member member = new Member();
    BeanUtils.copyProperties(vo, member);

    memberService.createMember(member);
    return R.ok();
  }
}

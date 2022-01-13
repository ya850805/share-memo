package com.sharememo.controller;

import com.sharememo.entity.Member;
import com.sharememo.exception.ShareMemoException;
import com.sharememo.service.MemberService;
import com.sharememo.vo.R;
import com.sharememo.vo.member.MemberCreateVo;
import com.sharememo.vo.member.MemberUpdateVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @GetMapping("/members")
  public List<Member> findAllMembers() {
    return memberService.findAllMembers();
  }

  @DeleteMapping("/member/{id}")
  public R deleteById(@PathVariable("id") Integer id) {
    memberService.deleteById(id);
    return R.ok();
  }

  @DeleteMapping("/members")
  public R deleteByIds(@RequestParam("ids") List<Integer> ids) {
    memberService.deleteByIds(ids);
    return R.ok();
  }

  @PutMapping("/member")
  public R updateMember(@RequestBody MemberUpdateVo vo) {
    if (vo.getId() == null) throw new ShareMemoException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "請選擇欲修改之Member");

    Member member = new Member();
    BeanUtils.copyProperties(vo, member);
    memberService.updateMember(member);

    return R.ok();
  }
}

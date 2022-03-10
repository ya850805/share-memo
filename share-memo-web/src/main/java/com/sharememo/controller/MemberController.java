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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/** @author Jason */
@Validated
@RestController
public class MemberController {
  @Autowired private MemberService memberService;

  @GetMapping("/member")
  public Member getById(@RequestParam("id") @Min(value = 1, message = "memberId最小為1") Integer id) {
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
  public R updateMember(@RequestBody @Valid MemberUpdateVo vo) {
    Member member = new Member();
    BeanUtils.copyProperties(vo, member);
    memberService.updateMember(member);

    return R.ok();
  }
}

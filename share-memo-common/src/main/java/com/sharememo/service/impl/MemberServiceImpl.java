package com.sharememo.service.impl;

import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.mapper.MemberMapper;
import com.sharememo.entity.Member;
import com.sharememo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/** @author Jason */
@Service
public class MemberServiceImpl implements MemberService {
  @Autowired private MemberMapper memberMapper;

  public Member getById(Integer id) {
    return memberMapper.selectByPrimaryKey(id);
  }

  @Override
  public void createMember(Member member) {
    member.setCreateTimestamp(LocalDateTime.now());
    member.setCreateUser(ShareMemoConstant.SYS_USER);
    member.setUpdateTimestamp(LocalDateTime.now());
    member.setUpdateUser(ShareMemoConstant.SYS_USER);

    memberMapper.create(member);
  }

  @Override
  public List<Member> findAllMembers() {
    return memberMapper.findAll();
  }
}

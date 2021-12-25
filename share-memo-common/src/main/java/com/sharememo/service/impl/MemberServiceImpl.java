package com.sharememo.service.impl;

import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.mapper.MemberMapper;
import com.sharememo.entity.Member;
import com.sharememo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/** @author Jason */
@Service
public class MemberServiceImpl implements MemberService {
  @Autowired private MemberMapper memberMapper;

  public Member getById(Integer id) {
    return memberMapper.selectByPrimaryKey(id);
  }

  @Override
  public void createMember(Member member) {
    member.setCreateTimestamp(LocalDateTime.now().format(ShareMemoConstant.DATE_TIME_FORMATTER));
    member.setCreateUser(ShareMemoConstant.SYS_USER);
    member.setUpdateTimestamp(LocalDateTime.now().format(ShareMemoConstant.DATE_TIME_FORMATTER));
    member.setUpdateUser(ShareMemoConstant.SYS_USER);

    memberMapper.create(member);
  }
}

package com.sharememo.service.impl;

import com.sharememo.constant.ShareMemoConstant;
import com.sharememo.mapper.MemberMapper;
import com.sharememo.entity.Member;
import com.sharememo.service.MemberNotificationService;
import com.sharememo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/** @author Jason */
@Service
public class MemberServiceImpl implements MemberService {
  @Autowired private MemberMapper memberMapper;
  @Autowired private MemberNotificationService memberNotificationService;

  public Member getById(Integer id) {
    return memberMapper.selectByPrimaryKey(id);
  }

  @Override
  @Transactional
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

  @Override
  @Transactional
  public void deleteById(Integer id) {
    memberNotificationService.deleteByMemberId(id);
    memberMapper.deleteByPrimaryKey(id);
  }

  @Override
  @Transactional
  public void deleteByIds(List<Integer> ids) {
    for (Integer id : ids) {
      deleteById(id);
    }
  }
}

package com.sharememo.service.impl;

import com.sharememo.mapper.MemberMapper;
import com.sharememo.entity.Member;
import com.sharememo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** @author Jason */
@Service
public class MemberServiceImpl implements MemberService {
  @Autowired private MemberMapper memberMapper;

  public Member getById(Integer id) {
    return memberMapper.selectByPrimaryKey(id);
  }
}

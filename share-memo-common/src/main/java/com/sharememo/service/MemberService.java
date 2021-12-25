package com.sharememo.service;

import com.sharememo.entity.Member;

/** @author Jason */
public interface MemberService {
  Member getById(Integer id);
  void createMember(Member member);
}

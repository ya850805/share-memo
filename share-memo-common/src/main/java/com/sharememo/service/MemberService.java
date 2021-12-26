package com.sharememo.service;

import com.sharememo.entity.Member;

import java.util.List;

/** @author Jason */
public interface MemberService {
  Member getById(Integer id);

  void createMember(Member member);

  List<Member> findAllMembers();
}

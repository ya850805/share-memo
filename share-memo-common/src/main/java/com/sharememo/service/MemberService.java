package com.sharememo.service;

import com.sharememo.entity.Member;

import java.util.List;

/** @author Jason */
public interface MemberService {
  Member getById(Integer id);

  void createMember(Member member);

  List<Member> findAllMembers();

  void deleteById(Integer id);

  void deleteByIds(List<Integer> ids);

  void deleteByLineId(String lineId);

  void updateMember(Member member);

  Member getByLineId(String lineId);
}

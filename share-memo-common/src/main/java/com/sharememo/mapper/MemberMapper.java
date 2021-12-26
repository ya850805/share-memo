package com.sharememo.mapper;

import com.sharememo.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
  Member selectByPrimaryKey(Integer id);
  void create(Member member);
  List<Member> findAll();
}

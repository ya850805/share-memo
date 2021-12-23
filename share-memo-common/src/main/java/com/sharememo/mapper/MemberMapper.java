package com.sharememo.mapper;

import com.sharememo.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
  Member selectByPrimaryKey(Integer id);
}

package com.sharememo.vo.member;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MemberUpdateVo {
  @NotNull(message = "請選擇欲修改之Member") private Integer id;

  private String name;

  private String lineId;

  private String email;
}

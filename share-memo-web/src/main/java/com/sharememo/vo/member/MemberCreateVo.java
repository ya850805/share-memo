package com.sharememo.vo.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/** @author Jason */
@Data
public class MemberCreateVo {
  @NotBlank(message = "member姓名不得為空")
  private String name;

  @NotBlank(message = "member line id不得為空")
  private String lineId;

  @NotBlank(message = "member email不得為空")
  private String email;
}

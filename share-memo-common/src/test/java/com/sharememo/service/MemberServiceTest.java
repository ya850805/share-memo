package com.sharememo.service;

import com.sharememo.entity.Member;
import com.sharememo.mapper.MemberMapper;
import com.sharememo.service.impl.MemberServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MemberServiceTest {
  @Mock private MemberMapper memberMapper;
  @InjectMocks private MemberServiceImpl memberServiceImpl;

  @Test
  public void getById_id1_NotNull() {
    Integer id = 1;
    Member member = new Member();
    Mockito.when(memberMapper.selectByPrimaryKey(id)).thenReturn(member);

    Assertions.assertNotNull(memberServiceImpl.getById(id));
  }

  @Test
  public void createMember_anyMember_happenedOnce() {
    Member createdMember = new Member();
    memberServiceImpl.createMember(createdMember);

    Mockito.verify(memberMapper, Mockito.times(1)).create(createdMember);
  }
}

package com.sharememo.service;

import com.sharememo.entity.Member;
import com.sharememo.mapper.MemberMapper;
import com.sharememo.service.impl.MemberServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    assertNotNull(memberServiceImpl.getById(id));
  }
}

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

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MemberServiceTest {
  @Mock private MemberMapper memberMapper;
  @Mock private MemberNotificationService memberNotificationService;
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

  @Test
  public void findAllMembers_anyMembers_happenedOnce() {
    memberServiceImpl.findAllMembers();

    Mockito.verify(memberMapper, Mockito.times(1)).findAll();
  }

  @Test
  public void deleteById_id1_happenedOnce() {
    Integer id = 1;
    memberServiceImpl.deleteById(id);

    Mockito.verify(memberNotificationService, Mockito.times(1)).deleteByMemberId(id);
    Mockito.verify(memberMapper, Mockito.times(1)).deleteByPrimaryKey(id);
  }

  @Test
  public void deleteByIds_listSizeIs5_happened5Times() {
    List<Integer> ids = Arrays.asList(1, 2, 3, 4, 5);
    memberServiceImpl.deleteByIds(ids);

    Mockito.verify(memberNotificationService, Mockito.times(5)).deleteByMemberId(Mockito.anyInt());
    Mockito.verify(memberMapper, Mockito.times(5)).deleteByPrimaryKey(Mockito.anyInt());
  }

  @Test
  public void deleteByLineId_anyLineId_happenedOnce() {
    String lineId = "123456";
    memberServiceImpl.deleteByLineId(lineId);

    Mockito.verify(memberMapper, Mockito.times(1)).deleteByLineId(lineId);
  }

  @Test
  public void updateMember_anyMember_happenedOnce() {
    Member updatedMember = new Member();
    memberServiceImpl.updateMember(updatedMember);

    Mockito.verify(memberMapper, Mockito.times(1)).updateMember(updatedMember);
  }
}

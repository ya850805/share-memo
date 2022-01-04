package com.sharememo.mapper;

import com.sharememo.entity.MemberNotification;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberNotificationMapper {
    void create(MemberNotification memberNotification);
}
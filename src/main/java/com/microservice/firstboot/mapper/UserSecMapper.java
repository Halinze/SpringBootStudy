package com.microservice.firstboot.mapper;

import com.microservice.firstboot.model.UserSec;

public interface UserSecMapper {
    //Selective  经过处理的
    int deleteByPrimaryKey(Long id);

    int insert(UserSec record);

    int insertSelective(UserSec record);

    UserSec selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserSec record);

    int updateByPrimaryKey(UserSec record);
}
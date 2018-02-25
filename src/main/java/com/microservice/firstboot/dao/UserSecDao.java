package com.microservice.firstboot.dao;

import com.microservice.firstboot.mapper.UserSecMapper;
import com.microservice.firstboot.model.UserSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by 46597 on 2018/2/25.
 */
@Repository
public class UserSecDao {

    @Autowired
    private UserSecMapper userSecMapper;

    public UserSec selectByPrimaryKey(long id){
        return userSecMapper.selectByPrimaryKey(id);
    }

}

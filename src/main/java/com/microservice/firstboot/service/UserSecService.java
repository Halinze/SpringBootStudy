package com.microservice.firstboot.service;

import com.microservice.firstboot.dao.UserSecDao;
import com.microservice.firstboot.model.UserSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 46597 on 2018/2/25.
 */
@Service
public class UserSecService {

    @Autowired
    private UserSecDao userSecDao;

    public UserSec getUser(long id){
        return userSecDao.selectByPrimaryKey(id);
    }

}

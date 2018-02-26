package com.microservice.firstboot.dao;

import com.microservice.firstboot.config.DatabaseContextHolder;
import com.microservice.firstboot.config.DatabaseType;
import com.microservice.firstboot.mapper.CarMapper;
import com.microservice.firstboot.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 46597 on 2018/2/25.
 */
@Repository
public class CarDao {

    @Autowired
    private CarMapper carMapper;

    public List<Car> selectByOwner(long ownerId){
        //设置数据库 设置切面了
       // DatabaseContextHolder.setDatabaseType(DatabaseType.microservicedb2);
        return  carMapper.selectByOwner(ownerId);
    }


}

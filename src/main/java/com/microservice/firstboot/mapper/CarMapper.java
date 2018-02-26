package com.microservice.firstboot.mapper;

import com.microservice.firstboot.model.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarMapper {

    List<Car> selectByOwner (@Param("ownerId")long ownerId);


    int deleteByPrimaryKey(Long id);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);
}
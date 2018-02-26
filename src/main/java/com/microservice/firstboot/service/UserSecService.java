package com.microservice.firstboot.service;

import com.microservice.firstboot.dao.CarDao;
import com.microservice.firstboot.dao.UserSecDao;
import com.microservice.firstboot.model.Car;
import com.microservice.firstboot.model.UserAndCar;
import com.microservice.firstboot.model.UserSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 46597 on 2018/2/25.
 */
@Service
public class UserSecService {

    @Autowired
    private UserSecDao userSecDao;

    @Autowired
    private CarDao carDao;

    public UserSec getUser(long id){
        return userSecDao.selectByPrimaryKey(id);
    }

    public UserAndCar getUserAndCar(long userId){

        UserAndCar userAndCar = new UserAndCar();
        UserSec userSec = userSecDao.selectByPrimaryKey(userId);
        userAndCar.setId(userId);
        userAndCar.setName(userSec.getName());
        userAndCar.setPhone(userSec.getPhone());

        List<Car> cars = carDao.selectByOwner(userId);
        userAndCar.setCars(cars);

        return userAndCar;


    }


}

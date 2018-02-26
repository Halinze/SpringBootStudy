package com.microservice.firstboot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by 46597 on 2018/2/25.
 */
@Getter
@Setter
@NoArgsConstructor
public class UserAndCar extends UserSec{

    private List<Car> cars ;



}

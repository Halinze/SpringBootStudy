package com.microservice.firstboot.model;

import lombok.Builder;

/**
 * Created by 46597 on 2018/2/24.
 */
@Builder
public class Address {

    private int id ;
    private String province;
    private String city;
    private String country;


    void test(){
        Address address = Address.builder().province("内蒙古自治区")
                .country("呼和浩特市")
                .city("回民区")
                .build();
    }

}

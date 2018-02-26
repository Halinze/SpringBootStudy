package com.microservice.firstboot.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Car {
    private Long id;

    private String name;

    private String color;

    private Long owner;


}
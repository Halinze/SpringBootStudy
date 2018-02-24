package com.microservice.firstboot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by 46597 on 2018/2/24.
 */
@ApiModel("用户模型")
@AllArgsConstructor   //生成全参构造器  需要装插件；
@Getter  // 生成get方法
public class User {

    @ApiModelProperty("用户ID")
    private int id ;
    @ApiModelProperty("用户姓名")
    private String name ;
    @ApiModelProperty("用户密码")
    private String password;





}

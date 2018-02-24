package com.microservice.firstboot.controller;

import com.microservice.firstboot.model.Address;
import com.microservice.firstboot.model.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 46597 on 2018/2/24.
 */
@Api("user相关api")
@RestController
@RequestMapping("/user")
public class SecondBootController {

    @ApiOperation("根据ID获取用户信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query",name="id",
            dataType = "int",required = true,value="用户的id",defaultValue = "1")})
    @ApiResponses({@ApiResponse(code = 400,message = "请求参数没填好"),
            @ApiResponse(code = 404,message ="请求路径没有或者页面跳转路径不对" )})
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    public User getUserInfo(@RequestParam("id")int id){


        if(id == 1){
            return new User(1,"小红","123456");
        }

        return new User(2,"小刚","123456");



    }




}

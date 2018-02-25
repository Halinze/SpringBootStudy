package com.microservice.firstboot.controller;

import com.microservice.firstboot.model.UserSec;
import com.microservice.firstboot.service.UserSecService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 46597 on 2018/2/25.
 */
@Api("userSec相关api")
@RestController
@RequestMapping("/usersec")
public class DbAndCacheController {

    @Autowired
    private UserSecService userSecService;

    @ApiOperation("根据ID获取用户信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query",name = "id",dataType = "long" ,
            required = true , value = "用户的id" ,defaultValue = "1")})
    @ApiResponses({@ApiResponse(code = 401,message = "权限校验不通过")})
    @RequestMapping(value = "/getUserInfo" , method = RequestMethod.GET)
    public UserSec getUserInfo(@RequestParam("id") long id){
        return userSecService.getUser(id);
    }


}

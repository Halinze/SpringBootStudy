package com.microservice.firstboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 46597 on 2018/2/24.
 * @RestController 复合注解  相当于@Controller @ResponseBody
 *
 * 学会使用： mvn dependency:tree 命令 ！！解决jar包冲突很有用！
 *
 */
@RestController
@RequestMapping("/firstboot")
public class FirstBootController {

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello(){
        return "hello,this is my first boot program !!!";
    }




}

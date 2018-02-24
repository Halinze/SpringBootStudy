package com.microservice.firstboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by 46597 on 2018/2/24.
 */

/**
 * 复合注解
 * @Configuration, 指明该类由Spring管理
 * @EnableAutoConfiguration  该注解用于启动服务的自动配置功能；
 * @ComponentScan: 该注解用于扫描类
 *
 *
 * 主类位于最短路径报下，方便扫描整个项目中的类；
 *
 */
@SpringBootApplication
@EnableSwagger2//启动Swagger
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }


}

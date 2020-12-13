package com.atguigu.eduorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
/**
 * @author bing  @create 2020/12/12-4:09 下午
 */
@SpringBootApplication
@EnableDiscoveryClient // 注册
@EnableFeignClients // 要远程调用
@MapperScan("com.atguigu.eduorder.mapper")
@ComponentScan(basePackages = {"com.atguigu"})
public class OrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }
}

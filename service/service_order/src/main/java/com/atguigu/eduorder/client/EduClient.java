package com.atguigu.eduorder.client;

import com.atguigu.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author bing  @create 2020/12/12-9:03 下午
 */
@Component
@FeignClient("service-edu")
public interface EduClient {

    //根据课程id查询课程信息
    @PostMapping("/eduservice/coursefront/getCourseInfoOrder/{id}") // 方法全路径
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id);// 注解必须写参数

}

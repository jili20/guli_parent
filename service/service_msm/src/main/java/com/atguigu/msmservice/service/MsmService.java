package com.atguigu.msmservice.service;

import java.util.Map;

/**
 * @author bing  @create 2020/12/9-9:13 下午
 */
public interface MsmService {
    //发送短信的方法
    boolean send(Map<String, Object> param, String phone);
}

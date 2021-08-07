package com.kim.spring.cloud.alibaba.sentinel.basic.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.kim.spring.cloud.alibaba.sentinel.basic.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author kim
 * @Since 2021/8/7
 */
@Service
public class UserServiceImpl implements UserService {


    @Override
    @SentinelResource(value = "hello",)
    public String hello(String name) {
        return null;
    }



}

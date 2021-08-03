package com.kim.spring.cloud.alibaba.dubbo.user.provider.service;

import com.kim.spring.cloud.alibaba.dubbo.user.api.UserService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Author kim
 * @Since 2021/8/3
 */
@DubboService
public class UserServiceImpl implements UserService {


    @Override
    public User getUserById(Integer userId) {
        return new User(userId,"mike",18);
    }
}

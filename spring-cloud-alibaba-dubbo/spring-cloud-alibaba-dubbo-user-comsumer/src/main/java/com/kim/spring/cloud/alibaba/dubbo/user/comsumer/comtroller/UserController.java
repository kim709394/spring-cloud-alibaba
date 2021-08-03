package com.kim.spring.cloud.alibaba.dubbo.user.comsumer.comtroller;

import com.kim.spring.cloud.alibaba.dubbo.user.api.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author kim
 * @Since 2021/8/3
 */
@RestController
@RequestMapping("/dubbo/user")
public class UserController {

    //获取dubbo订阅服务
    @DubboReference
    private UserService userService;


    @GetMapping("/get/{id}")
    public String get(@PathVariable Integer id){
        UserService.User user = userService.getUserById(id);
        return String.format("username:%s,id:%s,age:%s",user.getName(),user.getId(),user.getAge());
    }


}

package com.kim.spring.cloud.alibaba.sentinel.feign.comsumer.client;

import com.kim.spring.cloud.alibaba.sentinel.feign.comsumer.client.fallback.UserFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author kim
 * @Since 2021/8/18
 */
@FeignClient(name="sentinel-feign-provider",fallback = UserFallback.class)
public interface UserClient {

    @GetMapping("/sentinel/feign/user/get/{name}")
    String getUser(@PathVariable(name="name") String name);

}

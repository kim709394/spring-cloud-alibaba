package com.kim.spring.cloud.alibaba.nacos.discovery.comsumer.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author kim
 * @Since 2021/8/2
 */

@RestController
@RequestMapping("/nacos/comsumer")
public class ComsumerController {

    @Autowired
    private NacosServiceManager nacosServiceManager;

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/test")
    public String test() throws NacosException {
        //从注册中心nacos获取服务名为nacos-discovery-provider的实例
        NamingService namingService = nacosServiceManager.getNamingService(nacosDiscoveryProperties.getNacosProperties());
        Instance instance = namingService.selectOneHealthyInstance("nacos-discovery-provider");
        String url = String.format("http://%s:%s/nacos/discovery/provider",instance.getIp(),instance.getPort());
        String response = restTemplate.getForObject(url, String.class);
        return "provider service return :"+response;

    }
}

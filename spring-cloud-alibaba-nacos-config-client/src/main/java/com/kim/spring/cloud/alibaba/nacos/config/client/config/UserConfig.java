package com.kim.spring.cloud.alibaba.nacos.config.client.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author kim
 * @Since 2021/7/30
 */
@SpringBootConfiguration
//当nacos的配置更新了后，使用@ConfigurationProperties注解获取的配置也将会更新
@ConfigurationProperties(prefix = "user")
public class UserConfig {

    private Integer id;
    private String name;
    private Integer age;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

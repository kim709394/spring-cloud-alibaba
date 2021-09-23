package com.kim.spring.cloud.alibaba.rocketmq.spring.consumer.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huangjie
 * @description
 * @date 2021/9/17
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1569136815787597682L;
    private Integer id;

    private String name;

    private Date createTime;

    public static User builder(){
        return new User();
    }

    public User id(Integer id){
        this.id=id;
        return this;
    }

    public User name(String name){
        this.name=name;
        return this;
    }

    public User createTime(Date createTime){
        this.createTime=createTime;
        return this;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

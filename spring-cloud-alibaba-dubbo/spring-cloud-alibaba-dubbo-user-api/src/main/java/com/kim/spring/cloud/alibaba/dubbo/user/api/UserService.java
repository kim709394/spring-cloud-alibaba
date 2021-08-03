package com.kim.spring.cloud.alibaba.dubbo.user.api;

import java.io.Serializable;

/**
 * @Author kim
 * @Since 2021/8/3
 */
public interface UserService {

    /**
     * 查询一个用户信息
     * @param userId
     * @return User
     * */
    User getUserById(Integer userId);





    class User implements Serializable {
        private Integer id;
        private String name;
        private Integer age;

        public User(Integer id,String name,Integer age){
            this.id=id;
            this.name=name;
            this.age=age;
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

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}

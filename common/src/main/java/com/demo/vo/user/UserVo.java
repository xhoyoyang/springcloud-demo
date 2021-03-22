package com.demo.vo.user;

import java.io.Serializable;

public class UserVo implements Serializable {

    private Integer id;
    private String name;
    private Integer age;

    public UserVo() {

    }

    public UserVo(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

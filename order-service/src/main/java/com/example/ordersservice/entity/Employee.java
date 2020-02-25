package com.example.ordersservice.entity;

import java.io.Serializable;

/**
 * (Employee)实体类
 *
 * @author makejava
 * @since 2020-02-25 14:55:50
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 510322449742681066L;
    
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
package com.example.memberservice.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-02-25 17:03:03
 */
public class User implements Serializable {
    private static final long serialVersionUID = 203726375072258108L;

    @ApiModelProperty(name = "id",value = "用户id")
    private Integer id;
    @ApiModelProperty(name = "name",value = "用户名")
    private String name;


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

}
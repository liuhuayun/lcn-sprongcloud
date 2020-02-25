package com.example.memberservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.memberservice.entity.User;
import com.example.memberservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-02-25 14:58:58
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Integer id) {
        return this.userService.queryById(id);
    }



    @RequestMapping("/insertUser")
    public int insertUser(@RequestBody JSONObject jsonObject){
        System.out.println(jsonObject);
        String name = jsonObject.getString("name");
        User user=new User();
        user.setName(name);
        userService.insert(user);
        return 1;
    }
}
package com.example.memberservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.memberservice.entity.User;
import com.example.memberservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-02-25 14:58:58
 */
@RestController
@RequestMapping("user")
@Api("用户接口服务")
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


    /**
     * @RequestBody JSONObject jsonObject
     * @param jsonObject
     * @return
     */
    @PostMapping("/insertUser")
    @ApiOperation("测试分布式事务框架lcn")
    public int insertUser(@RequestBody User user){
        System.out.println(user.getName());
        String name =user.getName();
        userService.insert(user);
        return 1;
    }
}
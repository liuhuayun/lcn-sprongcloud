package com.example.ordersservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.ordersservice.entity.Employee;
import com.example.ordersservice.service.EmployeeService;
import com.example.ordersservice.service.OrderFeginService;
import com.example.ordersservice.service.biz.CeshiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Employee)表控制层
 *
 * @author makejava
 * @since 2020-02-25 14:55:52
 */
@RestController
@RequestMapping("employee")
@Api("员工接口服务")
public class EmployeeController {
    /**
     * 服务对象
     */
    @Resource
    private EmployeeService employeeService;

    @Resource
    private CeshiService ceshiService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Employee selectOne(Integer id) {
        return this.employeeService.queryById(id);
    }

    @GetMapping("/insert")
    @ApiOperation("测试分布式事务框架lcn")
    @ApiImplicitParam(name="name",value = "用户名")
    public Object insertTest(String name) {
        ceshiService.testInsert();
        return "success";
    }

}
package com.example.memberservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.memberservice.service.OrderService;
import com.example.ordersservice.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private OrderService orderService;

    /**
     * 测试基包 base-model-order的使用
     * @return
     */
    @RequestMapping("/emp")
    public Object getEmployee(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","特朗普");
        jsonObject.put("age",82);
        Employee employee=JSONObject.toJavaObject(jsonObject,Employee.class);
        return employee;
    }

    @RequestMapping("/users")
    public Object renderUsers() {
        HashMap map = new HashMap<String, String>();
        map.put("username", "金旭东");
        map.put("serverPort", port);
        return map;
    }

    /**
     * 测试负载均衡
     * @return
     */
    @RequestMapping("/getApplicationName")
    public Object getApplicationName() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("applicationName", appName);
        return jsonObject;
    }


    /**
     * 通过fegin方式调用接口
     * @return
     */
    @RequestMapping("/usersByFegin")
    public Object getUserByFegin() {
        return orderService.getOrders();
    }
}

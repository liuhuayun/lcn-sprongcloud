package com.example.ordersservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.ordersservice.service.OrderFeginService;
import com.example.ordersservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderFeginService orderFeginService;

    @Value("${spring.application.name}")
    private String appName;

    /**
     * rest 方式调用接口
     *
     * @return
     */
    @RequestMapping("/users")
    public Object getUsers() {
        return orderService.getUserList();
    }

    @RequestMapping("/getName")
    public Object getApplicationName() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("applicationName", appName);
        return jsonObject;
    }


    /**
     * 测试 fegin 调用接口
     * 测试 服务熔断
     * @return
     */
    @RequestMapping("/getUsersByFegin")
    public Object getUsersByFegin() {
        JSONObject json = orderFeginService.getMembers();
        System.out.println(json.toJSONString());
        System.out.println("return time:"+System.currentTimeMillis());
        return json;
    }
}

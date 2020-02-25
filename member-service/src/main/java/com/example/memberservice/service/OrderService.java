package com.example.memberservice.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-order")
public interface OrderService {

    @RequestMapping("/order/users")
    public JSONObject getOrders();
}

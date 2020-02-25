package com.example.ordersservice.service;

import com.alibaba.fastjson.JSONObject;
import com.example.ordersservice.service.fallback.FallBackOrderFeginService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 服务熔断
 */
@FeignClient(value = "service-member", fallback = FallBackOrderFeginService.class)
public interface OrderFeginService {
    @RequestMapping("/member/users")
    public JSONObject getMembers();

    @RequestMapping("/user/insertUser")
    public JSONObject insertUser(@RequestBody Map<String, String> map);
}

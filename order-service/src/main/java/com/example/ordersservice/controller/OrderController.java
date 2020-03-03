package com.example.ordersservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.ordersservice.service.OrderFeginService;
import com.example.ordersservice.service.OrderService;
import com.example.ordersservice.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisAccessor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisTemplate redisTemplate;

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

    /**
     * 刷的太快 不起作用
     * redisTemplate.boundValueOps(key).expire(1,TimeUnit.SECONDS);
     */
    @RequestMapping("/orderIds")
    public Object testOrderIds(){
        Map<Integer,String> map=new HashMap<>();
        String key="orderId1";
        redisTemplate.delete(key);
        for(int i=0;i<1000;i++){
            /**
             * 1毫秒最多100个并发 实际远远达不到
             */
            if(i%100==0){
                redisTemplate.delete(key);
            }
            Long num = redisTemplate.boundValueOps(key).increment(1);
            String orderId = MyUtil.renderOrderId(num);
            System.out.println(orderId);
            map.put(i,orderId);
        }
        return map;
    }
}

package com.example.ordersservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.ordersservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JSONObject getUserList() {
        ResponseEntity<JSONObject> responseEntity = restTemplate.getForEntity("http://service-member/member/users", JSONObject.class);
        return responseEntity.getBody();
    }

    @Override
    public int insert() {
        return 0;
    }
}

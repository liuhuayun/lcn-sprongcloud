package com.example.ordersservice.service.fallback;

import com.alibaba.fastjson.JSONObject;
import com.example.ordersservice.service.OrderFeginService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 服务挂掉后默认执行方法
 */
@Service
public class FallBackOrderFeginService implements OrderFeginService {
    @Override
    public JSONObject getMembers() {
        JSONObject json = new JSONObject();
        json.put("code", 500);
        return json;
    }

    @Override
    public JSONObject insertUser(Map<String,String> jsonObject) {
        System.out.println("异常 进入FallBackOrderFeginService.insertUser");
        JSONObject jsonObject1=new JSONObject();
        jsonObject1.put("key","callback.insertUser");
        System.out.println(jsonObject1.toJSONString());
        return jsonObject1;
    }
}

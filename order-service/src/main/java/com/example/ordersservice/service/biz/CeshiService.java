package com.example.ordersservice.service.biz;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.example.ordersservice.entity.Employee;
import com.example.ordersservice.service.EmployeeService;
import com.example.ordersservice.service.OrderFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class CeshiService {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private OrderFeginService orderFeginService;


    /**
     * 测试lcn 解决分布式事务
     * 即 调用其他接口成功后
     * 本地方法出现异常 则回滚(实际上市接口假提交 等 调用方成功之后返回给全局事务管理器通知接口方提交)
     * @return
     */
    @Transactional
    @LcnTransaction
    public int testInsert(){
        Map<String,String> jsonObject = new HashMap<>();
        jsonObject.put("name", "测试lcn");
        JSONObject result = orderFeginService.insertUser(jsonObject);
        System.out.println(result.toJSONString());
        Employee employee = new Employee();
        employee.setAge(23);
        employee.setName("测试lcn");
        /**
         * 测试异常 回滚
         */
        //int i=1/0;
        employeeService.insert(employee);
        return 1;
    }
}

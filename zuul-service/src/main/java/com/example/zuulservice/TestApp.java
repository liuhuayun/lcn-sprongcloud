package com.example.zuulservice;

import com.example.ordersservice.util.SnowFlakeUtil;

public class TestApp {
    public static void main(String[] args)  throws Exception{
        SnowFlakeUtil snow = new SnowFlakeUtil(5,6);
        for(int i=0;i<100;i++){
            Thread.sleep(120);
            long num = snow.nextId(false);
            System.out.println(num);
        }
    }
}

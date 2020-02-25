package com.example.configclient.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {
    /**
     * 获取的key值需与git 中配置文件中key值一模一样
     *
     * git 配置文件内容发生变化 重启config-client即可 不需要重启 client-sever
     */
    @Value("${name}")
    public String name;

    @Value("${abc.b}")
    private String ceshi;

    @RequestMapping("/get")
    public Object getConfigByGit() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("exp", ceshi);
        return jsonObject;
    }
}

package com.example.zuulservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * zuul 集成 多个服务 swagger
 */
@Component
@Primary
class DocumentationConfig implements SwaggerResourcesProvider {

    // zuul配置能够使用config实现实时更新
    @RefreshScope
    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }

    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        // app-itmayiedu-order name可以自定义 location serviceName 根据自己情况写 后边拼接写死
        resources.add(swaggerResource("api-member-service", "/api-member/v2/api-docs", "2.0"));
        resources.add(swaggerResource("api-order-service", "/api-order/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}

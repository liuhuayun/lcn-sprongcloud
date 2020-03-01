package com.example.zuulservice.config;

import com.example.ordersservice.util.MyUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 网关配置全局过滤器
 */
@Service
public class WebFilter extends ZuulFilter {
    @Value("${server.port}")
    private String zport;

    /**
     *pre：路由之前
     *       routing：路由之时
     *       post： 路由之后
     *       error：发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 让过滤器生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("zuul-IP:"+ MyUtil.getHostIp()+":::"+"port:"+zport);
        return null;
    }
}

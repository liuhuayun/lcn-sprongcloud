package com.example.ordersservice.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MyUtil {
    /**
     * 获取当前机器ip
     * @return
     */
    public static String getHostIp() {
        String sIP = "";
        InetAddress ip = null;
        try {
            boolean bFindIP = false;
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                if (bFindIP)
                    break;
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    ip = ips.nextElement();
                    if (!ip.isLoopbackAddress()
                            && ip.getHostAddress().matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
                        bFindIP = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != ip)
            sIP = ip.getHostAddress();
        return sIP;
    }

    /**
     * 日期精确到毫秒级别
     * @return
     */
    public static String currentDateTime(){
        LocalDateTime localDateTime=LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String now = dateTimeFormatter.format(localDateTime);
        return System.currentTimeMillis()+"";
    }

    /**
     * 根据自增id生成唯一订单号
     * @param incrKey 自增id
     * @return
     */
    public static String renderOrderId(Long incrKey){
        /**
         * 日期+00001 后边是3位数 补零
         */
        String orderId = currentDateTime() + String.format("%1$03d", incrKey);
        return orderId;
    }
}

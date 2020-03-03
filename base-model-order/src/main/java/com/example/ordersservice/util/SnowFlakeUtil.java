package com.example.ordersservice.util;

/**
 * 描述: Twitter的分布式自增ID雪花算法snowflake (Java版)
 *
 * @author yanpenglei
 * @create 2018-03-13 12:37
 **/
public class SnowFlakeUtil {

    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 1480166465631L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12; //序列号占用的位数
    private final static long MACHINE_BIT = 5;   //机器标识占用的位数
    private final static long DATACENTER_BIT = 5;//数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private long datacenterId;  //数据中心
    private long machineId;     //机器标识
    private long sequence = 0L; //序列号
    private long lastStmp = -1L;//上一次时间戳

    public SnowFlakeUtil(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     *
     * @param ifEvenNum 是否偶数 true 时间不连续全是偶数  时间连续 奇数偶数 false 时间不连续 奇偶都有
     * @return
     */
    public synchronized long nextId(boolean ifEvenNum) {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }
        /**
         * 时间不连续出来全是偶数
         */
        if(ifEvenNum){
            if (currStmp == lastStmp) {
                //相同毫秒内，序列号自增
                sequence = (sequence + 1) & MAX_SEQUENCE;
                //同一毫秒的序列数已经达到最大
                if (sequence == 0L) {
                    currStmp = getNextMill();
                }
            } else {
                //不同毫秒内，序列号置为0
                sequence = 0L;
            }
        }else {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args)  throws Exception{
        /**
         * 分布式数据中心id
         * 机器id
         */
        SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil(5, 6);
        for (int i = 0; i < 10; i++) {
            /**
             * 时间连续 奇数偶数都有
             */
            //System.out.println(snowFlakeUtil.nextId(true));
            /**
             * 429407369643581440
             * 429407369643581441
             * 429407369643581442
             * 429407369643581443
             * 429407369643581444
             * 429407369643581445
             * 429407369643581446
             * 429407369643581447
             * 429407369643581448
             * 429407369643581449
             */
            //System.out.println(snowFlakeUtil.nextId(true));
            /**
             * 时间不连续 原版 获取的id全是偶数
             */
            //Thread.sleep(1);
            /**
             * 429407954194935808
             * 429407954199130112
             * 429407954207518720
             * 429407954211713024
             * 429407954215907328
             * 429407954224295936
             * 429407954249461760
             * 429407954253656064
             * 429407954257850368
             * 429407954262044672
             */
            long snowFlakeId = snowFlakeUtil.nextId(false);
            System.out.println(snowFlakeId);
            /**
             * 时间不连续 改版 获取的id为奇偶数
             */
            Thread.sleep(1);
            /**
             * 429408617683496961
             * 429408617691885570
             * 429408617696079875
             * 429408617700274180
             * 429408617704468485
             * 429408617708662790
             * 429408617712857095
             * 429408617717051400
             * 429408617725440009
             * 429408617729634314
             */

        }
    }
}
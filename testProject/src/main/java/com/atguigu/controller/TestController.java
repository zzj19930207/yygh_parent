package com.atguigu.controller;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestController {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("获取主线程名称：" + Thread.currentThread().getName());
        /**
         *  这里也可以采用以下方式使用，但是使用线程池的方式可以很便捷的对线程管理（提高程序的整体性能），
         *  也可以减少每次执行该请求时都需要创建一个线程造成的性能消耗
         *  new Thread(() ->{
         *  run方法中的业务逻辑
         *  })
         */

        /**
         * 定义一个线程池
         * 核心线程数（corePoolSize）:1
         * 最大线程数（maximumPoolSize）: 1
         * 保持连接时间（keepAliveTime）：50000
         * 时间单位 (TimeUnit):TimeUnit.MILLISECONDS(毫秒)
         * 阻塞队列 new LinkedBlockingQueue<Runnable>()
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,5,50000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        // 执行业务逻辑方法serviceTest()
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // 这里执行实际的业务逻辑，在这里我们就是用一个简单的遍历来模拟
                Arrays.stream(new int[]{1,2,3,4,5,6,7,8,9,10}).forEach(t -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("获取number为:" + t) ;
                });
            }
        });
        System.out.println("执行完成，向用户响应成功信息");
    }

}

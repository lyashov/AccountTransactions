package com.lyaev.accounts.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@RunWith(SpringRunner.class)
@SpringBootTest
class ThreadsOperationsTest {

    @Autowired
    OperationsService operationsService;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void  ThreatsTest() throws InterruptedException {
        final int MAX_THREAS = 100;
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(MAX_THREAS);

        for (int i = 0; i < MAX_THREAS; i++) {
            byte isDebit = 1;
            if ((i % 2) == 0) isDebit = 0;
            ThreadOperation threadOperation = applicationContext.getBean(ThreadOperation.class);
            threadOperation.setParameters("testAccount", isDebit, new BigDecimal(Math.random() * 1000));
            executor.execute(threadOperation);
        }
        executor.shutdown();
        executor.awaitTermination(Thread.MAX_PRIORITY, TimeUnit.HOURS);

    }
}
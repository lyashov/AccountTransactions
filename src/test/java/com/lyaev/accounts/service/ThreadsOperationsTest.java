package com.lyaev.accounts.service;

import com.lyaev.accounts.repository.OperationsRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@RunWith(SpringRunner.class)
@SpringBootTest
class ThreadsOperationsTest {

    @Autowired
    OperationsService operationsService;

    @Autowired
    AccountService accountService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    OperationsRepository operationsRepository;

    @Test
    void  ThreatsTest() throws InterruptedException {
        final int MAX_THREAS = 100;
        final String ACCOUNT_NAME = "testAccount";
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(MAX_THREAS);
        Long countStart = operationsRepository.count();
        for (int i = 0; i < MAX_THREAS; i++) {
            BigDecimal summOperation = new BigDecimal(Math.random() * 1000);

            byte isDebit = 1;
            if ((i % 2) == 0) isDebit = 0;

            ThreadOperation threadOperation = applicationContext.getBean(ThreadOperation.class);
            threadOperation.setParameters(ACCOUNT_NAME, isDebit, summOperation);
            executor.execute(threadOperation);
        }

        executor.shutdown();
        executor.awaitTermination(Thread.MAX_PRIORITY, TimeUnit.HOURS);
        Long countFinish = operationsRepository.count();
        Long countAll = countStart + Long.valueOf(MAX_THREAS);
        Assert.assertEquals(countFinish, countAll);
    }
}
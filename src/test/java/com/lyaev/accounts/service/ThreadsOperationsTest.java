package com.lyaev.accounts.service;

import com.lyaev.accounts.model.OperationJSON;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    ThreadOperation threadOperation;

    /*BigDecimal startSummAmount =  accountService.getSummAccount("testAccount");
      BigDecimal debitSumm = new BigDecimal("0.0");
      BigDecimal creditSumm = new BigDecimal("0.0");

      ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(MAX_THREAS);
      for (int i = 0; i < MAX_THREAS; i++){
          BigDecimal operation = BigDecimal.valueOf(Math.random() * 1000000);

        //  if ((i % 2) == 0)
        //  else
          ThreadOperation testRunnuble = new ThreadOperation();
          Thread thread = new Thread(testRunnuble);
          executor.execute(thread);
      }
      executor.awaitTermination(Thread.MAX_PRIORITY, TimeUnit.HOURS);
      executor.shutdown();
  }*/

    @Test
    void  ThreatsTest() throws InterruptedException {
        final int MAX_THREAS = 1;

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(MAX_THREAS);
        threadOperation.setAccount("testAccount");
        Thread thread = new Thread(threadOperation);
        executor.execute(thread);
        executor.shutdown();
        executor.awaitTermination(Thread.MAX_PRIORITY, TimeUnit.HOURS);
    }
}
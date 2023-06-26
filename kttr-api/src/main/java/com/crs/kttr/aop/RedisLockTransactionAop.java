package com.crs.kttr.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
@Order(value = 1)
public class RedisLockTransactionAop {
  private final RedissonClient client;

  @Around("execution(* com.crs.kttr..*.*(..))")
  private void around(ProceedingJoinPoint joinPoint) throws Throwable {
    String key = "stock";
    RLock lock = client.getLock(key);

    try {
      boolean available = lock.tryLock(5, 1, TimeUnit.SECONDS);

      if (!available) {
        System.out.println("lock 획득 실패");
        return;
      }

      System.out.println("lock 획득");
      Object result = joinPoint.proceed();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      lock.unlock();
      System.out.println("lock 해제");
    }
  }
}

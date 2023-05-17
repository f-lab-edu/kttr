package com.crs.kttr.global.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisLockRunner {
  private final RedissonClient client;

  public void setStock(String key, int amount){
    client.getBucket(key).set(amount);
  }

  public int currentStock(String key) {
   return (int) client.getBucket(key).get();
  }

  public void run(String lockName, Runnable process) {
    final RLock lock = client.getLock(lockName);

    boolean res = false;
    try {
      res = lock.tryLock(500, TimeUnit.MILLISECONDS);

      if (res) {
        process.run();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      if (res) {
        lock.unlock();
      }
    }
  }
}

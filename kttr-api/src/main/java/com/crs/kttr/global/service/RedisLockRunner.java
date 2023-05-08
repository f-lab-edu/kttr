package com.crs.kttr.global.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Log4j2
public class RedisLockRunner {
  private final RedissonClient client;

  public void run(String lockName, Runnable process) {
    final RLock lock = client.getLock(lockName);

    boolean res = false;
    try {
      res = lock.tryLock(500, TimeUnit.MILLISECONDS);

      if (res) {
        process.run();
      }
    } catch (Exception e) {
      log.error(e);
      throw new RuntimeException(e);
    } finally {
      if (res) {
        lock.unlock();
      }
    }
  }
}

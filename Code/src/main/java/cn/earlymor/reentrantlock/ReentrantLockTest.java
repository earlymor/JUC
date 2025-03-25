package cn.earlymor.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @projectName: JUC
 * @package: cn.earlymor.reentrantlock
 * @className: ReentrantLockTest
 * @author: earlymor
 * @description: 测试ReentrantLock的特性
 * @date: 2025/3/24 15:52
 * @version: 1.0
 */
@Slf4j
public class ReentrantLockTest {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
//        interruptiblyTest();
        tryLockTest();
    }
    /**
     * @description 可打断
     * @param : 
     * @return void
     * @author earlymor
     * @date 2025/3/24 16:06
     */
    public static void interruptiblyTest() {
        Thread t1 = new Thread(() -> {
            try {
                // 如果没有竞争那么此方法就会获取lock对象锁
                // 如果有竞争就进入阻塞队列,可以被其他线程用interrupt方法打断
                log.debug("尝试获取锁");
                lock.lockInterruptibly();
//                lock.lock(); // 无法被打断
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.debug("没有获得锁,返回");
                return;
            }
            try {
                log.debug("获取到锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        log.debug("主线程获取到锁");
        t1.start();
        log.debug("打断t1");
        t1.interrupt();
    }

    /**
     * @description 锁超时
     * @param : 
     * @return void
     * @author earlymor
     * @date 2025/3/24 16:07
     */
    public static void tryLockTest() {
        Thread t1 = new Thread(() -> {
            log.debug("尝试获取锁");
//            lock.tryLock(1, TimeUnit.SECONDS);
            if(!lock.tryLock()) {
                // 避免无限制等待
                log.debug("获取不到锁");
                return;
            }
            try {
                log.debug("获取到锁");
            } finally {
                lock.unlock();
            }
        },"t1");

        lock.lock();
        log.debug("主线程获取到锁");
        t1.start();
    }

    /**
     * @description 条件变量
     * @param : 
     * @return void
     * @author earlymor
     * @date 2025/3/24 17:39
     */
    public static void conditionTest() throws InterruptedException {
        // 条件变量（休息室）
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        // 进入condition1 进行等待
        condition1.await();

        // 进入condition2 进行等待
        condition2.await();

        lock.lock();
        // 唤醒condition1中等待的线程
        condition1.signal();

    }
}

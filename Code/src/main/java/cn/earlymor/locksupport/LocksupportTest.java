package cn.earlymor.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @projectName: JUC
 * @package: cn.earlymor.locksupport
 * @className: LocksupportTest
 * @author: earlymor
 * @description: 测试 LockSupport 的特性
 * @date: 2025/3/26 15:46
 * @version: 1.0
 */
public class LocksupportTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ---come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ---被唤醒");
        }, "t1");
//        t1.start();

// 暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t ---发出通知");
        }, "t2").start();

        // 先唤醒后等待,照样可以执行
        t1.start();
    }
}

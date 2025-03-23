package cn.earlymor.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: JUC
 * @package: cn.earlymor.reference
 * @className: ReferenceTest
 * @author: earlymor
 * @description: 四种引用demo
 * @date: 2025/3/22 20:06
 * @version: 1.0
 */
public class ReferenceTest {
    public static void main(String[] args) {
//        strongReference();
//        softReference();
        weakReference();

    }
    private static void weakReference() {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println("softReference-- gc before ----内存足够时" + weakReference.get());
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("softReference-- gc after ----内存足够时" + weakReference.get());
    }
    private static void softReference() {
        // 配置虚拟机参数进行模拟内存不足时的场景
        // -Xms10m 设置 JVM 初始堆内存大小为 10MB
        // -Xmx10m 设置 JVM 最大堆内存大小为 10MB
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("softReference ----内存足够时" + softReference.get());
        try {
            byte[] bytes = new byte[20 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("softReference ----内存不够时" + softReference.get());
        }

    }

    private static void strongReference() {
        MyObject obj = new MyObject();
        System.out.println("gc before:" + obj.toString());
        obj = null;
        System.gc();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("gc after:" + obj);
    }
}

package cn.earlymor.future;

import java.util.concurrent.*;

/**
 * @projectName: JUC
 * @package: cn.earlymor.future
 * @className: FutureTest
 * @author: earlymor
 * @description: 学习 Future CompletableFuture
 * @date: 2025/3/25 15:40
 * @version: 1.0
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        futureTest();
        completableFutureTest();
    }
    public static void futureTest() {
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        // 获取开始时间
        long startTime = System.currentTimeMillis();

        // 创建并提交第一个任务
        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500); // 休眠500毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task1 over";
        });
        threadPool.submit(futureTask1);

        // 创建并提交第二个任务
        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300); // 休眠300毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task2 over";
        });
        threadPool.submit(futureTask2);

        // 获取任务结果并打印
        try {
            System.out.println(futureTask1.get());
            System.out.println(futureTask2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 主线程休眠30毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 获取结束时间并计算总耗时
        long endTime = System.currentTimeMillis();
        System.out.println("----costTime:" + (endTime - startTime) + " 毫秒");

        // 关闭线程池
        threadPool.shutdown();
        System.out.println(Thread.currentThread().getName() + "\t -----end");
    }

    public static void completableFutureTest() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task1 over";
        });
//        获取结果的api示例
    //         阻塞等待任务完成并获取结果
    //        System.out.println(completableFuture.get());

    //        指定时间内等待任务完成，超时抛出 TimeoutException
    //        System.out.println(completableFuture.get(2L, TimeUnit.SECONDS));

    //        阻塞等待任务完成并获取结果，与 get() 类似，但不抛出受检异常
    //        System.out.println(completableFuture.join());

    //        立即获取结果，如果任务未完成，则返回默认值
    //        System.out.println(completableFuture.getNow("任务未完成"));

//        TimeUnit.SECONDS.sleep(1); // 时间不足
//        TimeUnit.SECONDS.sleep(3); // 时间充足
//        System.out.println(completableFuture.complete("任务被打断") + completableFuture.get());


    }
}

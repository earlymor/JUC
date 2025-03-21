package cn.earlymor.Thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @projectName: JUC
 * @package: cn.earlymor.Thread
 * @className: NewThreadTest2
 * @author: earlymor
 * @description: 使用 FutureTask + Callable 创建线程任务
 * @date: 2025/3/21 20:08
 * @version: 1.0
 */
@Slf4j
public class NewThreadTest2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<>() {

            @Override
            public Integer call() throws Exception {
                    log.debug("running...");

                Thread.sleep(1000);
                return 0;
            }
        });
        Thread t = new Thread(task,"t1");
        t.start();
        System.out.println("task.get() = " + task.get());
        log.debug("running");
    }

}

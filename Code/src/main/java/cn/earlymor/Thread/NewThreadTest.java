package cn.earlymor.Thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test1")
public class NewThreadTest {
    /**
     * @param args:
     * @return void
     * @description 使用Thread子类创建线程
     * @author earlymor
     * @date 2025/3/21 20:28
     */
    public static void main(String[] args) {
        Thread t = new Thread() {
            public void run() {
                log.debug("running");
            }
        };
        t.start();
        log.debug("running");
    }

}

package cn.earlymor.reference;

/**
 * @projectName: JUC
 * @package: cn.earlymor.reference
 * @className: MyObject
 * @author: earlymor
 * @description: 为了测试垃圾回收创建的类
 * @date: 2025/3/22 20:09
 * @version: 1.0
 */
public class MyObject {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("MyObject.finalize");
    }
}

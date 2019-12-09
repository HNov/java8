package com.nov.console.jvm.oom;

/**
 * @version v1.0
 * @ProjectName: java8
 * @ClassName: JavaVmStackSOF
 * @Description: 虚拟机栈和本地方法栈OOM测试
 * @Author: wenbo.huang
 * VM Args:-Xss128k
 * @Date: 2019/12/8 17:23
 */
public class JavaVmStackSOF {
    private int stackLength = 1;


    public static void main(String[] args) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JavaVmStackSOF oom = new JavaVmStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable t) {
            System.out.println("stack length:" + oom.stackLength);
            throw t;
        }
    }

    private void stackLeak() {
        stackLength++;
        stackLeak();
    }


}

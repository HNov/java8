package com.nov.console.jvm.oom;

import lombok.Synchronized;

/**
 * @version v1.0
 * @ProjectName: java8
 * @ClassName: JavaVMStackOOM
 * @Description: 创建线程导致内存溢出
 * VM Argas: -Xss2M
 * @Author: wenbo.huang
 * @Date: 2019/12/9 14:57
 */
public class JavaVMStackOOM {

    private static int count = 0;

    private void donStop() {


        while (true) {

            System.out.println("线程ID：" + Thread.currentThread().getName()
                    + ",Count:" + getCount() + ",time:" + System.currentTimeMillis());
        }

    }

    synchronized static int getCount() {
        return count++;
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    donStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }

}

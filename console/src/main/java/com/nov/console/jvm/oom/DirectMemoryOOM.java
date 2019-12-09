package com.nov.console.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @version v1.0
 * @ProjectName: java8
 * @ClassName: DirectMemoryOOM
 * @Description: 本机内存溢出
 * VM Args:-Xmx20M -XX:MaxDirectMemorySize=10M
 * @Author: wenbo.huang
 * @Date: 2019/12/9 21:41
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }

}

package com.nov.console.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @ProjectName: java8
 * @ClassName: RuntimeConstantPoolOOM
 * @Description: 运行时常量池导致内存溢出
 * VM Args:-XXPermSize=10M -XX:MaxPermSize=10M -XX:+HeapDumpOnOutOfMemoryError
 * @Author: wenbo.huang
 * @Date: 2019/12/9 20:44
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        //使用List保存着常量池引用,避免Full GC 回收常量池行为
        String str1 = new StringBuilder("计算机").append("软件").toString();
        String str2 = new StringBuilder("ja").append("va1").toString();
        System.out.println(str1 == str1.intern());
        System.out.println(str2 == str2.intern());

        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}

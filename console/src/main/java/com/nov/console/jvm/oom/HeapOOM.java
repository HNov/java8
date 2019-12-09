package com.nov.console.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @ProjectName: java8
 * @ClassName: HeapOOM
 * @Description: JAVA堆内存溢出测试
 * <p>
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @Author: wenbo.huang
 * @Date: 2019/12/8 17:05
 */
public class HeapOOM {


    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        int count = 0;

        while (true) {
            list.add(new OOMObject());
            System.out.println(count += 1);
        }
    }

    static class OOMObject {
    }

}

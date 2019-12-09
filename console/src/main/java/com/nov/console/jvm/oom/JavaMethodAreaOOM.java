package com.nov.console.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @version v1.0
 * @ProjectName: java8
 * @ClassName: JavaMethodAreaOOM
 * @Description: 方法区内存溢出
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * @Author: wenbo.huang
 * @Date: 2019/12/9 21:28
 */
public class JavaMethodAreaOOM {


    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invoke(obj, args);
                }
            });
        }
    }

    static class OOMObject {

    }
}

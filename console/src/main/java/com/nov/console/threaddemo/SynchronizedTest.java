package com.nov.console.threaddemo;

import lombok.Synchronized;

/**
 * http://www.importnew.com/20444.html#comment-690508
 * http://mini.eastday.com/bdmip/180405051032035.html
 * http://www.importnew.com/20444.html#comment-690508
 * https://blog.csdn.net/jerrywu145/article/details/79821094
 * https://www.cnblogs.com/owenma/p/8609348.html
 */
public class SynchronizedTest {


    public static void main(String[] args) {

        HasSelfPrivateNum ahasSelfPrivateNum = new HasSelfPrivateNum();
        HasSelfPrivateNum bhasSelfPrivateNum = new HasSelfPrivateNum();

        ThreadA athread = new ThreadA(ahasSelfPrivateNum);
        athread.start();
        ThreadB bthread = new ThreadB(bhasSelfPrivateNum);
        bthread.start();
        System.out.println("*********** 對象鎖1**************");

        ObjectService service = new ObjectService();

        ThreadC c = new ThreadC(service);
        c.setName("c");
        c.start();
        ThreadD d = new ThreadD(service);
        d.setName("d");
        d.start();
        System.out.println("************對象鎖2**************");

        ThreadE e = new ThreadE();
        e.setName("E");
        e.start();
        ThreadF f = new ThreadF();
        f.setName("F");
        f.start();
        System.out.println("************對象鎖3**************");


    }

}

class HasSelfPrivateNum {

    private int num = 0;
    private String log = "";


    synchronized public void addI(String username) {
        try {
            if (username.equals("a")) {
                num = 100;
                log += "A";
                System.out.println("a set over!,num=" + num);
                Thread.sleep(2000);
            } else {
                num = 200;
                log += "B";
                System.out.println("b set over!,num=" + num);
            }
            System.out.println(username + " num=" + num + ",log=" + log);
        } catch (InterruptedException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

class ThreadA extends Thread {

    private HasSelfPrivateNum numRef;

    public ThreadA(HasSelfPrivateNum numRef) {
        super();
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.addI("a");
    }

}

class ThreadB extends Thread {

    private HasSelfPrivateNum numRef;

    public ThreadB(HasSelfPrivateNum numRef) {
        super();
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.addI("b");
    }

}

class ObjectService {

    public void serviceMethod() {
        try {
            synchronized (this) {
                Thread thread = Thread.currentThread();
                System.out.println("thread:" + thread.getName() + "begin time=" + System.currentTimeMillis());
                Thread.sleep(3000);
                System.out.println("thread:" + thread.getName() + "end    end=" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class ThreadC extends Thread {

    private ObjectService service;

    public ThreadC(ObjectService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.serviceMethod();
    }

}

class ThreadD extends Thread {
    private ObjectService service;

    public ThreadD(ObjectService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.serviceMethod();
    }
}


class Service {
    synchronized public static void printA() {
        try {
            System.out.println("线程名称为：" + Thread.currentThread().getName()
                    + "在" + System.currentTimeMillis() + "进入printA");
            Thread.sleep(3000);
            System.out.println("线程名称为：" + Thread.currentThread().getName()
                    + "在" + System.currentTimeMillis() + "离开printA");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public static void printB() {
        System.out.println("线程名称为：" + Thread.currentThread().getName() + "在"
                + System.currentTimeMillis() + "进入printB");
        System.out.println("线程名称为：" + Thread.currentThread().getName() + "在"
                + System.currentTimeMillis() + "离开printB");
    }
}

class ThreadE extends Thread {
    @Override
    public void run() {
        Service.printA();
    }
}

class ThreadF extends Thread {
    @Override
    public void run() {
        Service.printB();
    }
}
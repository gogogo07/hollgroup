package com.holl.wechat;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Global {
    public static int orderNumber;
    public static final ThreadLocal<Integer> sThreadLocal = new ThreadLocal<Integer>();
    public static int x=1;
    public static Lock lock = new ReentrantLock();
}

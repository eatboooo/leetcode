package cn.eatboooo.demo.algorithm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2021/7/2 23:26
 */
public class ThreadDemo01 {
    /**
     * @Description: 交替打印
     * @Param:
     * @return: void
     * @Author: weiZhiLin
     * @Date: 2021/7/3 00:05
     */
    public static void printSmt() throws InterruptedException {
        String s = "abcde";
        String n = "12345";
        char[] chars = s.toCharArray();
        char[] chars1 = n.toCharArray();
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition sc = reentrantLock.newCondition();
        Condition nc = reentrantLock.newCondition();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < chars.length; i++) {
                reentrantLock.lock();
                char aChar = chars[i];
                System.out.println("sc = " + aChar);
                try {
                    nc.signal();
                    if (i == chars.length - 1) {
                        break;
                    }
                    sc.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
            System.out.println("Override = sc");
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < chars1.length; i++) {
                reentrantLock.lock();
                char aChar = chars1[i];
                System.out.println("nc = " + aChar);
                try {
                    sc.signal();
                    if (i == chars1.length - 1) {
                        break;
                    }
                    nc.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
            System.out.println("Override = nc");
        });
        t1.start();
        Thread.sleep(100);
        t2.start();
    }
}

package top.takefly.test.jpa;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: Jpa_Hibernate
 * @description: 线程池创建
 * @author: 戴灵飞
 * @create: 2019-08-21 09:53
 **/
public class ThreadPoolExecutorTest {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        /**
         * 如果当前锁没有被其他线程占用，就获取的锁并立即返回。设置保持数为1
         * 如果当前线程已经占有当前锁，则保持计数增加一
         *如果当前锁已经被其他线程保持，出于线程调度的目的，就禁用当前线程，直到下面两种情况出现，一直出于休眠状态
         */
        lock.lockInterruptibly();
    }
}

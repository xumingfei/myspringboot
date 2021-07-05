package spring.thread.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Xiaofei
 * @DATE: 2021/7/4 20:59
 */
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        // 多个线程操作同一个资源类
        Ticket2 ticket = new Ticket2();
        new Thread(() -> { for (int i = 0; i < 60; i++) ticket.sale();}, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.sale();
            }
        }, "C").start();

    }
}

/**
 * 资源类 OOP
 */
class Ticket2{
    // 50张票
    private int number = 50;
    // 卖票的方式
    // synchronized 本质: 队列 锁 同步操作  monitorenter moniterexit 操作系统提供的mutex lock 比较浪费资源,效率低

    Lock lock = new ReentrantLock();

    public synchronized void sale(){

        lock.lock();

        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName()+"卖出了第"+(number--)+"张票,剩余:"+number);
            }
        }finally {
            lock.unlock();
        }

    }
}
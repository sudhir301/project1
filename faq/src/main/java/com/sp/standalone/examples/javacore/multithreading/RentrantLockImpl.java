package com.sp.standalone.examples.javacore.multithreading;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Runner {

    private int count = 0;
    private ReentrantLock  lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();
        
        System.out.println("firstThread count : " + lock.isHeldByCurrentThread());
        System.out.println("Waiting ....");
        cond.await();
        System.out.println("firstThread " + lock.isHeldByCurrentThread());
        
        System.out.println("Woken up!");

        try {
        	System.out.println("firstThread increment ");
            increment();
        } finally {
            lock.unlock();
        }
    }

    @SuppressWarnings("resource")
	public void secondThread() throws InterruptedException {
        
        System.out.println("secondThread" + lock.isHeldByCurrentThread());
        Thread.sleep(1000);
        lock.lock();
        System.out.println("secondThread count : " + lock.isHeldByCurrentThread());
        
        System.out.println("Press the return key!");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key!");
        
        cond.signal();

        try {
        	 System.out.println("secondThread increment ");
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }
}

public class RentrantLockImpl {

    
    public static void main(String[] args) throws Exception {
        
        final Runner runner = new Runner();
        
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    runner.firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    runner.secondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        
        runner.finished();
    }

}
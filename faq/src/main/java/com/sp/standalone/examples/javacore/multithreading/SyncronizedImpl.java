package com.sp.standalone.examples.javacore.multithreading;

public class SyncronizedImpl {
    private int count = 0;
    
    public synchronized void increment() {
        count++;
    }
    public static void main(String[] args){
    	SyncronizedImpl app = new SyncronizedImpl();
    	app.doWork();
    }
    
    public void doWork() {
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for(int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });
        thread1.start();
        
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for(int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });
        thread2.start();
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Count is: " + count);
    }
}

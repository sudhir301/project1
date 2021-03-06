package com.sp.standalone.examples.javacore.multithreading;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Semaphore;

class Connection {

    private static Connection instance = new Connection();

    private Semaphore sem = new Semaphore(10, true);

    private int connections = 0;

    private Connection() {

    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {
        try {
            sem.acquire();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        try {
            doConnect();
        } finally {

            sem.release();
        }
    }

    public void doConnect() {

        synchronized (this) {
            connections++;
            System.out.println("Current connections: " + connections);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            connections--;
        }

    }
}

public class SemaphoresImpl {

    public static void main(String[] args) throws Exception {
        
        ExecutorService executor = Executors.newCachedThreadPool();
        
        for(int i=0; i < 200; i++) {
            executor.submit(new Runnable() {
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }
        
        executor.shutdown();
        
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

}
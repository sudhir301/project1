package com.sp.standalone.examples.javacore.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor2 implements Runnable {
    private CountDownLatch latch;
    
    public Processor2(CountDownLatch latch) {
        this.latch = latch;
    }
    
    public void run() {
        System.out.println("Started.");
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        latch.countDown();
    }
}

public class CountDownLatchesImpl {

    public static void main(String[] args) {
        final String[] str = {"121","1212","123232"};
        System.out.println(str[0]);
        str[0]="1212";
        System.out.println(str[0]);
        
        CountDownLatch latch = new CountDownLatch(3);
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        for(int i=0; i < 3; i++) {
            executor.submit(new Processor2(latch));
        }
        
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Completed.");
    }

}
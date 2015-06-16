package com.sp.standalone.examples.javacore;

public class TestJoinMethod1 extends Thread {
	String name;

	TestJoinMethod1(String name1) {
		this.name = name1;
	}

	public void run() {
		for (int i = 1; i <= 5; i++) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				System.out.println(e);
			}
			System.out.println("name : " + name + "  i=" + i);
		}
	}

	public static void main(String args[]) {
		TestJoinMethod1 t1 = new TestJoinMethod1("T1");
		TestJoinMethod1 t2 = new TestJoinMethod1("T2");
		TestJoinMethod1 t3 = new TestJoinMethod1("T3");
		t1.start();
		try {
//			t1.join();
			t1.join(1500);
		} catch (Exception e) {
			System.out.println(e);
		}

		t2.start();
		t3.start();
	}
}

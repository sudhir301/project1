package com.sp.standalone.examples.javacore;

public class TestTryCatch {

	public static void main(String[] args) {
		System.out.println("returned Value : "+checkOne());

	}

	public static int checkOne(){
		String str = null;
		try {
			if (str.equals("xyz")) {
				
			}
			return 1;
		} catch (Exception e ){
			return 2;
		} finally {
			return 3;
		}
	}
}

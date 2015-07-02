package com.sp.standalone.examples.javacore.test;

import java.util.HashMap;

class Test{
	public static void main(String args[]){
	StringBuilder str = new StringBuilder();
	str.append("abc"+"def") ;
	Test test = new Test();
	HashMap<Integer,String> hm = new HashMap<Integer,String>();
	hm.put(1,"one");
	hm.put(2,"two");
	test.do1(hm);
	System.out.println("Inside Main method");
	test.print( hm); //what is printed in hm
	}


	void do1(HashMap<Integer,String> m){
//		m = new HashMap();
//		m.put(3,"three");
//		m=null;
		m.clear();
		System.out.println("Inside do1");
		print(m);  //what is printed here
	}
	
	public void print(HashMap<Integer,String> m){
		for (Integer key : m.keySet()) {
			System.out.println(m.get(key));
		}
	}
}

class Resource {
	private static Resource instance;

	public static Resource getInstance() {
		if (instance == null) {
			instance = new Resource();
		}
		return instance;
	}
}

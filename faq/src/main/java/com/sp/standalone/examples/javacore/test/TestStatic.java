package com.sp.standalone.examples.javacore.test;

public class TestStatic extends Parent1{

	public static void main(String[] args) {
		TestStatic.TestingCall();
		Parent1.TestingCall();	
	}

	public static void TestingCall(){
		System.out.println("TestStatic TestingCall");
	}
}

class Parent1{
	public static void TestingCall(){
		System.out.println("Parent TestingCall");
	}
	
}
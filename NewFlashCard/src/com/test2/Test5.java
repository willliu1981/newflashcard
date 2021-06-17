package com.test2;

import java.util.stream.Stream;

public class Test5 {
	
	static class Car{
		String name;
		
		public String toString() {
			return name;
		}
	}

	public static void main(String[] args) {
		Car car=new Car();
		car.name="car";
		test(car.toString());
	}
	
	
	static void test(String str) {
		System.out.println("1");
	}
	static void test2(Car car) {
		System.out.println("2");
	}
	
	

}

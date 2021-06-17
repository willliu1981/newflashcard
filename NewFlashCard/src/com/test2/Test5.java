package com.test2;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Test5 {
	
	static class Car{
		String name;
		
		
		@Override
		public boolean equals(Object obj) {
			if(obj!=null && obj instanceof Car ) {
				Car c=(Car )obj;
				if(c.name!=null ) {
					return this.name.equals(c.name);
				}
			}
			return false;
		}


		@Override
		public int hashCode() {
			return this.name.hashCode();
		}


		public String toString() {
			return name;
		}
	}

	public static void main(String[] args) {
		Car car1=new Car();
		Car car2=new Car();
		Car car3=new Car();
		car1.name="car1";
		car2.name="car2";
		car3.name="car1";
		
		Map<Car,String > map=new HashMap<>();
		
		map.put(car1, "a");
		map.put(car2, "b");
		map.put(car3, "c");
		
		System.out.println(map);
	}
	
	
	static void test(String str) {
		System.out.println("1");
	}
	static void test2(Car car) {
		System.out.println("2");
	}
	
	

}

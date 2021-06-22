package com.test2;

public class Test8 {
	
	static class Book{
		String name;
		int price;
		
		Book(){
			System.out.println("XXX");
		}
		
//		Book(String name){
//			this.name=name;
//		}
	}
	
	static class EBook extends Book{
		EBook(int price) {
			this.price=price;
		}
	}

	public static void main(String[] args) {
		new EBook(123) {
			
		};
	}

}

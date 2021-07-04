package com.test2;

public class Test11 {

	static class Book {
		int ISBN;

		Book(int isbn) {
			this.ISBN = isbn;
		}

		@Override
		public int hashCode() {
			return ISBN;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Book) {
				Book b = (Book) obj;
				return this.ISBN == b.ISBN;
			}
			return false;
		}

	}

	public static void main(String[] args) {
		System.out.println("String\n");
		String str1 = new String("B");
		String str2 = new String("B");
		String str3 = "B";
		String str4 = "B";

		System.out.println(String.format("s1=%s , s2=%s , s3=%s , s4=%s", str1, str2, str3, str4));
		System.out.println(String.format("s1=%s , s2=%s , s3=%s , s4=%s", str1.hashCode(), str2.hashCode(),
				str3.hashCode(), str4.hashCode()));
		System.out.println(String.format("s1=%s , s2=%s , s3=%s , s4=%s", System.identityHashCode(str1),
				System.identityHashCode(str2), System.identityHashCode(str3), System.identityHashCode(str4)));

		System.out.println("equal s2 == s1 ? " + (str2 == str1));
		System.out.println("equal s2 == s3 ? " + (str2 == str3));
		System.out.println("equal s4 == s3 ? " + (str4 == str3));

		System.out.println("\nBook\n");

		Book b1 = new Book(1);
		Book b2 = new Book(1);
		Book b3 = b2;

		System.out.println(String.format("b1=%s , b2=%s , s3=%s", b1, b2, b3));
		System.out.println(String.format("b1=%s , b2=%s , b3=%s", System.identityHashCode(b1),
				System.identityHashCode(b2), System.identityHashCode(b3)));

		System.out.println("equal b2 == b1 ? " + (b2 == b1));
		System.out.println("equal b2 == b3 ? " + (b2 == b3));
		
		System.out.println("\nStringBuilder\n");
		StringBuilder sb=new StringBuilder("B");
		StringBuilder sb2=new StringBuilder("B");
		String str5=sb.toString();
		String str6=sb2.toString();
		System.out.println("str5 == str4 ? "+(str5==str4));
		System.out.println("str4="+System.identityHashCode(str4));
		System.out.println("str5="+System.identityHashCode(str5));
		System.out.println("str6="+System.identityHashCode(str6));
		
	}

}

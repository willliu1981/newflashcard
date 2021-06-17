package com.test2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test4 {
	static class Student {
		private List<Book> books = new ArrayList<>();
		private String name;
		private Integer age;

		public Student(String name, Integer age) {
			this.name = name;
			this.age = age;
		}

		public void appendBook(Book book) {
			this.books.add(book);
		}

		public List<Book> getBooks() {
			return this.books;
		}

	}

	static class Book {
		private String isbn;
		private String name;

		public Book(String name, String isbn) {
			this.name = name;
			this.isbn = isbn;
		}

		public String getISBN() {
			return this.isbn;
		}

		public String toString() {
			return String.format("Book{name=%s, isbn=%s}", this.name, this.isbn);
		}
	}

	public static void main(String[] args) {

		ArrayList<Student> stList = new ArrayList<>();
		Student st1 = new Student("Ken", 25);
		st1.appendBook(new Book("BookA", "00001"));
		st1.appendBook(new Book("BookB", "00002"));
		Student st2 = new Student("Lucy", 71);
		st2.appendBook(new Book("BookC", "00003"));
		st2.appendBook(new Book("BookD", "00004"));
		Student st3 = new Student("Gigi", 13);
		st3.appendBook(new Book("BookE", "00001"));
		st3.appendBook(new Book("BookF", "00006"));

		stList.add(st1);
		stList.add(st2);
		stList.add(st3);

		ArrayList<Book> bookList = (ArrayList<Book>) stList.stream().flatMap(st -> st.getBooks().stream())
				.collect(Collectors.toList());
		System.out
				.println(stList.stream().flatMap(st -> st.getBooks().stream()).collect(Collectors.toList()).getClass());
		System.out.println(bookList);

		LinkedList<Book> bookList2 = stList.stream().flatMap(st -> st.getBooks().stream())
				.collect(Collectors.toCollection(LinkedList::new));
		System.out.println(stList.stream().flatMap(st -> st.getBooks().stream())
				.collect(Collectors.toCollection(LinkedList::new)).getClass());
		System.out.println(bookList2);

//		Map<String, Book> bookMap = stList.stream().flatMap(st -> st.getBooks().stream())
//				.collect(Collectors.toMap(b -> b.getISBN(), b -> b));
//		System.out.println(stList.stream().flatMap(st -> st.getBooks().stream())
//				.collect(Collectors.toMap(b -> b.getISBN(), b -> b)).getClass());
//		System.out.println(bookMap);

		Map<String, Book> bookMap2 = stList.stream().flatMap(st -> st.getBooks().stream())
				.collect(Collectors.toMap(b -> b.getISBN(), b -> b,(b1,b2)->b1));
		System.out.println(bookMap2);

	}

}

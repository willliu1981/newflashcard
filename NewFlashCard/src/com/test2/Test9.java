package com.test2;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.Test;

import com.control.pronounce.PronounceErrUrlFactory;
import com.google.gson.Gson;
import com.tool.ConfigFactory;

public class Test9 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		new Test9().test7();

	}

	public static void test0() {
		Map<String, String> rule = new LinkedHashMap<>();
		rule.put("[\\s]+", "_");
		rule.put("[_]+", "-");
		rule.put("[-]", "_");

		String str = "a b";

		Iterator<Entry<String, String>> it = rule.entrySet().iterator();
		Entry<String, String> s = it.next();

		System.out.println(s);
	}

	// @Test
	public void test1() throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.setProperty("aaa", "A");
		prop.store(new FileOutputStream("data/testProp.properties"), "today is fine");

		int a = 3;

		// assertEquals(3,a);
	}

	// @Test
	public void test2() throws FileNotFoundException, IOException {
		Properties prop = new Properties();

		// prop.load(
		// Test9.class.getResourceAsStream("../../data/testProp.properties"));

		// prop.load(new FileInputStream("data/testProp.properties"));
		String str = prop.getProperty("aaa");

		System.out.println(str);
	}

	class Book {
		List<String> list = new ArrayList<>();
		Date date;
		String name;
	}

	//@Test
	public void test3() {

		
		Book b = new Book();

		b.list.add("A");
		b.list.add("B");

		b.date = new Date();
		b.name = "KK";

		Gson g = new Gson();
		String str = g.toJson(b);

		System.out.println("str=" + str);
	}
	
	//@Test
	public void test4() {
		//String str="{\"list\":[\"A\",\"B\"],\"date\":\"Jun 26, 2021 5:06:51 PM\",\"name\":\"KK\"}";
		String str="";
		Gson g=new Gson();
		Book b=g.fromJson(str, Book.class);
		
		System.out.println(b);
		System.out.println(b.name);
		System.out.println(b.date);
		System.out.println(b.list);
		
	}
	
	//@Test
	public void test5() {
		PronounceErrUrlFactory.add("among");
		PronounceErrUrlFactory.add("test1");
		PronounceErrUrlFactory.date();
		PronounceErrUrlFactory.write();
		
	}
	
	//@Test
	public void test6() {
		List<String > list1=new ArrayList<>();
		list1.add("a");
		list1.add("b");
		list1.add("c");

		List<String > list2=new ArrayList<>();
		list2.add("a");
		list2.add("b");
		//list2.add("e");
		
		boolean r=false;
		
	r=list1.containsAll(list2) && list2.containsAll(list1) ;
	
	assertEquals(true,r);
	
		
	}

	@Test
	public void test7() {
		JFrame frame=new JFrame();
		JLabel label=new JLabel();
		frame .add(label);
		frame.setBounds(100,100,200,200);
		frame.setVisible(true);
		ttt(label);
		
	}
	
	public void ttt(JLabel label) {
		int count=0;
		while(true) {
			try {
				Thread.sleep(100);
				label.setText(""+count++);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}

/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

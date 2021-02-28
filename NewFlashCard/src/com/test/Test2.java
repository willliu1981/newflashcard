package com.test;

import java.util.Arrays;

public class Test2 {
	EE e;

	public enum EE {
		E1(0), E2(1), E3(2);

		private int v;

		EE(int v) {
			this.v = v;
		}

		public int value() {
			return v;
		}
		
		public EE previous() {
			return EE.values()[this.v-1];
		}
		
		public EE next() {
			return EE.values()[this.v+1];
		}

	}

	public static void main(String[] args) {
		Test2 t = new Test2();
		t.e = EE.E2;
		t.e=t.e.next();
		
		//EE eee=t.e.value();

		System.out.println(t.e);


		// Arrays.asList(EE.values()).stream().forEach(System.out::println);

	}

}

package com.test2;

import java.util.Optional;

public class Test2 {

	public static void main(String[] args) {
		AA aa=new AA();
		Optional<AA> op = Optional.ofNullable(aa);

		System.out.println(op);
		System.out.println(op.orElse(new AA(new BB("bbb"), "xxx")));

		System.out.println(op.orElseGet(() -> {
			return new AA(new BB("new"));
		}));

	}

	static class AA {
		String s;
		BB b;

		public AA(BB b) {
			this.b = b;
		}

		public AA(String s) {
			this.s = s;
		}

		public AA(BB b,String s) {
			this.b=b;
			this.s=s;
		}
		public AA() {
		}

		public String toString() {
			return this.b +":"+this.s;
		}
	}
	
	static class BB{
		String bs;
		public BB(String bs) {
			this.bs=bs;
		}
	}

}

package com.test;

import java.util.List;

import com.control.dao.CardBoxDao;
import com.model.CardBox;

public class TestResult {

	public static void main(String[] args) {
		List<CardBox> cardBoxs=new CardBoxDao().queryAll();
		System.out.println(cardBoxs.get(3));
	}

}

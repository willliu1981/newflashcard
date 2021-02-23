package com.test;

import java.sql.Date;
import java.util.List;

import com.control.dao.CardBoxDao;
import com.control.dao.VocabularyDao;
import com.model.CardBox;
import com.model.Vocabulary;

public class TestDao {

	public static void main(String[] args) {
		//VocabularyDao dao=new VocabularyDao();
		/*
		Vocabulary v=new Vocabulary();
		v.setVocabulary("vocabulary");
		v.setTranslation("詞彙");
		v.setCreate_date(new Date(new java.util.Date().getTime()).toString());
		dao.add(v);
		*///
		
		/*
		List<Vocabulary> list=dao.queryAll();
		list.forEach(x->{
			System.out.print(x.getVocabulary());
			System.out.println(" : " +x.getTranslation());
		});
		*///
		
		/*
		Vocabulary v=dao.query(2);
		System.out.print(v.getVocabulary());
		System.out.println(" : " +v.getTranslation());
		*///
		
		/*
		Vocabulary v=new Vocabulary();
		v.setTranslation("1234");
		v.setBox_id(5);
		v.setUpdate_date(new Date(new java.util.Date().getTime()).toString());
		dao.update(v, 2);
		*///
		//dao.delete(2);
		
		CardBoxDao dao=new CardBoxDao();
		/*
		CardBox box=new CardBox();
		box.setName("yyy");
		box.setCreate_date(new Date(new java.util.Date().getTime()).toString());
		dao.add(box);
		*///
		
		/*
		List<CardBox> lists=dao.queryAll();
		lists.forEach(x->{
			System.out.print(x.getName());
			System.out.println(" : " +x.getCreate_date());
		});
		
		CardBox box=dao.query(3);
		System.out.print(box.getName());
		System.out.println(" : " +box.getCreate_date());
		*///
		
		/*
		CardBox box=new CardBox();
		box.setName("uuu");
		box.setUpdate_date(new Date(new java.util.Date().getTime()).toString());
		dao.update(box, 2);
		*///
		
		dao.delete(1);
	}

}

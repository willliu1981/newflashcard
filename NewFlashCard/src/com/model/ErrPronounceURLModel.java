package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrPronounceURLModel {
	private Date date;
	private List<String> errVocabulary ;
	
	public ErrPronounceURLModel() {
		this.initialize();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	public void add(String vocabulary) {
		this.errVocabulary.add(vocabulary);
	}
	
	public boolean isExist(String vocabulary) {
		return this.errVocabulary.contains(vocabulary);
	}
	
	public void remove(String vocabulary) {
		this.errVocabulary.remove(vocabulary);
	}
	
	public void initialize() {
		this.errVocabulary=new ArrayList<String>();
		this.date=new Date();
	}
}

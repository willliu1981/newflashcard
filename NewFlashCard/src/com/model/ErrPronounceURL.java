package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrPronounceURL {
	private Date date;
	private List<String> errVocabulary = new ArrayList<>();

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
}

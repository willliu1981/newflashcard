package com.model;

import java.sql.Date;

public class Vocabulary {
	private Integer id;
	private String vocabulary;
	private String translation;
	private Integer box_id;
	private String create_date;
	private String update_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVocabulary() {
		return vocabulary;
	}

	public void setVocabulary(String vocabulary) {
		this.vocabulary = vocabulary;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public Integer getBox_id() {
		return (box_id == null ? -1 : this.box_id);
	}

	public void setBox_id(Integer box_id) {
		this.box_id = box_id;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}



}

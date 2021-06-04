package com.model;

public class Vocabulary {
	public final static String SortBy_Vocabualry = "sortby_vocabulary";
	public final static String SortBy_Translation = "sortby_translation";
	private Integer id;
	private String vocabulary;
	private String translation;
	private String explanation;
	private String example;
	private Integer box_id;
	private Integer test_time;
	private String test_date;
	private String create_date;
	private String update_date;

	public Vocabulary() {
	}

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

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
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

	public Integer getTest_time() {
		return test_time;
	}

	public void setTest_time(Integer test_time) {
		this.test_time = test_time;
	}

	public String getTest_date() {
		return test_date;
	}

	public void setTest_date(String test_date) {
		this.test_date = test_date;
	}

}

package com.model;

import java.sql.Date;

public class CardBox {
	private Integer id;
	private String name;
	private Integer test_time;
	private String test_date;
	private String create_date;
	private String update_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

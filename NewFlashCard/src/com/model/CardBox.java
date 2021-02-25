package com.model;

import java.sql.Date;

public class CardBox {
	public static final String ID="id";
	public static final String Name="name";
	public static final String Create_date="create_date";
	public static final String Update_date="update_date";
	public static final String Quantity="quantity";
	
	private Integer id;
	private String name;
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



}

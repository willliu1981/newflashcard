package com.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CardBox {
	private Integer id;
	private String name;
	private Integer test_times;
	private String test_date;
	private Integer state;
	private String create_date;
	private String update_date;
	private Map<Integer, Integer> stateRuleMap;

	public CardBox() {
		stateRuleMap = new HashMap<>();
		stateRuleMap.put(1, 1);
		stateRuleMap.put(2, 2);
		stateRuleMap.put(3, 4);
		stateRuleMap.put(4, 7);
		stateRuleMap.put(5, 15);
		stateRuleMap.put(6, 30);
	}

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

	public Integer getTest_times() {
		return test_times;
	}

	public void setTest_times(Integer test_time) {
		this.test_times = test_time;
	}

	public String getTest_date() {
		return test_date;
	}

	public void setTest_date(String test_date) {
		this.test_date = test_date;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getNextTestDate() {
		if(this.state==0) {
			return "--";
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		try {
			java.util.Date testDate=sdf.parse(this.test_date);
			cal.setTime(testDate);
			cal.add(Calendar.DAY_OF_MONTH, this.stateRuleMap.get(this.state));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sdf.format(cal.getTime());
	}

}

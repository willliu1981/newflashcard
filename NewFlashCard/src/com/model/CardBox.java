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

	private boolean nextState() {
		if (this.stateRuleMap.containsKey(this.state + 1)) {
			this.state++;
			return true;
		} else {
			return false;
		}
	}

	private void resetState() {
		this.state = 0;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void state() {
		if (this.getStateResult() == 1) {
			this.nextState();
		} else if (this.getStateResult() == -1) {
			this.resetState();
		}
	}

	public String getNextTestDateStr() {
		if (this.state == 0) {
			return "--";
		}
		return this.getNextTestDate().toString();
	}

	private Calendar getNextTestDate() {
		if (this.state == 0) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			java.util.Date testDate = sdf.parse(this.test_date);
			cal.setTime(testDate);
			cal.add(Calendar.DAY_OF_MONTH, this.stateRuleMap.get(this.state));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cal;
	}

	/*
	 * 取得next state result 0:即期重複測驗 1:即期第一次測驗 -1:過期測驗
	 */
	public int getStateResult() {
		int result = -1;
		Calendar now = Calendar.getInstance();
		Calendar next = this.getNextTestDate();
		if (next == null) {
			return 0;
		}
		Calendar nextDelay = (Calendar) this.getNextTestDate().clone();
		nextDelay.add(Calendar.DAY_OF_MONTH, this.stateRuleMap.get(this.state));
		if (now.before(next)) {
			result = 0;
		} else if (now.after(next) && now.before(nextDelay)) {
			result = 1;
		} else {
			result = -1;
		}
		return result;
	}

}

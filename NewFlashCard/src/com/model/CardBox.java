package com.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/*
 * 注意:更改state 規則將會影響TestQuestion 的 獎利獲得方法(ScoreControl),有可能會造成邏輯錯誤
 */
public class CardBox {
	public static enum StateResult {
		Retest, Primary, BeforeDay, Overdue, Default
	}

	private Integer id;
	private String name;
	private Integer test_times;
	private String test_date;
	private Integer state;
	private String create_date;
	private String update_date;
	private Map<Integer, Integer> stateRuleMap;// <stage,days> ,用於動態方法回傳從資料庫撈到的
												// state 欄位的值 的對應延遲天數

	public CardBox() {
		stateRuleMap = new HashMap<>();
		stateRuleMap.put(1, 1);
		stateRuleMap.put(2, 2);
		stateRuleMap.put(3, 4);
		stateRuleMap.put(4, 7);
		stateRuleMap.put(5, 15);
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
			this.state = this.stateRuleMap.size() + 1;// 使無效
			return false;
		}
	}

	private boolean preState() {
		if (this.state > 1) {
			this.state--;
			return true;
		}
		return false;
	}

	public void finish() {
		this.state = this.stateRuleMap.size() + 1;
	}

	public void resetTest() {
		this.state = 0;
		this.test_date = null;
		this.test_times = 0;
	}

	public boolean isFinish() {
		boolean r = false;
		if (this.state > 0 && !this.stateRuleMap.containsKey(this.state)) {
			r = true;
		}
		return r;
	}

	private void resetState() {
		this.state = 1;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/*
	 * 步進並回傳 是否完成任務(stage= last code)
	 */
	public boolean state() {
		boolean r = false;
		if (this.state == 0) {
			this.resetState();
			return r = false;
		}
		if (this.getStateResult() == StateResult.Primary
				|| this.getStateResult() == StateResult.BeforeDay) {
			r = !this.nextState();
		} else if (this.getStateResult() == StateResult.Overdue) {
			// this.resetState();
			this.preState();
			r = false;
		}
		return r;
	}

	public boolean isCurrentTesting() {
		StateResult r = this.getStateResult();
		return this.getState() != 0 && !this.isFinish()
				&& (r == StateResult.Primary || r == StateResult.BeforeDay
						|| r == StateResult.Overdue);
	}

	public String getNextTestDateStr() {
		if (this.state == 0 || this.isFinish()) {
			return "--";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.getNextTestDate().getTime()).toString();
	}

	private Calendar getNextTestDate() {
		if (this.state == 0 || this.isFinish()) {
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

	public StateResult getStateResult() {
		StateResult result = StateResult.Default;
		Calendar now = Calendar.getInstance();
		Calendar next = this.getNextTestDate();
		if (next == null) {
			return StateResult.Default;
		}

		Calendar nextDelay = (Calendar) this.getNextTestDate().clone();
		int day = this.stateRuleMap.get(this.state);
		// 如果緩衝天數只有一天,則再加一天
		if (day == 1) {
			day += 1;
		}
		nextDelay.add(Calendar.DAY_OF_MONTH, day);
		if (now.before(next)) {
			result = StateResult.Retest;// 即期重複測驗
		} else if (now.get(Calendar.YEAR) == nextDelay.get(Calendar.YEAR)
				&& now.get(Calendar.DAY_OF_YEAR) == nextDelay
						.get(Calendar.DAY_OF_YEAR) - 1) {
			result = StateResult.BeforeDay;// 即期第一次測驗,但是在即期前一天
		} else if (now.after(next) && now.before(nextDelay)) {
			result = StateResult.Primary;// 即期第一次測驗
		} else {
			result = StateResult.Overdue;// 逾期測驗
		}
		return result;
	}

	public int getStateMaxKey() {
		return this.stateRuleMap.keySet().stream().max((x1, x2) -> x1 - x2)
				.get();
	}

	public int getStateWithScore() {
		if (this.getState() > this.getStateMaxKey()) {
			return this.getStateMaxKey();
		} else {
			return this.getState();
		}
	}

	@Test
	public void test1() {
		CardBox box = new CardBox();
		System.out.println(box.getStateMaxKey());
	}

}

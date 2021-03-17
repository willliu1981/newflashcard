package com.control.viewcontrol;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import com.model.CardBox;

public class ShowRowControl<T> {
	public final static Color EventColor_Click = Color.blue;
	public final static Color EventColor_UnClick = Color.lightGray;
	public final static Color EventColor_MultiClick = Color.cyan;
	public final static Color EventColor_MultiUnClick =new Color(0xf0f0f0);
	protected List<ShowRow> showRows;
	protected List<T> results;//將資料庫query來的資料存入這個 results,其index 值對應 eventResultMap
	protected int fromIResulIdx = 0;// row 翻頁(滑鼠滾輪)起始位置,ex.fromIdx=6,Vocabulary panel component index=0,則 共列出第 6~16
	// 個 資料於 component text 上
	protected JFrame eventJFrame;// ex. MainView
	protected ShowRowInfo info;
	protected int eventReslultIdx = -1;// click row idx
	protected Map<Integer, T> eventResultMap;//用於cardbox-vocabulary editvar add 多選記錄

	public ShowRowControl(JFrame eventJFrame) {
		showRows = new ArrayList<>();
		this.eventResultMap = new HashMap<>();
		this.eventJFrame = eventJFrame;
	}

	public void addEventResultMap(Integer idx, T t) {
		this.eventResultMap.put(idx, t);
	}

	public Map<Integer, T> getEventResultMap() {
		return this.eventResultMap;
	}

	public int getFromIdx() {
		return fromIResulIdx;
	}

	public void towardFromIdx() {
		this.fromIResulIdx--;
		if (this.fromIResulIdx <= -1) {
			this.fromIResulIdx = 0;
		}
	}

	public void rearwardFromIdx() {
		this.fromIResulIdx++;
		if (this.fromIResulIdx >= this.results.size()) {
			this.fromIResulIdx = this.results.size() - 1;
		}
	}
	
	public void resetFromIdx() {
		this.fromIResulIdx=0;
	}

	public void add(ShowRow showRow) {
		this.showRows.add(showRow);
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public List<T> getResults() {
		return this.results;
	}

	public void showRow() {
		for (ShowRow row : showRows) {
			row.showRow();
		}
	}

	public JFrame getEventJFrame() {
		return eventJFrame;
	}

	public void showInfo(Map<String, String> map) {
		this.showInfo(map, ShowRowInfo.InfoName_Default);
	}

	public void showInfo(Map<String, String> map, String infoName) {
		if (this.info != null && map != null && infoName != null) {
			ShowRowInfo info = ShowRowInfo.findInfo(this.info, infoName);
			if (info != null) {
				info.showInfo(map);
			}
		}
	}

	public void setInfo(ShowRowInfo info) {
		this.setInfo(info, false);
	}

	public void setInfo(ShowRowInfo info, boolean createNew) {
		if (this.info == null) {
			this.info = new ShowRowInfo() {
				@Override
				public void showInfo(Map<String, String> map) {
				}
			};
		}
		if (createNew) {
			// 強制賦予info
			this.info.setInfo(info);
		} else {
			this.setAvailableInfo(this.info, info);
		}
	}

	public void setAvailableInfo(ShowRowInfo thisInfo, ShowRowInfo otherInfo) {
		if (thisInfo.getInfo() != null) {
			setAvailableInfo(thisInfo.getInfo(), otherInfo);
		} else {
			thisInfo.setInfo(otherInfo);
		}
	}

	public int getEventResultIdx() {
		return eventReslultIdx;
	}

	public void setEventResultIdx(int eventIdx) {
		this.eventReslultIdx = eventIdx;
	}

	// get reault on click row idx
	public T getEventReault() {
		return this.results.get(this.eventReslultIdx);
	}

	public ShowRowInfo getInfo(String name) {
		return ShowRowInfo.findInfo(this.info, name);
	}

	// 給一個event result idx and from idx ,取得clicked row idx
	public static int getEventRowIdx(int reslutidx, int fromidx) {
		if (reslutidx == -1) {
			return -1;
		} else {
			return reslutidx - fromidx;
		}
	}

}

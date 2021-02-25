package com.control.viewcontrol;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import com.model.CardBox;

public class ShowRowControl<T> {
	private List<ShowRow> showRows;
	private List<T> results;
	private int fromIdx = 0;
	private JFrame eventJFrame;
	private ShowRowInfo info;

	public ShowRowControl(JFrame eventJFrame) {
		showRows = new ArrayList<>();
		this.eventJFrame = eventJFrame;
	}

	public int getFromIdx() {
		return fromIdx;
	}

	public void towardFromIdx() {
		this.fromIdx--;
		if (this.fromIdx == -1) {
			this.fromIdx = 0;
		}
	}

	public void rearwardFromIdx() {
		this.fromIdx++;
		if (this.fromIdx >= this.results.size()) {
			this.fromIdx = this.results.size() - 1;
		}
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
		this.showInfo(map,ShowRowInfo.InfoName_Default);
	}
	
	public void showInfo(Map<String, String> map,String infoName) {
		this.find(info, infoName) .showInfo(map);
	}
	
	private ShowRowInfo find(ShowRowInfo info,String infoName) {
		if(info.getInfoName().equals(infoName)) {
			return info;
		}else {
			return find(info.getInfo(),infoName);
		}
	}

	public void setInfo(ShowRowInfo info) {
		this.setInfo(info, false);
	}

	public void setInfo(ShowRowInfo info, boolean createNew) {
		if (createNew) {
			//強制賦予info
			this.info = info;
			return;
		}
		
		if (this.info != null) {
			this.info.setInfo(info); 
		} else {
			this.info = info;
		}
	}

}

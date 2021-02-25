package com.view.control;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.model.CardBox;

public class ShowRowControl<T,F> {
	private List<ShowRow> showRows;
	private List<T> results;
	private int fromIdx = 0;
	private F eventFrame;

	public ShowRowControl(F eventFrame) {
		showRows = new ArrayList<>();
		this.eventFrame=eventFrame;
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

	public F getEventFrame() {
		return eventFrame;
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
}

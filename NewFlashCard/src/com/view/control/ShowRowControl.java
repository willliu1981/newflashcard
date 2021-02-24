package com.view.control;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import com.model.CardBox;

public class ShowRowControl<T> {
	private List<ShowRow> showRows;
	private List<T> results;
	private int fromIdx = 0;

	public ShowRowControl() {
		showRows = new ArrayList<>();
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
}

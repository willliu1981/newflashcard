package com.view.control;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import com.model.CardBox;

public class ShowRowControl<T> {
	private List<ShowRow> showRows;
	private List<T> results;

	public ShowRowControl() {
		showRows = new ArrayList<>();
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

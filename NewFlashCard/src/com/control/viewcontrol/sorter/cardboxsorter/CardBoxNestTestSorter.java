package com.control.viewcontrol.sorter.cardboxsorter;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;

import com.control.viewcontrol.ShowRowControl;
import com.model.CardBox;
import com.model.Vocabulary;

public class CardBoxNestTestSorter extends CardBoxSorter<CardBox> {

	@Override
	public void sort(JButton button, ActionEvent e, ShowRowControl<CardBox> control) {
		List<CardBox> lst = null;
		if (e.getActionCommand() == null || !e.getActionCommand().equalsIgnoreCase("desc")) {
			lst = sortResults(control.getResults(),
					(x1, x2) -> x1.getNextTestDateStr().compareTo(x2.getNextTestDateStr()));
			button.setActionCommand("desc");
		} else {
			lst = sortResults(control.getResults(),
					(x1, x2) -> x2.getNextTestDateStr().compareTo(x1.getNextTestDateStr()));
			button.setActionCommand("asc");
		}
		control.setResults(lst);
		control.resetEventResultIdx();

	}

}

package com.control.viewcontrol.sorter.cardboxsorter;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;

import com.control.viewcontrol.ShowRowControl;
import com.model.CardBox;
import com.model.Vocabulary;

public class CardBoxTestDateSorter extends CardBoxSorter<CardBox> {

	@Override
	public void sort(JButton button, ActionEvent e, ShowRowControl<CardBox> control) {
		List<CardBox> lst = null;
		if (e.getActionCommand() == null || !e.getActionCommand().equalsIgnoreCase("desc")) {
			lst = sortResults(control.getResults(), (x1, x2) -> {
				Optional<String> op1 = Optional.ofNullable(x1.getTest_date());
				Optional<String> op2 = Optional.ofNullable(x2.getTest_date());
				return op1.orElse("--").compareTo(op2.orElse("--"));
			});
			button.setActionCommand("desc");
		} else {
			lst = sortResults(control.getResults(), (x1, x2) -> {
				Optional<String> op1 = Optional.ofNullable(x1.getTest_date());
				Optional<String> op2 = Optional.ofNullable(x2.getTest_date());
				return op2.orElse("--").compareTo(op1.orElse("--"));
			});
			button.setActionCommand("asc");
		}
		control.setResults(lst);
		control.resetEventResultIdx();

	}

}

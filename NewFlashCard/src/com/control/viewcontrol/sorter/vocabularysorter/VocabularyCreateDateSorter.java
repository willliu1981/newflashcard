package com.control.viewcontrol.sorter.vocabularysorter;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;

import com.control.viewcontrol.ShowRowControl;
import com.model.Vocabulary;

public class VocabularyCreateDateSorter extends VocabularySorter<Vocabulary> {

	@Override
	public void sort(JButton button, ActionEvent e, ShowRowControl<Vocabulary> control) {
		List<Vocabulary> lst = null;
		if (e.getActionCommand() == null || !e.getActionCommand().equalsIgnoreCase("desc")) {
			lst = sortResults(control.getResults(), (x1, x2) -> x1.getCreate_date().compareTo(x2.getCreate_date()));
			button.setActionCommand("desc");
		} else {
			lst = sortResults(control.getResults(), (x1, x2) -> x2.getCreate_date().compareTo(x1.getCreate_date()));
			button.setActionCommand("asc");
		}
		control.setResults(lst);
		control.resetEventResultIdx();

	}

}

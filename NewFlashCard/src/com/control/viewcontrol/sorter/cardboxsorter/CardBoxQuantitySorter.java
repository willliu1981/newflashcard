package com.control.viewcontrol.sorter.cardboxsorter;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;

import com.control.viewcontrol.ShowRowControl;
import com.model.CardBox;
import com.model.Vocabulary;
import com.view.CardBoxRow;

public class CardBoxQuantitySorter extends CardBoxSorter<CardBox> {

	@Override
	public void sort(JButton button, ActionEvent e, ShowRowControl<CardBox> control) {
		List<CardBox> lst = null;
		if (e.getActionCommand() == null || !e.getActionCommand().equalsIgnoreCase("desc")) {
			lst = sortResults(control.getResults(), (x1, x2) -> {
				int sum1 = 0, sum2 = 0;
				if (CardBoxRow.getVocabularyQuantities().containsKey(x1.getId())) {
					sum1 = CardBoxRow.getVocabularyQuantities().get(x1.getId());
				}
				if (CardBoxRow.getVocabularyQuantities().containsKey(x2.getId())) {
					sum2 = CardBoxRow.getVocabularyQuantities().get(x2.getId());
				}
				return sum1 - sum2;
			});
			button.setActionCommand("desc");
		} else {
			lst = sortResults(control.getResults(), (x1, x2) -> {
				int sum1 = 0, sum2 = 0;
				if (CardBoxRow.getVocabularyQuantities().containsKey(x1.getId())) {
					sum1 = CardBoxRow.getVocabularyQuantities().get(x1.getId());
				}
				if (CardBoxRow.getVocabularyQuantities().containsKey(x2.getId())) {
					sum2 = CardBoxRow.getVocabularyQuantities().get(x2.getId());
				}
				return sum2 - sum1;
			});
			button.setActionCommand("asc");
		}
		control.setResults(lst);
		control.resetEventResultIdx();
	}

}

package com.control.viewcontrol.sorter;

import com.control.viewcontrol.sorter.cardboxsorter.CardBoxCreateDateSorter;
import com.control.viewcontrol.sorter.cardboxsorter.CardBoxIdSorter;
import com.control.viewcontrol.sorter.cardboxsorter.CardBoxNameSorter;
import com.control.viewcontrol.sorter.cardboxsorter.CardBoxNestTestSorter;
import com.control.viewcontrol.sorter.cardboxsorter.CardBoxQuantitySorter;
import com.control.viewcontrol.sorter.cardboxsorter.CardBoxSorter.CardBoxSorterType;
import com.control.viewcontrol.sorter.cardboxsorter.CardBoxStateSorter;
import com.control.viewcontrol.sorter.cardboxsorter.CardBoxTestDateSorter;
import com.control.viewcontrol.sorter.cardboxsorter.CardBoxTestTimesSorter;
import com.control.viewcontrol.sorter.cardboxsorter.CardBoxUpdateDateSorter;
import com.control.viewcontrol.sorter.vocabularysorter.VocabularyBoxIdSorter;
import com.control.viewcontrol.sorter.vocabularysorter.VocabularyCreateDateSorter;
import com.control.viewcontrol.sorter.vocabularysorter.VocabularyIdSorter;
import com.control.viewcontrol.sorter.vocabularysorter.VocabularySorter.VocabulrySorterType;
import com.control.viewcontrol.sorter.vocabularysorter.VocabularyTranslationSorter;
import com.control.viewcontrol.sorter.vocabularysorter.VocabularyUpdateDateSorter;
import com.control.viewcontrol.sorter.vocabularysorter.VocabularyVocabularySorter;
import com.model.CardBox;
import com.model.Vocabulary;

public class SorterFactory {

	public static Sorter<Vocabulary> getSorter(VocabulrySorterType type) {
		switch (type) {
		case Id:
			return new VocabularyIdSorter();
		case Vocabulary:
			return new VocabularyVocabularySorter();
		case Translation:
			return new VocabularyTranslationSorter();
		case BoxId:
			return new VocabularyBoxIdSorter();
		case CreateDate:
			return new VocabularyCreateDateSorter();
		case UpdateDate:
			return new VocabularyUpdateDateSorter();
		default:
			return null;
		}
	}

	public static Sorter<CardBox> getSorter(CardBoxSorterType type) {
		switch (type) {
		case Id:
			return new CardBoxIdSorter();
		case Name:
			return new CardBoxNameSorter();
		case CreateDate:
			return new CardBoxCreateDateSorter();
		case UpdateDate:
			return new CardBoxUpdateDateSorter();
		case TestTimes:
			return new CardBoxTestTimesSorter();
		case TestDate:
			return new CardBoxTestDateSorter();
		case State:
			return new CardBoxStateSorter();
		case Quantity:
			return new CardBoxQuantitySorter();
		case NestTest:
			return new CardBoxNestTestSorter();
		default:
			return null;
		}
	}

}

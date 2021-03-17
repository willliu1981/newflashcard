package com.control.viewcontrol.sorter;

import com.control.viewcontrol.sorter.vocabularysorter.VocabularyBoxIdSorter;
import com.control.viewcontrol.sorter.vocabularysorter.VocabularyCreateDateSorter;
import com.control.viewcontrol.sorter.vocabularysorter.VocabularyIdSorter;
import com.control.viewcontrol.sorter.vocabularysorter.VocabularySorter.VocabulrySorterType;
import com.control.viewcontrol.sorter.vocabularysorter.VocabularyTranslationSorter;
import com.control.viewcontrol.sorter.vocabularysorter.VocabularyUpdateDateSorter;
import com.control.viewcontrol.sorter.vocabularysorter.VocabularyVocabularySorter;
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

}

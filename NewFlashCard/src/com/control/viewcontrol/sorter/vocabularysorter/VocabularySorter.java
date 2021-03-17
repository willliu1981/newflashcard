package com.control.viewcontrol.sorter.vocabularysorter;

import com.control.viewcontrol.sorter.Sorter;

public abstract class VocabularySorter<T> extends Sorter<T> {
	public enum VocabulrySorterType {
		Id,Vocabulary,Translation,BoxId,CreateDate,UpdateDate
	}
}

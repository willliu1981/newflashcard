package com.control.viewcontrol.sorter.cardboxsorter;

import com.control.viewcontrol.sorter.Sorter;

public abstract class CardBoxSorter<T> extends Sorter<T> {
	public enum CardBoxSorterType {
		Id,CreateDate,UpdateDate,TestTimes,TestDate,State,Name,Quantity,NestTest
	}
}

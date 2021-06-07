package com.tool;

import java.util.ArrayList;
import java.util.List;

import com.control.exception.MyNullException;

public class ModelCollector<T> {
	private List<T> list = new ArrayList<>();
	private int pointer = -1;

	public ModelCollector(List<T> list) {
		this.list = list;
	}

	public T getNext() throws MyNullException{
		if (hasNext()) {
			return list.get(++pointer);
		} else {
			throw new MyNullException("try get next:"+pointer);
		}
	}

	public boolean hasNext() {
		return pointer + 1 < list.size();
	}

	public T getFormer()throws MyNullException {
		if (hasFormer()) {
			return list.get(--pointer);
		} else {
			throw new MyNullException("try get former:"+pointer);
		}
	}

	public boolean hasFormer() {
		return pointer - 1 >= 0;
	}
	
	public T get() {
		return list.get(pointer);
	}

}

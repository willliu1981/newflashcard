package com.control.viewcontrol.sorter;

import java.awt.event.ActionEvent;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;

import com.control.viewcontrol.ShowRowControl;

public abstract class Sorter<T> {
	protected static <T> List<T> sortResults(List<T> datas, Comparator<T> comp) {
		return datas.stream().sorted(comp).collect(Collectors.toList());
	}
	
	public abstract void sort(JButton button,ActionEvent e,ShowRowControl<T> control);
}

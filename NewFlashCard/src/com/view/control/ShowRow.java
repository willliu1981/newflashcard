package com.view.control;

import com.model.CardBox;

public interface ShowRow<T> {
	void showRow();
	public void setShowRowControl(ShowRowControl<T> control) ;
}

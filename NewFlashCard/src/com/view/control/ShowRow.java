package com.view.control;

import com.model.CardBox;

public interface ShowRow<T,F> {
	void showRow();
	public void setShowRowControl(ShowRowControl<T,F> control) ;
	
	
}

package com.control.pronounce.bridge;

import com.control.pronounce.PronounceFactory;

public class EditPronounceFomatStrBridge extends OpenPronounceSourceBridge {

	@Override
	public void doSend() {
		int index = (int) this.getParameter("index");
		String formatstr = (String) this.getParameter("formatstr");

		PronounceFactory.update(index, formatstr);
		
		super.doSend();

	}

}

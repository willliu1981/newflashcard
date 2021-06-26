package com.control.pronounce.bridge;

import com.control.pronounce.PronounceFormatStrFactory;

public class EditPronounceFomatStrBridge extends OpenPronounceSourceBridge {

	@Override
	public void doSend() {
		int index = (int) this.getParameter("index");
		String formatstr = (String) this.getParameter("formatstr");

		PronounceFormatStrFactory.update(index, formatstr);
		
		super.doSend();

	}

}

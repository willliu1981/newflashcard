package com.control.pronounce.bridge;

import com.control.pronounce.PronounceFactory;

public class AddPronounceFomatStrBridge extends OpenPronounceSourceBridge {

	@Override
	public void doSend() {
		int index = (int) this.getParameter("index");
		String formatstr = (String) this.getParameter("formatstr");
		
		if(index==-1) {
			PronounceFactory.add( formatstr);
		}else {
			PronounceFactory.add(index, formatstr);
		}

		super.doSend();

	}

}

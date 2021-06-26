package com.control.pronounce.bridge;

import com.control.pronounce.PronounceFormatStrFactory;

public class AddPronounceFomatStrBridge extends OpenPronounceSourceBridge {

	@Override
	public void doSend() {
		int index = (int) this.getParameter("index");
		String formatstr = (String) this.getParameter("formatstr");
		
		if(index==-1) {
			PronounceFormatStrFactory.add( formatstr);
		}else {
			PronounceFormatStrFactory.add(index, formatstr);
		}

		super.doSend();

	}

}

package com.control.pronounce.bridge;

import com.control.pronounce.PronounceFormatStrFactory;

public class RemovePronounceFomatStrBridge extends OpenPronounceSourceBridge {

	@Override
	public void doSend() {
		int index = (int) this.getParameter("index");
		
		if(index==-1) {
			return;
		}else {
			PronounceFormatStrFactory.remove(index);
		}

		super.doSend();

	}

}

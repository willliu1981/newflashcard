package com.control.pronounce.bridge;

import com.control.pronounce.PronounceFactory;

public class RemovePronounceFomatStrBridge extends OpenPronounceSourceBridge {

	@Override
	public void doSend() {
		int index = (int) this.getParameter("index");
		
		if(index==-1) {
			return;
		}else {
			PronounceFactory.remove(index);
		}

		super.doSend();

	}

}

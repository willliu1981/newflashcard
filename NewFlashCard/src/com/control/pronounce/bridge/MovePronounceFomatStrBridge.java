package com.control.pronounce.bridge;

import com.control.pronounce.PronounceFactory;

public class MovePronounceFomatStrBridge extends OpenPronounceSourceBridge<Integer> {

	@Override
	public void doSend() {

		super.doSend();

	}

	@Override
	public Integer callback() {
		int index = (int) this.getParameter("index");
		String movetype = (String) this.getParameter("movetype");

		if (index == -1) {
			return index;
		}

		int newIdx = -1;
		if (movetype.equals("up")) {
			newIdx = PronounceFactory.moveUp(index);
		} else if (movetype.equals("down")) {
			newIdx = PronounceFactory.moveDown(index);
		}
		
		if(newIdx==-1) {
			newIdx=index;
		}

		return newIdx;
	}

}

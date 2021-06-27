package com.control;

import com.control.bridge.Bridge;
import com.control.pronounce.PronounceControl;
import com.control.pronounce.PronounceErrUrlFactory;

public class PronounceBridge extends Bridge {

	@Override
	public void doSend() {
		String vocabulary = ((String) this.getParameter("vocabulary")).trim();

		if (vocabulary != null) {
			if (!PronounceErrUrlFactory.isContainError(vocabulary) && !PronounceControl.play(vocabulary)) {
				if (PronounceControl.download(vocabulary)) {
					PronounceControl.play(vocabulary);
				} else {
					PronounceErrUrlFactory.add(vocabulary);
				}
			}
		}
	}

}

package com.control;

import org.junit.Test;

import com.control.bridge.Bridge;
import com.control.pronounce.PronounceControl;
import com.tool.PropertiesFactory;

public class PronounceBridge extends Bridge {

	@Override
	public void doSend() {
		String vocabulary = (String) this.getParameter("vocabulary");

		if(vocabulary !=null) {
			if (!PronounceControl.play(vocabulary)) {
				PronounceControl.download(vocabulary);
				PronounceControl.play(vocabulary);
			}
		}
	}

}

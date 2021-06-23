package com.control;

import org.junit.Test;

import com.control.bridge.Bridge;
import com.control.pronounce.PronounceControl;
import com.tool.PropertiesFactory;

public class PronounceBridge extends Bridge {
	protected final String urlFormatStr = PropertiesFactory.getInstance().getProperty("pronounce_url_formatstr");

	@Override
	public void doSend() {
		String vocabulary = (String) this.getParameter("vocabulary");

		if (!PronounceControl.play(vocabulary)) {
			PronounceControl.download(vocabulary);
			PronounceControl.play(vocabulary);
		}

	}

}

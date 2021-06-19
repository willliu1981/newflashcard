package com.control;

import java.io.IOException;

import com.control.bridge.Bridge;
import com.tool.PropertiesFactory;

public class PronounceBridge extends Bridge {
	protected final String urlFormatStr=PropertiesFactory.getInstance().getProperty("pronounce_url_formatstr");

	@Override
	public void doSend() {
		String vocabulary=(String) this.getParameter("vocabulary");
		
		try {
			PronounceControl.downloadUsingStream(String.format(urlFormatStr, vocabulary), vocabulary);
			PronounceControl.playSound(vocabulary);
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}

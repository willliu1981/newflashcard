package com.control.pronounce.bridge;

import java.awt.Color;
import java.awt.Component;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.control.bridge.Bridge;
import com.control.pronounce.PronounceControl;
import com.control.pronounce.PronounceErrUrlFactory;
import com.view.TestQuestion;

public class PronounceBridge extends Bridge {

	@Override
	public void doSend() {
		String vocabulary = ((String) this.getParameter("vocabulary")).trim();
		TestQuestion parent = ((TestQuestion) this.getParameter("parent"));

		if (parent != null) {
			JLabel lblMsg = parent.getMsgLabel();
			if (vocabulary != null) {
				lblMsg.setVisible(false);
				if (!PronounceErrUrlFactory.isContainError(vocabulary) && !PronounceControl.play(vocabulary)) {
					lblMsg.setVisible(true);
					lblMsg.setText("downloading ...");
					lblMsg.setBackground(Color.yellow);
					lblMsg.setForeground(Color.blue);
					if (PronounceControl.download(vocabulary)) {
						lblMsg.setText("download finish");
						lblMsg.setBackground(Color.green);
						lblMsg.setForeground(Color.blue);
						PronounceControl.play(vocabulary);
					} else {
						lblMsg.setText("download fail");
						lblMsg.setBackground(Color.red);
						lblMsg.setForeground(Color.yellow);
						PronounceErrUrlFactory.add(vocabulary);
					}
				}
			}
		} else {
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

}

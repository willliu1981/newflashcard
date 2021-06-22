package com.control.viewcontrol.bridge;

import java.awt.Component;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import com.control.bridge.Bridge;
import com.control.pronounce.PronounceFactory;
import com.view.MainView;

public class OpenPronounceSourceBridge extends Bridge {

	@Override
	public void doSend() {
		Component parent =(Component) this.getParameter("parent");
		
		MainView.pronounceSourceFrame.setVisible(true);
		MainView.pronounceSourceFrame.setInitPosition(parent.getX(),parent.getY());
		MainView.pronounceSourceFrame.setState(JFrame.NORMAL);
		
		String [] arr=PronounceFactory.getFormatStrArr();
		DefaultListModel model=	new DefaultListModel();
		model.clear();
		for(String s:arr) {
			model.addElement(s);
		}
		
		this.setParameter("model",model );
		
		this.getDispatcher().sendAndBack(MainView.pronounceSourceFrame);
	}

}

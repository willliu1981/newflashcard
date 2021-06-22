package com.control.pronounce.bridge;

import java.awt.Component;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import com.control.bridge.Bridge;
import com.control.pronounce.PronounceFactory;
import com.view.MainView;

public class OpenPronounceSourceBridge<T> extends  Bridge<T> {

	@Override
	public void doSend() {
		Component parent =(Component) this.getParameter("parent");
		
		if(parent!=null) {
			MainView.pronounceSourceFrame.setInitPosition(parent.getX(),parent.getY());
		}
		MainView.pronounceSourceFrame.setVisible(true);
		MainView.pronounceSourceFrame.setState(JFrame.NORMAL);
		
		String [] arr=PronounceFactory.getEffectiveArr();
		DefaultListModel<String> model=	new DefaultListModel<String>();
		model.clear();
		for(String s:arr) {
			model.addElement(s);
		}
		
		this.setParameter("model",model );
		
		this.getDispatcher().sendAndBack(MainView.pronounceSourceFrame);
	}

}

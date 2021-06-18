package com.control.viewcontrol.bridge;

import java.awt.Component;
import java.util.Optional;

import javax.swing.JOptionPane;

import com.control.bridge.Bridge;
import com.control.pad.PadFactory;
import com.tool.Mask;
import com.view.MainView;

public class CheckChangedBridge extends Bridge {

	@Override
	public void doSend() {

	}

	@Override
	public Mask doSendAndBack() {

		Mask queryMask = (Mask) this.getParameter("mask");
		Component parent = (Component) this.getParameter("parent");

		this.setParameter("checkexist", Optional.of(new Object()));

		boolean empty = this.getDispatcher().sendAndBack(MainView.addVocabularyFrame).has(this.SENDANDBACK_NORMAL);

		int r = 0;
		if (PadFactory.isChanged(queryMask)) {
			if (!empty) {
				r = JOptionPane.showConfirmDialog(parent, "資料尚未儲存,請問是否忽略並開啟資料?", "資料開啟", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}
		} else {
			if (!empty) {
				r = JOptionPane.showConfirmDialog(parent, "已開啟一筆資料,請問是否忽略並開啟新的資料?", "資料開啟", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
			}
		}

		if (r == JOptionPane.NO_OPTION) {
			return this.SENDANDBACK_BROKEN;
		} else if (r == JOptionPane.YES_OPTION) {
			return this.SENDANDBACK_NORMAL;
		}

		return this.SENDANDBACK_DEFAULT;
	}

}

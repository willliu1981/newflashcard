package com.control.viewcontrol.bridge;

import java.awt.Component;

import javax.swing.JOptionPane;

import com.control.bridge.Bridge;
import com.control.dao.VocabularyDao;

public class CheckVocabularyExistBridge extends Bridge {

	@Override
	public void doSend() {
		String vocabulary = (String) this.getParameter("vocabulary");
		Component parent = (Component) this.getParameter("parent");
		int count = new VocabularyDao().queryByVocabulary(vocabulary).size();
		JOptionPane.showMessageDialog(parent, count > 0 ? String.format("找到 %d 筆", count) : "沒有找到重複資料", "檢查重複",
				JOptionPane.INFORMATION_MESSAGE);
	}

}

package com.control.viewcontrol.bridge;

import java.awt.Component;

import javax.swing.JOptionPane;

import com.control.bridge.Bridge;
import com.control.dao.VocabularyDao;
import com.control.pad.PadFactory;
import com.model.Vocabulary;
import com.view.MainView;

public class UpdateExplanationBridge extends Bridge {

	@Override
	public void doSend() {
		VocabularyDao dao = new VocabularyDao();
		Vocabulary voca = (Vocabulary) this.getParameter("vocabulary");
		Component parent = (Component) this.getParameter("parent");
		if (voca.getTranslation() == null || voca.getTranslation().equals("")) {
			JOptionPane.showMessageDialog(parent, "翻譯為必要的資料", "資料錯誤", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		PadFactory.getPad().setChange(PadFactory.MAIN_EXPLANATIONFRAME_EXPLANATION, false);
		PadFactory.getPad().setChange(PadFactory.MAIN_EXPLANATIONFRAME_EXAMPLE, false);
		
		dao.update(voca, voca.getId());
		
		this.getDispatcher().send(MainView.explantationFrame);
		
	}

}

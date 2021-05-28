package com.control.bridge;

import java.awt.Component;
import java.util.List;

import javax.swing.JOptionPane;

import com.control.bridge.session.UIDateTransportation;
import com.control.dao.VocabularyDao;
import com.model.Vocabulary;

public class AddVocabularyBridge extends Bridge {

	@Override
	public void doSend(UIDateTransportation dt) {

	}

	@Override
	public int doSendAndBack(UIDateTransportation dt) {
		int r = -1;
		Component parent = (Component) this.getParameter("parent");
		String vocabulary = (String) this.getParameter("vocabulary");
		String translation = (String) this.getParameter("translation");
		String explanation = (String) this.getParameter("explanation");
		String example = (String) this.getParameter("example");

		VocabularyDao dao = new VocabularyDao();
		List<Vocabulary> list = dao.queryByVocabulary(vocabulary);
		if (list.size() > 0) {
			r = JOptionPane.showConfirmDialog(parent, "已有重複的詞彙(" + list.size() + "筆),是否重複新增?", "重複詞彙",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (r == JOptionPane.YES_OPTION) {
				Vocabulary m = new Vocabulary();
				m.setVocabulary(vocabulary);
				m.setTranslation(translation);
				m.setExplanation(explanation);
				m.setExample(example);

				dao.add(m);
			}
		}

		return r;
	}

}

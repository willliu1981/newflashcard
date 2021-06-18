package com.control.viewcontrol;

import javax.swing.JOptionPane;

import com.control.bridge.Bridge;
import com.control.pad.Pad;
import com.control.pad.PadFactory;
import com.model.Vocabulary;
import com.view.MainView;

public class ExplanationBridge extends Bridge {
	@Override
	public void doSend() {
		if (PadFactory.isChanged(PadFactory.EXPLANATIONFRAME)) {
			int result = JOptionPane.showOptionDialog(MainView.explantationFrame, "資料尚未儲存,是否放棄當前資料?", "資料變更",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[] { "是(放棄)", "取消動作" },
					"取消動作");
			if (result != JOptionPane.YES_OPTION) {
				return;
			}
		}
		
		Vocabulary v=(Vocabulary) this.getParameter("vocabulary");
		Pad pad=PadFactory.getPad();
		pad.initializeContent(PadFactory.MAIN_EXPLANATIONFRAME_EXPLANATION,v.getExplanation());
		pad.initializeContent(PadFactory.MAIN_EXPLANATIONFRAME_EXAMPLE,v.getExample());
		this.getDispatcher().send(MainView.explantationFrame);
	}
}

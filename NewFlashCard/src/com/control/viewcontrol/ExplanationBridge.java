package com.control.viewcontrol;

import javax.swing.JOptionPane;

import com.control.bridge.Bridge;
import com.control.bridge.Dispatcher;
import com.control.bridge.session.UIDateTransportation;
import com.control.pad.PadFactory;
import com.view.MainView;

public class ExplanationBridge extends Bridge {

	@Override
	public void doSend() {
		if (PadFactory.getPad().isChanged(PadFactory.MAIN_EXPLANATIONFRAME_EXPLANATION)
				|| PadFactory.getPad().isChanged(PadFactory.MAIN_EXPLANATIONFRAME_EXAMPLE)) {
			int result = JOptionPane.showOptionDialog(MainView.explantationFrame, "資料尚未儲存,是否放棄當前資料?", "資料變更",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[] { "是(放棄)", "取消動作" },
					"取消動作");
			if (result != JOptionPane.YES_OPTION) {
				return;
			}
		}

		Dispatcher disp = this.getDispatcher();
		disp.send(MainView.explantationFrame);
	}

}

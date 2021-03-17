package com.view;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.control.viewcontrol.ShowRow;
import com.control.viewcontrol.ShowRowControl;
import com.control.viewcontrol.ShowRowInfo;
import com.model.Vocabulary;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

public class VocabularyRow extends JPanel implements ShowRow<Vocabulary> {
	private ShowRowControl<Vocabulary> showRowControl;

	private MouseWheelListener myWheelListener = new MouseWheelListener() {
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getWheelRotation() == 1) {
				showRowControl.rearwardFromIdx();
			} else {
				showRowControl.towardFromIdx();
			}
			showRowControl.showRow();
		}
	};

	private MouseAdapter myClickListener = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			int idx = Integer.valueOf(getName()) + showRowControl.getFromIdx();
			if(idx>=showRowControl.getResults().size()) {
				return ;
			}
			showRowControl.setEventResultIdx(idx);
			Vocabulary v = showRowControl.getEventReault();
			Map<String, String> map = new HashMap<>();
			map.put(ShowRowInfo.Translation, v.getTranslation());
			map.put(ShowRowInfo.BoxId, v.getBox_id().toString());
			showRowControl.showInfo(map, ShowRowInfo.InfoName_Vocabulary_Editbar_Edit);
			/*
			 * 顯示 vocabulary editbar edit
			 */
			((CardLayout) ((MainView) showRowControl.getEventJFrame()).getPanel_vocabulary_editbar().getLayout()).show(
					((MainView) showRowControl.getEventJFrame()).getPanel_vocabulary_editbar(),
					MainView.CardLayout_Editbar_edit);
			/*
			 * cardbox-vocabulary editbar add lock state process
			 */
			JPanel panel = (JPanel) ((MainView) showRowControl.getEventJFrame()).getCardboxShowRowControl()
					.getInfo(ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Add)
					.getComponent(ShowRowInfo.Cardbox_Editbar_add_lock);
			if (panel.getName() != null && panel.getName().equals(MainView.Unlock)) {
				/*
				 * 顯示cardbox-vocabulary editbar edit
				 */
				((CardLayout) ((MainView) showRowControl.getEventJFrame()).getPanel_cardbox_vocabulary_editbar()
						.getLayout()).show(
								((MainView) showRowControl.getEventJFrame()).getPanel_cardbox_vocabulary_editbar(),
								MainView.CardLayout_Editbar_edit);
				showRowControl.getEventResultMap().clear();
				showRowControl.addEventResultMap(idx, showRowControl.getEventReault());
			} else {
				if(showRowControl.getEventResultMap().containsValue(showRowControl.getEventReault())) {
					showRowControl.getEventResultMap().remove(idx);
				}else {
					showRowControl.addEventResultMap(idx, showRowControl.getEventReault());
				}
			}
			/*
			 * 關閉Editbar EditPanel
			 */
			JPanel editpanel = (JPanel) ((MainView) showRowControl.getEventJFrame()).getCardboxShowRowControl()
					.getInfo(ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Edit)
					.getComponent(ShowRowInfo.EditbarEditPanel);
			JPanel fieldpanel = (JPanel) ((MainView) showRowControl.getEventJFrame()).getCardboxShowRowControl()
					.getInfo(ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Edit)
					.getComponent(ShowRowInfo.EditbarEditPanel_Field);
			if (fieldpanel.isVisible()) {
				editpanel.setBorder(BorderFactory.createEmptyBorder());
				fieldpanel.setVisible(false);
			}
			showRowControl.showRow();
		}
	};
	private JPanel panel_main;

	/**
	 * Create the panel.
	 */
	public VocabularyRow() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		addMouseWheelListener(myWheelListener);
		addMouseListener(myClickListener);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		panel_main = new JPanel();
		add(panel_main);
		panel_main.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lbl_vocabulary_id = new JLabel("id");
		lbl_vocabulary_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_id.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_main.add(lbl_vocabulary_id);

		JLabel lbl_vocabulary_vocabulary = new JLabel("vocabulary");
		lbl_vocabulary_vocabulary.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_vocabulary.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_main.add(lbl_vocabulary_vocabulary);

		JLabel lbl_vocabulary_translation = new JLabel("translation");
		lbl_vocabulary_translation.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_translation.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_main.add(lbl_vocabulary_translation);

		JLabel lbl_vocabulary_boxid = new JLabel("box_id");
		lbl_vocabulary_boxid.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_boxid.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_main.add(lbl_vocabulary_boxid);

		JLabel lbl_vocabulary_createdate = new JLabel("create_date");
		lbl_vocabulary_createdate.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_createdate.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_main.add(lbl_vocabulary_createdate);

		JLabel lbl_vocabulary_updatedate = new JLabel("update_date");
		lbl_vocabulary_updatedate.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_updatedate.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_main.add(lbl_vocabulary_updatedate);

	}

	@Override
	public void showRow() {
		int idx = Integer.valueOf(this.getName()) + this.showRowControl.getFromIdx();
		int size = this.showRowControl.getResults().size();
		Component[] rows = panel_main.getComponents();
		if (idx < size) {
			Vocabulary v = this.showRowControl.getResults().get(idx);
			((JLabel) rows[0]).setText("" + v.getId());
			((JLabel) rows[1]).setText("" + v.getVocabulary());
			((JLabel) rows[2]).setText("" + v.getTranslation());
			((JLabel) rows[3]).setText("" + v.getBox_id());
			((JLabel) rows[4]).setText("" + v.getCreate_date());
			((JLabel) rows[5]).setText("" + v.getUpdate_date());
		} else {
			((JLabel) rows[0]).setText(" ");
			((JLabel) rows[1]).setText(" ");
			((JLabel) rows[2]).setText(" ");
			((JLabel) rows[3]).setText(" ");
			((JLabel) rows[4]).setText(" ");
			((JLabel) rows[5]).setText(" ");
			this.setBackground(ShowRowControl.EventColor_UnClick);
		}
		/*
		 * cardbox-vocabulary editbar add lock state process
		 */
		JPanel panel = (JPanel) ((MainView) showRowControl.getEventJFrame()).getCardboxShowRowControl()
				.getInfo(ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Add)
				.getComponent(ShowRowInfo.Cardbox_Editbar_add_lock);
		if (idx < size) {
			if (panel.getName() != null && panel.getName().equals(MainView.Unlock)) {
				if (idx == showRowControl.getEventResultIdx()) {
					this.setBackground(ShowRowControl.EventColor_Click);
				} else {
					this.setBackground(ShowRowControl.EventColor_UnClick);
				}
				this.getComponent(0) .setBackground(ShowRowControl.EventColor_MultiUnClick);
			} else if(panel.getName() != null && panel.getName().equals(MainView.Lock)){
				if (showRowControl.getEventResultMap().containsKey(idx)) {
					this.getComponent(0) .setBackground(ShowRowControl.EventColor_MultiClick);
				} else {
					this.getComponent(0) .setBackground(ShowRowControl.EventColor_MultiUnClick);
				}
			}
		}else {
			this.getComponent(0) .setBackground(ShowRowControl.EventColor_MultiUnClick);
		}
	}

	@Override
	public void setShowRowControl(ShowRowControl<Vocabulary> control) {
		this.showRowControl = control;

	}

}

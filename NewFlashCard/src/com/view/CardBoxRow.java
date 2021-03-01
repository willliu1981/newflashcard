package com.view;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.control.dao.VocabularyDao;
import com.control.viewcontrol.ShowRow;
import com.control.viewcontrol.ShowRowControl;
import com.control.viewcontrol.ShowRowInfo;
import com.model.CardBox;
import com.model.Vocabulary;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class CardBoxRow extends JPanel implements ShowRow<CardBox> {
	private ShowRowControl<CardBox> showRowControl;
	private static Map<Integer, Integer> vocabularyQuantities = new HashMap<>();

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
			if (e.getButton() == MouseEvent.BUTTON1) {
				((CardLayout) ((MainView) showRowControl.getEventJFrame()).getPanel_main_centerbar().getLayout()).show(
						((MainView) showRowControl.getEventJFrame()).getPanel_main_centerbar(),
						MainView.CardLayout_CardBox_Vocabulary);
				int idx = Integer.valueOf(getName()) + showRowControl.getFromIdx();
				CardBox cardbox = showRowControl.getResults().get(idx);
				List<Vocabulary> list = new VocabularyDao().queryByBoxID(cardbox.getId());
				((MainView) showRowControl.getEventJFrame()).getVocabularyShowRowControl().setResults(list);
				((MainView) showRowControl.getEventJFrame()).getVocabularyShowRowControl().showRow();
				
				int sum=0;
				if(vocabularyQuantities.containsKey(cardbox.getId())) {
					sum=vocabularyQuantities.get(cardbox.getId());
				}
				Map<String,String>map=new HashMap<>();
				map.put(ShowRowInfo.ID, cardbox.getId().toString());
				map.put(ShowRowInfo.Name, cardbox.getName());
				map.put(ShowRowInfo.Create_date, cardbox.getCreate_date());
				map.put(ShowRowInfo.Update_date, cardbox.getUpdate_date());
				map.put(ShowRowInfo.CardBox_Vocabulary_Quantity, String.valueOf(sum));
				showRowControl.showInfo(map,ShowRowInfo.InfoName_CardBox_Vocabulary);
			} else if (e.getButton() == MouseEvent.BUTTON2) {
			} else if (e.getButton() == MouseEvent.BUTTON3) {
			}
		}
	};

	/**
	 * Create the panel.
	 */
	public CardBoxRow() {
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		setLayout(new GridLayout(1, 0, 0, 0));
		addMouseListener(myClickListener);
		addMouseWheelListener(myWheelListener);

		JLabel lbl_cardbox_id = new JLabel("id");
		lbl_cardbox_id.addMouseListener(myClickListener);
		lbl_cardbox_id.addMouseWheelListener(myWheelListener);
		lbl_cardbox_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cardbox_id.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_cardbox_id);

		JLabel lbl_cardbox_name = new JLabel("name");
		lbl_cardbox_name.addMouseListener(myClickListener);
		lbl_cardbox_name.addMouseWheelListener(myWheelListener);
		lbl_cardbox_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cardbox_name.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_cardbox_name);

		JLabel lbl_cardbox_count = new JLabel("count");
		lbl_cardbox_count.addMouseListener(myClickListener);
		lbl_cardbox_count.addMouseWheelListener(myWheelListener);
		lbl_cardbox_count.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cardbox_count.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_cardbox_count);

		JLabel lbl_cardbox_createdate = new JLabel("create_date");
		lbl_cardbox_createdate.addMouseListener(myClickListener);
		lbl_cardbox_createdate.addMouseWheelListener(myWheelListener);
		lbl_cardbox_createdate.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cardbox_createdate.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_cardbox_createdate);

		JLabel lbl_cardbox_updatedate = new JLabel("update_date");
		lbl_cardbox_updatedate.addMouseListener(myClickListener);
		lbl_cardbox_updatedate.addMouseWheelListener(myWheelListener);
		lbl_cardbox_updatedate.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cardbox_updatedate.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_cardbox_updatedate);

	}

	public void setShowRowControl(ShowRowControl<CardBox> control) {
		this.showRowControl = control;
	}

	@Override
	public void showRow() {
		int idx = Integer.valueOf(this.getName()) + this.showRowControl.getFromIdx();
		int size = this.showRowControl.getResults().size();
		Component[] rows = this.getComponents();
		if (idx < size) {
			CardBox cardBox = this.showRowControl.getResults().get(idx);
			int sum = 0;
			if (vocabularyQuantities.containsKey(cardBox.getId())) {
				sum = vocabularyQuantities.get(cardBox.getId());
			}
			((JLabel) rows[0]).setText("" + cardBox.getId());
			((JLabel) rows[1]).setText("" + cardBox.getName());
			((JLabel) rows[2]).setText("" + sum);
			((JLabel) rows[3]).setText("" + cardBox.getCreate_date());
			((JLabel) rows[4]).setText("" + cardBox.getUpdate_date());
		} else {
			((JLabel) rows[0]).setText(" ");
			((JLabel) rows[1]).setText(" ");
			((JLabel) rows[2]).setText(" ");
			((JLabel) rows[3]).setText(" ");
			((JLabel) rows[4]).setText(" ");
		}
	}

	public static void setVocabularyQuantities(Map<Integer, Integer> map) {
		vocabularyQuantities = map;
	}

	public static void setVocabularyQuantities(List<Vocabulary> list) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0, id; i < list.size(); i++) {
			if (list.get(i).getBox_id() != null && (id = list.get(i).getBox_id()) > -1) {
				if (map.containsKey(id)) {
					map.put(id, map.get(id) + 1);
				} else {
					map.put(id, 1);
				}
			}
		}
		vocabularyQuantities = map;
	}

	public static Map<Integer, Integer> getVocabularyQuantities() {
		return vocabularyQuantities == null ? new HashMap<>() : vocabularyQuantities;
	}

}

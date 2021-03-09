package com.view;

import java.awt.BorderLayout;
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
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import com.control.dao.VocabularyDao;
import com.control.viewcontrol.ShowRow;
import com.control.viewcontrol.ShowRowControl;
import com.model.CardBox;
import com.model.Vocabulary;
import java.awt.Color;

public class TestRow extends JPanel implements ShowRow<CardBox> {
	private ShowRowControl<CardBox> showRowControl;
	private static Map<Integer, Integer> vocabularyQuantities = new HashMap<>();

	private MouseWheelListener myWheelListener = new MouseWheelListener() {
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getWheelRotation() == 1) {
				showRowControl.rearwardFromIdx();
				showRowControl.rearwardFromIdx();
				showRowControl.rearwardFromIdx();
			} else {
				showRowControl.towardFromIdx();
				showRowControl.towardFromIdx();
				showRowControl.towardFromIdx();
			}
			showRowControl.showRow();
		}
	};

	private MouseAdapter myClickListener = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				((CardLayout) ((MainView) showRowControl.getEventJFrame()).getPanel_test_center_cardlayout()
						.getLayout()).show(
								((MainView) showRowControl.getEventJFrame()).getPanel_test_center_cardlayout(),
								MainView.CardLayout_Test_Question);
				int idx = Integer.valueOf(getName()) + showRowControl.getFromIdx();
				CardBox cardbox = showRowControl.getResults().get(idx);
				List<Vocabulary> list = new VocabularyDao().queryAll();
				((MainView) showRowControl.getEventJFrame()).getTestQuestionControl().setCardboxIdx(cardbox.getId());
				((MainView) showRowControl.getEventJFrame()).getTestQuestionControl().setResults(list);
				((MainView) showRowControl.getEventJFrame()).getTestQuestionControl().init(3, 8);
				((MainView) showRowControl.getEventJFrame()).getTestQuestionControl().showRow();
				((MainView) showRowControl.getEventJFrame()).getTestQuestionControl().nextStage();
			} else if (e.getButton() == MouseEvent.BUTTON2) {
			} else if (e.getButton() == MouseEvent.BUTTON3) {
			}
		}
	};
	private JPanel panel_root;

	/**
	 * Create the panel.
	 */
	public TestRow() {
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		setLayout(new BorderLayout(0, 0));

		panel_root = new JPanel();
		add(panel_root);
		panel_root.setLayout(new BorderLayout(0, 0));

		JPanel panel_center = new JPanel();
		panel_root.add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("name");
		lblNewLabel_2.setToolTipText("box name");
		lblNewLabel_2.addMouseListener(myClickListener);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_center.add(lblNewLabel_2);

		JPanel panel_rbar = new JPanel();
		panel_root.add(panel_rbar, BorderLayout.EAST);
		panel_rbar.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("test date");
		lblNewLabel_1.setToolTipText("test date");
		lblNewLabel_1.addMouseListener(myClickListener);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_rbar.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("test times");
		lblNewLabel_1_1.setToolTipText("test times");
		lblNewLabel_1_1.addMouseListener(myClickListener);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_rbar.add(lblNewLabel_1_1);

		JLabel lblNewLabel = new JLabel("quantity");
		lblNewLabel.setToolTipText("quantity");
		lblNewLabel.addMouseListener(myClickListener);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_rbar.add(lblNewLabel);
		addMouseListener(myClickListener);
		addMouseWheelListener(myWheelListener);
	}

	public void setShowRowControl(ShowRowControl<CardBox> control) {
		this.showRowControl = control;
	}

	@Override
	public void showRow() {
		int idx = Integer.valueOf(this.getName()) + this.showRowControl.getFromIdx();
		int size = this.showRowControl.getResults().size();
		JPanel pc = ((JPanel) ((BorderLayout) this.panel_root.getLayout()).getLayoutComponent("Center"));
		JPanel pr = ((JPanel) ((BorderLayout) this.panel_root.getLayout()).getLayoutComponent("East"));
		Component[] row_center = pc.getComponents();
		Component[] row_right = pr.getComponents();
		if (idx < size) {
			CardBox cardBox = this.showRowControl.getResults().get(idx);
			int sum = 0;
			if (vocabularyQuantities.containsKey(cardBox.getId())) {
				sum = vocabularyQuantities.get(cardBox.getId());
			}
			((JLabel) row_center[0]).setText("" + cardBox.getName());
			((JLabel) row_right[0]).setText("詞彙數量: " + sum);
			((JLabel) row_right[1]).setText("已測驗次數: " + cardBox.getTest_times());
			((JLabel) row_right[2]).setText("時間: " + cardBox.getTest_date());
			switch (showRowControl.getResults().get(idx).getStateResult()) {
			case 0:
				this.setBackground(Color.gray);
				break;
			case 1:
				this.setBackground(Color.orange);
				break;
			case -1:
				this.setBackground(Color.red);
				break;
			default:
				break;
			}
		} else {
			((JLabel) row_center[0]).setText(" ");
			((JLabel) row_right[0]).setText(" ");
			((JLabel) row_right[1]).setText(" ");
			((JLabel) row_right[2]).setText(" ");
		}

	}

	public static void setVocabularyQuantities(Map<Integer, Integer> map) {
		vocabularyQuantities = map;
	}

	/*
	 * map <box-id,quantity>
	 */
	public static void setVocabularyQuantitiesInCardboxMap(List<Vocabulary> list) {
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

	public static Map<Integer, Integer> getVocabularyQuantitiesInCardboxMap() {
		return vocabularyQuantities == null ? new HashMap<>() : vocabularyQuantities;
	}

}

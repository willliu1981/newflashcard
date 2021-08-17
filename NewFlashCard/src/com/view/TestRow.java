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
import com.tool.MyColor;

import java.awt.Color;
import javax.swing.border.LineBorder;

public class TestRow extends JPanel implements ShowRow<CardBox> {
	private ShowRowControl<CardBox> showRowControl;
	private static Map<Integer, Integer> vocabularyQuantities = new HashMap<>();
	private  static LineBorder PrimaryLineBorder= new LineBorder(Color.orange, 3);
	private  static LineBorder BeforeDayLineBorder= new LineBorder(Color.red, 3);
	private  static LineBorder OverdueLineBorder= new LineBorder(Color.black,5);
	private  static LineBorder FinishLineBorder= new LineBorder(MyColor.darkGreen(), 3);
	private  static LineBorder DefaultLineBorder= new LineBorder(MyColor.defaultColor(), 3);
	private boolean wheelpress = false;// 滾輸按下

	private MouseWheelListener myWheelListener = new MouseWheelListener() {
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getWheelRotation() == 1) {
				if (wheelpress) {
					showRowControl.rearwardFromIdx(12);// 移動量取決於MainView panel_vocabulary add
														// VocabularyRow 的數量
				} else {
					showRowControl.rearwardFromIdx(3);
				}
			} else {
				if (wheelpress) {
					showRowControl.towardFromIdx(12);
				} else {
					showRowControl.towardFromIdx(3);
				}
			}
			showRowControl.showRow();
			
		}
	};

	private MouseAdapter myClickListener = new MouseAdapter() {
		@Override
		public void mouseReleased(MouseEvent e) {
			wheelpress = false;
		}

		
		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				int idx = Integer.valueOf(getName()) + showRowControl.getFromIdx();
				if (idx >= showRowControl.getResults().size()) {
					return;
				}
				((CardLayout) ((MainView) showRowControl.getEventJFrame()).getPanel_test_center_cardlayout()
						.getLayout()).show(
								((MainView) showRowControl.getEventJFrame()).getPanel_test_center_cardlayout(),
								MainView.CardLayout_Test_Question);
				CardBox cardbox = showRowControl.getResults().get(idx);
				List<Vocabulary> list = new VocabularyDao().queryAll();
				((MainView) showRowControl.getEventJFrame()).getTestQuestionControl().setCardboxIdx(cardbox.getId());
				((MainView) showRowControl.getEventJFrame()).getTestQuestionControl().setResults(list);// 這個部分可能會被覆寫,可能在MainView
																										// 定義TestQuestionControl
																										// 時即便override
				((MainView) showRowControl.getEventJFrame()).getTestQuestionControl().initialize(3, 8);// 設定問題Row 範圍區間
				((MainView) showRowControl.getEventJFrame()).getTestQuestionControl().showRow();
				((MainView) showRowControl.getEventJFrame()).getTestQuestionControl().nextStage();

			} else if (e.getButton() == MouseEvent.BUTTON2) {
				wheelpress = true;
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
		panel_root.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		add(panel_root);
		panel_root.setLayout(new BorderLayout(0, 0));

		JPanel panel_center = new JPanel();
		panel_root.add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("name");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_2.setToolTipText("box name");
		lblNewLabel_2.addMouseListener(myClickListener);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_center.add(lblNewLabel_2);

		JPanel panel_rbar = new JPanel();
		panel_root.add(panel_rbar, BorderLayout.EAST);
		panel_rbar.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("quantity");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1.setToolTipText("quantity");
		lblNewLabel_1.addMouseListener(myClickListener);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_rbar.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("test times");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setOpaque(true);
		lblNewLabel_1_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1_1.setToolTipText("test times");
		lblNewLabel_1_1.addMouseListener(myClickListener);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_rbar.add(lblNewLabel_1_1);

		JLabel lblNewLabel = new JLabel("test date");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setToolTipText("test date");
		lblNewLabel.addMouseListener(myClickListener);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_rbar.add(lblNewLabel);

		JLabel lblNextDate = new JLabel("next test");
		lblNextDate.setForeground(Color.BLACK);
		lblNextDate.setOpaque(true);
		lblNextDate.setBackground(Color.LIGHT_GRAY);
		lblNextDate.setToolTipText("next test date");
		lblNextDate.addMouseListener(myClickListener);
		lblNextDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblNextDate.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_rbar.add(lblNextDate);
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
			// 測驗時間為null時,標示紅色
			if (cardBox.getTest_date() == null && !cardBox.isFinish()) {
				((JLabel) row_right[2]).setForeground((Color.red));
			} else {
				((JLabel) row_right[2]).setForeground(Color.black);
			}
			((JLabel) row_right[2]).setText("時間: " + cardBox.getTest_date());
			((JLabel) row_right[3]).setText("下次: " + (cardBox.isFinish() ? "已完成任務" : cardBox.getNextTestDateStr()));
			switch (cardBox.getStateResult()) {
			case Retest:
				panel_root.setBorder(DefaultLineBorder);
				break;
			case Primary:
				panel_root.setBorder(PrimaryLineBorder);
				break;
			case BeforeDay:
				panel_root.setBorder(BeforeDayLineBorder);
				break;
			case Overdue:
				panel_root.setBorder(OverdueLineBorder);
				break;
			default:
				panel_root.setBorder(DefaultLineBorder);
				break;
			}

			if (cardBox.isFinish()) {
				panel_root.setBorder(FinishLineBorder);
			}
		} else {
			((JLabel) row_center[0]).setText(" ");
			((JLabel) row_right[0]).setText(" ");
			((JLabel) row_right[1]).setText(" ");
			((JLabel) row_right[2]).setText(" ");
			((JLabel) row_right[3]).setText(" ");
			panel_root.setBorder(DefaultLineBorder);
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

package com.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.SystemColor;
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
import com.control.viewcontrol.TestQuestionControl;
import com.model.Vocabulary;

public class TestQuestion extends JPanel implements ShowRow<Vocabulary> {
	public static final String CardLayout_Question = "question";
	public static final String CardLayout_Answer = "answer";
	public static final String CardLayout_Background = "background";
	private TestQuestionControl<Vocabulary> showRowControl;
	private static Map<Integer, Integer> vocabularyQuantities = new HashMap<>();
	private JPanel panel_root_cardlayout;
	private JPanel panel_question;
	private JPanel panel_answer;
	private JPanel panel_background;

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
				((CardLayout) ((MainView) showRowControl.getEventJFrame()).getPanel_centerbar().getLayout()).show(
						((MainView) showRowControl.getEventJFrame()).getPanel_centerbar(),
						MainView.CardLayout_CardBox_Vocabulary);
				int idx = Integer.valueOf(getName()) + showRowControl.getFromIdx();
				Vocabulary vocabulary = showRowControl.getResults().get(idx);
				List<Vocabulary> list = new VocabularyDao().queryByBoxID(vocabulary.getId());
				((MainView) showRowControl.getEventJFrame()).getVocabularyShowRowControl().setResults(list);
				((MainView) showRowControl.getEventJFrame()).getVocabularyShowRowControl().showRow();

//				int sum = 0;
//				if (vocabularyQuantities.containsKey(vocabulary.getId())) {
//					sum = vocabularyQuantities.get(vocabulary.getId());
//				}
//				Map<String, String> map = new HashMap<>();
//				map.put(ShowRowInfo.ID, vocabulary.getId().toString());
//				//map.put(ShowRowInfo.Name, vocabulary.getName());
//				map.put(ShowRowInfo.Create_date, vocabulary.getCreate_date());
//				map.put(ShowRowInfo.Update_date, vocabulary.getUpdate_date());
//				map.put(ShowRowInfo.CardBox_Vocabulary_Quantity, String.valueOf(sum));
//				showRowControl.showInfo(map, ShowRowInfo.InfoName_CardBox_Vocabulary);
			} else if (e.getButton() == MouseEvent.BUTTON2) {
			} else if (e.getButton() == MouseEvent.BUTTON3) {
			}
		}
	};
	private JLabel lblNewLabel_2;

	/**
	 * Create the panel.
	 */
	public TestQuestion() {
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		setLayout(new BorderLayout(0, 0));

		panel_root_cardlayout = new JPanel();
		add(panel_root_cardlayout);
		panel_root_cardlayout.setLayout(new CardLayout(0, 0));

		panel_question = new JPanel();
		panel_question.setBackground(SystemColor.textHighlightText);
		panel_question.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_root_cardlayout.add(panel_question, CardLayout_Question);
		panel_question.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("question");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_question.add(lblNewLabel);

		panel_answer = new JPanel();
		panel_answer.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_answer.setBackground(SystemColor.controlHighlight);
		panel_root_cardlayout.add(panel_answer, CardLayout_Answer);
		panel_answer.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("answer");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_answer.add(lblNewLabel_1);

		panel_background = new JPanel();
		panel_background.setBackground(SystemColor.control);
		panel_background.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_root_cardlayout.add(panel_background, CardLayout_Background);
		panel_background.setLayout(new BorderLayout(0, 0));

		lblNewLabel_2 = new JLabel("info");
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_background.add(lblNewLabel_2, BorderLayout.CENTER);
		addMouseListener(myClickListener);
		addMouseWheelListener(myWheelListener);

	}

	@Override
	public void showRow() {
		int idx = Integer.valueOf(this.getName());
		if (idx == 0) {
			// 問題
			((CardLayout) this.panel_root_cardlayout.getLayout()).show(this.panel_root_cardlayout, CardLayout_Question);
			((JLabel) ((BorderLayout) this.panel_question.getLayout()).getLayoutComponent("Center")).setText(
					this.showRowControl.getQuestionResult().get(this.showRowControl.getEventIdx()).getVocabulary());
		} else if (idx == 1 || idx == 2) {
			// info
			((CardLayout) this.panel_root_cardlayout.getLayout()).show(this.panel_root_cardlayout,
					CardLayout_Background);
		} else {
			// answers
			((CardLayout) this.panel_root_cardlayout.getLayout()).show(this.panel_root_cardlayout, CardLayout_Answer);
			if (this.showRowControl.getCorrectAnswerRowIdx() == idx) {
				((JLabel) ((BorderLayout) this.panel_answer.getLayout()).getLayoutComponent("Center"))
						.setText(this.showRowControl.getQuestionResult().get(this.showRowControl.getEventIdx())
								.getTranslation());
			} else {
				((JLabel) ((BorderLayout) this.panel_answer.getLayout()).getLayoutComponent("Center"))
						.setText(this.showRowControl.getRandomAnswer().getTranslation());
			}
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

	@Override
	public void setShowRowControl(ShowRowControl<Vocabulary> control) {
		this.showRowControl = (TestQuestionControl) control;
	}
}

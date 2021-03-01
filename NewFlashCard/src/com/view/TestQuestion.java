package com.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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

	private MouseAdapter myClickListener = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				((CardLayout) ((MainView) showRowControl.getEventJFrame()).getPanel_main_centerbar().getLayout()).show(
						((MainView) showRowControl.getEventJFrame()).getPanel_main_centerbar(),
						MainView.CardLayout_Test_Question);
				int rowIdx = Integer.valueOf(getName());
				switch (showRowControl.getStage()) {
				case ShowQuestion:
					break;
				case Guess:
					if (showRowControl.clickAnswerRowInRange(rowIdx)) {
						if (!showRowControl.isBingo()) {
							if (showRowControl.isCorrectAnswer(rowIdx)) {
								setBackground(Color.green);
								showRowControl.nextStage();
							} else {
								setBackground(Color.orange);
							}
						}
					}
					showRowControl.showRow();
					break;
				case GotAnswer:
					if (rowIdx == 1) {
						if (showRowControl.isLastQuestion()) {
							if (showRowControl.reviewIsEmpty()) {
								((CardLayout) ((MainView) showRowControl.getEventJFrame()).getPanel_main_centerbar()
										.getLayout()).show(
												((MainView) showRowControl.getEventJFrame()).getPanel_main_centerbar(),
												MainView.CardLayout_Start);
							} else {
								showRowControl.setQuestionFromReviews();
								showRowControl.startReview();
								showRowControl.showRow();
								showRowControl.nextStage();
							}
						} else {
							showRowControl.questionReset();
							showRowControl.showRow();
							showRowControl.nextStage();
						}
					}
					break;
				default:
					break;
				}
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

	}

	@Override
	public void showRow() {
		int idx = Integer.valueOf(this.getName());
		switch (this.showRowControl.getStage()) {
		case ShowQuestion:
			if (idx == 0) {
				// 問題
				((CardLayout) this.panel_root_cardlayout.getLayout()).show(this.panel_root_cardlayout,
						CardLayout_Question);
				((JLabel) ((BorderLayout) this.panel_question.getLayout()).getLayoutComponent("Center"))
						.setText(this.showRowControl.getQuestionResult()
								.get(this.showRowControl.getCurrentQuestionIdx()).getVocabulary());
			} else if (idx == 1 || idx == 2) {
				// info
				((CardLayout) this.panel_root_cardlayout.getLayout()).show(this.panel_root_cardlayout,
						CardLayout_Background);
				if (idx == 2) {
					// 進度
					((JLabel) ((BorderLayout) this.panel_background.getLayout()).getLayoutComponent("Center"))
							.setText(String.format("%d / %d", this.showRowControl.getCurrentQuestionIdx() + 1,
									this.showRowControl.getQuestionResult().size()));
				}
			} else {
				// answers
				((CardLayout) this.panel_root_cardlayout.getLayout()).show(this.panel_root_cardlayout,
						CardLayout_Answer);
				if (this.showRowControl.getCorrectAnswerRowIdx() == idx) {
					((JLabel) ((BorderLayout) this.panel_answer.getLayout()).getLayoutComponent("Center"))
							.setText(this.showRowControl.getQuestionResult()
									.get(this.showRowControl.getCurrentQuestionIdx()).getTranslation());
				} else {
					((JLabel) ((BorderLayout) this.panel_answer.getLayout()).getLayoutComponent("Center"))
							.setText(this.showRowControl.getRandomAnswer().getTranslation());
				}
			}
			this.setBackground(this.showRowControl.Color_Base);
			break;
		case Guess:
			if (idx == 1) {
				// 提示評論
				String info = "";
				if (this.showRowControl.isFirstFailure()) {
					info = "猜錯了";
				}
				((JLabel) ((BorderLayout) this.panel_background.getLayout()).getLayoutComponent("Center"))
						.setText(info);
			}
			break;
		case GotAnswer:
			if (idx == 1) {
				// 提示評論
				String info = "";
				if (this.showRowControl.isLastQuestion()) {
					if (!this.showRowControl.reviewIsEmpty()) {
						if (!this.showRowControl.isFirstFailure()) {
							info = String.format("答對了 (重新複習,共%d題)", this.showRowControl.getReviews().size());
						} else {
							info = String.format("重新複習,共%d題", this.showRowControl.getReviews().size());
						}

					} else {
						if (!this.showRowControl.isFirstFailure()) {
							info = "答對了 (已完成測驗,回首頁)";
						} else {
							info = "已完成測驗,回首頁";
						}
					}
				} else {
					if (!this.showRowControl.isFirstFailure()) {
						info = "答對了 (下一題)";
					} else {
						info = "下一題";
						this.showRowControl.addReviews(this.showRowControl.getCurrentQueation());
					}
				}
				((JLabel) ((BorderLayout) this.panel_background.getLayout()).getLayoutComponent("Center"))
						.setText(info);
				this.setBackground(Color.blue);
			}
			break;
		default:
			break;
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

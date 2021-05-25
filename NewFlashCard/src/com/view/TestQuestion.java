package com.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import com.control.bridge.Dispatcher;
import com.control.bridge.ExposeExplanationBridge;
import com.control.bridge.session.UIDateTransportation;
import com.control.bridge.session.UIDateTransportation.Session;
import com.control.dao.CardBoxDao;
import com.control.viewcontrol.MyColor;
import com.control.viewcontrol.ShowRow;
import com.control.viewcontrol.ShowRowControl;
import com.control.viewcontrol.TestQuestionControl;
import com.model.CardBox;
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
								setBackground(Color.blue);
								showRowControl.nextStage();
							} else {
								setBackground(new Color(255, 127, 37));
							}
						}
					} else {
						/*
						 * 按下了"X" 或 無法答題 按鈕
						 */
						if (rowIdx == 2) {
							showRowControl.setFirstFailure();
						}
					}
					showRowControl.showRow();
					break;
				case GotAnswer:
					if (rowIdx == 1) {
						if (showRowControl.isLastQuestion()) {
							if (showRowControl.reviewIsEmpty()) {
								((MainView) showRowControl.getEventJFrame()).getBtnNewButton_topbar_test().doClick();
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
					} else if (rowIdx >= 4) {
						ExposeExplanationBridge bridge = new ExposeExplanationBridge();
						Dispatcher disp = bridge.getDispatcher();
						bridge.setParameter("id", rowIdx);
						disp.send(bridge);
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
		panel_question.setBackground(Color.BLACK);
		panel_question.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_root_cardlayout.add(panel_question, CardLayout_Question);
		panel_question.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("question");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_question.add(lblNewLabel);

		panel_answer = new JPanel();
		panel_answer.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_answer.setBackground(Color.DARK_GRAY);
		panel_root_cardlayout.add(panel_answer, CardLayout_Answer);
		panel_answer.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("隨機答案");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		panel_answer.add(lblNewLabel_1);

		panel_background = new JPanel();
		panel_background.setBackground(Color.BLACK);
		panel_background.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_root_cardlayout.add(panel_background, CardLayout_Background);
		panel_background.setLayout(new BorderLayout(0, 0));

		lblNewLabel_2 = new JLabel("info");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.BOLD, 20));
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
			} else if (idx == 1 || idx == 2 || idx == 3) {
				// info
				((CardLayout) this.panel_root_cardlayout.getLayout()).show(this.panel_root_cardlayout,
						CardLayout_Background);
				if (idx == 1) {
					// 訊息-提示答對或錯
					((JLabel) ((BorderLayout) this.panel_background.getLayout()).getLayoutComponent("Center"))
							.setText("");
				} else if (idx == 2) {
					// 不知道答案按此
					((JLabel) ((BorderLayout) this.panel_background.getLayout()).getLayoutComponent("Center"))
							.setText("X");
				} else if (idx == 3) {
					// 進度
					((JLabel) ((BorderLayout) this.panel_background.getLayout()).getLayoutComponent("Center"))
							.setText(String.format("%d / %d", this.showRowControl.getCurrentQuestionIdx() + 1,
									this.showRowControl.getQuestionResult().size()));
				}
			} else {
				// answers
				((CardLayout) this.panel_root_cardlayout.getLayout()).show(this.panel_root_cardlayout,
						CardLayout_Answer);
				Vocabulary v = null;
				if (this.showRowControl.getCorrectAnswerRowIdx() == idx) {
					v = this.showRowControl.getQuestionResult().get(this.showRowControl.getCurrentQuestionIdx());
					((JLabel) ((BorderLayout) this.panel_answer.getLayout()).getLayoutComponent("Center"))
							.setText(v.getTranslation());
				} else {
					v = this.showRowControl.getRandomAnswer();
					((JLabel) ((BorderLayout) this.panel_answer.getLayout()).getLayoutComponent("Center"))
							.setText(v.getTranslation());
				}

				Session sess = UIDateTransportation.getSession();
				Map<Integer, Vocabulary> map = (Map<Integer, Vocabulary>) sess.getAttribute("randomAnswers");
				if (map == null) {
					map = new HashMap<>();
				}
				map.put(idx, v);
				sess.setAttribute("randomAnswers", map);
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
			} else if (idx == 2) {
				// "X" 或無法答題
				if (this.showRowControl.isFirstFailure()) {
					this.setBackground(Color.red);
				} else {
					this.setBackground(MyColor.getBase());
				}
			}
			break;
		case GotAnswer:
			if (idx == 1) {
				// 提示評論
				String info = "";
				if (this.showRowControl.isFirstFailure()) {
					this.showRowControl.addReviews(this.showRowControl.getCurrentQueation());
					if (this.showRowControl.isLastQuestion()) {
						info = String.format("複習答錯的題目,共%d題", this.showRowControl.getReviews().size());
					} else {
						info = "下一題";
					}
				} else {
					if (this.showRowControl.isLastQuestion()) {
						if (this.showRowControl.reviewIsEmpty()) {
							info = "已完成測驗,回測驗首頁";
							int boxid = ((MainView) showRowControl.getEventJFrame()).getTestQuestionControl()
									.getCardboxIdx();
							CardBox b = new CardBoxDao().query(boxid);
							b.setTest_times(b.getTest_times() + 1);
							boolean r = b.state();
							if (r) {
								info = "任務完成!!!,回測驗首頁";
							}
							new CardBoxDao().updateTest(b, boxid);
						} else {
							info = String.format("答對了 (複習題目,共%d題)", this.showRowControl.getReviews().size());
						}
					} else {
						info = "答對了 (下一題)";
					}
				}
				((JLabel) ((BorderLayout) this.panel_background.getLayout()).getLayoutComponent("Center"))
						.setText(info);
				this.setBackground(Color.red);
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

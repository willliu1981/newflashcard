package com.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import com.control.ScoreControl;
import com.control.bridge.Dispatcher;
import com.control.bridge.session.UIDateTransportation;
import com.control.dao.CardBoxDao;
import com.control.pronounce.bridge.PronounceBridge;
import com.control.viewcontrol.ShowRow;
import com.control.viewcontrol.ShowRowControl;
import com.control.viewcontrol.TestQuestionControl;
import com.control.viewcontrol.bridge.ExposeExplanationBridge;
import com.model.CardBox;
import com.model.Vocabulary;
import com.tool.MyColor;

public class TestQuestion extends JPanel implements ShowRow<Vocabulary> {
	public static final String CardLayout_Question = "question";
	public static final String CardLayout_Answer = "answer";
	public static final String CardLayout_Background = "background";
	private static final Integer IDX_QUESTION = 0;
	private static final Integer IDX_MESSAGE = 1;
	private static final Integer IDX_PROGRESS = 2;
	private static final Integer IDX_ANSWER = 3;
	private TestQuestionControl<Vocabulary> testQuestionShowRowControl;
	private static Map<Integer, Integer> vocabularyQuantities = new HashMap<>();
	private JPanel panel_root_cardlayout;
	private JPanel panel_question;
	private JPanel panel_answer;
	private JPanel panel_background;
	private double expDecayRerate = 1.0;

	private MouseAdapter myClickListener = new MouseAdapter() {

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getClickCount() == TestQuestion.IDX_PROGRESS) {
				MainView.explantationFrame.setVisible(true);
			} else {

				if (e.getButton() == MouseEvent.BUTTON1) {
					((CardLayout) ((MainView) testQuestionShowRowControl.getEventJFrame()).getPanel_main_centerbar()
							.getLayout()).show(
									((MainView) testQuestionShowRowControl.getEventJFrame()).getPanel_main_centerbar(),
									MainView.CardLayout_Test_Question);
					int rowIdx = Integer.valueOf(getName());
					switch (testQuestionShowRowControl.getStage()) {
					case ShowQuestion:

						break;
					case Guess:
						if (testQuestionShowRowControl.clickAnswerRowInRange(rowIdx)) {
							if (!testQuestionShowRowControl.isBingo()) {
								if (testQuestionShowRowControl.isCorrectAnswer(rowIdx)) {
									setBackground(Color.blue);
									testQuestionShowRowControl.nextStage();
								} else {
									setBackground(new Color(255, 127, 37));
								}
							}
						} else {
							if (rowIdx == TestQuestion.IDX_QUESTION) {
								pronounce(testQuestionShowRowControl.getCurrentQueation().getVocabulary());
							} else if (rowIdx == TestQuestion.IDX_MESSAGE) {
								/*
								 * 無法答題
								 */
								testQuestionShowRowControl.setFirstFailure();
							} else {
								return;
							}
						}
						testQuestionShowRowControl.showRow();
						break;
					case GotAnswer:
						if (rowIdx == TestQuestion.IDX_QUESTION) {
							ExposeExplanationBridge bridge = new ExposeExplanationBridge();
							Dispatcher disp = bridge.getDispatcher();
							bridge.setParameter("correctVocabulary", testQuestionShowRowControl.getQuestionResult()
									.get(testQuestionShowRowControl.getCurrentQuestionIdx()));
							disp.send();

							pronounce(testQuestionShowRowControl.getCurrentQueation().getVocabulary());
						} else if (rowIdx == TestQuestion.IDX_MESSAGE) {
							if (testQuestionShowRowControl.isLastQuestion()) {
								if (testQuestionShowRowControl.reviewIsEmpty()) {
									((MainView) testQuestionShowRowControl.getEventJFrame())
											.getBtnNewButton_topbar_test().doClick();
								} else {
									testQuestionShowRowControl.setQuestionFromReviews();
									testQuestionShowRowControl.startReview();
									testQuestionShowRowControl.showRow();
									testQuestionShowRowControl.nextStage();
								}
							} else {
								testQuestionShowRowControl.questionReset();
								testQuestionShowRowControl.showRow();
								testQuestionShowRowControl.nextStage();
							}
						} else if (rowIdx >= TestQuestion.IDX_ANSWER) {
							ExposeExplanationBridge bridge = new ExposeExplanationBridge();
							Dispatcher<?> disp = bridge.getDispatcher();
							bridge.setParameter("id", rowIdx);
							disp.send();

							Map<Integer, Vocabulary> randomAnswers = (Map<Integer, Vocabulary>) UIDateTransportation
									.getSession().getAttribute("randomAnswers");
							pronounce(randomAnswers.get(rowIdx).getVocabulary());
						}
						break;
					default:
						break;
					}
				} else if (e.getButton() == MouseEvent.BUTTON2) {
				} else if (e.getButton() == MouseEvent.BUTTON3) {
				}
			}
		}
	};
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_msg;
	private JPanel panel;
	private JButton btnNewButton_mark;

	/**
	 * Create the panel.
	 * 
	 * @throws InterruptedException
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

		JLabel lblNewLabel = new JLabel("vocabulary");
		lblNewLabel.setSize(200, 0);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_question.add(lblNewLabel, BorderLayout.CENTER);

		lblNewLabel_msg = new JLabel("download...");
		lblNewLabel_msg.setVisible(false);
		lblNewLabel_msg.setForeground(Color.RED);
		lblNewLabel_msg.setFont(new Font("新細明體", Font.BOLD, 16));
		lblNewLabel_msg.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_msg.setOpaque(true);
		lblNewLabel_msg.setBackground(Color.YELLOW);
		panel_question.add(lblNewLabel_msg, BorderLayout.SOUTH);

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

		panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.DARK_GRAY);
		panel_answer.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		btnNewButton_mark = new JButton("☆");
		btnNewButton_mark.setContentAreaFilled(false);

		btnNewButton_mark.setFocusPainted(false);
		btnNewButton_mark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mark();
				testQuestionShowRowControl.addReviewsVocabulary(testQuestionShowRowControl.getCurrentQueation());
			}
		});
		btnNewButton_mark.setMargin(new Insets(0, 0, 0, 3));
		btnNewButton_mark.setFont(new Font("DialogInput", Font.BOLD, 20));
		btnNewButton_mark.setBorderPainted(false);
		btnNewButton_mark.setForeground(Color.ORANGE);
		panel.add(btnNewButton_mark, BorderLayout.EAST);

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
		String vocabulary = null;
		switch (this.testQuestionShowRowControl.getStage()) {
		case ShowQuestion:
			// initialize
			btnNewButton_mark.setVisible(false);

			if (idx == TestQuestion.IDX_QUESTION) {
				// 問題
				vocabulary = this.testQuestionShowRowControl.getQuestionResult()
						.get(this.testQuestionShowRowControl.getCurrentQuestionIdx()).getVocabulary();
				((CardLayout) this.panel_root_cardlayout.getLayout()).show(this.panel_root_cardlayout,
						CardLayout_Question);
				((JLabel) ((BorderLayout) this.panel_question.getLayout()).getLayoutComponent("Center"))
						.setText(vocabulary);
				String v = vocabulary;
				new Thread() {
					@Override
					public void run() {
						pronounce(v);

					}
				}.start();

			} else if (idx == TestQuestion.IDX_MESSAGE || idx == TestQuestion.IDX_PROGRESS) {
				// info
				((CardLayout) this.panel_root_cardlayout.getLayout()).show(this.panel_root_cardlayout,
						CardLayout_Background);
				if (idx == TestQuestion.IDX_MESSAGE) {
					// 訊息-提示答對或錯
					((JLabel) ((BorderLayout) this.panel_background.getLayout()).getLayoutComponent("Center"))
							.setText("不確定");
				} else if (idx == TestQuestion.IDX_PROGRESS) {
					// 進度
					((JLabel) ((BorderLayout) this.panel_background.getLayout()).getLayoutComponent("Center")).setText(
							String.format("%d / %d", this.testQuestionShowRowControl.getCurrentQuestionIdx() + 1,
									this.testQuestionShowRowControl.getQuestionResult().size()));
				}
			} else {
				// answers
				((CardLayout) this.panel_root_cardlayout.getLayout()).show(this.panel_root_cardlayout,
						CardLayout_Answer);
				Vocabulary v = null;
				if (this.testQuestionShowRowControl.getCorrectAnswerRowIdx() == idx) {
					v = this.testQuestionShowRowControl.getQuestionResult()
							.get(this.testQuestionShowRowControl.getCurrentQuestionIdx());
				} else {
					v = this.testQuestionShowRowControl.getRandomAnswer();
				}
				jlabelSetText((JLabel) ((BorderLayout) this.panel_answer.getLayout()).getLayoutComponent("Center"),
						v.getTranslation());
				Map<Integer, Vocabulary> randomAnswers = (Map<Integer, Vocabulary>) UIDateTransportation.getSession()
						.getAttribute("randomAnswers");
				if (randomAnswers == null) {
					randomAnswers = new HashMap<>();
				}
				randomAnswers.put(idx, v);
				UIDateTransportation.getSession().setAttribute("randomAnswers", randomAnswers);
			}
			/*
			 * 是否複習,則顯示為他色,否為預設
			 */
			this.setBackground(MyColor.getBase());
			if (this.testQuestionShowRowControl.isReview() && idx == TestQuestion.IDX_QUESTION) {
				this.setBackground(Color.red);
			}

			break;
		case Guess:
			if (idx == TestQuestion.IDX_MESSAGE) {
				// 提示評論
				if (this.testQuestionShowRowControl.isFirstFailure()) {
					((JLabel) ((BorderLayout) this.panel_background.getLayout()).getLayoutComponent("Center"))
							.setText("猜錯了");
					this.setBackground(Color.red);
				} else {
					this.setBackground(MyColor.getBase());
				}
			} else if (idx == TestQuestion.IDX_PROGRESS) {

			}
			break;
		case GotAnswer:
			if (idx == TestQuestion.IDX_MESSAGE) {
				// 提示評論
				int boxid = ((MainView) testQuestionShowRowControl.getEventJFrame()).getTestQuestionControl()
						.getCardboxIdx();
				CardBox box = new CardBoxDao().query(boxid);
				if (testQuestionShowRowControl.isReview()) {
					double gainexp_rerate = ScoreControl.RERATE_GAINEXP * expDecayRerate;
					double gainexp_rate = gainexp_rerate;
					if (box.getStateResult() == CardBox.StateResult.Retest) {
						gainexp_rate = gainexp_rerate * ScoreControl.RETESTRATE_GAINEXP;
					}

					if (box.getTest_times() == 0) {
						ScoreControl.primaryExp(gainexp_rate);
					} else {
						ScoreControl.secondaryExp(gainexp_rate);
					}
				} else {
					expDecayRerate = 1.0;
					double gainexp_rate = 1;
					if (box.getStateResult() == CardBox.StateResult.Retest) {
						gainexp_rate *= ScoreControl.RETESTRATE_GAINEXP;
					}

					if (box.getTest_times() == 0) {
						ScoreControl.primaryExp(gainexp_rate);
					} else {
						ScoreControl.secondaryExp(gainexp_rate);
					}
				}

				String info = "";

				if (this.testQuestionShowRowControl.isFirstFailure()) {
					this.testQuestionShowRowControl
							.addReviewsVocabulary(this.testQuestionShowRowControl.getCurrentQueation());
					if (this.testQuestionShowRowControl.isLastQuestion()) {
						info = String.format("複習答錯的題目,共%d題", this.testQuestionShowRowControl.getReviews().size());
						this.testQuestionShowRowControl.setReview();
						expDecayRerate *= ScoreControl.DECAYRATE_GAINEXP;
					} else {
						info = "下一題";
					}
					this.setBackground(Color.red);

				} else {
					if (this.testQuestionShowRowControl.isLastQuestion()) {
						if (this.testQuestionShowRowControl.reviewIsEmpty()) {
							info = "已完成測驗,回測驗首頁";

							// 在state 還沒變動前先取得rate,避免誤判為即期重複測驗
							double gaincoin_rerate = 1;
							if (box.getStateResult() == CardBox.StateResult.Retest || box.isFinish()) {
								// 即期重複測驗,獎利減少
								gaincoin_rerate = ScoreControl.RERATE_GAINCOIN;
							}
							boolean isBeforeFinish = box.isFinish();

							box.setTest_times(box.getTest_times() + 1);
							boolean r = box.state();
							if (r) {
								info = "任務完成!!!,回測驗首頁";
							}
							new CardBoxDao().updateTest(box, boxid);
							testQuestionShowRowControl.endTest();

							double gaincoin_rate = ScoreControl.model.getRate(box.getStateWithScore()) * gaincoin_rerate;
							ScoreControl.gainCoin(gaincoin_rate, testQuestionShowRowControl.getOriginQuestionQuantity());
							// state 變動後判斷是否 all finish, 再額外獲得bonus
							if (box.isFinish() && !isBeforeFinish) {
								ScoreControl.gainBonusCoin(gaincoin_rate,
										testQuestionShowRowControl.getOriginQuestionQuantity());
							}

						} else {
							info = String.format("答對了 (複習題目,共%d題)",
									this.testQuestionShowRowControl.getReviews().size());
							this.testQuestionShowRowControl.setReview();
							expDecayRerate *= 0.6;
						}
					} else {
						info = "答對了 (下一題)";
					}
					this.setBackground(Color.blue);
				}
				((JLabel) ((BorderLayout) this.panel_background.getLayout()).getLayoutComponent("Center"))
						.setText(info);

				ExposeExplanationBridge bridge = new ExposeExplanationBridge();
				Dispatcher disp = bridge.getDispatcher();
				bridge.setParameter("id", testQuestionShowRowControl.getCorrectAnswerRowIdx());
				disp.send();

				pronounce(this.testQuestionShowRowControl.getCurrentQueation().getVocabulary());

				MainView.setCoin(ScoreControl.getScore().getStarcoin().toString());
				MainView.setExp(ScoreControl.getScore().getExperience().toString());

			} else if (idx == testQuestionShowRowControl.getCorrectAnswerRowIdx()) {
				if (testQuestionShowRowControl.isFirstFailure()) {
					this.mark();
				} else {
					this.removeMark();
				}
				btnNewButton_mark.setVisible(true);
			}
			break;
		default:
			break;
		}
	}

	/*
	 * label 自動換行
	 */
	void jlabelSetText(JLabel jLabel, String longString) {
		StringBuilder builder = new StringBuilder("<html>");
		char[] chars = longString.toCharArray();
		FontMetrics fontMetrics = jLabel.getFontMetrics(jLabel.getFont());
		int start = 0;
		int len = 0;
		while (start + len < longString.length()) {
			while (true) {
				len++;
				if (start + len > longString.length())
					break;
				if (fontMetrics.charsWidth(chars, start, len) > jLabel.getWidth()) {
					break;
				}
			}
			builder.append(chars, start, len - 1).append("<br/>");
			start = start + len - 1;
			len = 0;
		}
		builder.append(chars, start, longString.length() - start);
		builder.append("</html>");
		jLabel.setText(builder.toString());
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
		this.testQuestionShowRowControl = (TestQuestionControl) control;
	}

	private void pronounce(String vocabulary) {
		PronounceBridge bridge = new PronounceBridge();
		bridge.setParameter("vocabulary", vocabulary);
		bridge.setParameter("parent", this);
		bridge.getDispatcher().send();
	}

	public JLabel getMsgLabel() {
		return this.lblNewLabel_msg;
	}

	private void mark() {
		btnNewButton_mark.setText("★");
	}

	private void removeMark() {
		btnNewButton_mark.setText("☆");
	}
}

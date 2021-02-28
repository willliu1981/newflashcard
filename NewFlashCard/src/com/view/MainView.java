package com.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import com.control.dao.CardBoxDao;
import com.control.dao.VocabularyDao;
import com.control.viewcontrol.ShowRowControl;
import com.control.viewcontrol.ShowRowInfo;
import com.control.viewcontrol.TestQuestionControl;
import com.model.CardBox;
import com.model.Vocabulary;
import com.test.Test4;

public class MainView extends JFrame {

	
	public static final String CardLayout_CardBox = "cardbox";
	public static final String CardLayout_Vocabulary = "vocabulary";
	public static final String CardLayout_CardBox_Vocabulary = "cardboxvocabulary";
	public static final String CardLayout_Test = "test";
	public static final String CardLayout_Test_Question = "test_question";
	private JPanel contentPane;
	private JPanel panel_centerbar;
	private JPanel panel_cardbox;
	private JPanel panel_vocabulary;
	private ShowRowControl<CardBox> cardboxShowRowControl = new ShowRowControl(this);
	private ShowRowControl<Vocabulary> vocabularyShowRowControl = new ShowRowControl(this);
	private TestQuestionControl<Vocabulary> testQuestionControl = new TestQuestionControl<Vocabulary>(this) {
		@Override
		public void setResults(List<Vocabulary> results) {
			super.setResults(results);
			this.answers = results;
			List<Vocabulary> list = results.stream().filter(x -> {
				return x.getBox_id() == this.cardboxIdx;
			}).collect(Collectors.toList());
			Collections.shuffle(list);
			this.questions = list;
		}

		@Override
		public Vocabulary getRandomAnswer() {
			int idx = 0;
			while (true) {
				idx = (int) (Math.random() * this.answers.size());
				if (this.answers.get(idx).getId() != this.questions.get(this.currentQuestionIdx).getId()) {
					break;
				} else {
					if (this.answers.size() <= 1) {
						break;
					}
				}
			}
			return this.answers.get(idx);
		}
	};
	private JPanel panel_cardbox_vocabulary;
	private JPanel panel_test_center_cardlayout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 521);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("檔案");
		mnNewMenu.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mntmNewMenuItem.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		/*
		 * topbar
		 */
		{
			JPanel panel_topbar = new JPanel();
			contentPane.add(panel_topbar, BorderLayout.NORTH);

			JButton btnNewButton = new JButton("卡片小盒");
			btnNewButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			btnNewButton.setBackground(SystemColor.controlHighlight);
			btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 18));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					((CardLayout) panel_centerbar.getLayout()).show(panel_centerbar, MainView.CardLayout_CardBox);
					List<CardBox> list = new CardBoxDao().queryAll();
					CardBoxRow.setVocabularyQuantities(new VocabularyDao().queryAll());
					cardboxShowRowControl.setResults(list);
					cardboxShowRowControl.showRow();
					Map<String, String> map = new HashMap<>();
					map.put(ShowRowInfo.CardBox_Quantity, String.valueOf(list.size()));
					cardboxShowRowControl.showInfo(map, ShowRowInfo.InfoName_CardBox);
				}
			});

			JButton btnNewButton_2 = new JButton("字卡測驗");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					((CardLayout) panel_centerbar.getLayout()).show(panel_centerbar, MainView.CardLayout_Test);
					List<CardBox> list = new CardBoxDao().queryAll();
					TestRow.setVocabularyQuantities(new VocabularyDao().queryAll());
					cardboxShowRowControl.setResults(list);
					cardboxShowRowControl.showRow();
					Map<String, String> map = new HashMap<>();
					map.put(ShowRowInfo.Test_Quantity, String.valueOf(list.size()));
					cardboxShowRowControl.showInfo(map, ShowRowInfo.InfoName_Test);
				}
			});
			btnNewButton_2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			btnNewButton_2.setBackground(SystemColor.controlHighlight);
			btnNewButton_2.setFont(new Font("新細明體", Font.PLAIN, 18));

			JButton btnNewButton_1 = new JButton("詞彙");
			btnNewButton_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			btnNewButton_1.setBackground(SystemColor.controlHighlight);
			btnNewButton_1.setFont(new Font("新細明體", Font.PLAIN, 18));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					((CardLayout) panel_centerbar.getLayout()).show(panel_centerbar, MainView.CardLayout_Vocabulary);
					List<Vocabulary> list = new VocabularyDao().queryAll();
					vocabularyShowRowControl.setResults(list);
					vocabularyShowRowControl.showRow();
					Map<String, String> map = new HashMap<>();
					map.put(ShowRowInfo.Vocabulary_Quantity, String.valueOf(list.size()));
					vocabularyShowRowControl.showInfo(map, ShowRowInfo.InfoName_Vocabulary);
				}
			});
			panel_topbar.add(btnNewButton_1);
			panel_topbar.add(btnNewButton);
			panel_topbar.add(btnNewButton_2);
		}

		panel_centerbar = new JPanel();
		panel_centerbar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel_centerbar, BorderLayout.CENTER);
		panel_centerbar.setLayout(new CardLayout(0, 0));

		/*
		 * panel start
		 */
		JPanel panel_start = new JPanel();
		panel_centerbar.add(panel_start, "start");
		panel_start.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_12 = new JLabel("Welcome");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setFont(new Font("新細明體", Font.PLAIN, 24));
		panel_start.add(lblNewLabel_12, BorderLayout.CENTER);

		createVocabularyPanel();
		createCardBoxPanel();
		createCardBoxVocabularyPanel();
		createTestPanel();
	}

	private void createVocabularyPanel() {
		ShowRowInfo info = new ShowRowInfo(ShowRowInfo.InfoName_Vocabulary) {
			@Override
			public void showInfo(Map<String, String> m) {
				JLabel lbl = ((JLabel) this.comps.get(ShowRowInfo.Vocabulary_Quantity));
				lbl.setText(lbl.getName() + m.get(ShowRowInfo.Vocabulary_Quantity));
			}
		};
		this.vocabularyShowRowControl.setInfo(info);
		panel_vocabulary = new JPanel();
		panel_centerbar.add(panel_vocabulary, CardLayout_Vocabulary);
		panel_vocabulary.setLayout(new BoxLayout(panel_vocabulary, BoxLayout.Y_AXIS));

		JPanel panel_vocabulary_title_1 = new JPanel();
		panel_vocabulary.add(panel_vocabulary_title_1);
		panel_vocabulary_title_1.setLayout(new BoxLayout(panel_vocabulary_title_1, BoxLayout.Y_AXIS));

		JPanel panel_2_1 = new JPanel();
		panel_vocabulary_title_1.add(panel_2_1);

		JPanel panel_1 = new JPanel();
		panel_vocabulary_title_1.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_11);

		JButton btnNewButton_8 = new JButton("新增單詞");
		btnNewButton_8.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_8.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_8.setBackground(SystemColor.controlHighlight);
		panel_1.add(btnNewButton_8);

		JLabel lblNewLabel_10 = new JLabel("詞彙數量：");
		lblNewLabel_10.setName("詞彙數量：");
		info.add(lblNewLabel_10, ShowRowInfo.Vocabulary_Quantity);
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_10);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		panel_vocabulary_title_1.add(panel_2);

		JPanel panel_title = new JPanel();
		panel_vocabulary_title_1.add(panel_title);
		panel_title.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_3_1 = new JButton("詞彙ID");
		btnNewButton_3_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_3_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_3_1);
		JButton btnNewButton_6_1 = new JButton("英文單詞");
		btnNewButton_6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vocabularyShowRowControl.getResults();
			}
		});
		btnNewButton_6_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_6_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_6_1);

		JButton btnNewButton_4_1 = new JButton("中文翻譯");
		btnNewButton_4_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_4_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_4_1);

		JButton btnNewButton_5_1 = new JButton("小盒連結");
		btnNewButton_5_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_5_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_5_1);

		JButton btnNewButton_5_1_1 = new JButton("建立時間");
		btnNewButton_5_1_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_5_1_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_5_1_1);

		JButton btnNewButton_7_1 = new JButton("更新時間");
		btnNewButton_7_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_7_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_7_1);

		/*
		 * 創建 vocabulary row
		 */
		for (int i = 0; i < 10; i++) {
			VocabularyRow vocabularyRow = new VocabularyRow();
			panel_vocabulary.add(vocabularyRow);
			vocabularyRow.setName("" + i);
			vocabularyRow.setShowRowControl(vocabularyShowRowControl);
			this.vocabularyShowRowControl.add(vocabularyRow);
		}

	}

	private void createCardBoxVocabularyPanel() {
		ShowRowInfo info = new ShowRowInfo(ShowRowInfo.InfoName_CardBox_Vocabulary) {
			@Override
			public void showInfo(Map<String, String> m) {
				((JLabel) this.comps.get(ShowRowInfo.ID)).setText(m.get(ShowRowInfo.ID));
				((JLabel) this.comps.get(ShowRowInfo.Name)).setText(m.get(ShowRowInfo.Name));
				((JLabel) this.comps.get(ShowRowInfo.CardBox_Vocabulary_Quantity))
						.setText(m.get(ShowRowInfo.CardBox_Vocabulary_Quantity));
				((JLabel) this.comps.get(ShowRowInfo.Create_date)).setText(m.get(ShowRowInfo.Create_date));
				((JLabel) this.comps.get(ShowRowInfo.Update_date)).setText(m.get(ShowRowInfo.Update_date));
			}
		};
		this.cardboxShowRowControl.setInfo(info);

		panel_cardbox_vocabulary = new JPanel();
		panel_centerbar.add(panel_cardbox_vocabulary, MainView.CardLayout_CardBox_Vocabulary);
		panel_cardbox_vocabulary.setLayout(new BoxLayout(panel_cardbox_vocabulary, BoxLayout.Y_AXIS));

		JPanel panel_cardbox_vocabulary_title = new JPanel();
		panel_cardbox_vocabulary.add(panel_cardbox_vocabulary_title);
		panel_cardbox_vocabulary_title.setLayout(new BoxLayout(panel_cardbox_vocabulary_title, BoxLayout.Y_AXIS));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_cardbox_vocabulary_title.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 6, 0, 0));

		JLabel lblNewLabel = new JLabel("小盒ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_4 = new JLabel("小盒名稱");
		lblNewLabel_4.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_4);

		JLabel lblNewLabel_1 = new JLabel("卡片數量");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_6 = new JLabel("建立時間");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("更新時間");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_7);

		JLabel lblNewLabel_2 = new JLabel("id");
		info.add(lblNewLabel_2, ShowRowInfo.ID);
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("name");
		info.add(lblNewLabel_3, ShowRowInfo.Name);
		lblNewLabel_3.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("quantity");
		info.add(lblNewLabel_5, ShowRowInfo.CardBox_Vocabulary_Quantity);
		lblNewLabel_5.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_5);

		JLabel lblNewLabel_8 = new JLabel("create date");
		info.add(lblNewLabel_8, ShowRowInfo.Create_date);
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("update date");
		info.add(lblNewLabel_9, ShowRowInfo.Update_date);
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_9);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_cardbox_vocabulary_title.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnNewButton_9 = new JButton("新增卡片");
		btnNewButton_9.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_9.setBackground(SystemColor.controlHighlight);
		btnNewButton_9.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_3.add(btnNewButton_9);

		JButton btnNewButton_10 = new JButton("編輯名稱");
		btnNewButton_10.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_10.setBackground(SystemColor.controlHighlight);
		btnNewButton_10.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_3.add(btnNewButton_10);

		JPanel panel_title = new JPanel();
		panel_cardbox_vocabulary_title.add(panel_title);
		panel_title.setLayout(new GridLayout(0, 6, 0, 0));

		JButton btnNewButton_3_1_1 = new JButton("詞彙ID");
		btnNewButton_3_1_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_3_1_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_3_1_1);

		JButton btnNewButton_6_1_1 = new JButton("英文單詞");
		btnNewButton_6_1_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_6_1_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_6_1_1);

		JButton btnNewButton_4_1_1 = new JButton("中文翻譯");
		btnNewButton_4_1_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_4_1_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_4_1_1);

		JButton btnNewButton_5_1_2 = new JButton("小盒連結");
		btnNewButton_5_1_2.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_5_1_2.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_5_1_2);

		JButton btnNewButton_5_1_1_1 = new JButton("建立時間");
		btnNewButton_5_1_1_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_5_1_1_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_5_1_1_1);

		JButton btnNewButton_7_1_1 = new JButton("更新時間");
		btnNewButton_7_1_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_7_1_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_7_1_1);

		/*
		 * 創建 vocabulary row
		 */
		for (int i = 0; i < 10; i++) {
			VocabularyRow vocabularyRow = new VocabularyRow();
			panel_cardbox_vocabulary.add(vocabularyRow);
			vocabularyRow.setName("" + i);
			vocabularyRow.setShowRowControl(vocabularyShowRowControl);
			this.vocabularyShowRowControl.add(vocabularyRow);
		}
	}

	private void createTestPanel() {
//		ShowRowInfo info = new ShowRowInfo(ShowRowInfo.InfoName_Test) {
//			@Override
//			public void showInfo(Map<String, String> m) {
//				JLabel lbl = ((JLabel) this.comps.get(ShowRowInfo.Test_Quantity));
//				lbl.setText("" + m.get(ShowRowInfo.Test_Quantity));
//			}
//		};
		// this.cardboxShowRowControl.setInfo(info);
		JPanel panel_test = new JPanel();
		panel_centerbar.add(panel_test, CardLayout_Test);
		panel_test.setLayout(new BorderLayout(0, 0));

		JPanel panel_info = new JPanel();
		panel_test.add(panel_info, BorderLayout.NORTH);
		panel_info.setLayout(new BoxLayout(panel_info, BoxLayout.Y_AXIS));

		JPanel panel_top = new JPanel();
		panel_info.add(panel_top);

		JPanel panel_center = new JPanel();
		panel_info.add(panel_center);
		panel_center.setLayout(new BoxLayout(panel_center, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		panel_center.add(panel);

		JButton btnNewButton_11 = new JButton("New button");
		btnNewButton_11.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_11.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_11.setBackground(SystemColor.controlHighlight);
		panel.add(btnNewButton_11);

		JButton btnNewButton_12 = new JButton("New button");
		btnNewButton_12.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_12.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_12.setBackground(SystemColor.controlHighlight);
		panel.add(btnNewButton_12);

		JButton btnNewButton_13 = new JButton("New button");
		btnNewButton_13.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_13.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_13.setBackground(SystemColor.controlHighlight);
		panel.add(btnNewButton_13);

		JPanel panel_bottom = new JPanel();
		panel_info.add(panel_bottom);

		panel_test_center_cardlayout = new JPanel();
		panel_test.add(panel_test_center_cardlayout, BorderLayout.CENTER);
		panel_test_center_cardlayout.setLayout(new CardLayout(0, 0));

		JPanel panel_testrow = new JPanel();
		panel_test_center_cardlayout.add(panel_testrow, CardLayout_Test);
		panel_testrow.setLayout(new GridLayout(4, 3, 0, 0));

		JPanel panel_question = new JPanel();
		panel_test_center_cardlayout.add(panel_question, CardLayout_Test_Question);
		panel_question.setLayout(new GridLayout(3, 3, 0, 0));

		/*
		 * 創建 test row
		 */
		for (int i = 0; i < 12; i++) {
			TestRow TestRow = new TestRow();
			panel_testrow.add(TestRow);
			TestRow.setName("" + i);
			TestRow.setShowRowControl(cardboxShowRowControl);
			this.cardboxShowRowControl.add(TestRow);
		}

		/*
		 * 創建 test question
		 */
		for (int i = 0; i < 9; i++) {
			TestQuestion testQuestion = new TestQuestion();
			panel_question.add(testQuestion);
			testQuestion.setName("" + i);
			testQuestion.setShowRowControl(testQuestionControl);
			this.testQuestionControl.add(testQuestion);
		}
	}

	private void createCardBoxPanel() {
		ShowRowInfo info = new ShowRowInfo(ShowRowInfo.InfoName_CardBox) {
			@Override
			public void showInfo(Map<String, String> m) {
				JLabel lbl = ((JLabel) this.comps.get(ShowRowInfo.CardBox_Quantity));
				lbl.setText(lbl.getName() + m.get(ShowRowInfo.CardBox_Quantity));
			}
		};
		this.cardboxShowRowControl.setInfo(info);

		panel_cardbox = new JPanel();
		panel_centerbar.add(panel_cardbox, CardLayout_CardBox);
		panel_cardbox.setLayout(new BoxLayout(panel_cardbox, BoxLayout.Y_AXIS));

		/*
		 * title
		 */
		JPanel panel_cardbox_title = new JPanel();
		panel_cardbox.add(panel_cardbox_title);
		panel_cardbox_title.setLayout(new BoxLayout(panel_cardbox_title, BoxLayout.Y_AXIS));

		JPanel panel_2_1 = new JPanel();
		panel_cardbox_title.add(panel_2_1);

		JPanel panel_1 = new JPanel();
		panel_cardbox_title.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_11);

		JButton btnNewButton_8 = new JButton("創建小盒");
		btnNewButton_8.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_8.setBackground(SystemColor.controlHighlight);
		btnNewButton_8.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(btnNewButton_8);

		JLabel lblNewLabel_10 = new JLabel("小盒數量：");
		lblNewLabel_10.setName("小盒數量：");
		info.add(lblNewLabel_10, ShowRowInfo.CardBox_Quantity);
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_10);

		JPanel panel_2 = new JPanel();
		panel_cardbox_title.add(panel_2);

		JPanel panel_title = new JPanel();
		panel_cardbox_title.add(panel_title);
		panel_title.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_3 = new JButton("小盒ID");
		btnNewButton_3.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_3.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_3);

		JButton btnNewButton_6 = new JButton("小盒名稱");
		btnNewButton_6.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_6.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_6);

		JButton btnNewButton_4 = new JButton("卡片數量");
		btnNewButton_4.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_4.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("建立時間");
		btnNewButton_5.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_5.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_5);

		JButton btnNewButton_7 = new JButton("更新時間");
		btnNewButton_7.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_7.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_7);

		/*
		 * 創建 cardbox row
		 */
		for (int i = 0; i < 10; i++) {
			CardBoxRow cardBoxRow = new CardBoxRow();
			panel_cardbox.add(cardBoxRow);
			cardBoxRow.setName("" + i);
			cardBoxRow.setShowRowControl(cardboxShowRowControl);
			this.cardboxShowRowControl.add(cardBoxRow);
		}
	}

	public JPanel getPanel_centerbar() {
		return panel_centerbar;
	}

	public JPanel getPanel_test_center_cardlayout() {
		return panel_test_center_cardlayout;
	}

	public ShowRowControl<Vocabulary> getVocabularyShowRowControl() {
		return vocabularyShowRowControl;
	}

	public TestQuestionControl<Vocabulary> getTestQuestionControl() {
		return testQuestionControl;
	}

}

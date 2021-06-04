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
import java.io.FileInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;

import com.control.dao.CardBoxDao;
import com.control.dao.VocabularyDao;
import com.control.viewcontrol.InfoProperty;
import com.control.viewcontrol.ShowRowControl;
import com.control.viewcontrol.ShowRowInfo;
import com.control.viewcontrol.TestQuestionControl;
import com.control.viewcontrol.sorter.Sorter;
import com.control.viewcontrol.sorter.SorterFactory;
import com.control.viewcontrol.sorter.cardboxsorter.CardBoxSorter.CardBoxSorterType;
import com.control.viewcontrol.sorter.vocabularysorter.VocabularySorter.VocabulrySorterType;
import com.model.CardBox;
import com.model.Vocabulary;
import com.tool.MyColor;
import java.awt.Dimension;

public class MainView extends JFrame {
	private static MainView thisApp;
	public static final AddVocabularyFrame addVocabularyFrame = new AddVocabularyFrame();
	public static final ExplanationFrame explantationFrame = new ExplanationFrame();
	public static final QueryResultFrame queryResultFrame = new QueryResultFrame();
	public static final String externalTranslationPrefixUrl = "https://www.quword.com/ciyuan/s/";
	public static final String CardLayout_topbar_CardBox = "cardbox";
	public static final String CardLayout_topbar_Vocabulary = "vocabulary";
	public static final String CardLayout_topbar_CardBox_Vocabulary = "cardboxvocabulary";
	public static final String CardLayout_topbar_Test = "test";
	public static final String CardLayout_Test_List = "test_list";
	public static final String CardLayout_Test_Question = "test_question";
	public static final String CardLayout_topbar_Start = "test_start";
	public static final String CardLayout_Editbar_Serch = "editbar_serch";
	public static final String CardLayout_Editbar_Add = "editbar_add";
	public static final String CardLayout_Editbar_edit = "editbar_edit";
	public static final String Lock = "lock";
	public static final String Unlock = "unlock";
	public static final String MouseEvent_RemoteClickUnResetFromIdx = "event_remoteclickunresetfromidx";
	public static final String MouseEvent_RemoteClickResetFromIdx = "event_remoteclickresetfromidx";
	private JPanel contentPane;
	private JPanel panel_main_centerbar;
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
	private JPanel panel_start;
	private JTextField textField_1;
	private JPanel panel_vocabulary_editbar;
	private JButton btnNewButton_topbar_vocabulary;
	private JTextField textField_7;
	private JTextField textField_10;
	private JPanel panel_cardbox_vocabulary_editbar;
	private JButton btnNewButton_topbar_cardbox;
	private JPanel panel_cardbox_editbar;
	private JTextField textField_14;
	private JButton btnNewButton_topbar_test;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (thisApp != null) {
						return;
					}
					thisApp = new MainView();
					thisApp.setVisible(true);

					Properties prop = new Properties();
					prop.load(new FileInputStream("data//config.properties"));
					String deisgn = prop.getProperty("design");
					String msg = "1";
					if (deisgn.equals("true")) {
						msg += " Debug";
					}
					thisApp.setTitle(String.format("字卡 App 版本 %s", msg));

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
		setBounds(100, 100, 1000, 800);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("檔案");
		mnNewMenu.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("開啟舊檔");
		mntmNewMenuItem.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnNewMenu_1 = new JMenu("編輯");
		mnNewMenu_1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("新增詞彙");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				addVocabularyFrame.setVisible(true);
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		mnNewMenu_1.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("開啟測驗中提示");
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				explantationFrame.setVisible(true);
			}
		});
		mntmNewMenuItem_2.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		mnNewMenu_1.add(mntmNewMenuItem_2);
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
			btnNewButton_topbar_cardbox = new JButton(InfoProperty.getInfo(InfoProperty.CardBox));
			btnNewButton_topbar_cardbox.setFocusPainted(false);
			btnNewButton_topbar_cardbox.setPreferredSize(new Dimension(100, 28));
			btnNewButton_topbar_cardbox.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			btnNewButton_topbar_cardbox.setBackground(SystemColor.controlHighlight);
			btnNewButton_topbar_cardbox.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
			btnNewButton_topbar_cardbox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (judgeTest()) {
						return;
					} else {
						testQuestionControl.endTest();
					}

					if (e.getActionCommand().equalsIgnoreCase(MainView.MouseEvent_RemoteClickUnResetFromIdx)) {
						btnNewButton_topbar_cardbox.setActionCommand(MouseEvent_RemoteClickResetFromIdx);
					} else {
						cardboxShowRowControl.resetFromIdx();
					}
					((CardLayout) panel_main_centerbar.getLayout()).show(panel_main_centerbar,
							MainView.CardLayout_topbar_CardBox);
					List<CardBox> list = new CardBoxDao().queryAll();
					CardBoxRow.setVocabularyQuantities(new VocabularyDao().queryAll());
					cardboxShowRowControl.setResults(list);
					cardboxShowRowControl.showRow();
					Map<String, String> map = new HashMap<>();
					map.put(ShowRowInfo.CardBox_Quantity, String.valueOf(list.size()));
					cardboxShowRowControl.showInfo(map, ShowRowInfo.InfoName_CardBox);
					((CardLayout) panel_cardbox_editbar.getLayout()).show(panel_cardbox_editbar,
							CardLayout_Editbar_Serch);
					Map<String, String> map2 = new HashMap<>();
					map2.put(ShowRowInfo.Cardbox_Editbar_add_lock, Unlock);
					cardboxShowRowControl.showInfo(map2, ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Add);
				}
			});

			btnNewButton_topbar_test = new JButton(InfoProperty.getInfo(InfoProperty.Test));
			btnNewButton_topbar_test.setFocusPainted(false);
			btnNewButton_topbar_test.setPreferredSize(new Dimension(100, 28));
			btnNewButton_topbar_test.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (judgeTest()) {
						return;
					} else {
						testQuestionControl.endTest();
					}

					((CardLayout) panel_main_centerbar.getLayout()).show(panel_main_centerbar,
							MainView.CardLayout_topbar_Test);
					((CardLayout) panel_test_center_cardlayout.getLayout()).show(panel_test_center_cardlayout,
							MainView.CardLayout_Test_List);
					List<CardBox> list = new CardBoxDao().queryAll();
					TestRow.setVocabularyQuantitiesInCardboxMap(new VocabularyDao().queryAll());
					List<CardBox> newlist = list.stream().filter(x -> {
						boolean r = false;
						if (TestRow.getVocabularyQuantitiesInCardboxMap().containsKey(x.getId())) {
							r = TestRow.getVocabularyQuantitiesInCardboxMap().get(x.getId()) > 0;
						}
						return r;
					}).collect(Collectors.toList());
					cardboxShowRowControl.resetFromIdx();
					cardboxShowRowControl.setResults(newlist);
					cardboxShowRowControl.showRow();
					Map<String, String> map = new HashMap<>();
					map.put(ShowRowInfo.Test_Quantity, String.valueOf(list.size()));
					cardboxShowRowControl.showInfo(map, ShowRowInfo.InfoName_Test);
				}
			});
			btnNewButton_topbar_test.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			btnNewButton_topbar_test.setBackground(SystemColor.controlHighlight);
			btnNewButton_topbar_test.setFont(new Font("�蝝唳���", Font.PLAIN, 18));

			btnNewButton_topbar_vocabulary = new JButton(InfoProperty.getInfo(InfoProperty.Vocabulary));
			btnNewButton_topbar_vocabulary.setFocusPainted(false);
			btnNewButton_topbar_vocabulary.setPreferredSize(new Dimension(100, 28));
			btnNewButton_topbar_vocabulary.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			btnNewButton_topbar_vocabulary.setBackground(SystemColor.controlHighlight);
			btnNewButton_topbar_vocabulary.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
			btnNewButton_topbar_vocabulary.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (judgeTest()) {
						return;
					} else {
						testQuestionControl.endTest();
					}

					if (e.getActionCommand().equalsIgnoreCase(MainView.MouseEvent_RemoteClickUnResetFromIdx)) {
						btnNewButton_topbar_vocabulary.setActionCommand(MouseEvent_RemoteClickResetFromIdx);
					} else {
						vocabularyShowRowControl.resetFromIdx();
					}
					vocabularyShowRowControl.setEventResultIdx(-1);// 消除選中
					((CardLayout) panel_main_centerbar.getLayout()).show(panel_main_centerbar,
							MainView.CardLayout_topbar_Vocabulary);
					List<Vocabulary> list = new VocabularyDao().queryAll();
					vocabularyShowRowControl.setResults(list);
					Map<String, String> map = new HashMap<>();
					map.put(ShowRowInfo.Vocabulary_Quantity, String.valueOf(list.size()));
					vocabularyShowRowControl.showInfo(map, ShowRowInfo.InfoName_Vocabulary);
					((CardLayout) panel_vocabulary_editbar.getLayout()).show(panel_vocabulary_editbar,
							CardLayout_Editbar_Serch);
					Map<String, String> map2 = new HashMap<>();
					map2.put(ShowRowInfo.Cardbox_Editbar_add_lock, Unlock);
					cardboxShowRowControl.showInfo(map2, ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Add);
					vocabularyShowRowControl.getEventResultMap().clear();
					vocabularyShowRowControl.showRow();
				}
			});
			panel_topbar.add(btnNewButton_topbar_vocabulary);
			panel_topbar.add(btnNewButton_topbar_cardbox);
			panel_topbar.add(btnNewButton_topbar_test);
		}
		panel_main_centerbar = new JPanel();
		panel_main_centerbar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel_main_centerbar, BorderLayout.CENTER);
		panel_main_centerbar.setLayout(new CardLayout(0, 0));

		/*
		 * panel start
		 */
		panel_start = new JPanel();
		panel_main_centerbar.add(panel_start, MainView.CardLayout_topbar_Start);
		panel_start.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_12 = new JLabel("Welcome");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setFont(new Font("�蝝唳���", Font.PLAIN, 24));
		panel_start.add(lblNewLabel_12, BorderLayout.CENTER);

		JPanel panel_bottombar = new JPanel();
		panel_bottombar.setVisible(false);
		contentPane.add(panel_bottombar, BorderLayout.SOUTH);
		panel_bottombar.setLayout(new CardLayout(0, 0));

		JPanel panel = new JPanel();
		panel_bottombar.add(panel, "name_165616982003200");

		JLabel lblNewLabel_14 = new JLabel("New label");
		lblNewLabel_14.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel.add(lblNewLabel_14);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel.add(textField_1);
		textField_1.setColumns(10);

		createVocabularyPanel();
		createCardBoxPanel();
		createCardBoxVocabularyPanel();
		createTestPanel();
	}

	private boolean judgeTest() {
		boolean r = false;
		if (testQuestionControl.isTesting()) {
			int result = JOptionPane.showConfirmDialog(this, "測驗仍在進行中,是否離開?", "測驗中", JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			if (result == JOptionPane.NO_OPTION || result == -1) {
				r = true;
			}
		}
		return r;
	}

	private void createVocabularyPanel() {
		ShowRowInfo info_query_vocabulary = new ShowRowInfo(ShowRowInfo.InfoName_Vocabulary) {
			@Override
			public void showInfo(Map<String, String> m) {
				JLabel lbl = ((JLabel) this.comps.get(ShowRowInfo.Vocabulary_Quantity));
				lbl.setText(lbl.getName() + ":" + m.get(ShowRowInfo.Vocabulary_Quantity));
			}
		};
		ShowRowInfo info_vocabulary_editbar_add = new ShowRowInfo(ShowRowInfo.InfoName_Vocabulary_Editbar_Add) {
			@Override
			public void showInfo(Map<String, String> m) {
				((JTextField) this.comps.get(ShowRowInfo.Translation)).setText(m.get(ShowRowInfo.Translation));
				((JTextField) this.comps.get(ShowRowInfo.BoxId)).setText(m.get(ShowRowInfo.BoxId));
				((JTextField) this.comps.get(ShowRowInfo.Vocabulary)).setText(m.get(ShowRowInfo.Vocabulary));
			}
		};
		ShowRowInfo info_vocabulary_editbar_edit = new ShowRowInfo(ShowRowInfo.InfoName_Vocabulary_Editbar_Edit) {
			@Override
			public void showInfo(Map<String, String> m) {
				((JTextField) this.comps.get(ShowRowInfo.Translation)).setText(m.get(ShowRowInfo.Translation));
				((JTextField) this.comps.get(ShowRowInfo.BoxId)).setText(m.get(ShowRowInfo.BoxId));
			}
		};
		this.vocabularyShowRowControl.setInfo(info_query_vocabulary);
		this.vocabularyShowRowControl.setInfo(info_vocabulary_editbar_add);
		this.vocabularyShowRowControl.setInfo(info_vocabulary_editbar_edit);
		panel_vocabulary = new JPanel();
		panel_main_centerbar.add(panel_vocabulary, CardLayout_topbar_Vocabulary);
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
		lblNewLabel_11.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_11);
		JButton btnNewButton_8 = new JButton(InfoProperty.getInfo(InfoProperty.Create));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vocabularyShowRowControl.setEventResultIdx(-1);
				vocabularyShowRowControl.showRow();
				((CardLayout) panel_vocabulary_editbar.getLayout()).show(panel_vocabulary_editbar,
						CardLayout_Editbar_Add);
			}
		});
		btnNewButton_8.setToolTipText("create new word");
		btnNewButton_8.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_8.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		btnNewButton_8.setBackground(SystemColor.controlHighlight);
		panel_1.add(btnNewButton_8);

		JLabel lblNewLabel_10 = new JLabel(InfoProperty.getInfo(InfoProperty.Quantity));
		lblNewLabel_10.setName(InfoProperty.getInfo(InfoProperty.Quantity));
		info_query_vocabulary.add(lblNewLabel_10, ShowRowInfo.Vocabulary_Quantity);
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_10);

		JPanel panel_2 = new JPanel();
		panel_vocabulary_title_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel_vocabulary_editbar = new JPanel();
		panel_vocabulary_editbar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_vocabulary_title_1.add(panel_vocabulary_editbar);
		panel_vocabulary_editbar.setLayout(new CardLayout(0, 0));

		JPanel panel_serch = new JPanel();
		panel_serch.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_vocabulary_editbar.add(panel_serch, CardLayout_Editbar_Serch);

		JLabel lblNewLabel_13 = new JLabel("serch");
		lblNewLabel_13.setVisible(false);
		lblNewLabel_13.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_serch.add(lblNewLabel_13);

		JTextField textField = new JTextField();
		textField.setVisible(false);
		textField.setFont(new Font("新細明體", Font.PLAIN, 18));
		textField.setColumns(10);
		panel_serch.add(textField);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setVisible(false);
		comboBox.setBackground(SystemColor.control);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Vocabulary", "Translation", "Box ID" }));
		comboBox.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_serch.add(comboBox);

		JPanel panel_add = new JPanel();
		panel_vocabulary_editbar.add(panel_add, CardLayout_Editbar_Add);
		panel_add.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel_15 = new JLabel("Vocab");
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_15.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_add.add(lblNewLabel_15);

		JTextField textField_2 = new JTextField();
		info_vocabulary_editbar_add.add(textField_2, ShowRowInfo.Vocabulary);
		textField_2.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_add.add(textField_2);
		textField_2.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_add.add(panel_3);

		JLabel lblNewLabel_16 = new JLabel("Trans");
		lblNewLabel_16.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_16.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_add.add(lblNewLabel_16);

		JTextField textField_3 = new JTextField();
		info_vocabulary_editbar_add.add(textField_3, ShowRowInfo.Translation);
		textField_3.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_add.add(textField_3);
		textField_3.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_add.add(panel_4);

		JLabel lblNewLabel_17 = new JLabel("Box id");
		lblNewLabel_17.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_17.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_add.add(lblNewLabel_17);

		JTextField textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		info_vocabulary_editbar_add.add(textField_4, ShowRowInfo.BoxId);
		textField_4.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_add.add(textField_4);
		textField_4.setColumns(4);
		JPanel panel = new JPanel();
		panel_add.add(panel);
		JButton btnNewButton_14 = new JButton("Add");
		btnNewButton_14.setToolTipText("commit");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vocab = ((JTextField) vocabularyShowRowControl
						.getInfo(ShowRowInfo.InfoName_Vocabulary_Editbar_Add).getComponent(ShowRowInfo.Vocabulary))
								.getText();
				String trans = ((JTextField) vocabularyShowRowControl
						.getInfo(ShowRowInfo.InfoName_Vocabulary_Editbar_Add).getComponent(ShowRowInfo.Translation))
								.getText();
				String boxid = ((JTextField) vocabularyShowRowControl
						.getInfo(ShowRowInfo.InfoName_Vocabulary_Editbar_Add).getComponent(ShowRowInfo.BoxId))
								.getText();
				Vocabulary v = new Vocabulary();
				v.setVocabulary(vocab);
				v.setTranslation(trans);
				int iboxid = 0;
				try {
					iboxid = Integer.valueOf(boxid);
				} catch (Exception ex) {
					// ex.printStackTrace();
					iboxid = -1;
				}
				v.setBox_id(iboxid);
				if (!(vocab == null || trans == null) && !(vocab.trim().equals("") || trans.trim().equals(""))) {
					if (iboxid == -1 || new CardBoxDao().isExist(iboxid)) {
						new VocabularyDao().add(v);
						btnNewButton_topbar_vocabulary.doClick();
					}
				}
				Map<String, String> map = new HashMap<>();
				map.put(ShowRowInfo.Translation, "");
				map.put(ShowRowInfo.BoxId, "");
				map.put(ShowRowInfo.Vocabulary, "");
				vocabularyShowRowControl.showInfo(map, ShowRowInfo.InfoName_Vocabulary_Editbar_Add);
				((CardLayout) panel_vocabulary_editbar.getLayout()).show(panel_vocabulary_editbar,
						CardLayout_Editbar_Serch);
			}
		});
		btnNewButton_14.setFont(new Font("新細明體", Font.PLAIN, 14));
		btnNewButton_14.setBackground(SystemColor.controlHighlight);
		panel_add.add(btnNewButton_14);
		JPanel panel_5 = new JPanel();
		panel_add.add(panel_5);

		JButton btnNewButton_15 = new JButton("取消");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String, String> map = new HashMap<>();
				map.put(ShowRowInfo.Translation, "");
				map.put(ShowRowInfo.BoxId, "");
				map.put(ShowRowInfo.Vocabulary, "");
				vocabularyShowRowControl.showInfo(map, ShowRowInfo.InfoName_Vocabulary_Editbar_Add);
				((CardLayout) panel_vocabulary_editbar.getLayout()).show(panel_vocabulary_editbar,
						CardLayout_Editbar_Serch);
			}
		});
		btnNewButton_15.setFont(new Font("新細明體", Font.PLAIN, 14));
		btnNewButton_15.setBackground(SystemColor.controlHighlight);
		panel_add.add(btnNewButton_15);

		JPanel panel_edit = new JPanel();
		panel_vocabulary_editbar.add(panel_edit, CardLayout_Editbar_edit);
		panel_edit.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_3_1 = new JPanel();
		panel_edit.add(panel_3_1);

		JLabel lblNewLabel_16_1 = new JLabel("Trans");
		lblNewLabel_16_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_16_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_edit.add(lblNewLabel_16_1);

		JTextField textField_5 = new JTextField();
		info_vocabulary_editbar_edit.add(textField_5, ShowRowInfo.Translation);
		textField_5.setFont(new Font("新細明體", Font.PLAIN, 16));
		textField_5.setColumns(10);
		panel_edit.add(textField_5);

		JPanel panel_4_1 = new JPanel();
		panel_edit.add(panel_4_1);

		JLabel lblNewLabel_17_1 = new JLabel("Box id");
		lblNewLabel_17_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_17_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_edit.add(lblNewLabel_17_1);

		JTextField textField_6 = new JTextField();
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		info_vocabulary_editbar_edit.add(textField_6, ShowRowInfo.BoxId);
		textField_6.setFont(new Font("新細明體", Font.PLAIN, 16));
		textField_6.setColumns(4);
		panel_edit.add(textField_6);

		JPanel panel_6 = new JPanel();
		panel_edit.add(panel_6);

		JButton btnNewButton_14_1 = new JButton("Update");
		btnNewButton_14_1.setToolTipText("update this word");
		btnNewButton_14_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String trans = ((JTextField) vocabularyShowRowControl
						.getInfo(ShowRowInfo.InfoName_Vocabulary_Editbar_Edit).getComponent(ShowRowInfo.Translation))
								.getText();
				String boxid = ((JTextField) vocabularyShowRowControl
						.getInfo(ShowRowInfo.InfoName_Vocabulary_Editbar_Edit).getComponent(ShowRowInfo.BoxId))
								.getText();
				Vocabulary v = new Vocabulary();
				v.setTranslation(trans);
				int iboxid = -1;
				try {
					iboxid = Integer.valueOf(boxid);
				} catch (Exception ex) {
					// ex.printStackTrace();
					iboxid = -1;
				}
				v.setBox_id(iboxid);
				if (trans != null && !trans.trim().equals("")) {
					if (iboxid == -1 || new CardBoxDao().isExist(iboxid)) {
						new VocabularyDao().update(v, vocabularyShowRowControl.getEventReault().getId());
						btnNewButton_topbar_vocabulary.setActionCommand(MouseEvent_RemoteClickUnResetFromIdx);
						btnNewButton_topbar_vocabulary.doClick();
					}
				}
				Map<String, String> map = new HashMap<>();
				map.put(ShowRowInfo.Translation, "");
				map.put(ShowRowInfo.BoxId, "");
				map.put(ShowRowInfo.Vocabulary, "");
				vocabularyShowRowControl.showInfo(map, ShowRowInfo.InfoName_Vocabulary_Editbar_Edit);
				((CardLayout) panel_vocabulary_editbar.getLayout()).show(panel_vocabulary_editbar,
						CardLayout_Editbar_Serch);
				vocabularyShowRowControl.setEventResultIdx(-1);
				vocabularyShowRowControl.showRow();
			}
		});
		JButton btnNewButton_2 = new JButton("Exp.");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				explantationFrame.setVisible(true);
			}
		});
		btnNewButton_2.setBackground(SystemColor.controlHighlight);
		btnNewButton_2.setFont(new Font("新細明體", Font.PLAIN, 14));
		panel_edit.add(btnNewButton_2);

		JPanel panel_6_1 = new JPanel();
		panel_edit.add(panel_6_1);
		btnNewButton_14_1.setFont(new Font("新細明體", Font.PLAIN, 14));
		btnNewButton_14_1.setBackground(SystemColor.controlHighlight);
		panel_edit.add(btnNewButton_14_1);

		JPanel panel_5_1 = new JPanel();
		panel_edit.add(panel_5_1);

		JButton btnNewButton_15_1 = new JButton("取消");
		btnNewButton_15_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vocabularyShowRowControl.setEventResultIdx(-1);
				vocabularyShowRowControl.showRow();
				Map<String, String> map = new HashMap<>();
				map.put(ShowRowInfo.Translation, "");
				map.put(ShowRowInfo.BoxId, "");
				vocabularyShowRowControl.showInfo(map, ShowRowInfo.InfoName_Vocabulary_Editbar_Edit);
				((CardLayout) panel_vocabulary_editbar.getLayout()).show(panel_vocabulary_editbar,
						CardLayout_Editbar_Serch);
			}
		});
		JButton btnNewButton_14_1_1 = new JButton("Delete");
		btnNewButton_14_1_1.setToolTipText("delete this word");
		btnNewButton_14_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] options = { "Delete", "Cancel" };
				int r = JOptionPane.showOptionDialog(null,
						String.format("Delete \"%s\" ?",
								getVocabularyShowRowControl().getEventReault().getVocabulary()),
						"Delete Date", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, "Delete");
				if (r == JOptionPane.NO_OPTION) {
					return;
				}

				new VocabularyDao().delete(getVocabularyShowRowControl().getEventReault().getId());
				btnNewButton_topbar_vocabulary.doClick();
				Map<String, String> map = new HashMap<>();
				map.put(ShowRowInfo.Translation, "");
				map.put(ShowRowInfo.BoxId, "");
				map.put(ShowRowInfo.Vocabulary, "");
				vocabularyShowRowControl.showInfo(map, ShowRowInfo.InfoName_Vocabulary_Editbar_Edit);
				((CardLayout) panel_vocabulary_editbar.getLayout()).show(panel_vocabulary_editbar,
						CardLayout_Editbar_Serch);
				vocabularyShowRowControl.setEventResultIdx(-1);
				vocabularyShowRowControl.showRow();

			}
		});
		btnNewButton_14_1_1.setFont(new Font("新細明體", Font.PLAIN, 14));
		btnNewButton_14_1_1.setBackground(SystemColor.controlHighlight);
		panel_edit.add(btnNewButton_14_1_1);

		JPanel panel_7 = new JPanel();
		panel_edit.add(panel_7);
		btnNewButton_15_1.setFont(new Font("新細明體", Font.PLAIN, 14));
		btnNewButton_15_1.setBackground(SystemColor.controlHighlight);
		panel_edit.add(btnNewButton_15_1);

		JPanel panel_title = new JPanel();
		panel_vocabulary_title_1.add(panel_title);
		panel_title.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_3_1 = new JButton("ID");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(VocabulrySorterType.Id), btnNewButton_3_1, e, vocabularyShowRowControl);
				vocabularyShowRowControl.showRow();
			}
		});
		btnNewButton_3_1.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		btnNewButton_3_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_3_1);
		JButton btnNewButton_6_1 = new JButton("Vocabulary");
		btnNewButton_6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(VocabulrySorterType.Vocabulary), btnNewButton_6_1, e,
						vocabularyShowRowControl);
				vocabularyShowRowControl.showRow();
			}
		});
		btnNewButton_6_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnNewButton_6_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_6_1);

		JButton btnNewButton_4_1 = new JButton("Translation");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(VocabulrySorterType.Translation), btnNewButton_4_1, e,
						vocabularyShowRowControl);
				vocabularyShowRowControl.showRow();
			}
		});
		btnNewButton_4_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnNewButton_4_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_4_1);

		JButton btnNewButton_5_1 = new JButton("Box ID");
		btnNewButton_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(VocabulrySorterType.BoxId), btnNewButton_5_1, e, vocabularyShowRowControl);
				vocabularyShowRowControl.showRow();
			}
		});
		btnNewButton_5_1.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		btnNewButton_5_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_5_1);

		JButton btnNewButton_5_1_1 = new JButton("Create Date");
		btnNewButton_5_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(VocabulrySorterType.CreateDate), btnNewButton_5_1_1, e,
						vocabularyShowRowControl);
				vocabularyShowRowControl.showRow();
			}
		});
		btnNewButton_5_1_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnNewButton_5_1_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_5_1_1);

		JButton btnNewButton_7_1 = new JButton("Update Date");
		btnNewButton_7_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(VocabulrySorterType.UpdateDate), btnNewButton_7_1, e,
						vocabularyShowRowControl);
				vocabularyShowRowControl.showRow();
			}
		});
		btnNewButton_7_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnNewButton_7_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_7_1);

		/*
		 * �撱� vocabulary row
		 */
		for (int i = 0; i < 20; i++) {
			VocabularyRow vocabularyRow = new VocabularyRow();
			panel_vocabulary.add(vocabularyRow);
			vocabularyRow.setName("" + i);
			vocabularyRow.setShowRowControl(vocabularyShowRowControl);
			this.vocabularyShowRowControl.add(vocabularyRow);
		}

	}

	private <T> void sort(Sorter<T> sorter, JButton button, ActionEvent e, ShowRowControl<T> control) {
		sorter.sort(button, e, control);
	}

	private void createCardBoxVocabularyPanel() {
		ShowRowInfo info = new ShowRowInfo(ShowRowInfo.InfoName_CardBox_Vocabulary) {
			@Override
			public void showInfo(Map<String, String> m) {
				((JLabel) this.comps.get(ShowRowInfo.ID)).setText(m.get(ShowRowInfo.ID));
				((JLabel) this.comps.get(ShowRowInfo.Name)).setText(m.get(ShowRowInfo.Name));
				((JLabel) this.comps.get(ShowRowInfo.CardBox_Vocabulary_Quantity))
						.setText(m.get(ShowRowInfo.CardBox_Vocabulary_Quantity));
				((JLabel) this.comps.get(ShowRowInfo.Test_time)).setText(m.get(ShowRowInfo.Test_time));
				((JLabel) this.comps.get(ShowRowInfo.Test_date)).setText(m.get(ShowRowInfo.Test_date));
				((JLabel) this.comps.get(ShowRowInfo.Create_date)).setText(m.get(ShowRowInfo.Create_date));
				((JLabel) this.comps.get(ShowRowInfo.Update_date)).setText(m.get(ShowRowInfo.Update_date));
			}
		};
		ShowRowInfo info_cardbox_vocabulary_editbar_add = new ShowRowInfo(
				ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Add) {
			@Override
			public void showInfo(Map<String, String> m) {
				// 使Add lock ,點擊 vocabulary row 將使用add 模式 來多選row 並 add 至 cardbox
				((JPanel) this.comps.get(ShowRowInfo.Cardbox_Editbar_add_lock))
						.setName((m.get(ShowRowInfo.Cardbox_Editbar_add_lock)));
			}
		};
		ShowRowInfo info_cardbox_vocabulary_editbar_edit = new ShowRowInfo(
				ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Edit) {
			@Override
			public void showInfo(Map<String, String> m) {
				((JTextField) this.comps.get(ShowRowInfo.Name)).setText((m.get(ShowRowInfo.Name)));
			}
		};
		ShowRowInfo info_cardbox_vocabulary_editbar_remove = new ShowRowInfo(
				ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Remove) {
			@Override
			public void showInfo(Map<String, String> m) {
				((JTextField) this.comps.get(ShowRowInfo.Translation)).setText(m.get(ShowRowInfo.Translation));
				((JTextField) this.comps.get(ShowRowInfo.BoxId)).setText(m.get(ShowRowInfo.BoxId));
			}
		};
		this.cardboxShowRowControl.setInfo(info);
		this.cardboxShowRowControl.setInfo(info_cardbox_vocabulary_editbar_add);
		this.cardboxShowRowControl.setInfo(info_cardbox_vocabulary_editbar_edit);
		this.cardboxShowRowControl.setInfo(info_cardbox_vocabulary_editbar_remove);

		panel_cardbox_vocabulary = new JPanel();
		panel_main_centerbar.add(panel_cardbox_vocabulary, MainView.CardLayout_topbar_CardBox_Vocabulary);
		panel_cardbox_vocabulary.setLayout(new BoxLayout(panel_cardbox_vocabulary, BoxLayout.Y_AXIS));

		JPanel panel_cardbox_vocabulary_title = new JPanel();
		panel_cardbox_vocabulary.add(panel_cardbox_vocabulary_title);
		panel_cardbox_vocabulary_title.setLayout(new BoxLayout(panel_cardbox_vocabulary_title, BoxLayout.Y_AXIS));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_cardbox_vocabulary_title.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 8, 0, 0));

		JLabel lblNewLabel = new JLabel(InfoProperty.getInfo(InfoProperty.Id));
		lblNewLabel.setToolTipText("id");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_4 = new JLabel(InfoProperty.getInfo(InfoProperty.Name));
		lblNewLabel_4.setToolTipText("name");
		lblNewLabel_4.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_4);

		JLabel lblNewLabel_1 = new JLabel(InfoProperty.getInfo(InfoProperty.Quantity));
		lblNewLabel_1.setToolTipText("quantity");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_7_1 = new JLabel(InfoProperty.getInfo(InfoProperty.Test_Time));
		lblNewLabel_7_1.setToolTipText("test time");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_7_1);

		JLabel lblNewLabel_7_1_1 = new JLabel(InfoProperty.getInfo(InfoProperty.Test_Date));
		lblNewLabel_7_1_1.setToolTipText("test date");
		lblNewLabel_7_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_7_1_1);

		JLabel lblNewLabel_6 = new JLabel(InfoProperty.getInfo(InfoProperty.Create_Date));
		lblNewLabel_6.setToolTipText("create date");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel(InfoProperty.getInfo(InfoProperty.Update_Date));
		lblNewLabel_7.setToolTipText("updata date");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_1.add(lblNewLabel_7);

		JLabel lblNewLabel_2 = new JLabel("id");
		info.add(lblNewLabel_2, ShowRowInfo.ID);
		lblNewLabel_2.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("name");
		info.add(lblNewLabel_3, ShowRowInfo.Name);
		lblNewLabel_3.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("quantity");
		info.add(lblNewLabel_5, ShowRowInfo.CardBox_Vocabulary_Quantity);
		lblNewLabel_5.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_5);

		JLabel lblNewLabel_8_1 = new JLabel("test time");
		info.add(lblNewLabel_8_1, ShowRowInfo.Test_time);
		lblNewLabel_8_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_8_1);

		JLabel lblNewLabel_8_2 = new JLabel("test date");
		info.add(lblNewLabel_8_2, ShowRowInfo.Test_date);
		lblNewLabel_8_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8_2.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_8_2);

		JLabel lblNewLabel_8 = new JLabel("create date");
		info.add(lblNewLabel_8, ShowRowInfo.Create_date);
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("update date");
		info.add(lblNewLabel_9, ShowRowInfo.Update_date);
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_9);

		JPanel panel_addandeditname = new JPanel();
		panel_addandeditname.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_cardbox_vocabulary_title.add(panel_addandeditname);
		panel_addandeditname.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton btnNewButton_9 = new JButton(InfoProperty.getInfo(InfoProperty.Create));
		btnNewButton_9.setPreferredSize(new Dimension(50, 28));
		btnNewButton_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				vocabularyShowRowControl.resetFromIdx();
				vocabularyShowRowControl.setEventResultIdx(-1);
				vocabularyShowRowControl.showRow();
				((CardLayout) panel_cardbox_vocabulary_editbar.getLayout()).show(panel_cardbox_vocabulary_editbar,
						CardLayout_Editbar_Add);
				List<Vocabulary> list = new VocabularyDao().queryExceptHadBoxID();
				((MainView) vocabularyShowRowControl.getEventJFrame()).getVocabularyShowRowControl().setResults(list);
				((MainView) vocabularyShowRowControl.getEventJFrame()).getVocabularyShowRowControl().showRow();
				Map<String, String> map = new HashMap<>();
				map.put(ShowRowInfo.Cardbox_Editbar_add_lock, Lock);
				cardboxShowRowControl.showInfo(map, ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Add);
				/*
				 * 關閉Editbar EditPanel
				 */
				JPanel editpanel = (JPanel) cardboxShowRowControl
						.getInfo(ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Edit)
						.getComponent(ShowRowInfo.EditbarEditPanel);
				JPanel fieldpanel = (JPanel) cardboxShowRowControl
						.getInfo(ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Edit)
						.getComponent(ShowRowInfo.EditbarEditPanel_Field);
				if (fieldpanel.isVisible()) {
					editpanel.setBorder(BorderFactory.createEmptyBorder());
					fieldpanel.setVisible(false);
				}
				vocabularyShowRowControl.getEventResultMap().clear();
			}
		});

		JButton btnNewButton_16 = new JButton("Explanation");
		btnNewButton_16.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_16.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				explantationFrame.setVisible(true);
			}
		});
		btnNewButton_16.setBackground(SystemColor.controlHighlight);
		btnNewButton_16.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_addandeditname.add(btnNewButton_16);

		JPanel panel_3 = new JPanel();
		panel_addandeditname.add(panel_3);
		btnNewButton_9.setToolTipText("add words from list for this card-box");
		btnNewButton_9.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_9.setBackground(SystemColor.controlHighlight);
		btnNewButton_9.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_addandeditname.add(btnNewButton_9);

		JPanel panel_cardbox_vocabulary_editbar_editpanel = new JPanel();
		info_cardbox_vocabulary_editbar_edit.add(panel_cardbox_vocabulary_editbar_editpanel,
				ShowRowInfo.EditbarEditPanel);
		panel_addandeditname.add(panel_cardbox_vocabulary_editbar_editpanel);
		JButton btnNewButton_10 = new JButton("Edit");
		btnNewButton_10.setPreferredSize(new Dimension(50, 28));
		btnNewButton_10.setToolTipText("edit this card-box name");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int eventRowIdx = ShowRowControl.getEventRowIdx(cardboxShowRowControl.getEventResultIdx(),
						cardboxShowRowControl.getFromIdx());
				CardBoxRow eventP = (CardBoxRow) panel_cardbox.getComponent(eventRowIdx + 1);// 取得事件panel
				eventP.getMouseListeners()[0].mousePressed(eventP.getLastMouseEvent());// 執行click
				JPanel editpanel = (JPanel) cardboxShowRowControl
						.getInfo(ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Edit)
						.getComponent(ShowRowInfo.EditbarEditPanel);
				JPanel fieldpanel = (JPanel) cardboxShowRowControl
						.getInfo(ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Edit)
						.getComponent(ShowRowInfo.EditbarEditPanel_Field);
				if (fieldpanel.isVisible()) {
					editpanel.setBorder(BorderFactory.createEmptyBorder());
					fieldpanel.setVisible(false);
					Map<String, String> map = new HashMap<>();
					map.put(ShowRowInfo.Cardbox_Editbar_add_lock, Unlock);
					cardboxShowRowControl.showInfo(map, ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Add);
				} else {
					editpanel.setBorder(BorderFactory.createEtchedBorder());
					fieldpanel.setVisible(true);
					JTextField f = (JTextField) cardboxShowRowControl
							.getInfo(ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Edit)
							.getComponent(ShowRowInfo.Name);
					f.setText(cardboxShowRowControl.getEventReault().getName());
					JCheckBox chckbxNewCheckBox_finish = (JCheckBox) cardboxShowRowControl
							.getInfo(ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Edit)
							.getComponent(ShowRowInfo.IsFinish);
					boolean checked = cardboxShowRowControl.getEventReault().isFinish();
					chckbxNewCheckBox_finish.setEnabled(!checked);
					chckbxNewCheckBox_finish.setSelected(cardboxShowRowControl.getEventReault().isFinish());

					((CardLayout) ((MainView) cardboxShowRowControl.getEventJFrame())
							.getPanel_cardbox_vocabulary_editbar().getLayout())
									.show(((MainView) cardboxShowRowControl.getEventJFrame())
											.getPanel_cardbox_vocabulary_editbar(), MainView.CardLayout_Editbar_Serch);
					vocabularyShowRowControl.setEventResultIdx(-1);
					vocabularyShowRowControl.showRow();
				}
			}
		});
		btnNewButton_10.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_10.setBackground(SystemColor.controlHighlight);
		btnNewButton_10.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_cardbox_vocabulary_editbar_editpanel.add(btnNewButton_10);

		JPanel panel_cardbox_vocabulary_editbar_editpfeild = new JPanel();
		info_cardbox_vocabulary_editbar_edit.add(panel_cardbox_vocabulary_editbar_editpfeild,
				ShowRowInfo.EditbarEditPanel_Field);
		panel_cardbox_vocabulary_editbar_editpfeild.setVisible(false);
		panel_cardbox_vocabulary_editbar_editpanel.add(panel_cardbox_vocabulary_editbar_editpfeild);

		JTextField textField = new JTextField();
		info_cardbox_vocabulary_editbar_edit.add(textField, ShowRowInfo.Name);
		textField.setFont(new Font("新細明體", Font.PLAIN, 18));
		textField.setColumns(10);
		panel_cardbox_vocabulary_editbar_editpfeild.add(textField);

		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setToolTipText("commit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel editpanel = (JPanel) cardboxShowRowControl
						.getInfo(ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Edit)
						.getComponent(ShowRowInfo.EditbarEditPanel);
				JPanel fieldpanel = (JPanel) cardboxShowRowControl
						.getInfo(ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Edit)
						.getComponent(ShowRowInfo.EditbarEditPanel_Field);
				editpanel.setBorder(BorderFactory.createEmptyBorder());
				fieldpanel.setVisible(false);
				/*
				 * 更新日期
				 */
				JTextField f = (JTextField) cardboxShowRowControl
						.getInfo(ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Edit).getComponent(ShowRowInfo.Name);
				CardBox b = cardboxShowRowControl.getEventReault();
				b.setName(f.getText());
				JCheckBox chckbxNewCheckBox_finish = (JCheckBox) cardboxShowRowControl
						.getInfo(ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Edit)
						.getComponent(ShowRowInfo.IsFinish);
				if (chckbxNewCheckBox_finish.isSelected()) {
					b.finish();
				}

				new CardBoxDao().update(b, b.getId());
				/*
				 * 重繪狀態
				 */
				int eventRowIdx = ShowRowControl.getEventRowIdx(cardboxShowRowControl.getEventResultIdx(),
						cardboxShowRowControl.getFromIdx());
				CardBoxRow eventP = (CardBoxRow) panel_cardbox.getComponent(eventRowIdx + 1);// 取得事件panel
				eventP.getMouseListeners()[0].mousePressed(eventP.getLastMouseEvent());// 執行click
				Map<String, String> map = new HashMap<>();
				map.put(ShowRowInfo.Cardbox_Editbar_add_lock, Unlock);
				cardboxShowRowControl.showInfo(map, ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Add);
			}
		});

		JCheckBox chckbxNewCheckBox_finish = new JCheckBox("Finish");
		chckbxNewCheckBox_finish.setBorderPaintedFlat(true);
		chckbxNewCheckBox_finish.setBorderPainted(true);
		chckbxNewCheckBox_finish.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		info_cardbox_vocabulary_editbar_edit.add(chckbxNewCheckBox_finish, ShowRowInfo.IsFinish);
		chckbxNewCheckBox_finish.setFont(new Font("新細明體", Font.PLAIN, 14));
		panel_cardbox_vocabulary_editbar_editpfeild.add(chckbxNewCheckBox_finish);
		btnNewButton_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnNewButton_1.setBackground(SystemColor.controlHighlight);
		panel_cardbox_vocabulary_editbar_editpfeild.add(btnNewButton_1);

		JButton btnNewButton = new JButton("Delete This Box");
		btnNewButton.setToolTipText("delete this card-box");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] options = { "Delete", "Cancel" };
				int r = JOptionPane.showOptionDialog(null,
						String.format("Delete \"%s\" ?", cardboxShowRowControl.getEventReault().getName()),
						"Delete Date", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, "Delete");
				if (r == JOptionPane.NO_OPTION) {
					return;
				}

				int id = cardboxShowRowControl.getEventReault().getId();
				new CardBoxDao().delete(id);
				new VocabularyDao().updateClearBoxID(id);
				btnNewButton_topbar_cardbox.doClick();
			}
		});

		JButton btnNewButton_17 = new JButton("Reset");
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(thisApp, "重設測驗記錄將無法回復檔案資料\n提示:可先儲存備份檔案,以便回復檔案", "重設測驗記錄",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				if(result==JOptionPane.YES_OPTION) {
					CardBox b = cardboxShowRowControl.getEventReault();
					b.resetTest();
					new CardBoxDao().update(b, b.getId());
				}

				/*
				 * 簡易重繪UI
				 */
				JPanel editpanel = (JPanel) cardboxShowRowControl
						.getInfo(ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Edit)
						.getComponent(ShowRowInfo.EditbarEditPanel);
				JPanel fieldpanel = (JPanel) cardboxShowRowControl
						.getInfo(ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Edit)
						.getComponent(ShowRowInfo.EditbarEditPanel_Field);
				editpanel.setBorder(BorderFactory.createEmptyBorder());
				fieldpanel.setVisible(false);

			}
		});
		btnNewButton_17.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_17.setBackground(SystemColor.controlHighlight);
		btnNewButton_17.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_addandeditname.add(btnNewButton_17);

		JPanel panel_2 = new JPanel();
		panel_addandeditname.add(panel_2);
		btnNewButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_addandeditname.add(btnNewButton);

		panel_cardbox_vocabulary_editbar = new JPanel();
		panel_cardbox_vocabulary_title.add(panel_cardbox_vocabulary_editbar);
		panel_cardbox_vocabulary_editbar.setLayout(new CardLayout(0, 0));

		JPanel panel_serch = new JPanel();
		panel_serch.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_cardbox_vocabulary_editbar.add(panel_serch, CardLayout_Editbar_Serch);

		JLabel lblNewLabel_13 = new JLabel("serch");
		lblNewLabel_13.setVisible(false);
		lblNewLabel_13.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_serch.add(lblNewLabel_13);

		textField_7 = new JTextField();
		textField_7.setVisible(false);
		textField_7.setFont(new Font("新細明體", Font.PLAIN, 18));
		textField_7.setColumns(10);
		panel_serch.add(textField_7);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setVisible(false);
		comboBox.setFont(new Font("新細明體", Font.PLAIN, 16));
		comboBox.setBackground(SystemColor.menu);
		panel_serch.add(comboBox);

		JPanel panel_add = new JPanel();
		info_cardbox_vocabulary_editbar_add.add(panel_add, ShowRowInfo.Cardbox_Editbar_add_lock);
		panel_cardbox_vocabulary_editbar.add(panel_add, CardLayout_Editbar_Add);
		panel_add.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel_15 = new JLabel("Serch");
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_15.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_add.add(lblNewLabel_15);

		textField_10 = new JTextField();
		textField_10.setFont(new Font("新細明體", Font.PLAIN, 16));
		textField_10.setColumns(4);
		panel_add.add(textField_10);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBackground(SystemColor.controlHighlight);
		panel_add.add(comboBox_1);

		JPanel panel = new JPanel();
		panel_add.add(panel);

		JButton btnNewButton_14 = new JButton("Add");
		btnNewButton_14.setToolTipText("commit");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cardbox_id = cardboxShowRowControl.getEventReault().getId();
				List<Vocabulary> v = vocabularyShowRowControl.getEventResultMap().values().stream()
						.collect(Collectors.toList());
				VocabularyDao vDao = new VocabularyDao();
				v.forEach(x -> {
					x.setBox_id(cardbox_id);
					vDao.update(x, x.getId());
				});
				Map<String, String> map = new HashMap<>();
				map.put(ShowRowInfo.Cardbox_Editbar_add_lock, Unlock);
				cardboxShowRowControl.showInfo(map, ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Add);
				vocabularyShowRowControl.getEventResultMap().clear();
				/*
				 * 更新日期
				 */
				JTextField f = (JTextField) cardboxShowRowControl
						.getInfo(ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Edit).getComponent(ShowRowInfo.Name);
				CardBox b = cardboxShowRowControl.getEventReault();
				// b.setName(f.getText());
				new CardBoxDao().update(b, b.getId());// 更新日期
				/*
				 * 重繪新的狀態
				 */
				int eventRowIdx = ShowRowControl.getEventRowIdx(cardboxShowRowControl.getEventResultIdx(),
						cardboxShowRowControl.getFromIdx());
				CardBoxRow eventP = (CardBoxRow) panel_cardbox.getComponent(eventRowIdx + 1);// 取得事件panel
				eventP.getMouseListeners()[0].mousePressed(eventP.getLastMouseEvent());// 執行click
			}
		});
		btnNewButton_14.setFont(new Font("新細明體", Font.PLAIN, 14));
		btnNewButton_14.setBackground(SystemColor.controlHighlight);
		panel_add.add(btnNewButton_14);

		JPanel panel_5 = new JPanel();
		panel_add.add(panel_5);

		JButton btnNewButton_15 = new JButton("取消");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String, String> map = new HashMap<>();
				map.put(ShowRowInfo.Translation, "");
				map.put(ShowRowInfo.BoxId, "");
				map.put(ShowRowInfo.Vocabulary, "");
				vocabularyShowRowControl.showInfo(map, ShowRowInfo.InfoName_CardBox_Vocabulary_Editbar_Add);
				((CardLayout) panel_cardbox_vocabulary_editbar.getLayout()).show(panel_cardbox_vocabulary_editbar,
						CardLayout_Editbar_Serch);
				/*
				 * 重繪狀態
				 */
				int eventRowIdx = ShowRowControl.getEventRowIdx(cardboxShowRowControl.getEventResultIdx(),
						cardboxShowRowControl.getFromIdx());
				CardBoxRow eventP = (CardBoxRow) panel_cardbox.getComponent(eventRowIdx + 1);// 取得事件panel
				eventP.getMouseListeners()[0].mousePressed(eventP.getLastMouseEvent());// 執行click
			}
		});
		btnNewButton_15.setFont(new Font("新細明體", Font.PLAIN, 14));
		btnNewButton_15.setBackground(SystemColor.controlHighlight);
		panel_add.add(btnNewButton_15);

		JPanel panel_edit = new JPanel();
		panel_cardbox_vocabulary_editbar.add(panel_edit, CardLayout_Editbar_edit);
		panel_edit.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton btnNewButton_14_1 = new JButton("Remove");
		btnNewButton_14_1.setToolTipText("remove selected word");
		btnNewButton_14_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int eventRowIdx = ShowRowControl.getEventRowIdx(cardboxShowRowControl.getEventResultIdx(),
						cardboxShowRowControl.getFromIdx());
				CardBoxRow eventP = (CardBoxRow) panel_cardbox.getComponent(eventRowIdx + 1);// 取得事件panel
				Vocabulary v = vocabularyShowRowControl.getEventReault();
				v.setBox_id(-1);
				new VocabularyDao().update(v, v.getId());
				CardBox c = cardboxShowRowControl.getEventReault();
				new CardBoxDao().update(c, c.getId());// 更新日期
				// btnNewButton_topbar_cardbox.doClick();
				eventP.getMouseListeners()[0].mousePressed(eventP.getLastMouseEvent());// 執行click
			}
		});
		btnNewButton_14_1.setFont(new Font("新細明體", Font.PLAIN, 14));
		btnNewButton_14_1.setBackground(SystemColor.controlHighlight);
		panel_edit.add(btnNewButton_14_1);

		JPanel panel_5_1 = new JPanel();
		panel_edit.add(panel_5_1);

		JButton btnNewButton_15_1 = new JButton("取消");
		btnNewButton_15_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vocabularyShowRowControl.setEventResultIdx(-1);
				vocabularyShowRowControl.showRow();
				((CardLayout) panel_cardbox_vocabulary_editbar.getLayout()).show(panel_cardbox_vocabulary_editbar,
						CardLayout_Editbar_Serch);
			}
		});
		btnNewButton_15_1.setFont(new Font("新細明體", Font.PLAIN, 14));
		btnNewButton_15_1.setBackground(SystemColor.controlHighlight);
		panel_edit.add(btnNewButton_15_1);

		JPanel panel_title = new JPanel();
		panel_cardbox_vocabulary_title.add(panel_title);
		panel_title.setLayout(new GridLayout(0, 6, 0, 0));

		JButton btnNewButton_3_1_1 = new JButton("ID");
		btnNewButton_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(VocabulrySorterType.Id), btnNewButton_3_1_1, e, vocabularyShowRowControl);
				vocabularyShowRowControl.resetEventResultMap();
				vocabularyShowRowControl.showRow();
			}
		});
		btnNewButton_3_1_1.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		btnNewButton_3_1_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_3_1_1);

		JButton btnNewButton_6_1_1 = new JButton("Vocabulary");
		btnNewButton_6_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(VocabulrySorterType.Vocabulary), btnNewButton_6_1_1, e,
						vocabularyShowRowControl);
				vocabularyShowRowControl.resetEventResultMap();
				vocabularyShowRowControl.showRow();
			}
		});
		btnNewButton_6_1_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnNewButton_6_1_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_6_1_1);

		JButton btnNewButton_4_1_1 = new JButton("Translation");
		btnNewButton_4_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(VocabulrySorterType.Translation), btnNewButton_4_1_1, e,
						vocabularyShowRowControl);
				vocabularyShowRowControl.resetEventResultMap();
				vocabularyShowRowControl.showRow();
			}
		});
		btnNewButton_4_1_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnNewButton_4_1_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_4_1_1);

		JButton btnNewButton_4_1_1_1 = new JButton("Box Id");
		btnNewButton_4_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(VocabulrySorterType.BoxId), btnNewButton_4_1_1_1, e,
						vocabularyShowRowControl);
				vocabularyShowRowControl.resetEventResultMap();
				vocabularyShowRowControl.showRow();
			}
		});
		btnNewButton_4_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnNewButton_4_1_1_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_4_1_1_1);

		JButton btnNewButton_5_1_1_1 = new JButton("Create Date");
		btnNewButton_5_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(VocabulrySorterType.CreateDate), btnNewButton_5_1_1_1, e,
						vocabularyShowRowControl);
				vocabularyShowRowControl.resetEventResultMap();
				vocabularyShowRowControl.showRow();
			}
		});
		btnNewButton_5_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnNewButton_5_1_1_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_5_1_1_1);

		JButton btnNewButton_7_1_1 = new JButton("Update Date");
		btnNewButton_7_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(VocabulrySorterType.UpdateDate), btnNewButton_7_1_1, e,
						vocabularyShowRowControl);
				vocabularyShowRowControl.resetEventResultMap();
				vocabularyShowRowControl.showRow();
			}
		});
		btnNewButton_7_1_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnNewButton_7_1_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_7_1_1);

		/*
		 * �撱� vocabulary row
		 */
		for (int i = 0; i < 18; i++) {
			VocabularyRow vocabularyRow = new VocabularyRow();
			panel_cardbox_vocabulary.add(vocabularyRow);
			vocabularyRow.setName("" + i);
			vocabularyRow.setShowRowControl(vocabularyShowRowControl);
			this.vocabularyShowRowControl.add(vocabularyRow);
		}
	}

	private void createTestPanel() {
		JPanel panel_test = new JPanel();
		panel_main_centerbar.add(panel_test, CardLayout_topbar_Test);
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
		panel.setVisible(false);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		panel_center.add(panel);

		JButton btnNewButton_11 = new JButton("New button");
		btnNewButton_11.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		btnNewButton_11.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_11.setBackground(SystemColor.controlHighlight);
		panel.add(btnNewButton_11);

		JButton btnNewButton_12 = new JButton("New button");
		btnNewButton_12.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		btnNewButton_12.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_12.setBackground(SystemColor.controlHighlight);
		panel.add(btnNewButton_12);

		JButton btnNewButton_13 = new JButton("New button");
		btnNewButton_13.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		btnNewButton_13.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_13.setBackground(SystemColor.controlHighlight);
		panel.add(btnNewButton_13);

		JPanel panel_bottom = new JPanel();
		panel_info.add(panel_bottom);

		panel_test_center_cardlayout = new JPanel();
		panel_test.add(panel_test_center_cardlayout, BorderLayout.CENTER);
		panel_test_center_cardlayout.setLayout(new CardLayout(0, 0));

		JPanel panel_test_list = new JPanel();
		panel_test_center_cardlayout.add(panel_test_list, CardLayout_Test_List);
		panel_test_list.setLayout(new GridLayout(4, 3, 0, 0));

		JPanel panel_question = new JPanel();
		panel_test_center_cardlayout.add(panel_question, CardLayout_Test_Question);
		panel_question.setLayout(new GridLayout(0, 3, 0, 0));

		/*
		 * �test row
		 */
		for (int i = 0; i < 12; i++) {
			TestRow TestRow = new TestRow();
			panel_test_list.add(TestRow);
			TestRow.setName("" + i);
			TestRow.setShowRowControl(cardboxShowRowControl);
			this.cardboxShowRowControl.add(TestRow);
		}

		/*
		 * �test question, 更改此項需一併修改TestRow 的 myClickListerner 的 ((MainView)
		 * showRowControl.getEventJFrame()).getTestQuestionControl().init(4, 15) 初始化, 以及
		 * TestQuestion 相關參數
		 */
		for (int i = 0; i < 12; i++) {
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
				lbl.setText(lbl.getName() + ":" + m.get(ShowRowInfo.CardBox_Quantity));
			}
		};
		ShowRowInfo info_cardbox_editbar_add = new ShowRowInfo(ShowRowInfo.InfoName_CardBox_Editbar_Add) {
			@Override
			public void showInfo(Map<String, String> m) {
				((JTextField) this.comps.get(ShowRowInfo.Name)).setText(m.get(ShowRowInfo.Name));
			}
		};
		this.cardboxShowRowControl.setInfo(info);
		this.cardboxShowRowControl.setInfo(info_cardbox_editbar_add);

		panel_cardbox = new JPanel();
		panel_main_centerbar.add(panel_cardbox, CardLayout_topbar_CardBox);
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
		lblNewLabel_11.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_11);

		JButton btnNewButton_8 = new JButton(InfoProperty.getInfo(InfoProperty.Create));
		btnNewButton_8.setToolTipText("create new card-box");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) panel_cardbox_editbar.getLayout()).show(panel_cardbox_editbar, CardLayout_Editbar_Add);
			}
		});
		btnNewButton_8.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_8.setBackground(SystemColor.controlHighlight);
		btnNewButton_8.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		panel_1.add(btnNewButton_8);

		JLabel lblNewLabel_10 = new JLabel(InfoProperty.getInfo(InfoProperty.Quantity));
		lblNewLabel_10.setName(InfoProperty.getInfo(InfoProperty.Quantity));
		info.add(lblNewLabel_10, ShowRowInfo.CardBox_Quantity);
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("�蝝唳���", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_10);

		JPanel panel_2 = new JPanel();
		panel_cardbox_title.add(panel_2);

		panel_cardbox_editbar = new JPanel();
		panel_cardbox_title.add(panel_cardbox_editbar);
		panel_cardbox_editbar.setLayout(new CardLayout(0, 0));

		JPanel panel_add = new JPanel();
		panel_cardbox_editbar.add(panel_add, CardLayout_Editbar_Add);
		panel_add.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel_15 = new JLabel("Box Name");
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_15.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_add.add(lblNewLabel_15);

		JTextField textField = new JTextField();
		info_cardbox_editbar_add.add(textField, ShowRowInfo.Name);
		textField.setFont(new Font("新細明體", Font.PLAIN, 16));
		textField.setColumns(10);
		panel_add.add(textField);

		JPanel panel_3_1 = new JPanel();
		panel_add.add(panel_3_1);

		JButton btnNewButton_14 = new JButton("Add");
		btnNewButton_14.setToolTipText("commit");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardBox c = new CardBox();
				JTextField t = (JTextField) cardboxShowRowControl.getInfo(ShowRowInfo.InfoName_CardBox_Editbar_Add)
						.getComponent(ShowRowInfo.Name);
				c.setName(t.getText());
				new CardBoxDao().add(c);
				btnNewButton_topbar_cardbox.doClick();
				t.setText("");
			}
		});

		btnNewButton_14.setFont(new Font("新細明體", Font.PLAIN, 14));
		btnNewButton_14.setBackground(SystemColor.controlHighlight);
		panel_add.add(btnNewButton_14);

		JPanel panel_5 = new JPanel();
		panel_add.add(panel_5);

		JButton btnNewButton_15 = new JButton("取消");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) panel_cardbox_editbar.getLayout()).show(panel_cardbox_editbar, CardLayout_Editbar_Serch);
			}
		});
		btnNewButton_15.setFont(new Font("新細明體", Font.PLAIN, 14));
		btnNewButton_15.setBackground(SystemColor.controlHighlight);
		panel_add.add(btnNewButton_15);

		JPanel panel_serch = new JPanel();
		panel_serch.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_cardbox_editbar.add(panel_serch, CardLayout_Editbar_Serch);

		JLabel lblNewLabel_13 = new JLabel("serch");
		lblNewLabel_13.setVisible(false);
		lblNewLabel_13.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_serch.add(lblNewLabel_13);

		textField_14 = new JTextField();
		textField_14.setVisible(false);
		textField_14.setFont(new Font("新細明體", Font.PLAIN, 18));
		textField_14.setColumns(10);
		panel_serch.add(textField_14);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setVisible(false);
		comboBox.setFont(new Font("新細明體", Font.PLAIN, 16));
		comboBox.setBackground(SystemColor.menu);
		panel_serch.add(comboBox);

		JPanel panel_title = new JPanel();
		panel_cardbox_title.add(panel_title);
		panel_title.setLayout(new GridLayout(1, 0, 0, 0));
		JButton btnNewButton_3 = new JButton("ID");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(CardBoxSorterType.Id), btnNewButton_3, e, cardboxShowRowControl);
				cardboxShowRowControl.showRow();
			}
		});
		btnNewButton_3.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnNewButton_3.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_3);

		JButton btnNewButton_6 = new JButton("Name");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(CardBoxSorterType.Name), btnNewButton_6, e, cardboxShowRowControl);
				cardboxShowRowControl.showRow();
			}
		});
		btnNewButton_6.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnNewButton_6.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_6);

		JButton btnNewButton_4 = new JButton("Quantity");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(CardBoxSorterType.Quantity), btnNewButton_4, e, cardboxShowRowControl);
				cardboxShowRowControl.showRow();
			}
		});
		btnNewButton_4.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnNewButton_4.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_4);

		JButton btnNewButton_4_1 = new JButton("<html>Test<br/>Times</html>");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(CardBoxSorterType.TestTimes), btnNewButton_4_1, e, cardboxShowRowControl);
				cardboxShowRowControl.showRow();
			}
		});
		btnNewButton_4_1.setToolTipText("Test Times");
		btnNewButton_4_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnNewButton_4_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_4_1);

		JButton btnNewButton_4_2 = new JButton("<html>Test<br/>Date</html>");
		btnNewButton_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(CardBoxSorterType.TestDate), btnNewButton_4_2, e, cardboxShowRowControl);
				cardboxShowRowControl.showRow();
			}
		});
		btnNewButton_4_2.setToolTipText("Last Test Date");
		btnNewButton_4_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnNewButton_4_2.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_4_2);

		JButton btnNewButton_4_2_1 = new JButton("<html>Next<br/>Test</html>");
		btnNewButton_4_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(CardBoxSorterType.NestTest), btnNewButton_4_2_1, e, cardboxShowRowControl);
				cardboxShowRowControl.showRow();
			}
		});
		btnNewButton_4_2_1.setToolTipText("Last Test Date");
		btnNewButton_4_2_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnNewButton_4_2_1.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_4_2_1);

		JButton btnNewButton_5 = new JButton("<html>Create<br/>Date</html>");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(CardBoxSorterType.CreateDate), btnNewButton_5, e, cardboxShowRowControl);
				cardboxShowRowControl.showRow();
			}
		});
		btnNewButton_5.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnNewButton_5.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_5);

		JButton btnNewButton_7 = new JButton("<html>Update<br/>Date</html>");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sort(SorterFactory.getSorter(CardBoxSorterType.UpdateDate), btnNewButton_7, e, cardboxShowRowControl);
				cardboxShowRowControl.showRow();
			}
		});
		btnNewButton_7.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnNewButton_7.setBackground(SystemColor.controlHighlight);
		panel_title.add(btnNewButton_7);

		/*
		 * �撱� cardbox row
		 */
		for (int i = 0; i < 18; i++) {
			CardBoxRow cardBoxRow = new CardBoxRow();
			panel_cardbox.add(cardBoxRow);
			cardBoxRow.setName("" + i);
			cardBoxRow.setShowRowControl(cardboxShowRowControl);
			this.cardboxShowRowControl.add(cardBoxRow);
		}
	}

	public JPanel getPanel_main_centerbar() {
		return panel_main_centerbar;
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

	public JPanel getPanel_vocabulary_editbar() {
		return panel_vocabulary_editbar;
	}

	public JPanel getPanel_cardbox_vocabulary_editbar() {
		return panel_cardbox_vocabulary_editbar;
	}

	public ShowRowControl<CardBox> getCardboxShowRowControl() {
		return cardboxShowRowControl;
	}

	public JButton getBtnNewButton_topbar_test() {
		return btnNewButton_topbar_test;
	}

}

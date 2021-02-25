package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.control.dao.CardBoxDao;
import com.control.dao.VocabularyDao;
import com.model.CardBox;
import com.model.Vocabulary;
import com.view.control.ShowRowControl;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JPanel panel_centerbar;
	private JPanel panel_cardbox;
	private JPanel panel_vocabulary;
	private ShowRowControl<CardBox> cardboxShowRowControl = new ShowRowControl();
	private ShowRowControl<Vocabulary> vocabularyShowRowControl = new ShowRowControl();
	private JPanel panel_cardbox_vocabulary;

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
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
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
			btnNewButton.setBackground(SystemColor.controlHighlight);
			btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 18));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					((CardLayout) panel_centerbar.getLayout()).show(panel_centerbar, "cardbox");
					List<CardBox> list = new CardBoxDao().queryAll();
					cardboxShowRowControl.setResults(list);
					cardboxShowRowControl.showRow();
				}
			});

			JButton btnNewButton_2 = new JButton("New button");
			btnNewButton_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					((CardLayout) panel_centerbar.getLayout()).show(panel_centerbar, "cardboxvocabualry");
					List<Vocabulary> list = new VocabularyDao().queryAll();
					vocabularyShowRowControl.setResults(list);
					vocabularyShowRowControl.showRow();
				}
			});
			btnNewButton_2.setBackground(SystemColor.controlHighlight);
			btnNewButton_2.setFont(new Font("新細明體", Font.PLAIN, 18));
			JButton btnNewButton_1 = new JButton("詞彙");
			btnNewButton_1.setBackground(SystemColor.controlHighlight);
			btnNewButton_1.setFont(new Font("新細明體", Font.PLAIN, 18));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					((CardLayout) panel_centerbar.getLayout()).show(panel_centerbar, "vocabulary");
					List<Vocabulary> list = new VocabularyDao().queryAll();
					vocabularyShowRowControl.setResults(list);
					vocabularyShowRowControl.showRow();
				}
			});
			panel_topbar.add(btnNewButton_1);

			panel_topbar.add(btnNewButton);
			panel_topbar.add(btnNewButton_2);
		}

		panel_centerbar = new JPanel();
		contentPane.add(panel_centerbar, BorderLayout.CENTER);
		panel_centerbar.setLayout(new CardLayout(0, 0));

		/*
		 * panel start
		 */
		JPanel panel_start = new JPanel();
		panel_centerbar.add(panel_start, "start");

		createVocabularyPanel();
		createCardBoxPanel();
		createCardBoxVocabularyPanel();
	}

	private void createVocabularyPanel() {
		panel_vocabulary = new JPanel();
		panel_centerbar.add(panel_vocabulary, "vocabulary");
		panel_vocabulary.setLayout(new BoxLayout(panel_vocabulary, BoxLayout.Y_AXIS));

		JPanel panel_vocabulary_title_1 = new JPanel();
		panel_vocabulary.add(panel_vocabulary_title_1);
		panel_vocabulary_title_1.setLayout(new GridLayout(0, 6, 0, 0));

		JButton btnNewButton_3_1 = new JButton("詞彙ID");
		btnNewButton_3_1.setBackground(SystemColor.controlHighlight);
		btnNewButton_3_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_vocabulary_title_1.add(btnNewButton_3_1);

		JButton btnNewButton_6_1 = new JButton("英文單詞");
		btnNewButton_6_1.setBackground(SystemColor.controlHighlight);
		btnNewButton_6_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_vocabulary_title_1.add(btnNewButton_6_1);

		JButton btnNewButton_4_1 = new JButton("中文翻譯");
		btnNewButton_4_1.setBackground(SystemColor.controlHighlight);
		btnNewButton_4_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_vocabulary_title_1.add(btnNewButton_4_1);

		JButton btnNewButton_5_1 = new JButton("小盒連結");
		btnNewButton_5_1.setBackground(SystemColor.controlHighlight);
		btnNewButton_5_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_vocabulary_title_1.add(btnNewButton_5_1);

		JButton btnNewButton_5_1_1 = new JButton("建立時間");
		btnNewButton_5_1_1.setBackground(SystemColor.controlHighlight);
		btnNewButton_5_1_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_vocabulary_title_1.add(btnNewButton_5_1_1);

		JButton btnNewButton_7_1 = new JButton("更新時間");
		btnNewButton_7_1.setBackground(SystemColor.controlHighlight);
		btnNewButton_7_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_vocabulary_title_1.add(btnNewButton_7_1);

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
		panel_cardbox_vocabulary = new JPanel();
		panel_centerbar.add(panel_cardbox_vocabulary, "cardboxvocabualry");
		panel_cardbox_vocabulary.setLayout(new BoxLayout(panel_cardbox_vocabulary, BoxLayout.Y_AXIS));

		JPanel panel_cardbox_vocabulary_title = new JPanel();
		panel_cardbox_vocabulary.add(panel_cardbox_vocabulary_title);
		panel_cardbox_vocabulary_title.setLayout(new BoxLayout(panel_cardbox_vocabulary_title, BoxLayout.Y_AXIS));

		JPanel panel_1 = new JPanel();
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
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("name");
		lblNewLabel_3.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("quantity");
		lblNewLabel_5.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_5);

		JLabel lblNewLabel_8 = new JLabel("create date");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("update date");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_9);

		JPanel panel = new JPanel();
		panel_cardbox_vocabulary_title.add(panel);
		panel.setLayout(new GridLayout(0, 6, 0, 0));

		JButton btnNewButton_3_1_1 = new JButton("詞彙ID");
		btnNewButton_3_1_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_3_1_1.setBackground(SystemColor.controlHighlight);
		panel.add(btnNewButton_3_1_1);

		JButton btnNewButton_6_1_1 = new JButton("英文單詞");
		btnNewButton_6_1_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_6_1_1.setBackground(SystemColor.controlHighlight);
		panel.add(btnNewButton_6_1_1);

		JButton btnNewButton_4_1_1 = new JButton("中文翻譯");
		btnNewButton_4_1_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_4_1_1.setBackground(SystemColor.controlHighlight);
		panel.add(btnNewButton_4_1_1);

		JButton btnNewButton_5_1_2 = new JButton("小盒連結");
		btnNewButton_5_1_2.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_5_1_2.setBackground(SystemColor.controlHighlight);
		panel.add(btnNewButton_5_1_2);

		JButton btnNewButton_5_1_1_1 = new JButton("建立時間");
		btnNewButton_5_1_1_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_5_1_1_1.setBackground(SystemColor.controlHighlight);
		panel.add(btnNewButton_5_1_1_1);

		JButton btnNewButton_7_1_1 = new JButton("更新時間");
		btnNewButton_7_1_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_7_1_1.setBackground(SystemColor.controlHighlight);
		panel.add(btnNewButton_7_1_1);
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

	private void createCardBoxPanel() {
		panel_cardbox = new JPanel();
		panel_centerbar.add(panel_cardbox, "cardbox");
		panel_cardbox.setLayout(new BoxLayout(panel_cardbox, BoxLayout.Y_AXIS));

		/*
		 * title
		 */
		JPanel panel_cardbox_title = new JPanel();
		panel_cardbox.add(panel_cardbox_title);
		panel_cardbox_title.setLayout(new GridLayout(0, 5, 0, 0));

		JButton btnNewButton_3 = new JButton("小盒ID");
		btnNewButton_3.setBackground(SystemColor.controlHighlight);
		btnNewButton_3.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_cardbox_title.add(btnNewButton_3);

		JButton btnNewButton_6 = new JButton("小盒名稱");
		btnNewButton_6.setBackground(SystemColor.controlHighlight);
		btnNewButton_6.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_cardbox_title.add(btnNewButton_6);

		JButton btnNewButton_4 = new JButton("卡片數量");
		btnNewButton_4.setBackground(SystemColor.controlHighlight);
		btnNewButton_4.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_cardbox_title.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("建立時間");
		btnNewButton_5.setBackground(SystemColor.controlHighlight);
		btnNewButton_5.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_cardbox_title.add(btnNewButton_5);

		JButton btnNewButton_7 = new JButton("更新時間");
		btnNewButton_7.setBackground(SystemColor.controlHighlight);
		btnNewButton_7.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_cardbox_title.add(btnNewButton_7);

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

}

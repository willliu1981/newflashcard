package com.control.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
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

public class MainView extends JFrame {

	private JPanel contentPane;
	private JPanel panel_cardlayout;

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
		setBounds(100, 100, 720, 544);

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

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		JButton btnNewButton = new JButton("卡片小盒");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((CardLayout) panel_cardlayout.getLayout()).show(panel_cardlayout, "cardbox");
			}
		});

		JButton btnNewButton_1 = new JButton("詞彙");
		panel.add(btnNewButton_1);
		panel.add(btnNewButton);

		JButton btnNewButton_2 = new JButton("New button");
		panel.add(btnNewButton_2);

		panel_cardlayout = new JPanel();
		contentPane.add(panel_cardlayout, BorderLayout.CENTER);
		panel_cardlayout.setLayout(new CardLayout(0, 0));

		JPanel panel_vocabulary = new JPanel();
		panel_cardlayout.add(panel_vocabulary, "vocabulary");

		/*
		 * panel_cardbox
		 */
		{
			JPanel panel_cardbox = new JPanel();
			panel_cardlayout.add(panel_cardbox, "cardbox");
			panel_cardbox.setLayout(new BoxLayout(panel_cardbox, BoxLayout.Y_AXIS));

			/*
			 * panel_cardbox
			 * 創建row
			 */
			{
				JPanel panel_cardbox_title = new JPanel();
				panel_cardbox.add(panel_cardbox_title);
				panel_cardbox_title.setLayout(new GridLayout(0, 5, 0, 0));

				for (int i = 0; i < 10; i++) {
					CardBoxRow cardBoxRow = new CardBoxRow();
					panel_cardbox.add(cardBoxRow);
					cardBoxRow.setName("" + i);
				}

				JButton btnNewButton_3 = new JButton("小盒ID");
				btnNewButton_3.setFont(new Font("新細明體", Font.PLAIN, 18));
				panel_cardbox_title.add(btnNewButton_3);

				JButton btnNewButton_6 = new JButton("小盒名稱");
				panel_cardbox_title.add(btnNewButton_6);

				JButton btnNewButton_4 = new JButton("卡片數量");
				btnNewButton_4.setFont(new Font("新細明體", Font.PLAIN, 18));
				panel_cardbox_title.add(btnNewButton_4);

				JButton btnNewButton_5 = new JButton("建立時間");
				btnNewButton_5.setFont(new Font("新細明體", Font.PLAIN, 18));
				panel_cardbox_title.add(btnNewButton_5);

				JButton btnNewButton_7 = new JButton("更新時間");
				panel_cardbox_title.add(btnNewButton_7);
			}
		}

		JLabel lblNewLabel_1 = new JLabel("T2");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_vocabulary.add(lblNewLabel_1);
	}

}

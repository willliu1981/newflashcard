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

public class MainView extends JFrame {

	private JPanel contentPane;
	private JPanel panel_cardlayout;
	private JPanel panel_cardbox;
	private JPanel panel_vocabulary;

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
				((CardLayout) panel_cardlayout.getLayout()).show(panel_cardlayout, "vocabulary");
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

		panel_cardbox = new JPanel();
		panel_cardlayout.add(panel_cardbox, "cardbox");

		JLabel lblNewLabel = new JLabel("t1");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_cardbox.add(lblNewLabel);

		panel_vocabulary = new JPanel();
		panel_cardlayout.add(panel_vocabulary, "vocabulary");

		JLabel lblNewLabel_1 = new JLabel("T2");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_vocabulary.add(lblNewLabel_1);
	}

}

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

		JPanel panel_cardbox = new JPanel();
		panel_cardlayout.add(panel_cardbox, "cardbox");
		panel_cardbox.setLayout(new BoxLayout(panel_cardbox, BoxLayout.Y_AXIS));
		
		JPanel panel_cardbox_title = new JPanel();
		panel_cardbox.add(panel_cardbox_title);
		panel_cardbox_title.setLayout(new BoxLayout(panel_cardbox_title, BoxLayout.X_AXIS));
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_cardbox_title.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_cardbox_title.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_cardbox_title.add(btnNewButton_5);
		
		JPanel panel_cardbox_row = new JPanel();
		panel_cardbox.add(panel_cardbox_row);
		GridBagLayout gbl_panel_cardbox_row = new GridBagLayout();
		gbl_panel_cardbox_row.columnWidths = new int[] {40, 120, 120, 150, 150, 0};
		gbl_panel_cardbox_row.rowHeights = new int[] {50, 0};
		gbl_panel_cardbox_row.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panel_cardbox_row.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_cardbox_row.setLayout(gbl_panel_cardbox_row);
		
		JLabel lblId = new JLabel("id");
		lblId.setFont(new Font("新細明體", Font.PLAIN, 18));
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.WEST;
		gbc_lblId.insets = new Insets(0, 0, 0, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 0;
		panel_cardbox_row.add(lblId, gbc_lblId);
		
		JLabel lblNewLabel_2_1 = new JLabel("name");
		lblNewLabel_2_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2_1.gridx = 1;
		gbc_lblNewLabel_2_1.gridy = 0;
		panel_cardbox_row.add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("count");
		lblNewLabel_2_1_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel_2_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2_1_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2_1_1.gridx = 2;
		gbc_lblNewLabel_2_1_1.gridy = 0;
		panel_cardbox_row.add(lblNewLabel_2_1_1, gbc_lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("create_date");
		lblNewLabel_2_1_2.setFont(new Font("新細明體", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel_2_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_2_1_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2_1_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2_1_2.gridx = 3;
		gbc_lblNewLabel_2_1_2.gridy = 0;
		panel_cardbox_row.add(lblNewLabel_2_1_2, gbc_lblNewLabel_2_1_2);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("update_date");
		lblNewLabel_2_1_1_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel_2_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2_1_1_1.gridx = 4;
		gbc_lblNewLabel_2_1_1_1.gridy = 0;
		panel_cardbox_row.add(lblNewLabel_2_1_1_1, gbc_lblNewLabel_2_1_1_1);

		JPanel panel_vocabulary = new JPanel();
		panel_cardlayout.add(panel_vocabulary, "vocabulary");

		JLabel lblNewLabel_1 = new JLabel("T2");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_vocabulary.add(lblNewLabel_1);
	}

}

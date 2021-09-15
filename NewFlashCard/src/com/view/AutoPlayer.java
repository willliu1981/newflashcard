package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import com.control.dao.CardBoxDao;
import com.control.dao.VocabularyDao;
import com.model.CardBox;
import com.model.Vocabulary;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.SystemColor;
import java.util.List;

public class AutoPlayer extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutoPlayer frame = new AutoPlayer();
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
	public AutoPlayer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1218, 859);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		ButtonGroup  radioPlayerGroup=new ButtonGroup ();
		JRadioButton rdbtnNewRadioButton = new JRadioButton("全部");
		rdbtnNewRadioButton.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_1.add(rdbtnNewRadioButton);
		radioPlayerGroup.add(rdbtnNewRadioButton);
		
		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("測驗中");
		rdbtnNewRadioButton_1.setSelected(true);
		rdbtnNewRadioButton_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_1.add(rdbtnNewRadioButton_1);
		radioPlayerGroup.add(rdbtnNewRadioButton_1);
		
		JPanel panel_10 = new JPanel();
		panel_1.add(panel_10);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("已完成");
		rdbtnNewRadioButton_2.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_1.add(rdbtnNewRadioButton_2);
		radioPlayerGroup.add(rdbtnNewRadioButton_2);
		
		JPanel panel_11 = new JPanel();
		panel_1.add(panel_11);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("已完成+測驗中");
		rdbtnNewRadioButton_3.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_1.add(rdbtnNewRadioButton_3);
		radioPlayerGroup.add(rdbtnNewRadioButton_3);
		
		JPanel panel_12 = new JPanel();
		panel_1.add(panel_12);
		
		JRadioButton rdbtnNewRadioButton_3_1 = new JRadioButton("未開始");
		rdbtnNewRadioButton_3_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_1.add(rdbtnNewRadioButton_3_1);
		radioPlayerGroup.add(rdbtnNewRadioButton_3_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(80, 10));
		panel.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("播放清單");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_2.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(10, 120));
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("word");
		lblNewLabel_1.setBounds(192, 10, 108, 31);
		lblNewLabel_1.setFont(new Font("DialogInput", Font.BOLD, 20));
		panel_4.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("translation");
		lblNewLabel_1_1.setBounds(46, 44, 1098, 55);
		lblNewLabel_1_1.setFont(new Font("DialogInput", Font.PLAIN, 20));
		panel_4.add(lblNewLabel_1_1);
		
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);
		JPanel panel_5 = new JPanel();
		scrollPane.setViewportView(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		JTextArea txtrContent = new JTextArea();
		txtrContent.setMargin(new Insets(2, 8, 2, 8));
		txtrContent.setFont(new Font("DialogInput", Font.PLAIN, 24));
		txtrContent.setText("content");
		txtrContent.setForeground(Color.WHITE);
		txtrContent.setBackground(Color.DARK_GRAY);
		panel_5.add(txtrContent);
		
		JPanel panel_6 = new JPanel();
		panel_6.setPreferredSize(new Dimension(10, 80));
		contentPane.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		
		JButton btnNewButton = new JButton("▶");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setFont(new Font("DialogInput", Font.BOLD, 20));
		panel_7.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("❚❚");
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBackground(SystemColor.controlHighlight);
		btnNewButton_1.setFont(new Font("DialogInput", Font.BOLD, 20));
		panel_7.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("■");
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setBackground(SystemColor.controlHighlight);
		btnNewButton_2.setFont(new Font("DialogInput", Font.BOLD, 20));
		panel_7.add(btnNewButton_2);
		
		JPanel panel_8 = new JPanel();
		panel_8.setPreferredSize(new Dimension(10, 20));
		panel_6.add(panel_8, BorderLayout.NORTH);
	}
	
	public void testPlay() {
		CardBoxDao cbDao=new CardBoxDao();
		List<CardBox> boxs= cbDao.queryAll();
		VocabularyDao vocbDao=new VocabularyDao();
		List<Vocabulary> vocbs= vocbDao.queryAll();
		
		
		
		
		
		
	}
}

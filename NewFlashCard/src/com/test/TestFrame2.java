package com.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestFrame2 extends JFrame {

	private JPanel contentPane;
	private JPanel panel_card;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame2 frame = new TestFrame2();
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
	public TestFrame2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout)panel_card.getLayout()).show(panel_card, "A");
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("B");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout)panel_card.getLayout()).show(panel_card, "B");
			}
		});
		panel.add(btnNewButton_1);
		
		panel_card = new JPanel();
		contentPane.add(panel_card, BorderLayout.CENTER);
		panel_card.setLayout(new CardLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_card.add(panel_2, "A");
		
		JLabel lblNewLabel = new JLabel("A");
		panel_2.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel_card.add(panel_3, "B");
		
		JLabel lblNewLabel_1 = new JLabel("B");
		panel_3.add(lblNewLabel_1);
	}

}

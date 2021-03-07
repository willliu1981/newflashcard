package com.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JLabel;

public class TestFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel_card;
	private JPanel panel_1;
	private int bv=1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_1;
	private JButton btnNewButton;
	private JPanel panel_4;
	private JButton button;
	private JButton btnNewButton_4;
	private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
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
	public TestFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel_card = new JPanel();
		panel_card.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("xxx");
			}
		});
		contentPane.add(panel_card, BorderLayout.CENTER);
		panel_card.setLayout(new CardLayout(0, 0));
		
		btnNewButton_1 = new JButton("A");
		panel_card.add(btnNewButton_1, "A");
		
		button = new JButton("New button");
		panel_card.add(button, "name_2938116814500");
		
		btnNewButton = new JButton("B");
		panel_card.add(btnNewButton, "B");
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		panel_2 = new JPanel();
		panel_1.add(panel_2,"A");
		
		btnNewButton_2 = new JButton("A");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout)panel_card.getLayout()).show(panel_card, "A");
			}
		});
		panel_2.add(btnNewButton_2);
		
		panel_3 = new JPanel();
		panel_1.add(panel_3,"B");
		
		btnNewButton_3 = new JButton("B");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout)panel_card.getLayout()).show(panel_card, "B");
			}
		});
		panel_3.add(btnNewButton_3);
		
		panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.SOUTH);
		
		btnNewButton_4 = new JButton("New button");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//((CardLayout)panel_card.getLayout())
				lblNewLabel.setText("");
			}
		});
		panel_4.add(btnNewButton_4);
		
		lblNewLabel = new JLabel("New label");
		panel_4.add(lblNewLabel);
	}

}

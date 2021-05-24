package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateExplanation extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea_explanation;
	private JLabel lblNewLabel_vocabulary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateExplanation frame = new UpdateExplanation();
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
	public UpdateExplanation() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 45));
		contentPane.add(panel, BorderLayout.NORTH);
		
		lblNewLabel_vocabulary = new JLabel("null");
		lblNewLabel_vocabulary.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel.add(lblNewLabel_vocabulary);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);

		JButton btnNewButton_update = new JButton("更新");
		btnNewButton_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNewButton_update.setBackground(SystemColor.controlHighlight);
		btnNewButton_update.setFont(new Font("新細明體", Font.PLAIN, 14));
		panel.add(btnNewButton_update);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textArea_explanation = new JTextArea();
		panel_1.add(textArea_explanation, BorderLayout.CENTER);
	}

	public void setExplanation(String explanation) {
		textArea_explanation.setText(explanation);
	}
	
	public void setVocabulary(String vocabulary) {
		lblNewLabel_vocabulary.setText(vocabulary);
	}

}

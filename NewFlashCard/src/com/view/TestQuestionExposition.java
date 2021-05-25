package com.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Map;

import javax.swing.SwingConstants;

import com.control.bridge.Transportable;
import com.control.bridge.session.UIDateTransportation;
import com.control.bridge.session.UIDateTransportation.Session;
import com.model.Vocabulary;

import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TestQuestionExposition extends JFrame implements  Transportable {
	private UIDateTransportation dt;
	private JLabel lblNewLabel_vocabulary;
	private JTextArea textArea_explanation;
	private JScrollPane scrollPane;
	private JTextField textField;
	public TestQuestionExposition() {
		this.setBounds(0,0,800,600);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 45));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		lblNewLabel_vocabulary = new JLabel("null");
		lblNewLabel_vocabulary.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_vocabulary.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel.add(lblNewLabel_vocabulary);
		
		textField = new JTextField();
		panel.add(textField, BorderLayout.EAST);
		textField.setColumns(10);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		textArea_explanation = new JTextArea();
		textArea_explanation.setLineWrap(true);
		textArea_explanation.setWrapStyleWord(true);
		textArea_explanation.setForeground(Color.WHITE);
		textArea_explanation.setBackground(Color.DARK_GRAY);
		textArea_explanation.setFont(new Font("Monospaced", Font.PLAIN, 18));
		/*
		 * 此行註解,否則 WindowBuilder 會報錯
		 * 原因它已加入 scrollPane 之中
		 */
		//panel_1.add(textArea_explanation, BorderLayout.CENTER);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(textArea_explanation);
		
		panel_1.add(scrollPane, BorderLayout.CENTER);
	}

	@Override
	public void accpet() {
		int id=(int) dt.getParameter("id");
		Session sess=UIDateTransportation.getSession();
		Map<Integer,Vocabulary> map=(Map<Integer, Vocabulary>) sess.getAttribute("randomAnswers");
		Vocabulary v=map.get(id);
		this.lblNewLabel_vocabulary.setText( v.getVocabulary());
		this.textArea_explanation.setText(v.getExplanation());
	}

	@Override
	public void setUIDateTransportation(UIDateTransportation dt) {
		this.dt=dt;
	}

}

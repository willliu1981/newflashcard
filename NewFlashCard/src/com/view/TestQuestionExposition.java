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

public class TestQuestionExposition extends JFrame implements  Transportable {
	private UIDateTransportation dt;
	private JLabel lblNewLabel_vocabulary;
	private JTextArea textArea_explanation;
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

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		textArea_explanation = new JTextArea();
		textArea_explanation.setFont(new Font("Monospaced", Font.PLAIN, 18));
		panel_1.add(textArea_explanation);
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

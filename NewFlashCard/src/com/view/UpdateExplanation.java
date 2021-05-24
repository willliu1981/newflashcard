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

import com.control.bridge.Dispatcher;
import com.control.bridge.Transportable;
import com.control.bridge.session.UIDateTransportation;
import com.control.viewcontrol.ExplanationBridge;
import com.control.viewcontrol.UpdateExplanationBridge;
import com.model.Vocabulary;

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
import java.awt.Color;

public class UpdateExplanation extends JFrame implements Transportable {

	private JPanel contentPane;
	private JTextArea textArea_explanation;
	private JLabel lblNewLabel_vocabulary;
	private UIDateTransportation dt;

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
				UpdateExplanationBridge bridge=new UpdateExplanationBridge();
				Dispatcher disp=bridge.getDispatcher();
				Vocabulary vocabulary=(Vocabulary) dt.getParameter("vocabulary");
				vocabulary.setExplanation(textArea_explanation.getText());
				bridge.setParameter("vocabulary", vocabulary);
				disp.send(bridge);
			}
		});
		btnNewButton_update.setBackground(SystemColor.controlHighlight);
		btnNewButton_update.setFont(new Font("新細明體", Font.PLAIN, 14));
		panel.add(btnNewButton_update);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		textArea_explanation = new JTextArea();
		textArea_explanation.setCaretColor(Color.ORANGE);
		textArea_explanation.setForeground(Color.WHITE);
		textArea_explanation.setBackground(Color.DARK_GRAY);
		textArea_explanation.setFont(new Font("Monospaced", Font.PLAIN, 16));
		panel_1.add(textArea_explanation, BorderLayout.CENTER);
	}

	private void setExplanation(String explanation) {
		textArea_explanation.setText(explanation);
	}

	private void setVocabulary(String vocabulary) {
		lblNewLabel_vocabulary.setText(vocabulary);
	}

	@Override
	public void accpet() {
		ExplanationBridge birdge = (ExplanationBridge) dt;
		Vocabulary vocabulary = (Vocabulary) birdge.getParameter("vocabulary");
		this.setVocabulary(vocabulary.getVocabulary());
		this.setExplanation(vocabulary.getExplanation());

	}

	@Override
	public void setUIDateTransportation(UIDateTransportation dt) {
		this.dt = dt;
	}

}

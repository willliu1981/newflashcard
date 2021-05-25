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

import com.control.bridge.Bridge;
import com.control.bridge.Dispatcher;
import com.control.bridge.Transportable;
import com.control.bridge.session.UIDateTransportation;
import com.control.viewcontrol.ExplanationBridge;
import com.control.viewcontrol.MyColor;
import com.control.viewcontrol.UpdateExplanationBridge;
import com.model.Vocabulary;

import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextArea;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.ComponentOrientation;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.SwingConstants;

public class ExplanationFrame extends JFrame implements Transportable {

	private JPanel contentPane;
	private JTextArea textArea_explanation;
	private UIDateTransportation dt;
	private JPanel panel_border;
	private JScrollPane scrollPane;
	private JTextField txtVocabulary;
	private JTextField textField_translation;
	private JPanel panel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExplanationFrame frame = new ExplanationFrame();
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
	public ExplanationFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 45));
		contentPane.add(panel, BorderLayout.NORTH);

		txtVocabulary = new JTextField();
		txtVocabulary.setEditable(false);
		txtVocabulary.setText("null");
		txtVocabulary.setHorizontalAlignment(SwingConstants.TRAILING);
		txtVocabulary.setPreferredSize(new Dimension(7, 25));
		txtVocabulary.setBorder(null);
		txtVocabulary.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		panel.add(txtVocabulary);
		txtVocabulary.setColumns(20);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		textField_translation = new JTextField();
		textField_translation.setBorder(null);
		textField_translation.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		panel.add(textField_translation);
		textField_translation.setColumns(20);
		
		panel_3 = new JPanel();
		panel.add(panel_3);

		panel_border = new JPanel();
		panel.add(panel_border);
		
		JButton btnNewButton_update = new JButton("更新");
		panel_border.add(btnNewButton_update);
		btnNewButton_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateExplanationBridge bridge = new UpdateExplanationBridge();
				Dispatcher disp = bridge.getDispatcher();
				Vocabulary vocabulary = (Vocabulary) dt.getParameter("vocabulary");
				vocabulary.setExplanation(textArea_explanation.getText());
				vocabulary.setTranslation(textField_translation.getText());
				bridge.setParameter("vocabulary", vocabulary);
				disp.send(bridge);
				panel_border.setBackground(MyColor.getBase());
			}
		});
		btnNewButton_update.setBackground(SystemColor.controlHighlight);
		btnNewButton_update.setFont(new Font("新細明體", Font.PLAIN, 14));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		textArea_explanation = new JTextArea();
		textArea_explanation.setMargin(new Insets(10, 10, 10, 10));
		textArea_explanation.setWrapStyleWord(true);
		textArea_explanation.setLineWrap(true);
		textArea_explanation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				panel_border.setBackground(Color.red);
			}
		});
		textArea_explanation.setCaretColor(Color.ORANGE);
		textArea_explanation.setForeground(Color.WHITE);
		textArea_explanation.setBackground(Color.DARK_GRAY);
		textArea_explanation.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		/*
		 * 此行註解,否則 WindowBuilder 會報錯 原因它已加入 scrollPane 之中
		 */
		// panel_1.add(textArea_explanation, BorderLayout.CENTER);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(textArea_explanation);

		panel_1.add(scrollPane, BorderLayout.CENTER);
	}

	@Override
	public void accpet() {
		Bridge birdge = (Bridge) dt;
		Vocabulary vocabulary = (Vocabulary) birdge.getParameter("vocabulary");
		this.txtVocabulary.setText(vocabulary.getVocabulary());
		this.textArea_explanation.setText(vocabulary.getExplanation());
		this.textField_translation.setText(vocabulary.getTranslation());
	}

	@Override
	public void setUIDateTransportation(UIDateTransportation dt) {
		this.dt = dt;
	}

}

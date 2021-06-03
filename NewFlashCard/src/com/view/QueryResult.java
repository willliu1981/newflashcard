package com.view;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.control.pad.Pads;
import com.model.Vocabulary;

import java.awt.BorderLayout;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;

public class QueryResult extends JFrame {
	private JTextField txt_vocabulary;
	private JTextArea txtr_result;
	private JLabel lblNewLabel_vocabulary;

	public QueryResult() {
		setType(Type.UTILITY);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 400, 300);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		txtr_result = new JTextArea();
		txtr_result.setLineWrap(true);
		txtr_result.setWrapStyleWord(true);
		txtr_result.setEditable(false);
		txtr_result.setMargin(new Insets(8, 8, 8, 8));
		txtr_result.setText("null");
		txtr_result.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel.add(txtr_result);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);

		txt_vocabulary = new JTextField();
		txt_vocabulary.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Pads.query(txt_vocabulary.getText());
			}
		});
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		txt_vocabulary.setFont(new Font("標楷體", Font.BOLD, 16));
		txt_vocabulary.setHorizontalAlignment(SwingConstants.CENTER);
		txt_vocabulary.setText("null");
		panel_1.add(txt_vocabulary);
		txt_vocabulary.setColumns(20);
		
		lblNewLabel_vocabulary = new JLabel("null");
		lblNewLabel_vocabulary.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_vocabulary.setFont(new Font("標楷體", Font.BOLD, 16));
		panel_1.add(lblNewLabel_vocabulary);
	}

	public void query(Component parent, Vocabulary result) {
		if (parent != null) {
			this.setBounds(parent.getX(), parent.getY(), this.getWidth(), this.getHeight());
		}
		this.txt_vocabulary.setText(result.getVocabulary());
		this.lblNewLabel_vocabulary.setText(result.getVocabulary());
		this.txtr_result.setText(result.getTranslation());
		this.setVisible(true);
	}

}

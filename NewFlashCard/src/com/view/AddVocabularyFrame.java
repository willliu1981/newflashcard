package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.border.EtchedBorder;

import com.control.dao.Dao;
import com.control.dao.VocabularyDao;
import com.model.Vocabulary;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddVocabularyFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_vocabulary;
	private JTextField textField_translation;
	private JTextArea textArea_explanation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddVocabularyFrame frame = new AddVocabularyFrame();
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
	public AddVocabularyFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 849, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_top = new JPanel();
		panel.add(panel_top, BorderLayout.NORTH);
		panel_top.setLayout(new BorderLayout(0, 0));

		JPanel panel_top_top = new JPanel();
		panel_top_top.setPreferredSize(new Dimension(10, 15));
		panel_top.add(panel_top_top, BorderLayout.NORTH);

		JPanel panel_top_center = new JPanel();
		panel_top.add(panel_top_center);

		JLabel lblNewLabel = new JLabel("詞彙");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_top_center.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_top_center.add(panel_1);

		textField_vocabulary = new JTextField();
		textField_vocabulary.setFont(new Font("新細明體", Font.PLAIN, 18));
		textField_vocabulary.setColumns(10);
		panel_top_center.add(textField_vocabulary);

		JPanel panel_1_1 = new JPanel();
		panel_top_center.add(panel_1_1);

		JLabel lblNewLabel_1 = new JLabel("詞彙");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_top_center.add(lblNewLabel_1);

		textField_translation = new JTextField();
		textField_translation.setFont(new Font("新細明體", Font.PLAIN, 18));
		textField_translation.setColumns(10);
		panel_top_center.add(textField_translation);

		JPanel panel_1_1_1 = new JPanel();
		panel_top_center.add(panel_1_1_1);

		JButton btnNewButton = new JButton("新增");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String vocabulary = textField_vocabulary.getText();
				String translation = textField_translation.getText();
				String explanation = textArea_explanation.getText();
				Vocabulary m = new Vocabulary();
				m.setVocabulary(vocabulary);
				m.setTranslation(translation);
				m.setExplanation(explanation);
				new VocabularyDao().add(m);
				
				textField_vocabulary.setText("");
				textField_translation.setText("");
				textArea_explanation.setText("");
			}
		});
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_top_center.add(btnNewButton);

		JPanel panel_center = new JPanel();
		panel.add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));

		JPanel panel_center_center = new JPanel();
		panel_center.add(panel_center_center, BorderLayout.CENTER);
		panel_center_center.setLayout(new BorderLayout(0, 0));

		textArea_explanation = new JTextArea();
		panel_center_center.add(textArea_explanation);

		JPanel panel_center_top = new JPanel();
		panel_center_top.setPreferredSize(new Dimension(10, 30));
		panel_center.add(panel_center_top, BorderLayout.NORTH);
		panel_center_top.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JPanel panel_1_2 = new JPanel();
		panel_center_top.add(panel_1_2);

		JLabel lblNewLabel_2 = new JLabel("其它解釋");
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_center_top.add(lblNewLabel_2);
	}

}

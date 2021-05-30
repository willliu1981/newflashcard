package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import com.control.pad.PadFactory;
import com.control.viewcontrol.bridge.AddVocabularyBridge;
import com.control.viewcontrol.bridge.CheckVocabularyExistBridge;
import com.model.Vocabulary;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;
import java.awt.CardLayout;

public class AddVocabularyFrame extends JFrame {
	private AddVocabularyFrame thisFrame;
	private JPanel contentPane;
	private JTextField textField_vocabulary;
	private JTextField textField_translation;
	private JTextArea textArea_explanation;
	private JTextArea textArea_example;
	private JPanel panel_center_card;

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
		this.thisFrame = this;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1046, 800);
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

		JButton btnNewButton_check = new JButton("ck");
		btnNewButton_check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CheckVocabularyExistBridge bridge = new CheckVocabularyExistBridge();
				bridge.setParameter("parent",thisFrame);
				bridge.setParameter("vocabulary", textField_vocabulary.getText());
				bridge.getDispatcher().send();
			}

		});
		btnNewButton_check.setBackground(SystemColor.controlHighlight);
		btnNewButton_check.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton_check.setMinimumSize(new Dimension(21, 23));
		btnNewButton_check.setMargin(new Insets(2, 2, 2, 2));
		btnNewButton_check.setPreferredSize(new Dimension(21, 23));
		btnNewButton_check.setFont(new Font("新細明體", Font.PLAIN, 12));
		panel_top_center.add(btnNewButton_check);

		JPanel panel_1_1 = new JPanel();
		panel_top_center.add(panel_1_1);

		JLabel lblNewLabel_1 = new JLabel("翻譯");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_top_center.add(lblNewLabel_1);

		textField_translation = new JTextField();
		textField_translation.setFont(new Font("新細明體", Font.PLAIN, 18));
		textField_translation.setColumns(20);
		panel_top_center.add(textField_translation);

		JPanel panel_1_1_1 = new JPanel();
		panel_top_center.add(panel_1_1_1);

		JButton btnNewButton = new JButton("新增");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String vocabulary = textField_vocabulary.getText();
				String translation = textField_translation.getText();
				String explanation = textArea_explanation.getText();
				String example = textArea_example.getText();
				if (vocabulary == null || translation == null || vocabulary.trim().equals("")
						|| translation.trim().equals("")) {
					JOptionPane.showMessageDialog(thisFrame, "詞彙或翻譯未填", "資料錯誤", JOptionPane.WARNING_MESSAGE);
					return;
				}

				AddVocabularyBridge bridge = new AddVocabularyBridge();
				bridge.setParameter("vocabulary", vocabulary);
				bridge.setParameter("translation", translation);
				bridge.setParameter("explanation", explanation);
				bridge.setParameter("example", example);
				bridge.setParameter("parent", thisFrame);
				int r = bridge.getDispatcher().sendAndBack();

				if (r == bridge.SENDANDBACK_NORMAL) {
					textField_vocabulary.setText("");
					textField_translation.setText("");
					textArea_explanation.setText("");
					textArea_example.setText("");
					((CardLayout)panel_center_card.getLayout()).show(panel_center_card, "explantion");
				}
			}
		});
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_top_center.add(btnNewButton);

		JPanel panel_center = new JPanel();
		panel.add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));

		JPanel panel_explanation = new JPanel();
		panel_explanation.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_explanation.setLayout(new BorderLayout(0, 0));

		textArea_explanation = new JTextArea();
		textArea_explanation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				PadFactory.getPad().keyAction_pressed(PadFactory.MAIN_ADDVOCABULARYFRAME_EXPLANATION,
						textArea_explanation, e);
			}

			@Override
			public void keyTyped(KeyEvent e) {

				PadFactory.getPad().keyAction_typed(PadFactory.MAIN_ADDVOCABULARYFRAME_EXPLANATION, textArea_explanation,
						e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				PadFactory.getPad().keyAction_release(PadFactory.MAIN_ADDVOCABULARYFRAME_EXPLANATION,
						textArea_explanation, e);
			}
		});
		textArea_explanation.setCaretColor(Color.YELLOW);
		textArea_explanation.setMargin(new Insets(20, 20, 20, 20));
		textArea_explanation.setForeground(Color.WHITE);
		textArea_explanation.setBackground(Color.DARK_GRAY);
		textArea_explanation.setLineWrap(true);
		textArea_explanation.setWrapStyleWord(true);
		textArea_explanation.setFont(new Font("DialogInput", Font.PLAIN, 18));

		JScrollPane scrollPane_explanation = new JScrollPane();
		scrollPane_explanation.setViewportView(textArea_explanation);
		panel_explanation.add(scrollPane_explanation, BorderLayout.CENTER);

		JPanel panel_example = new JPanel();
		panel_example.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_example.setLayout(new BorderLayout(0, 0));

		textArea_example = new JTextArea();
		textArea_example.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				PadFactory.getPad().keyAction_pressed(PadFactory.MAIN_ADDVOCABULARYFRAME_EXAMPLE,
						textArea_example, e);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				PadFactory.getPad().keyAction_typed(PadFactory.MAIN_ADDVOCABULARYFRAME_EXAMPLE, textArea_example,
						e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				PadFactory.getPad().keyAction_release(PadFactory.MAIN_ADDVOCABULARYFRAME_EXAMPLE,
						textArea_example, e);
			}

		});
		textArea_example.setCaretColor(Color.YELLOW);
		textArea_example.setMargin(new Insets(20, 20, 20, 20));
		textArea_example.setForeground(new Color(255, 255, 255));
		textArea_example.setBackground(Color.BLACK);
		textArea_example.setWrapStyleWord(true);
		textArea_example.setLineWrap(true);
		textArea_example.setFont(new Font("DialogInput", Font.PLAIN, 18));

		JScrollPane scrollPane_example = new JScrollPane();
		scrollPane_example.setViewportView(textArea_example);
		panel_example.add(scrollPane_example, BorderLayout.CENTER);
		
		JPanel panel_center_title = new JPanel();
		panel_center.add(panel_center_title, BorderLayout.NORTH);
		
		JButton btnNewButton_1 = new JButton("解釋");
		btnNewButton_1.setBackground(SystemColor.controlHighlight);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((CardLayout)panel_center_card.getLayout()).show(panel_center_card, "explanation");
			}
		});
		btnNewButton_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_center_title.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_center_title.add(panel_2);
		
		JButton btnNewButton_2 = new JButton("例句");
		btnNewButton_2.setBackground(SystemColor.controlHighlight);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout)panel_center_card.getLayout()).show(panel_center_card, "example");
			}
		});
		btnNewButton_2.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_center_title.add(btnNewButton_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(100, 10));
		panel_center_title.add(panel_3);
		
		JButton btnNewButton_3 = new JButton("全部清除");
		btnNewButton_3.setBackground(SystemColor.controlHighlight);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result =JOptionPane.showConfirmDialog(thisFrame, "將清除畫面上所有輸入的資料,請確認","全部清除",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				if(result==JOptionPane.YES_OPTION) {
					PadFactory.getPad().setReverseContent(PadFactory.MAIN_ADDVOCABULARYFRAME_EXPLANATION, textArea_explanation.getText());
					PadFactory.getPad().setReverseContent(PadFactory.MAIN_ADDVOCABULARYFRAME_EXAMPLE, textArea_example.getText());
					textArea_example.setText("");
					textArea_explanation.setText("");
					textField_vocabulary.setText("");
					textField_translation.setText("");
					((CardLayout)panel_center_card.getLayout()).show(panel_center_card, "explantion");
				}
			}
		});
		btnNewButton_3.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_center_title.add(btnNewButton_3);
		
		panel_center_card = new JPanel();
		panel_center.add(panel_center_card, BorderLayout.CENTER);
		panel_center_card.setLayout(new CardLayout(0, 0));
		
		panel_center_card.add(panel_explanation, "explanation");
		panel_center_card.add(panel_example, "example");
	
	}

}

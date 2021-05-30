package com.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.control.bridge.Transportable;
import com.control.bridge.session.UIDateTransportation;
import com.control.pad.PadFactory;
import com.control.viewcontrol.bridge.UpdateExplanationBridge;
import com.model.Vocabulary;
import com.tool.MyColor;

public class ExplanationFrame extends JFrame implements Transportable {
	private ExplanationFrame thisFrame;
	private static final String EXPLANATIONTYPE_EXPLANATION = "解釋";
	private static final String EXPLANATIONTYPE_EXAMPLE = "例句";
	private static String explanationType = EXPLANATIONTYPE_EXPLANATION;
	private JPanel contentPane;
	private JTextArea textArea_explanation;
	private UIDateTransportation dt;
	private JPanel panel_updateborder;
	private JScrollPane scrollPane_explanation;
	private JTextField txtVocabulary;
	private JTextField textField_translation;
	private JPanel panel_3;
	private JButton btnNewButton_type;
	private JScrollPane scrollPane_example;
	private JTextArea textArea_example;
	private JPanel panel_cardlayout;
	private JPanel panel_1;
	private JPanel panel_exampleborder;
	private String explanationTemp;// 用於反悔復原
	private String exampleTemp;// 用於反悔復原

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
		thisFrame = this;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 900, 800);
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
		txtVocabulary.setBorder(null);
		txtVocabulary.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		panel.add(txtVocabulary);
		txtVocabulary.setColumns(18);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);

		textField_translation = new JTextField();
		textField_translation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				PadFactory.getPad().change(panel_updateborder, PadFactory.MAIN_EXPLANATIONFRAME_TRANSLATION, e);
			}
		});
		textField_translation.setBorder(null);
		textField_translation.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		panel.add(textField_translation);
		textField_translation.setColumns(20);

		panel_3 = new JPanel();
		panel.add(panel_3);

		panel_exampleborder = new JPanel();
		panel.add(panel_exampleborder);
		btnNewButton_type = new JButton("例句");
		btnNewButton_type.setBackground(SystemColor.controlHighlight);
		panel_exampleborder.add(btnNewButton_type);
		btnNewButton_type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (explanationType.equals(EXPLANATIONTYPE_EXPLANATION)) {
					((CardLayout) panel_cardlayout.getLayout()).show(panel_cardlayout,
							explanationType = EXPLANATIONTYPE_EXAMPLE);
					btnNewButton_type.setText("解釋");
				} else {
					((CardLayout) panel_cardlayout.getLayout()).show(panel_cardlayout,
							explanationType = EXPLANATIONTYPE_EXPLANATION);
					btnNewButton_type.setText("例句");
				}
			}
		});
		btnNewButton_type.setFont(new Font("新細明體", Font.PLAIN, 14));

		panel_1 = new JPanel();
		panel.add(panel_1);

		panel_updateborder = new JPanel();
		panel.add(panel_updateborder);
		JButton btnNewButton_update = new JButton("更新");
		btnNewButton_update.setBackground(SystemColor.controlHighlight);
		panel_updateborder.add(btnNewButton_update);
		btnNewButton_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dt == null) {
					return;
				}

				UpdateExplanationBridge bridge = new UpdateExplanationBridge();
				Vocabulary vocabulary = (Vocabulary) dt.getParameter("vocabulary");
				vocabulary.setExplanation(textArea_explanation.getText());
				vocabulary.setExample(textArea_example.getText());
				vocabulary.setTranslation(textField_translation.getText());
				bridge.setParameter("parent", thisFrame);
				bridge.setParameter("vocabulary", vocabulary);
				bridge.getDispatcher().send();
			}
		});
		btnNewButton_update.setFont(new Font("新細明體", Font.PLAIN, 14));

		panel_cardlayout = new JPanel();
		contentPane.add(panel_cardlayout, BorderLayout.CENTER);
		panel_cardlayout.setLayout(new CardLayout(0, 0));
		textArea_explanation = new JTextArea();
		textArea_explanation.setMargin(new Insets(10, 10, 10, 10));
		textArea_explanation.setWrapStyleWord(true);
		textArea_explanation.setLineWrap(true);
		textArea_explanation.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				PadFactory.getPad().keyAction_pressed(PadFactory.MAIN_EXPLANATIONFRAME_EXPLANATION,
						textArea_explanation, e);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				PadFactory.getPad().change(panel_updateborder, PadFactory.MAIN_EXPLANATIONFRAME_EXPLANATION, e);

				PadFactory.getPad().keyAction_typed(PadFactory.MAIN_EXPLANATIONFRAME_EXPLANATION, textArea_explanation,
						e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				PadFactory.getPad().keyAction_release(PadFactory.MAIN_EXPLANATIONFRAME_EXPLANATION,
						textArea_explanation, e);
			}
		});
		textArea_explanation.setCaretColor(Color.YELLOW);
		textArea_explanation.setForeground(Color.WHITE);
		textArea_explanation.setBackground(Color.DARK_GRAY);
		textArea_explanation.setFont(new Font("DialogInput", Font.PLAIN, 20));
		scrollPane_explanation = new JScrollPane();
		scrollPane_explanation.setViewportView(textArea_explanation);
		panel_cardlayout.add(scrollPane_explanation, EXPLANATIONTYPE_EXPLANATION);

		textArea_example = new JTextArea();
		textArea_example.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (((int) e.getKeyChar()) != 65535 && ((int) e.getKeyChar()) != 26 && ((int) e.getKeyChar()) != 3
						&& ((int) e.getKeyChar()) != 1) {
					exampleTemp = textArea_example.getText();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				PadFactory.getPad().change(panel_updateborder, PadFactory.MAIN_EXPLANATIONFRAME_EXAMPLE, e);

				if (((int) e.getKeyChar()) == 26) {
					String temp = textArea_example.getText();
					textArea_example.setText(exampleTemp);
					exampleTemp = temp;
				}

			}

		});
		textArea_example.setWrapStyleWord(true);
		textArea_example.setMargin(new Insets(10, 10, 10, 10));
		textArea_example.setLineWrap(true);
		textArea_example.setForeground(Color.WHITE);
		textArea_example.setFont(new Font("DialogInput", Font.PLAIN, 20));
		textArea_example.setCaretColor(Color.ORANGE);
		textArea_example.setBackground(new Color(0, 0, 0));
		scrollPane_example = new JScrollPane();
		scrollPane_example.setViewportView(textArea_example);
		panel_cardlayout.add(scrollPane_example, EXPLANATIONTYPE_EXAMPLE);
	}

	@Override
	public UIDateTransportation accpet(UIDateTransportation dt) {
		Vocabulary vocabulary = (Vocabulary) dt.getParameter("vocabulary");
		this.txtVocabulary.setText(vocabulary.getVocabulary());
		this.textArea_explanation.setText(vocabulary.getExplanation());
		this.textArea_example.setText(vocabulary.getExample());
		this.textField_translation.setText(vocabulary.getTranslation());
		this.textArea_explanation.setSelectionStart(0);
		this.textArea_explanation.setSelectionEnd(0);

		/*
		 * init component
		 */
		((CardLayout) panel_cardlayout.getLayout()).show(panel_cardlayout,
				explanationType = EXPLANATIONTYPE_EXPLANATION);
		btnNewButton_type.setText(EXPLANATIONTYPE_EXAMPLE);

		/*
		 * set highlight
		 */
		if (vocabulary.getExample() != null && !(vocabulary.getExample().equals(""))) {
			panel_exampleborder.setBackground(new Color(0, 176, 0));
		} else {
			panel_exampleborder.setBackground(MyColor.getBase());
		}

		if (PadFactory.getPad().isChanged(PadFactory.MAIN_EXPLANATIONFRAME_EXPLANATION)
				|| PadFactory.getPad().isChanged(PadFactory.MAIN_EXPLANATIONFRAME_EXAMPLE)) {
			panel_updateborder.setBackground(Color.red);
		} else {
			panel_updateborder.setBackground(MyColor.getBase());
		}

		return dt;
	}

	@Override
	public void setUIDateTransportation(UIDateTransportation dt) {
		this.dt = dt;
	}

}

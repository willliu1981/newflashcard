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
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
import com.control.pad.Pads;
import com.control.viewcontrol.bridge.UpdateExplanationBridge;
import com.model.Vocabulary;
import com.tool.MyColor;
import com.tool.PropertiesFactory;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class ExplanationFrame extends JFrame implements Transportable {
	private ExplanationFrame thisFrame;
	private static final String EXPLANATION = "解釋";
	private static final String EXAMPLE = "例句";
	private static String explanationType = EXPLANATION;
	private JPanel contentPane;
	private JTextArea textArea_explanation;
	private UIDateTransportation dt;
	private JPanel panel_updateborder;
	private JScrollPane scrollPane_explanation;
	private JTextField txtVocabulary;
	private JTextField textField_translation;
	private JButton btnNewButton_type;
	private JScrollPane scrollPane_example;
	private JTextArea textArea_example;
	private JPanel panel_cardlayout;
	private JPanel panel_1;
	private JPanel panel_exampleborder;
	private boolean locked = false;// input 解鎖
	private static Color explanationBackground = PropertiesFactory.getColor("explanation_background");
	private static Color exampleBackground = PropertiesFactory.getColor("example_background");
	private JPanel panel_explanation;
	private JPanel panel_example;
	private JPanel panel_4;
	private JPanel panel_3;
	private JLabel lblNewLabel;
	private JButton btnNewButton;

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

		btnNewButton = new JButton("解鎖");
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseUnlock();
			}
		});
		btnNewButton.setMargin(new Insets(2, 4, 2, 4));
		btnNewButton.setFont(new Font("標楷體", Font.PLAIN, 14));
		panel.add(btnNewButton);

		txtVocabulary = new JTextField();
		txtVocabulary.setEditable(false);
		txtVocabulary.setText("null");
		txtVocabulary.setHorizontalAlignment(SwingConstants.CENTER);
		txtVocabulary.setBorder(null);
		txtVocabulary.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		panel.add(txtVocabulary);
		txtVocabulary.setColumns(18);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);

		panel_exampleborder = new JPanel();
		panel.add(panel_exampleborder);
		btnNewButton_type = new JButton("例句");
		btnNewButton_type.setBackground(SystemColor.controlHighlight);
		panel_exampleborder.add(btnNewButton_type);
		btnNewButton_type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (explanationType.equals(EXPLANATION)) {
					((CardLayout) panel_cardlayout.getLayout()).show(panel_cardlayout, explanationType = EXAMPLE);
					btnNewButton_type.setText("解釋");
					panel_exampleborder.setBackground(MyColor.getBase());
				} else {
					((CardLayout) panel_cardlayout.getLayout()).show(panel_cardlayout, explanationType = EXPLANATION);
					btnNewButton_type.setText("例句");
					Vocabulary vocabulary = (Vocabulary) dt.getParameter("vocabulary");
					exampleHighLight(vocabulary);
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
		textArea_explanation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseUnlock(e);
				if (e.getButton() == 3) {
					Pads.query(thisFrame, textArea_explanation.getSelectedText());
				}
			}
		});
		textArea_explanation.setDisabledTextColor(Color.WHITE);
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
				if (!locked) {
					PadFactory.getPad().change(panel_updateborder, PadFactory.MAIN_EXPLANATIONFRAME_EXPLANATION, e);
				}

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
		textArea_explanation.setBackground(new Color(0, 0, 0));
		textArea_explanation.setFont(new Font("DialogInput", Font.PLAIN, 20));
		scrollPane_explanation = new JScrollPane();
		scrollPane_explanation.setViewportView(textArea_explanation);

		textArea_example = new JTextArea();
		textArea_example.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseUnlock(e);
				if (e.getButton() == 3) {
					Pads.query(thisFrame, textArea_example.getSelectedText());
				}
			}
		});
		textArea_example.setDisabledTextColor(Color.WHITE);
		textArea_example.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				PadFactory.getPad().keyAction_pressed(PadFactory.MAIN_EXPLANATIONFRAME_EXAMPLE, textArea_example, e);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (!locked) {
					PadFactory.getPad().change(panel_updateborder, PadFactory.MAIN_EXPLANATIONFRAME_EXAMPLE, e);
				}

				PadFactory.getPad().keyAction_typed(PadFactory.MAIN_EXPLANATIONFRAME_EXAMPLE, textArea_example, e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				PadFactory.getPad().keyAction_release(PadFactory.MAIN_EXPLANATIONFRAME_EXAMPLE, textArea_example, e);
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

		panel_explanation = new JPanel();
		panel_explanation.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_cardlayout.add(panel_explanation, EXPLANATION);
		panel_explanation.setLayout(new BorderLayout(0, 0));
		panel_explanation.add(scrollPane_explanation);

		panel_4 = new JPanel();
		panel_explanation.add(panel_4, BorderLayout.NORTH);

		textField_translation = new JTextField();
		textField_translation.setPreferredSize(new Dimension(7, 32));
		textField_translation.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(textField_translation);
		textField_translation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseUnlock(e);
			}
		});
		textField_translation.setDisabledTextColor(Color.BLACK);
		textField_translation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!locked) {
					PadFactory.getPad().change(panel_updateborder, PadFactory.MAIN_EXPLANATIONFRAME_TRANSLATION, e);
				}
			}
		});
		textField_translation.setBorder(new LineBorder(Color.LIGHT_GRAY));
		textField_translation.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		textField_translation.setColumns(45);

		panel_example = new JPanel();
		panel_example.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_cardlayout.add(panel_example, EXAMPLE);
		panel_example.setLayout(new BorderLayout(0, 0));
		panel_example.add(scrollPane_example);

		panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 0));
		panel_example.add(panel_3, BorderLayout.NORTH);

		lblNewLabel = new JLabel("例句");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setPreferredSize(new Dimension(55, 32));
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel
				.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(169, 169, 169), new Color(128, 128, 128)));
		lblNewLabel.setFont(new Font("標楷體", Font.BOLD, 20));
		panel_3.add(lblNewLabel);
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
		 * init
		 */
		this.locked = true;
		lock();
		((CardLayout) panel_cardlayout.getLayout()).show(panel_cardlayout, explanationType = EXPLANATION);
		btnNewButton_type.setText(EXAMPLE);

		/*
		 * set highlight
		 */
		exampleHighLight(vocabulary);

		if (PadFactory.isChanged(PadFactory.EXPLANATIONFRAME)) {
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

	private void exampleHighLight(Vocabulary vocabulary) {
		if (vocabulary.getExample() != null && !(vocabulary.getExample().equals(""))) {
			panel_exampleborder.setBackground(new Color(0, 176, 0));
		} else {
			panel_exampleborder.setBackground(MyColor.getBase());
		}
	}

	private void lock() {
		Color bcExplanation = null;
		Color bcExample = null;
		if (locked) {
			bcExplanation = Color.black;
			bcExample = Color.black;
		} else {
			bcExplanation = explanationBackground;
			bcExample = exampleBackground;
		}
		this.textArea_explanation.setEditable(!locked);
		this.textArea_example.setEditable(!locked);
		this.textField_translation.setEditable(!locked);
		this.panel_explanation.setBackground(bcExplanation);
		this.panel_example.setBackground(bcExample);
		this.textArea_explanation.transferFocus();// 這行不加,該元件會無法得到游標
	}

	private void mouseUnlock(MouseEvent e) {
		if (e.getButton() == 2) {
			mouseUnlock();
		}
	}

	private void mouseUnlock() {
		locked = false;
		lock();
	}

}

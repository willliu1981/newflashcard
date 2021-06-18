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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import com.control.bridge.Transportable;
import com.control.bridge.session.UIDateTransportation;
import com.control.pad.PadFactory;
import com.control.viewcontrol.bridge.AddVocabularyBridge;
import com.control.viewcontrol.bridge.AddVocabularyQueryBridge;
import com.control.viewcontrol.bridge.AddVocabularyUpdateBridge;
import com.model.Vocabulary;
import com.tool.Mask;
import com.tool.MyColor;
import com.tool.PropertiesFactory;
import javax.swing.border.LineBorder;

public class AddVocabularyFrame extends JFrame implements Transportable {
	private static final String EXPLANATION = "解釋";
	private static final String EXAMPLE = "例句";
	private AddVocabularyFrame thisFrame;
	private JPanel contentPane;
	private JTextField textField_vocabulary;
	private JTextField textField_translation;
	private JTextArea textArea_explanation;
	private JTextArea textArea_example;
	private JPanel panel_center_card;
	private UIDateTransportation dt;
	private JButton btnNewButton_update;
	private static Color explanationBackground = PropertiesFactory.getColor("explanation_background");
	private static Color exampleBackground = PropertiesFactory.getColor("example_background");
	private JPanel panel_explanation_translation;
	private JPanel panel_top_center;
	private JButton btnNewButton_query;
	private JPanel panel_query;
	private String explanationType = EXPLANATION;
	private JButton btnNewButton_type;
	private JPanel panel_center;

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

		panel_top_center = new JPanel();
		panel_top.add(panel_top_center);

		JButton btnNewButton_speedquery = new JButton("翻譯");
		btnNewButton_speedquery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView.queryResultFrame.setVisible(true);
			}
		});
		btnNewButton_speedquery.setMargin(new Insets(2, 4, 2, 4));
		btnNewButton_speedquery.setFont(new Font("標楷體", Font.PLAIN, 14));
		btnNewButton_speedquery.setBackground(SystemColor.controlHighlight);
		panel_top_center.add(btnNewButton_speedquery);

		JPanel panel_6 = new JPanel();
		panel_top_center.add(panel_6);

		JLabel lblNewLabel = new JLabel("詞彙");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_top_center.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_top_center.add(panel_1);

		textField_vocabulary = new JTextField();
		textField_vocabulary.setMargin(new Insets(2, 8, 2, 8));
		textField_vocabulary.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (((int) e.getKeyChar()) == 27) {
					textField_vocabulary.setText("");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				queryBtnHighLight();
			}
		});
		textField_vocabulary.setFont(new Font("新細明體", Font.PLAIN, 18));
		textField_vocabulary.setColumns(10);
		panel_top_center.add(textField_vocabulary);

		JPanel panel_1_1 = new JPanel();
		panel_top_center.add(panel_1_1);

		panel_query = new JPanel();
		panel_top_center.add(panel_query);

		btnNewButton_query = new JButton("查詢");
		panel_query.add(btnNewButton_query);
		btnNewButton_query.setFont(new Font("新細明體", Font.PLAIN, 16));
		btnNewButton_query.setMargin(new Insets(2, 2, 2, 2));
		btnNewButton_query.setBackground(SystemColor.controlHighlight);
		btnNewButton_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vocabulary = textField_vocabulary.getText().trim();
				AddVocabularyQueryBridge bridge = new AddVocabularyQueryBridge();
				bridge.setParameter("parent", thisFrame);
				bridge.setParameter("vocabulary", vocabulary);
				bridge.getDispatcher().send();
			}
		});

		JPanel panel_5 = new JPanel();
		panel_top_center.add(panel_5);

		JButton btnNewButton_3 = new JButton("全部清除");
		panel_top_center.add(btnNewButton_3);
		btnNewButton_3.setPreferredSize(new Dimension(100, 30));
		btnNewButton_3.setBackground(SystemColor.controlHighlight);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(thisFrame, "將清除畫面上所有輸入的資料,請確認", "全部清除",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					PadFactory.getPad().setReverseContent(PadFactory.MAIN_ADDVOCABULARYFRAME_EXPLANATION,
							textArea_explanation.getText());
					PadFactory.getPad().setReverseContent(PadFactory.MAIN_ADDVOCABULARYFRAME_EXAMPLE,
							textArea_example.getText());
					explanationType = EXPLANATION;
					initializeComponent();
					initializeType();
					highLightCauseQuery(false);
				}
			}
		});
		btnNewButton_3.setFont(new Font("新細明體", Font.PLAIN, 16));

		panel_center = new JPanel();
		panel_center.setBorder(new LineBorder(SystemColor.controlHighlight, 3, true));
		panel.add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));

		JPanel panel_explanation = new JPanel();
		panel_explanation.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_explanation.setLayout(new BorderLayout(0, 0));

		textArea_explanation = new JTextArea();
		textArea_explanation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 3) {
					PadFactory.query(thisFrame, textArea_explanation.getSelectedText());
				}
			}
		});
		textArea_explanation.setBackground(Color.black);
		textArea_explanation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				PadFactory.getPad().keyAction_pressed(PadFactory.MAIN_ADDVOCABULARYFRAME_EXPLANATION,
						textArea_explanation, e);
			}

			@Override
			public void keyTyped(KeyEvent e) {

				PadFactory.getPad().keyAction_typed(PadFactory.MAIN_ADDVOCABULARYFRAME_EXPLANATION,
						textArea_explanation, e);
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
		textArea_explanation.setLineWrap(true);
		textArea_explanation.setWrapStyleWord(true);
		textArea_explanation.setFont(new Font("DialogInput", Font.PLAIN, 20));

		JScrollPane scrollPane_explanation = new JScrollPane();
		scrollPane_explanation.setViewportView(textArea_explanation);
		panel_explanation.add(scrollPane_explanation, BorderLayout.CENTER);

		JPanel panel_example = new JPanel();
		panel_example.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_example.setLayout(new BorderLayout(0, 0));

		textArea_example = new JTextArea();
		textArea_example.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 3) {
					PadFactory.query(thisFrame, textArea_example.getSelectedText());
				}
			}
		});
		textArea_example.setBackground(Color.black);
		textArea_example.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				PadFactory.getPad().keyAction_pressed(PadFactory.MAIN_ADDVOCABULARYFRAME_EXAMPLE, textArea_example, e);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				PadFactory.getPad().keyAction_typed(PadFactory.MAIN_ADDVOCABULARYFRAME_EXAMPLE, textArea_example, e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				PadFactory.getPad().keyAction_release(PadFactory.MAIN_ADDVOCABULARYFRAME_EXAMPLE, textArea_example, e);
			}

		});
		textArea_example.setCaretColor(Color.YELLOW);
		textArea_example.setMargin(new Insets(20, 20, 20, 20));
		textArea_example.setForeground(new Color(255, 255, 255));
		textArea_example.setWrapStyleWord(true);
		textArea_example.setLineWrap(true);
		textArea_example.setFont(new Font("DialogInput", Font.PLAIN, 20));

		JScrollPane scrollPane_example = new JScrollPane();
		scrollPane_example.setViewportView(textArea_example);
		panel_example.add(scrollPane_example, BorderLayout.CENTER);

		JPanel panel_center_title = new JPanel();
		panel_center.add(panel_center_title, BorderLayout.NORTH);

		btnNewButton_type = new JButton("解釋");
		btnNewButton_type.setForeground(Color.WHITE);
		btnNewButton_type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (explanationType.equals(EXPLANATION)) {
					explanationType = EXAMPLE;
				} else {
					explanationType = EXPLANATION;
				}
				initializeType();
			}
		});
		btnNewButton_type.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_center_title.add(btnNewButton_type);

		JPanel panel_1_1_1 = new JPanel();
		panel_center_title.add(panel_1_1_1);

		btnNewButton_update = new JButton("更新");
		btnNewButton_update.setEnabled(false);
		btnNewButton_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddVocabularyUpdateBridge bridge = new AddVocabularyUpdateBridge();
				Vocabulary vocabulary = (Vocabulary) dt.getParameter("vocabulary");
				vocabulary.setTranslation(textField_translation.getText().trim());
				vocabulary.setExplanation(textArea_explanation.getText());
				vocabulary.setExample(textArea_example.getText());
				bridge.setParameter("parent", thisFrame);
				bridge.setParameter("vocabulary", vocabulary);
				bridge.getDispatcher().send();
			}
		});
		btnNewButton_update.setPreferredSize(new Dimension(100, 30));
		btnNewButton_update.setFont(new Font("新細明體", Font.PLAIN, 16));
		btnNewButton_update.setBackground(SystemColor.controlHighlight);
		panel_center_title.add(btnNewButton_update);

		JPanel panel_7 = new JPanel();
		panel_center_title.add(panel_7);

		JButton btnNewButton_add = new JButton("新增");
		btnNewButton_add.setPreferredSize(new Dimension(100, 30));
		panel_center_title.add(btnNewButton_add);
		btnNewButton_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String vocabulary = textField_vocabulary.getText().trim();
				String translation = textField_translation.getText().trim();
				String explanation = textArea_explanation.getText();
				String example = textArea_example.getText();
				if (vocabulary == null || translation == null || vocabulary.equals("") || translation.equals("")) {
					JOptionPane.showMessageDialog(thisFrame, "詞彙或翻譯未填", "資料錯誤", JOptionPane.WARNING_MESSAGE);
					return;
				}

				AddVocabularyBridge bridge = new AddVocabularyBridge();
				bridge.setParameter("vocabulary", vocabulary);
				bridge.setParameter("translation", translation);
				bridge.setParameter("explanation", explanation);
				bridge.setParameter("example", example);
				bridge.setParameter("parent", thisFrame);
				Mask r = bridge.getDispatcher().sendAndBack();

				if (r.has(bridge.SENDANDBACK_NORMAL)) {
					explanationType = EXPLANATION;
					initializeComponent();
					initializeType();
					highLightCauseQuery(false);
				}
			}
		});
		btnNewButton_add.setBackground(SystemColor.controlHighlight);
		btnNewButton_add.setFont(new Font("新細明體", Font.PLAIN, 16));

		panel_center_card = new JPanel();
		panel_center.add(panel_center_card, BorderLayout.CENTER);
		panel_center_card.setLayout(new CardLayout(0, 0));

		panel_center_card.add(panel_explanation, EXPLANATION);

		panel_explanation_translation = new JPanel();
		panel_explanation_translation.setBackground(Color.BLACK);
		panel_explanation.add(panel_explanation_translation, BorderLayout.NORTH);

		JLabel lblNewLabel_1 = new JLabel("翻譯");
		lblNewLabel_1.setForeground(Color.WHITE);
		panel_explanation_translation.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("標楷體", Font.BOLD, 18));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.BLACK);
		panel_explanation_translation.add(panel_4);

		textField_translation = new JTextField();
		textField_translation.setColumns(45);
		textField_translation.setMargin(new Insets(2, 8, 2, 8));
		panel_explanation_translation.add(textField_translation);
		textField_translation.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_center_card.add(panel_example, EXAMPLE);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(0, 0, 0));
		panel_example.add(panel_8, BorderLayout.NORTH);

		JLabel lblNewLabel_2 = new JLabel("例句");
		lblNewLabel_2.setForeground(new Color(0, 128, 0));
		lblNewLabel_2.setFont(new Font("標楷體", Font.BOLD, 20));
		lblNewLabel_2.setPreferredSize(new Dimension(45, 28));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblNewLabel_2);

		/*
		 * initialize
		 */
		initializeType();
	}

	private void initializeComponent() {
		textField_vocabulary.setText("");
		textField_translation.setText("");
		textArea_explanation.setText("");
		textArea_example.setText("");
		btnNewButton_update.setEnabled(false);
		textField_vocabulary.setEditable(true);
		((CardLayout) panel_center_card.getLayout()).show(panel_center_card, "explanation");
		panel_query.setBackground(MyColor.getBase());
		queryBtnHighLight();
	}

	@Override
	public void accpet(UIDateTransportation dt) {
		this.setVisible(true);
		Vocabulary vocabulary = (Vocabulary) dt.getParameter("vocabulary");
		boolean fetch = (boolean) dt.getParameter("fetch");

		btnNewButton_update.setEnabled(fetch);

		if (vocabulary != null) {
			textArea_explanation.setText(vocabulary.getExplanation());
			textArea_example.setText(vocabulary.getExample());
			textField_vocabulary.setText(vocabulary.getVocabulary());
			textField_translation.setText(vocabulary.getTranslation());
			textField_vocabulary.setEditable(false);
			highLightCauseQuery(true);
		} else {
			highLightCauseQuery(false);
			initializeComponent();
		}
		textArea_explanation.setSelectionStart(0);
		textArea_explanation.setSelectionEnd(0);
		textArea_example.setSelectionStart(0);
		textArea_example.setSelectionEnd(0);
		explanationType = EXPLANATION;
		initializeType();
	}

	@Override
	public Mask accpetThenBack(UIDateTransportation dt) {
		Optional<?> query = (Optional<?>) dt.getParameter("checkexist");
		if (query.isPresent()) {
			boolean r = this.textField_vocabulary.getText().equals("")
					&& this.textField_translation.getText().equals("") && this.textArea_explanation.getText().equals("")
					&& this.textArea_example.getText().equals("");
			Mask empty = r ? dt.SENDANDBACK_NORMAL : dt.SENDANDBACK_BROKEN;
			return dt.SENDANDBACK_INTERRUPT.add(empty);
		}
		return dt.SENDANDBACK_DEFAULT;
	}

	private void queryBtnHighLight() {
		AddVocabularyQueryBridge bridge = new AddVocabularyQueryBridge();
		bridge.setParameter("vocabulary", textField_vocabulary.getText().trim());
		Mask r = bridge.getDispatcher().sendAndBack();
		if (r.has(dt.SENDANDBACK_NORMAL)) {
			panel_query.setBackground(Color.red);
		} else {
			panel_query.setBackground(MyColor.getBase());
		}
	}

	private void highLightCauseQuery(boolean query) {
		if (query) {
			this.panel_center.setBackground(Color.red);
			panel_center.setBorder(new LineBorder(SystemColor.red, 3, true));
			this.panel_query.setBackground(MyColor.getBase());
		} else {
			this.panel_center.setBackground(MyColor.getBase());
			panel_center.setBorder(new LineBorder(SystemColor.controlHighlight, 3, true));
		}
	}

	private void initializeType() {
		if (this.explanationType.equals(EXPLANATION)) {
			this.btnNewButton_type
					.setBackground(MyColor.heightenColor(PropertiesFactory.getColor("example_background"), -0.55));
			btnNewButton_type.setText(this.EXAMPLE);
		} else {
			this.btnNewButton_type
					.setBackground(MyColor.heightenColor(PropertiesFactory.getColor("explanation_background"), -0.35));
			btnNewButton_type.setText(this.EXPLANATION);
		}
		((CardLayout) panel_center_card.getLayout()).show(panel_center_card, explanationType);
	}

}

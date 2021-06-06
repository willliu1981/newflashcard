package com.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import com.control.viewcontrol.bridge.AddVocabularyQueryBridge;
import com.model.Vocabulary;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class QueryResultFrame extends JFrame implements Transportable {
	private QueryResultFrame thisFrame;
	private static final String EXPLANATION = "解釋";
	private static final String EXAMPLE = "例句";
	private final int baseHeight = 200;
	private JTextField txt_vocabulary;
	private JTextArea txtr_result;
	private JLabel lblNewLabel_vocabulary;
	private JScrollPane scrollPane_result;
	private JPanel panel_main;
	private JPanel panel_more;
	private JPanel panel_more_card;
	private JPanel panel_explanation;
	private JScrollPane scrollPane_explanation;
	private JTextArea textArea_explanation;
	private JPanel panel_3;
	private JButton btnNewButton_more;
	private JPanel panel_example;
	private JScrollPane scrollPane_example;
	private JTextArea textArea_example;
	private boolean isMoOpening = false;
	private JPanel panel_null;
	private String explanationType = EXPLANATION;
	private JButton btnNewButton_type;
	private JPanel panel;
	private JButton btnNewButton;

	public QueryResultFrame() {
		setType(Type.UTILITY);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setBounds(0, 0, 400, baseHeight);
		thisFrame = this;

		panel_main = new JPanel();
		panel_main.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(panel_main, BorderLayout.CENTER);
		panel_main.setLayout(new BorderLayout(0, 0));

		JPanel panel_top = new JPanel();
		panel_main.add(panel_top, BorderLayout.NORTH);
		panel_top.setLayout(new BoxLayout(panel_top, BoxLayout.Y_AXIS));

		txt_vocabulary = new JTextField();
		panel_top.add(txt_vocabulary);
		txt_vocabulary.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				PadFactory.query(null, txt_vocabulary.getText());
			}
		});
		txt_vocabulary.setFont(new Font("標楷體", Font.BOLD, 16));
		txt_vocabulary.setHorizontalAlignment(SwingConstants.CENTER);
		txt_vocabulary.setText("null");
		txt_vocabulary.setColumns(20);

		panel = new JPanel();
		panel_top.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		lblNewLabel_vocabulary = new JLabel("null");
		lblNewLabel_vocabulary.setBorder(new EmptyBorder(0, 4, 0, 0));
		panel.add(lblNewLabel_vocabulary, BorderLayout.CENTER);
		lblNewLabel_vocabulary.setFont(new Font("標楷體", Font.BOLD, 16));

		btnNewButton = new JButton("開啟至");
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vocabulary = lblNewLabel_vocabulary.getText().trim();
				AddVocabularyQueryBridge bridge = new AddVocabularyQueryBridge();
				bridge.setParameter("parent", thisFrame);
				bridge.setParameter("vocabulary", vocabulary);
				bridge.getDispatcher().send();
			}
		});
		btnNewButton.setPreferredSize(new Dimension(50, 23));
		btnNewButton.setMargin(new Insets(4, 4, 0, 4));
		panel.add(btnNewButton, BorderLayout.EAST);

		JPanel panel_center = new JPanel();
		panel_main.add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));

		txtr_result = new JTextArea();
		txtr_result.setMargin(new Insets(8, 8, 8, 8));
		txtr_result.setEditable(false);
		txtr_result.setLineWrap(true);
		txtr_result.setWrapStyleWord(true);
		txtr_result.setText("null");
		txtr_result.setFont(new Font("新細明體", Font.PLAIN, 18));

		scrollPane_result = new JScrollPane();
		scrollPane_result.setBorder(null);
		panel_center.add(scrollPane_result, BorderLayout.CENTER);
		scrollPane_result.setViewportView(txtr_result);

		panel_more = new JPanel();
		panel_more.setBackground(Color.WHITE);
		panel_main.add(panel_more, BorderLayout.SOUTH);
		panel_more.setLayout(new BorderLayout(0, 0));

		panel_more_card = new JPanel();
		panel_more.add(panel_more_card, BorderLayout.CENTER);
		panel_more_card.setLayout(new CardLayout(0, 0));

		panel_explanation = new JPanel();
		panel_more_card.add(panel_explanation, EXPLANATION);
		panel_explanation.setLayout(new BorderLayout(0, 0));

		textArea_explanation = new JTextArea();
		textArea_explanation.setMargin(new Insets(8, 8, 8, 8));
		textArea_explanation.setLineWrap(true);
		textArea_explanation.setWrapStyleWord(true);

		scrollPane_explanation = new JScrollPane();
		scrollPane_explanation.setViewportView(textArea_explanation);
		panel_explanation.add(scrollPane_explanation);

		panel_example = new JPanel();
		panel_more_card.add(panel_example, EXAMPLE);
		panel_example.setLayout(new BorderLayout(0, 0));

		textArea_example = new JTextArea();
		textArea_example.setMargin(new Insets(8, 8, 8, 8));
		textArea_example.setWrapStyleWord(true);
		textArea_example.setLineWrap(true);

		scrollPane_example = new JScrollPane();
		scrollPane_example.setViewportView(textArea_example);
		panel_example.add(scrollPane_example, BorderLayout.CENTER);

		panel_null = new JPanel();
		panel_more_card.add(panel_null, "null");

		panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_more.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new BorderLayout(0, 0));

		btnNewButton_more = new JButton("更多");
		btnNewButton_more.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isMoOpening == true) {
					isMoOpening = false;
				} else {
					isMoOpening = true;
				}
				initializeMo();
			}
		});
		btnNewButton_more.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_more.setPreferredSize(new Dimension(38, 24));
		btnNewButton_more.setBorder(new EmptyBorder(0, 0, 2, 0));
		btnNewButton_more.setBackground(Color.WHITE);
		panel_3.add(btnNewButton_more, BorderLayout.SOUTH);

		btnNewButton_type = new JButton("Type");
		btnNewButton_type.setBackground(Color.WHITE);
		btnNewButton_type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (explanationType.equals(EXPLANATION)) {
					explanationType = EXAMPLE;
				} else {
					explanationType = EXPLANATION;
				}
				initializeMo();
			}
		});
		panel_3.add(btnNewButton_type, BorderLayout.NORTH);
	}

	@Override
	public UIDateTransportation accpet(UIDateTransportation dt) {
		Component parent = (Component) dt.getParameter("parent");
		List<Vocabulary> vs = (List<Vocabulary>) dt.getParameter("vocabularies");

		if (parent != null) {
			this.setBounds(parent.getX() + (int) ((parent.getWidth() - this.getWidth()) * 0.25), parent.getY() - 35,
					this.getWidth(), this.getHeight());
		}

		StringBuilder sb = new StringBuilder();
		vs.forEach(x -> {
			sb.append(x.getTranslation() + "\n");
		});

		this.txt_vocabulary.setText(vs.get(0).getVocabulary());
		this.lblNewLabel_vocabulary.setText(vs.get(0).getVocabulary());
		this.txtr_result.setText(sb.toString());
		this.setVisible(true);
		this.txtr_result.setSelectionStart(0);
		this.txtr_result.setSelectionEnd(0);
		this.textArea_explanation.setText(vs.get(0).getExplanation());
		this.textArea_example.setText(vs.get(0).getExample());
		this.isMoOpening = false;
		initializeMo();

		return dt;
	}

	@Override
	public void setUIDateTransportation(UIDateTransportation dt) {

	}

	private void initializeMo() {
		if (this.isMoOpening) {
			this.btnNewButton_more.setText("收合");
			((CardLayout) this.panel_more_card.getLayout()).show(panel_more_card, this.explanationType);
			this.btnNewButton_type.setVisible(true);
			this.setMoHeight(200);
		} else {
			this.btnNewButton_more.setText("更多");
			((CardLayout) this.panel_more_card.getLayout()).show(panel_more_card, "null");
			this.btnNewButton_type.setVisible(false);
			this.explanationType = this.EXPLANATION;
			this.setMoHeight(0);
		}
		this.btnNewButton_type.setText(this.explanationType.equals(EXPLANATION) ? EXAMPLE : EXPLANATION);
	}

	private void setMoHeight(int increment) {
		this.panel_explanation
				.setPreferredSize(new Dimension((int) panel_explanation.getPreferredSize().getWidth(), increment));
		this.panel_example
				.setPreferredSize(new Dimension((int) panel_example.getPreferredSize().getWidth(), increment));
		this.setBounds(this.getX(), this.getY(), this.getWidth(), this.baseHeight + increment);
		this.textArea_explanation.setSelectionStart(0);
		this.textArea_explanation.setSelectionEnd(0);
		this.textArea_example.setSelectionStart(0);
		this.textArea_example.setSelectionEnd(0);
	}
}

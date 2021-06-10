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
import java.util.Arrays;
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
import com.control.exception.MyNullException;
import com.control.pad.PadFactory;
import com.control.viewcontrol.bridge.AddVocabularyQueryBridge;
import com.control.viewcontrol.bridge.OpenToAddVocabularyFrameBridge;
import com.model.Vocabulary;
import com.tool.ModelCollector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JComboBox;

public class QueryResultFrame extends JFrame implements Transportable {
	private QueryResultFrame thisFrame;
	private UIDateTransportation dt;
	private static final String EXPLANATION = "解釋";
	private static final String EXAMPLE = "例句";
	private final int baseHeight = 225;
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
	private JPanel panel_rightbar;
	private JButton btnNewButton_more;
	private JPanel panel_example;
	private JScrollPane scrollPane_example;
	private JTextArea textArea_example;
	private boolean isMoOpening = false;
	private String explanationType = EXPLANATION;
	private JButton btnNewButton_type;
	private JPanel panel;
	private JButton btnNewButton;
	private JPanel panel_scroll;
	private JPanel panel_null;
	private JPanel panel_translation;
	private JButton btnNewButton_former;
	private JButton btnNewButton_next;
	private JLabel lblNewLabel_mo_translation;
	private JButton btnNewButton_more_opento;
	private String serchType = PadFactory.SERCH_EXACTLY_MATCHING;
	private JPanel panel_vocabulary_card;
	private JComboBox<String> comboBox_vocabularies;

	public QueryResultFrame() {
		setType(Type.UTILITY);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setBounds(0, 0, 400, 290);
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

		btnNewButton = new JButton("模糊");
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (serchType.equals(PadFactory.SERCH_EXACTLY_MATCHING)) {
					serchType = PadFactory.SERCH_FUZZY_SERCH;
				} else {
					serchType = PadFactory.SERCH_EXACTLY_MATCHING;
				}
				PadFactory.query(null, txt_vocabulary.getText().trim(), serchType);
			}
		});
		btnNewButton.setMargin(new Insets(4, 4, 0, 4));
		panel.add(btnNewButton, BorderLayout.EAST);

		panel_vocabulary_card = new JPanel();
		panel.add(panel_vocabulary_card, BorderLayout.CENTER);
		panel_vocabulary_card.setLayout(new CardLayout(0, 0));

		lblNewLabel_vocabulary = new JLabel("null");
		panel_vocabulary_card.add(lblNewLabel_vocabulary, PadFactory.SERCH_EXACTLY_MATCHING);
		lblNewLabel_vocabulary.setBorder(new EmptyBorder(0, 4, 0, 0));
		lblNewLabel_vocabulary.setFont(new Font("標楷體", Font.BOLD, 16));
		
		comboBox_vocabularies = new JComboBox();
		comboBox_vocabularies.setBackground(SystemColor.controlHighlight);
		panel_vocabulary_card.add(comboBox_vocabularies, PadFactory.SERCH_FUZZY_SERCH);

		JPanel panel_center = new JPanel();
		panel_main.add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));

		txtr_result = new JTextArea();
		txtr_result.setBackground(Color.BLACK);
		txtr_result.setForeground(Color.WHITE);
		txtr_result.setMargin(new Insets(8, 8, 8, 8));
		txtr_result.setEditable(false);
		txtr_result.setLineWrap(true);
		txtr_result.setWrapStyleWord(true);
		txtr_result.setText("null");
		txtr_result.setFont(new Font("DialogInput", Font.PLAIN, 16));

		scrollPane_result = new JScrollPane();
		scrollPane_result.setBorder(null);
		panel_center.add(scrollPane_result, BorderLayout.CENTER);
		scrollPane_result.setViewportView(txtr_result);

		panel_scroll = new JPanel();
		panel_scroll.setLayout(new BorderLayout(0, 0));

		panel_translation = new JPanel();
		panel_translation.setBackground(Color.BLACK);
		panel_translation.setForeground(Color.WHITE);
		panel_scroll.add(panel_translation);

		lblNewLabel_mo_translation = new JLabel("null");
		lblNewLabel_mo_translation.setFont(new Font("DialogInput", Font.PLAIN, 12));
		lblNewLabel_mo_translation.setForeground(Color.WHITE);
		panel_translation.add(lblNewLabel_mo_translation);

		btnNewButton_former = new JButton("◁");
		btnNewButton_former.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModelCollector mc = (ModelCollector) dt.getParameter("mc");
				Vocabulary v = null;
				try {
					v = (Vocabulary) mc.getFormer();
				} catch (MyNullException ex) {
					ex.printStackTrace();
				}
				showData(v);
			}
		});
		btnNewButton_former.setMargin(new Insets(2, 4, 2, 4));
		btnNewButton_former.setForeground(Color.WHITE);
		btnNewButton_former.setBorderPainted(false);
		btnNewButton_former.setBackground(Color.BLACK);
		panel_scroll.add(btnNewButton_former, BorderLayout.WEST);

		btnNewButton_next = new JButton("▷");
		btnNewButton_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModelCollector mc = (ModelCollector) dt.getParameter("mc");
				Vocabulary v = null;
				try {
					v = (Vocabulary) mc.getNext();
				} catch (MyNullException ex) {
					ex.printStackTrace();
				}
				showData(v);
			}
		});
		btnNewButton_next.setMargin(new Insets(2, 4, 2, 4));
		btnNewButton_next.setForeground(Color.WHITE);
		btnNewButton_next.setBorderPainted(false);
		btnNewButton_next.setBackground(Color.BLACK);
		panel_scroll.add(btnNewButton_next, BorderLayout.EAST);

		panel_more = new JPanel();
		panel_more.setBorder(new LineBorder(SystemColor.controlHighlight, 1, true));
		panel_more.setBackground(Color.WHITE);
		panel_main.add(panel_more, BorderLayout.SOUTH);
		panel_more.setLayout(new BorderLayout(0, 0));

		panel_more.add(panel_scroll, BorderLayout.NORTH);

		panel_more_card = new JPanel();
		panel_more.add(panel_more_card, BorderLayout.CENTER);
		panel_more_card.setLayout(new CardLayout(0, 0));

		panel_explanation = new JPanel();
		panel_more_card.add(panel_explanation, EXPLANATION);
		panel_explanation.setLayout(new BorderLayout(0, 0));

		textArea_explanation = new JTextArea();
		textArea_explanation.setBackground(Color.BLACK);
		textArea_explanation.setForeground(Color.WHITE);
		textArea_explanation.setFont(new Font("DialogInput", Font.PLAIN, 16));
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
		textArea_example.setBackground(Color.BLACK);
		textArea_example.setForeground(Color.WHITE);
		textArea_example.setFont(new Font("DialogInput", Font.PLAIN, 16));
		textArea_example.setMargin(new Insets(8, 8, 8, 8));
		textArea_example.setWrapStyleWord(true);
		textArea_example.setLineWrap(true);

		scrollPane_example = new JScrollPane();
		scrollPane_example.setViewportView(textArea_example);
		panel_example.add(scrollPane_example, BorderLayout.CENTER);

		panel_null = new JPanel();
		panel_null.setBackground(Color.BLACK);
		panel_more_card.add(panel_null, "null");

		panel_rightbar = new JPanel();
		panel_rightbar.setBorder(new LineBorder(SystemColor.controlHighlight));
		panel_rightbar.setBackground(Color.BLACK);
		panel_more.add(panel_rightbar, BorderLayout.EAST);
		panel_rightbar.setLayout(new BorderLayout(0, 0));

		btnNewButton_more = new JButton("更多...");
		btnNewButton_more.setBorderPainted(false);
		btnNewButton_more.setForeground(Color.WHITE);
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
		btnNewButton_more.setBackground(Color.BLACK);
		panel_rightbar.add(btnNewButton_more, BorderLayout.SOUTH);

		btnNewButton_type = new JButton("Type");
		btnNewButton_type.setForeground(Color.WHITE);
		btnNewButton_type.setBorderPainted(false);
		btnNewButton_type.setBackground(Color.BLACK);
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
		panel_rightbar.add(btnNewButton_type, BorderLayout.NORTH);

		btnNewButton_more_opento = new JButton("開啟至");
		btnNewButton_more_opento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModelCollector<?> mc = (ModelCollector<?>) dt.getParameter("mc");
				Vocabulary vocabulary = (Vocabulary) mc.get();
				OpenToAddVocabularyFrameBridge bridge = new OpenToAddVocabularyFrameBridge();
				bridge.setParameter("vocabulary", vocabulary);
				bridge.setParameter("parent", thisFrame);
				bridge.getDispatcher().send();
			}
		});
		btnNewButton_more_opento.setForeground(Color.WHITE);
		btnNewButton_more_opento.setBorderPainted(false);
		btnNewButton_more_opento.setBackground(Color.BLACK);
		panel_rightbar.add(btnNewButton_more_opento, BorderLayout.CENTER);

		/*
		 * init
		 */
		initializeMo();
	}

	@Override
	public UIDateTransportation accpet(UIDateTransportation dt) {
		this.dt = dt;
		Component parent = (Component) dt.getParameter("parent");

		String queryStr = (String) dt.getParameter("vocabulary");
		String queryType = (String) dt.getParameter("type");
		List<Vocabulary> vs = null;
		List<String> ss = null;
		if (queryType.equals(PadFactory.SERCH_EXACTLY_MATCHING)) {
			vs = (List<Vocabulary>) dt.getParameter("vocabularies");
			((CardLayout)this.panel_vocabulary_card.getLayout()).show(panel_vocabulary_card, PadFactory.SERCH_EXACTLY_MATCHING);
		} else if (queryType.equals(PadFactory.SERCH_FUZZY_SERCH)) {
			ss = (List<String>) dt.getParameter("fuzzyvocabularies");
			((CardLayout)this.panel_vocabulary_card.getLayout()).show(panel_vocabulary_card, PadFactory.SERCH_FUZZY_SERCH);
			String [] arrs=new String[ss.size()];
			ss.toArray(arrs);
			this.comboBox_vocabularies=new JComboBox<String>(arrs);
			this.panel_vocabulary_card .add(comboBox_vocabularies,PadFactory.SERCH_FUZZY_SERCH);
			System.out.println("query result frame ** ");
			Arrays.asList(arrs).stream().forEach(x->System.out.println(x));
		}

		if (parent != null) {
			this.setBounds(parent.getX() + (int) ((parent.getWidth() - this.getWidth()) * 0.25), parent.getY() - 35,
					this.getWidth(), this.getHeight());
		}

		StringBuilder sb = new StringBuilder();
		vs.forEach(x -> {
			sb.append(x.getTranslation() + "\n");
		});

		ModelCollector<Vocabulary> mc = new ModelCollector<Vocabulary>(vs);
		dt.setParameter("mc", mc);
		Vocabulary v = null;
		try {
			v = (Vocabulary) mc.getNext();
		} catch (MyNullException e) {
			e.printStackTrace();
		}
		this.showData(v);

		this.txt_vocabulary.setText(queryStr);
		this.lblNewLabel_vocabulary.setText(v.getVocabulary());
		this.txtr_result.setText(sb.toString());
		this.txtr_result.setSelectionStart(0);
		this.txtr_result.setSelectionEnd(0);
		this.isMoOpening = false;
		this.setVisible(true);
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
			this.setMoHeight(300);
		} else {
			this.btnNewButton_more.setText("更多");
			((CardLayout) this.panel_more_card.getLayout()).show(panel_more_card, "null");
			this.explanationType = this.EXPLANATION;
			this.setMoHeight(0);
		}
		this.btnNewButton_type.setVisible(isMoOpening);
		this.btnNewButton_more_opento.setVisible(isMoOpening);
		this.panel_scroll.setVisible(isMoOpening);
		this.btnNewButton_type.setText("例  句");
	}

	private void showData(Vocabulary v) {
		ModelCollector<?> mc = (ModelCollector<?>) dt.getParameter("mc");
		this.lblNewLabel_mo_translation.setText(v.getTranslation());
		this.textArea_explanation.setText(v.getExplanation());
		this.textArea_example.setText(v.getExample());
		this.btnNewButton_former.setVisible(mc.hasFormer());
		this.btnNewButton_next.setVisible(mc.hasNext());
		initializeMoSelectText();
	}

	private void setMoHeight(int incrementHeight) {
		this.panel_explanation.setPreferredSize(
				new Dimension((int) panel_explanation.getPreferredSize().getWidth(), incrementHeight));
		this.panel_example
				.setPreferredSize(new Dimension((int) panel_example.getPreferredSize().getWidth(), incrementHeight));
		this.setBounds(this.getX(), this.getY(), this.getWidth(), this.baseHeight + incrementHeight);
		initializeMoSelectText();
	}

	private void initializeMoSelectText() {
		this.textArea_explanation.setSelectionStart(0);
		this.textArea_explanation.setSelectionEnd(0);
		this.textArea_example.setSelectionStart(0);
		this.textArea_example.setSelectionEnd(0);
	}
}

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
import javax.swing.DefaultComboBoxModel;
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
import com.tool.MyColor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class QueryResultFrame extends JFrame implements Transportable {
	public final static String SEARCH_FUZZY_SERCH = "模糊";
	public final static String SEARCH_EXACTLY_MATCHING = "精準";
	private QueryResultFrame thisFrame;
	private UIDateTransportation dt;
	private static final String EXPLANATION = "解釋";
	private static final String EXAMPLE = "例句";
	private final int baseHeight = 225;
	private JTextField txt_input;
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
	private JButton btnNewButton_fuzzy;
	private JPanel panel_hScroll;
	private JPanel panel_null;
	private JPanel panel_translation;
	private JButton btnNewButton_former;
	private JButton btnNewButton_next;
	private JLabel lblNewLabel_mo_translation;
	private JButton btnNewButton_more_opento;
	private String serchType =this.SEARCH_EXACTLY_MATCHING;
	private JPanel panel_vocabulary_card;
	private JComboBox<String> comboBox_vocabularies;
	private JPanel panel_1;
	private JButton btnNewButton_exactly;

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

		txt_input = new JTextField();
		txt_input.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					txt_input.setText((String) dt.getParameter("vocabulary"));
				}
			}
		});
		panel_top.add(txt_input);
		txt_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				PadFactory.query(null,  txt_input.getText().trim(), serchType);
			}
		});
		txt_input.setFont(new Font("標楷體", Font.BOLD, 16));
		txt_input.setHorizontalAlignment(SwingConstants.CENTER);
		txt_input.setText("null");
		txt_input.setColumns(20);

		panel = new JPanel();
		panel_top.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		panel_vocabulary_card = new JPanel();
		panel.add(panel_vocabulary_card, BorderLayout.CENTER);
		panel_vocabulary_card.setLayout(new CardLayout(0, 0));

		lblNewLabel_vocabulary = new JLabel("null");
		panel_vocabulary_card.add(lblNewLabel_vocabulary,this.SEARCH_EXACTLY_MATCHING);
		lblNewLabel_vocabulary.setBorder(new EmptyBorder(0, 4, 0, 0));
		lblNewLabel_vocabulary.setFont(new Font("標楷體", Font.BOLD, 16));

		comboBox_vocabularies = new JComboBox();
		comboBox_vocabularies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PadFactory.query(null, comboBox_vocabularies.getSelectedItem().toString().trim(), PadFactory.SEARCH_EXACTLY);
				refreshDueToInputChange();
			}
		});
		comboBox_vocabularies.setFont(new Font("標楷體", Font.BOLD, 16));
		comboBox_vocabularies.setBackground(SystemColor.controlHighlight);
		panel_vocabulary_card.add(comboBox_vocabularies,this.SEARCH_FUZZY_SERCH);

		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.EAST);

		btnNewButton_exactly = new JButton("精準");
		btnNewButton_exactly.setFont(new Font("標楷體", Font.PLAIN, 13));
		btnNewButton_exactly.setFocusPainted(false);
		btnNewButton_exactly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serchType = SEARCH_EXACTLY_MATCHING;
				PadFactory.query(null, txt_input.getText().trim(), serchType);
				refreshDueToInputChange();
			}
		});
		btnNewButton_exactly.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		btnNewButton_exactly.setMargin(new Insets(4, 4, 0, 4));
		btnNewButton_exactly.setBackground(SystemColor.controlHighlight);
		panel_1.add(btnNewButton_exactly);

		btnNewButton_fuzzy = new JButton("模糊");
		btnNewButton_fuzzy.setFont(new Font("標楷體", Font.PLAIN, 13));
		btnNewButton_fuzzy.setFocusPainted(false);
		btnNewButton_fuzzy.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		panel_1.add(btnNewButton_fuzzy);
		btnNewButton_fuzzy.setBackground(SystemColor.controlHighlight);
		btnNewButton_fuzzy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serchType = SEARCH_FUZZY_SERCH;
				PadFactory.query(null, txt_input.getText().trim(), serchType);
				refreshDueToInputChange();
			}
		});
		btnNewButton_fuzzy.setMargin(new Insets(4, 4, 0, 4));

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

		panel_hScroll = new JPanel();
		panel_hScroll.setLayout(new BorderLayout(0, 0));

		panel_translation = new JPanel();
		panel_translation.setBackground(Color.BLACK);
		panel_translation.setForeground(Color.WHITE);
		panel_hScroll.add(panel_translation);

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
				refreshDueToPressHScrollBtn(v);
			}
		});
		btnNewButton_former.setMargin(new Insets(2, 4, 2, 4));
		btnNewButton_former.setForeground(Color.WHITE);
		btnNewButton_former.setBorderPainted(false);
		btnNewButton_former.setBackground(Color.BLACK);
		panel_hScroll.add(btnNewButton_former, BorderLayout.WEST);

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
				refreshDueToPressHScrollBtn(v);
			}
		});
		btnNewButton_next.setMargin(new Insets(2, 4, 2, 4));
		btnNewButton_next.setForeground(Color.WHITE);
		btnNewButton_next.setBorderPainted(false);
		btnNewButton_next.setBackground(Color.BLACK);
		panel_hScroll.add(btnNewButton_next, BorderLayout.EAST);

		panel_more = new JPanel();
		panel_more.setBorder(new LineBorder(SystemColor.controlHighlight, 1, true));
		panel_more.setBackground(Color.WHITE);
		panel_main.add(panel_more, BorderLayout.SOUTH);
		panel_more.setLayout(new BorderLayout(0, 0));

		panel_more.add(panel_hScroll, BorderLayout.NORTH);

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
				refreshDueToPressMoreBtn();
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
				refreshDueToPressMoreBtn();
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
		refreshDueToPressMoreBtn();
		refreshDueToInputChange();
	}

	@Override
	public UIDateTransportation accpet(UIDateTransportation dt) {
		this.dt = dt;
		Component parent = (Component) dt.getParameter("parent");
		String queryStr = (String) dt.getParameter("vocabulary");
		this.serchType = (String) dt.getParameter("type");

		List<String> ss = null;
		if (this.serchType.equals(this.SEARCH_FUZZY_SERCH)) {
			ss = (List<String>) dt.getParameter("fuzzyvocabularies");
			this.comboBox_vocabularies.setModel(new DefaultComboBoxModel(ss.toArray(new String[ss.size()])));
		}
		
		List<Vocabulary> vs = (List<Vocabulary>) dt.getParameter("vocabularies");

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
		
		if(this.serchType.equals(PadFactory.SEARCH_FUZZY)) {
			this.txt_input.setText(queryStr);
		}
		this.lblNewLabel_vocabulary.setText(v.getVocabulary());
		this.txtr_result.setText(sb.toString());
		this.txtr_result.setSelectionStart(0);
		this.txtr_result.setSelectionEnd(0);
		this.isMoOpening = false;
		this.setVisible(true);

		refreshDueToInputChange();
		refreshDueToPressMoreBtn();
		refreshDueToPressHScrollBtn(v);

		return dt;
	}

	@Override
	public void setUIDateTransportation(UIDateTransportation dt) {

	}

	private void refreshDueToInputChange() {
		if (this.serchType.equals(this.SEARCH_EXACTLY_MATCHING)) {
			this.btnNewButton_exactly.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.RAISED, null, null),
					new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
			this.btnNewButton_fuzzy
					.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
							new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		} else if (this.serchType.equals(this.SEARCH_FUZZY_SERCH)) {
			this.btnNewButton_exactly
					.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
							new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
			this.btnNewButton_fuzzy.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.RAISED, null, null),
					new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		}
		((CardLayout) this.panel_vocabulary_card.getLayout()).show(panel_vocabulary_card, this.serchType);
	}

	/*
	 * 基本來自於 按下"更多"或"收合"
	 */
	private void refreshDueToPressMoreBtn() {
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
		this.btnNewButton_type.setText("例  句");
		this.btnNewButton_more_opento.setVisible(isMoOpening);
		this.panel_hScroll.setVisible(isMoOpening);
	}

	/*
	 * 基本來自於 按下水平捲軸
	 */
	private void refreshDueToPressHScrollBtn(Vocabulary v) {
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

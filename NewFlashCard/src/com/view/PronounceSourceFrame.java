package com.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;

import com.control.bridge.Transportable;
import com.control.bridge.session.UIDateTransportation;
import com.control.pronounce.PronounceFormatStrFactory;
import com.control.pronounce.bridge.AddPronounceFomatStrBridge;
import com.control.pronounce.bridge.EditPronounceFomatStrBridge;
import com.control.pronounce.bridge.MovePronounceFomatStrBridge;
import com.control.pronounce.bridge.RemovePronounceFomatStrBridge;
import com.tool.ConfigFactory;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Insets;

public class PronounceSourceFrame extends JFrame implements Transportable {
	public final static String ADD = "add";
	public final static String EDIT = "edit";
	public final static String NULL = "null";
	private static String editType = NULL;
	private JList list_source;
	private JTextField textField_add;
	private JTextField textField_edit;
	private JPanel panel_card_bottom;
	private JLabel lblNewLabel_add;
	private JLabel lblNewLabel_edit;

	public PronounceSourceFrame() {
		setTitle("發音網站來源");
		this.setBounds(100, 100, 840, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		list_source = new JList();
		list_source.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				initEditType(NULL);
			}
		});
		list_source.setFont(new Font("DialogInput", Font.PLAIN, 16));
		list_source.setBorder(new LineBorder(SystemColor.controlHighlight, 3, true));
		panel.add(list_source, BorderLayout.CENTER);

		JPanel panel_rbar = new JPanel();
		panel.add(panel_rbar, BorderLayout.EAST);
		panel_rbar.setLayout(new BoxLayout(panel_rbar, BoxLayout.Y_AXIS));

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(0, 10));
		panel_rbar.add(panel_1);

		JPanel panel_4 = new JPanel();
		panel_rbar.add(panel_4);

		JButton btnNewButton_add = new JButton("新增");
		btnNewButton_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initEditType(ADD);
			}
		});
		btnNewButton_add.setBackground(SystemColor.control);
		panel_4.add(btnNewButton_add);
		btnNewButton_add.setFont(new Font("微軟正黑體", Font.PLAIN, 16));

		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(0, 10));
		panel_rbar.add(panel_2);

		JPanel panel_5 = new JPanel();
		panel_rbar.add(panel_5);

		JButton btnNewButton_edit = new JButton("編輯");
		btnNewButton_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initEditType(EDIT);
			}
		});
		btnNewButton_edit.setBackground(SystemColor.control);
		panel_5.add(btnNewButton_edit);
		btnNewButton_edit.setFont(new Font("微軟正黑體", Font.PLAIN, 16));

		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(0, 10));
		panel_rbar.add(panel_3);

		JPanel panel_6 = new JPanel();
		panel_rbar.add(panel_6);

		JButton btnNewButton_remove = new JButton("移除");
		btnNewButton_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemovePronounceFomatStrBridge bridge =new RemovePronounceFomatStrBridge();
				bridge.setParameter("index",list_source.getSelectedIndex() );
				bridge.getDispatcher().send();
				initEditType(NULL);
			}
		});
		btnNewButton_remove.setBackground(SystemColor.control);
		panel_6.add(btnNewButton_remove);
		btnNewButton_remove.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		
		JPanel panel_12 = new JPanel();
		panel_12.setPreferredSize(new Dimension(10, 30));
		panel_rbar.add(panel_12);
		
		JPanel panel_10 = new JPanel();
		panel_rbar.add(panel_10);
		JButton btnNewButton_remove_2 = new JButton("上移");
		btnNewButton_remove_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MovePronounceFomatStrBridge bridge=new MovePronounceFomatStrBridge();
				bridge.setParameter("movetype", "up");
				bridge.setParameter("index",list_source.getSelectedIndex() );
				list_source.setSelectedIndex((int) bridge.getDispatcher().callback());
			}
		});
		btnNewButton_remove_2.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		btnNewButton_remove_2.setBackground(SystemColor.menu);
		panel_10.add(btnNewButton_remove_2);
		
		JPanel panel_13 = new JPanel();
		panel_rbar.add(panel_13);
		
		JPanel panel_11 = new JPanel();
		panel_rbar.add(panel_11);
		
		JButton btnNewButton_remove_2_1 = new JButton("下移");
		btnNewButton_remove_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MovePronounceFomatStrBridge bridge=new MovePronounceFomatStrBridge();
				bridge.setParameter("movetype", "down");
				bridge.setParameter("index",list_source.getSelectedIndex() );
				list_source.setSelectedIndex((int) bridge.getDispatcher().callback());
			}
		});
		btnNewButton_remove_2_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		btnNewButton_remove_2_1.setBackground(SystemColor.menu);
		panel_11.add(btnNewButton_remove_2_1);

		JPanel panel_7 = new JPanel();
		panel_7.setPreferredSize(new Dimension(100, 350));
		panel_rbar.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_rbar.add(panel_8);
		
		JButton btnNewButton_remove_1 = new JButton("套用");
		btnNewButton_remove_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PronounceFormatStrFactory.setProperties();
			}
		});
		btnNewButton_remove_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		btnNewButton_remove_1.setBackground(SystemColor.menu);
		panel_8.add(btnNewButton_remove_1);
		
		JPanel panel_9 = new JPanel();
		panel_9.setPreferredSize(new Dimension(10, 30));
		panel_rbar.add(panel_9);

		panel_card_bottom = new JPanel();
		panel_card_bottom.setVisible(false);
		panel.add(panel_card_bottom, BorderLayout.SOUTH);
		panel_card_bottom.setLayout(new CardLayout(0, 0));

		JPanel panel_add = new JPanel();
		panel_card_bottom.add(panel_add, ADD);

		lblNewLabel_add = new JLabel("新增");
		lblNewLabel_add.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_add.add(lblNewLabel_add);

		textField_add = new JTextField();
		textField_add.setMargin(new Insets(2, 4, 2, 2));
		textField_add.setFont(new Font("DialogInput", Font.PLAIN, 16));
		textField_add.setColumns(50);
		panel_add.add(textField_add);

		JButton btnNewButton_1 = new JButton("確認");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddPronounceFomatStrBridge bridge =new AddPronounceFomatStrBridge();
				bridge.setParameter("index",list_source.getSelectedIndex() );
				bridge.setParameter("formatstr",textField_add.getText().trim() );
				bridge.getDispatcher().send();
			}
		});
		btnNewButton_1.setBackground(SystemColor.control);
		btnNewButton_1.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		panel_add.add(btnNewButton_1);

		JPanel panel_edit = new JPanel();
		panel_card_bottom.add(panel_edit, EDIT);

		lblNewLabel_edit = new JLabel("編輯");
		lblNewLabel_edit.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_edit.add(lblNewLabel_edit);

		textField_edit = new JTextField();
		textField_edit.setMargin(new Insets(2, 4, 2, 2));
		textField_edit.setFont(new Font("DialogInput", Font.PLAIN, 16));
		textField_edit.setColumns(50);
		panel_edit.add(textField_edit);
		JButton btnNewButton = new JButton("確認");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditPronounceFomatStrBridge bridge =new EditPronounceFomatStrBridge();
				bridge.setParameter("index",list_source.getSelectedIndex() );
				bridge.setParameter("formatstr",textField_edit.getText().trim() );
				bridge.getDispatcher().send();
			}
		});
		btnNewButton.setBackground(SystemColor.control);
		btnNewButton.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		panel_edit.add(btnNewButton);
	}

	public void setInitPosition(int x, int y) {
		this.setBounds(x, y, this.getWidth(), this.getHeight());
	}

	@Override
	public void accpet(UIDateTransportation dt) {
		DefaultListModel elements = (DefaultListModel) dt.getParameter("model");
		elements.add(elements.size(), "");
		this.list_source.setModel(elements);

		initEditType(NULL);
	}

	private void initEditType(String type) {

		int index = this.list_source.getSelectedIndex();
		switch (type) {
		case ADD:
			if (index == -1) {
				index = this.list_source.getModel().getSize();
			} else {
				index++;
			}
			((CardLayout) this.panel_card_bottom.getLayout()).show(panel_card_bottom, ADD);
			this.lblNewLabel_add.setText("新增到第 " + index + " 筆");
			panel_card_bottom.setVisible(true);
			this.textField_add.setText("");
			break;
		case EDIT:
			if (index == -1) {
				panel_card_bottom.setVisible(false);
				return;
			} else {
				index++;
			}
			((CardLayout) this.panel_card_bottom.getLayout()).show(panel_card_bottom, EDIT);
			this.lblNewLabel_edit.setText("編輯第 " + index + " 筆");
			panel_card_bottom.setVisible(true);
			this.textField_edit.setText(this.list_source.getSelectedValue().toString());
			break;
		case NULL:
			panel_card_bottom.setVisible(false);
			break;
		default:
			break;
		}

	}

}

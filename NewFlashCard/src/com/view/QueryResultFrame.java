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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class QueryResultFrame extends JFrame {
	private JTextField txt_vocabulary;
	private JTextArea txtr_result;
	private JLabel lblNewLabel_vocabulary;
	private JScrollPane scrollPane_result;
	private JPanel panel_main;

	public QueryResultFrame() {
		setType(Type.UTILITY);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setBounds(0, 0, 400, 200);
		
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
										Pads.query(txt_vocabulary.getText());
									}
								});
								txt_vocabulary.setFont(new Font("標楷體", Font.BOLD, 16));
								txt_vocabulary.setHorizontalAlignment(SwingConstants.CENTER);
								txt_vocabulary.setText("null");
								txt_vocabulary.setColumns(20);
								
										lblNewLabel_vocabulary = new JLabel("null");
										lblNewLabel_vocabulary.setHorizontalAlignment(SwingConstants.CENTER);
										lblNewLabel_vocabulary.setFont(new Font("標楷體", Font.BOLD, 16));
										panel_top.add(lblNewLabel_vocabulary);
										
												JPanel panel_center = new JPanel();
												panel_main.add(panel_center, BorderLayout.CENTER);
												panel_center.setLayout(new BorderLayout(0, 0));
												
														txtr_result = new JTextArea();
														txtr_result.setEditable(false);
														txtr_result.setLineWrap(true);
														txtr_result.setWrapStyleWord(true);
														txtr_result.setMargin(new Insets(8, 8, 8, 8));
														txtr_result.setText("null");
														txtr_result.setFont(new Font("新細明體", Font.PLAIN, 18));
														
														scrollPane_result = new JScrollPane();
														panel_center.add(scrollPane_result, BorderLayout.CENTER);
														scrollPane_result.setViewportView(txtr_result);
	}


	public void query(Component parent,List< Vocabulary> results) {
		if (parent != null) {
			this.setBounds(parent.getX()+(int)((parent.getWidth()-this.getWidth())*0.25), parent.getY()-35, this.getWidth(), this.getHeight());
		}
		
		StringBuilder sb=new StringBuilder();
		results.forEach(x->{
			sb.append(x.getTranslation()+"\n");
		});
		
		this.txt_vocabulary.setText(results.get(0).getVocabulary());
		this.lblNewLabel_vocabulary.setText(results.get(0).getVocabulary());
		this.txtr_result.setText(sb.toString());
		this.setVisible(true);
		this.txtr_result.setSelectionStart(0);
		this.txtr_result.setSelectionEnd(0);
	}

}

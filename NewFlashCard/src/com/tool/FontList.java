package com.tool;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FontList extends JFrame {
	static String[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	static JLabel[] labelList = new JLabel[fontList.length];

	public static void main(String[] args) {
		FontList f = new FontList();
		for (String i : fontList)
			System.out.println(i);
	}

	public FontList() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new GridLayout(fontList.length, 1));
		for (int i = 0; i < fontList.length; i++) {
			labelList[i] = new JLabel(fontList[i] +" :: "+"unconscious\r\n"
					+ "KK[ʌnˋkɑnʃəs]DJ[ʌnˋkɔnʃəs]\r\n"
					+ "adj.不省人事的");
			labelList[i].setFont(new Font(fontList[i], Font.PLAIN, 24));
			panel.add(labelList[i]);
		}
		add(new JScrollPane(panel));
		pack();
		setVisible(true);
	}

}

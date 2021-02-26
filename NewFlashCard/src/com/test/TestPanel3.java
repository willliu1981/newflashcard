package com.test;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class TestPanel3 extends JPanel {
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public TestPanel3() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("New labelc");
		panel.add(lblNewLabel, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("New labelt");
		panel.add(lblNewLabel_1, BorderLayout.NORTH);

	}
	
	public static void main(String[] s) {
		TestPanel3 p=new TestPanel3();
		JLabel lbl=(JLabel)((BorderLayout)p.panel.getLayout()).getLayoutComponent("North");
		
		System.out.println(lbl.getText());
	}

}

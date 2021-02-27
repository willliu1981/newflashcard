package com.test;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.Component;

public class TestPanel3 extends JPanel {
	private JPanel panel;
	private JPanel panel_cardlayout;

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
		
		panel_cardlayout = new JPanel();
		panel.add(panel_cardlayout, BorderLayout.SOUTH);
		panel_cardlayout.setLayout(new CardLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_cardlayout.add(panel_1, "p1");
		
		JPanel panel_2 = new JPanel();
		panel_cardlayout.add(panel_2, "p2");

	}
	
	public static void main(String[] s) {
		TestPanel3 p=new TestPanel3();
		JLabel lbl=(JLabel)((BorderLayout)p.panel.getLayout()).getLayoutComponent("North");
		
		System.out.println(lbl.getText());
		System.out.println("xxxx");

		Component [] comps=p.getComponents();
		for(Component _p:comps) {
		
		}
		
		
	}

}

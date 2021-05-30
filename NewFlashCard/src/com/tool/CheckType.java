package com.tool;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class CheckType extends JFrame {

	private JPanel contentPane;
	private JPanel panel_1;
	private JLabel lblNewLabel_msg2;
	private JLabel lblNewLabel_msg1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckType frame = new CheckType();
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
	public CheckType() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JTextArea textArea_input1 = new JTextArea();
		textArea_input1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				lblNewLabel_msg1.setText(""+(int)e.getKeyChar());
			}
		});
		textArea_input1.setFont(new Font("Monospaced", Font.PLAIN, 18));
		panel_1.add(textArea_input1);
		
		lblNewLabel_msg1 = new JLabel("New label");
		lblNewLabel_msg1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_msg1.setPreferredSize(new Dimension(120, 15));
		lblNewLabel_msg1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_msg1);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JTextArea textArea_input2 = new JTextArea();
		textArea_input2.setFont(new Font("Monospaced", Font.PLAIN, 18));
		panel_2.add(textArea_input2);
		
		lblNewLabel_msg2 = new JLabel("New label");
		lblNewLabel_msg2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_msg2.setPreferredSize(new Dimension(120, 15));
		lblNewLabel_msg2.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_2.add(lblNewLabel_msg2);
	}

}

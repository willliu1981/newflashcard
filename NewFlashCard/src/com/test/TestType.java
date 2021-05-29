package com.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;

import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Hashtable;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestType extends JFrame {

	private JPanel contentPane;
	String temp = "";
	String _temp = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestType frame = new TestType();
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

	public TestType() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		int offset = 0;
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// System.out.println("mp **"+textArea.getSelectionStart());
//				try {
				// System.out.format("press ** %s \n",
				// textArea.getLineOfOffset(textArea.getCaretPosition()));

//				} catch (BadLocationException e1) {
//					e1.printStackTrace();
//				}
			}
		});

		Keymap parent = textArea.getKeymap();
		Keymap newmap = JTextComponent.addKeymap("k1", parent);
		Hashtable lookup = new Hashtable();
		Action actList[] = textArea.getActions();
		for (int i = 0; i < actList.length; i++) {
			lookup.put(actList[i].getValue(Action.NAME), actList[i]);
		}
		KeyStroke sl = KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK);
		Action actSL = (Action) lookup.get(DefaultEditorKit.selectLineAction);

		newmap.addActionForKeyStroke(sl, actSL);
		textArea.setKeymap(newmap);

		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// System.out.println("press** "+ textArea.getText());
				// System.out.println("press** " +(int)e.getKeyCode());
				_temp = textArea.getText();
				// System.out.format("press ** %s , %s\n",
				// textArea.getSelectionStart(),textArea.getSelectionEnd());

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// System.out.println(((int) e.getKeyChar()) + "," + textArea.getText());
				// System.out.println("x2 "+ textArea.getText() );
				// temp=textArea.getText();
				// System.out.format( "%s , %s\n",temp,textArea.getText());
				if ((int) e.getKeyChar() == 22) {
					// temp = _temp;
				} else if ((int) e.getKeyChar() == 26) {
					// System.out.println(temp);
					textArea.setText(temp);
				} else if ((int) e.getKeyChar() == 24) {
					try {
						int line = textArea.getLineOfOffset(textArea.getCaretPosition());
						textArea.setSelectionStart(textArea.getLineStartOffset(line));
						textArea.setSelectionEnd(textArea.getLineEndOffset(line));
						String ss=textArea.getText();
						String ff = ss.substring(0, textArea.getSelectionStart());
						String rr = ss.substring(textArea.getSelectionEnd(), textArea.getText().length());
						textArea.setText(ff + rr);
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					} catch (NullPointerException e1) {
						e1.printStackTrace();
					}
				}

				System.out.format("%s , %s\n", textArea.getSelectionStart(), textArea.getSelectionEnd());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// System.out.println("x3 "+ textArea.getText() );
				if (!_temp.equals(textArea.getText())) {
					temp = _temp;
				}
			}

		});
		contentPane.add(textArea, BorderLayout.CENTER);
	}

}

package com.control.pad;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Hashtable;

import javax.swing.Action;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;

public class MyPad extends Pad {

	@Override
	public void change(Component parent, String name, KeyEvent e) {
		/*
		 * 非按下Ctrl+A 和 Ctrl+C
		 */
		if ((int) e.getKeyChar() != 1 && (int) e.getKeyChar() != 3) {
			parent.setBackground(Color.red);
			this.setChange(name);
		}
	}

	@Override
	public void setKeymap(String name, JTextComponent comp) {
		/*
		Keymap parent = comp.getKeymap();
		Keymap newmap = JTextComponent.addKeymap("k1", parent);
		Hashtable<Object, Action> lookup = new Hashtable<Object, Action>();
		Action actList[] = comp.getActions();
		for (int i = 0; i < actList.length; i++) {
			lookup.put(actList[i].getValue(Action.NAME), actList[i]);
		}
		KeyStroke sl = KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK);
		Action actSL = (Action) lookup.get(DefaultEditorKit.selectLineAction);

		newmap.addActionForKeyStroke(sl, actSL);
		comp.setKeymap(newmap);
		*/
	}

	@Override
	public void keyAction_pressed(String frame, JTextComponent comp, KeyEvent e) {
		super.keyAction_typed(frame, comp, e);
		
		setContentTemp(frame, comp.getText());
	}

	@Override
	public void keyAction_typed(String frame, JTextComponent comp, KeyEvent e) {
		super.keyAction_typed(frame, comp, e);
		
		JTextArea textArea = (JTextArea) comp;

		try {
			switch ((int) e.getKeyChar()) {
			case 24:// "x" delete line
				int line = textArea.getLineOfOffset(textArea.getCaretPosition());
				textArea.setSelectionStart(textArea.getLineStartOffset(line));
				textArea.setSelectionEnd(textArea.getLineEndOffset(line));
				String ss = textArea.getText();
				String ff = ss.substring(0, textArea.getSelectionStart());
				String rr = ss.substring(textArea.getSelectionEnd(), textArea.getText().length());
				textArea.setText(ff + rr);
				break;
			case 26:// "z" revert content
				textArea.setText(getReverseContent(frame));
				break;
			default:

			}
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void keyAction_release(String frame, JTextComponent comp, KeyEvent e) {
		super.keyAction_typed(frame, comp, e);
		
		if (!getContentTemp(frame).equals(comp.getText())) {
			setContentTemp(frame, getContentTemp(frame));
		}
	}

}

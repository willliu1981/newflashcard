package com.control.pad;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.util.List;

import javax.swing.Action;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;

import com.control.dao.VocabularyDao;
import com.control.viewcontrol.bridge.QueryResultBridge;
import com.control.viewcontrol.bridge.QueryResultBridge2;
import com.model.Vocabulary;
import com.tool.Mask;
import com.tool.MyColor;

public class MyPad2 extends TextPad {
	private static final int defaultLimit = 100;

	@Override
	public void change(Component parent, String name, KeyEvent e) {
		String frame = "";
		switch (name) {
		case PadFactory.MAIN_ADDVOCABULARYFRAME_EXAMPLE:
		case PadFactory.MAIN_ADDVOCABULARYFRAME_EXPLANATION:
		case PadFactory.MAIN_ADDVOCABULARYFRAME_TRANSLATION:
			frame = PadFactory.ADDVOCABULARYFRAME;
			break;
		case PadFactory.MAIN_EXPLANATIONFRAME_EXAMPLE:
		case PadFactory.MAIN_EXPLANATIONFRAME_EXPLANATION:
		case PadFactory.MAIN_EXPLANATIONFRAME_TRANSLATION:
			frame = PadFactory.EXPLANATIONFRAME;
			break;
		default:
			break;
		}
		
		PadFactory.change(parent, this.adaptType(frame), e);
	}

	@Override
	public void keyAction_typed(Mask mask, JTextComponent comp, KeyEvent e) {
		
		try {
			int primaryOffset = comp.getCaretPosition();
			int ls = 0;
			int le =0;
			if(comp instanceof JTextArea) {
				int line = ((JTextArea) comp).getLineOfOffset(primaryOffset);
				 ls = ((JTextArea) comp).getLineStartOffset(line);
				 le = ((JTextArea) comp).getLineEndOffset(line);
			}else if(comp instanceof JTextField) {
				 le =comp.getText().length();
			}
			
			int offsetLS = ls;
			String ss = comp.getText();
			String ff = "";
			String rr = "";
			/*
			 * ctrl+key
			 */
			switch ((int) e.getKeyChar()) {
			case 23:// "w" select line
				comp.setSelectionStart(ls);
				comp.setSelectionEnd(le - 1);
				break;
			case 24:// "x" delete line , 不刪換行符
				ff = ss.substring(0, ls);
				rr = ss.substring(le, ss.length());
				if (comp.getText(ls, le - ls).equals("\n")) {
					comp.setText(ff + rr);
				} else {
					comp.setText(ff + "\n" + rr);
				}
				resetOffset(comp, offsetLS);
				break;
			case 4:// "d" delete line , 刪除換行符
				ff = ss.substring(0, ls);
				rr = ss.substring(le, ss.length());
				comp.setText(ff + rr);
				resetOffset(comp, offsetLS);
				break;
			case 26:// "z" revert content
				comp.setText(getReverseContent(mask));
				resetOffset(comp, primaryOffset);
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
	public void keyAction_typed(String name, JTextComponent comp, KeyEvent e) {
		super.keyAction_typed(name, comp, e);

		this.keyAction_typed(this.adaptType(name), comp, e);
	}

	private void resetOffset(JTextComponent comp, int offset) {
		comp.setSelectionStart(offset);
		comp.setSelectionEnd(offset);
	}

	@Override
	public void keyAction_release(Mask mask, JTextComponent comp, KeyEvent e) {
		super.keyAction_typed(mask, comp, e);

		if (!getContentTemp(mask).equals(comp.getText())) {
			setReverseContent(mask, getContentTemp(mask));
			setContentTemp(mask, comp.getText());
		}
	}

	@Override
	public void keyAction_release(String name, JTextComponent comp, KeyEvent e) {
		super.keyAction_typed(name, comp, e);

		keyAction_release(this.adaptType(name), comp, e);
	}

	@Override
	public boolean query(Component parent, String vocabulary) {
		return query(parent, vocabulary, PadFactory.SEARCH_INTERCEPT);
	}

	@Override
	public boolean query(Component parent, String vocabulary, Mask mask) {
		return query(parent, vocabulary, mask, defaultLimit);

	}

	@Override
	public boolean query(Component parent, String vocabulary, Mask mask, int limit) {
		boolean r = false;
		try {
			QueryResultBridge2 bridge = new QueryResultBridge2();
			bridge.setParameter("vocabulary", vocabulary.trim());
			bridge.setParameter("parent", parent);
			bridge.setParameter("type", mask);
			bridge.setParameter("limit", limit);
			Mask m = bridge.getDispatcher().sendAndBack();
			if (m.has(bridge.SENDANDBACK_NORMAL)) {
				r = true;
			} else {
				r = false;
			}
		} catch (NullPointerException e) {
			r = false;
		}
		return r;

	}

}

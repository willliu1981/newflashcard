package com.control.pad;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.util.List;

import javax.swing.Action;
import javax.swing.JTextArea;
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

public class MyPad extends Pad {
	private int offsetTemp = 0;// 用於記錄暫時的text 位置
	private static final int defaultLimit=100;

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
	public void keyAction_pressed(String frame, JTextComponent comp, KeyEvent e) {
		super.keyAction_typed(frame, comp, e);

		setContentTemp(frame, comp.getText());
		offsetTemp = comp.getSelectionStart();
	}

	@Override
	public void keyAction_typed(String frame, JTextComponent comp, KeyEvent e) {
		super.keyAction_typed(frame, comp, e);

		try {
			int primaryOffset = comp.getCaretPosition();
			int line = ((JTextArea) comp).getLineOfOffset(primaryOffset);
			int ls = ((JTextArea) comp).getLineStartOffset(line);
			int le = ((JTextArea) comp).getLineEndOffset(line);
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
				comp.setText(getReverseContent(frame));
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

	private void resetOffset(JTextComponent comp, int offset) {
		comp.setSelectionStart(offset);
		comp.setSelectionEnd(offset);
	}

	@Override
	public void keyAction_release(String frame, JTextComponent comp, KeyEvent e) {
		super.keyAction_typed(frame, comp, e);

		if (!getContentTemp(frame).equals(comp.getText())) {
			setReverseContent(frame, getContentTemp(frame));
		}
	}

	@Override
	public boolean query(Component parent, String vocabulary) {
		return query(parent,vocabulary,PadFactory.SEARCH_INTERCEPT);
	}
	
	
	@Override
	public boolean query(Component parent,  String vocabulary, Mask mask) {
		return query(parent,vocabulary,mask,defaultLimit);

	}

	@Override
	public boolean query(Component parent,  String vocabulary,Mask mask,int limit) {
		boolean r = false;
		try {
			QueryResultBridge2 bridge = new QueryResultBridge2(); 
			bridge.setParameter("vocabulary", vocabulary.trim());
			bridge.setParameter("parent", parent);
			bridge.setParameter("type", mask);
			bridge.setParameter("limit", limit);
			Mask m=bridge.getDispatcher().sendAndBack();
			if(m.has(bridge.SENDANDBACK_NORMAL)) {
				r=true;
			}else {
				r=false;
			}
		} catch (NullPointerException e) {
			r = false;
		}
		return r;

	}
	
	

}

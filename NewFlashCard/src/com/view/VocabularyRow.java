package com.view;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.model.CardBox;
import com.model.Vocabulary;
import com.view.control.ShowRow;
import com.view.control.ShowRowControl;

public class VocabularyRow extends JPanel implements ShowRow<Vocabulary> {
	private ShowRowControl<Vocabulary> showRowControl;
	private MouseWheelListener myWheelListener = new MouseWheelListener() {
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getWheelRotation() == 1) {
				showRowControl.rearwardFromIdx();
			} else {
				showRowControl.towardFromIdx();
			}
			showRowControl.showRow();
		}
	};

	/**
	 * Create the panel.
	 */
	public VocabularyRow() {
		setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lbl_vocabulary_id = new JLabel("id");
		lbl_vocabulary_id.addMouseWheelListener(myWheelListener);
		lbl_vocabulary_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_id.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_vocabulary_id);

		JLabel lbl_vocabulary_vocabulary = new JLabel("vocabulary");
		lbl_vocabulary_vocabulary.addMouseWheelListener(myWheelListener);
		lbl_vocabulary_vocabulary.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_vocabulary.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_vocabulary_vocabulary);

		JLabel lbl_vocabulary_translation = new JLabel("translation");
		lbl_vocabulary_translation.addMouseWheelListener(myWheelListener);
		lbl_vocabulary_translation.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_translation.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_vocabulary_translation);

		JLabel lbl_vocabulary_boxid = new JLabel("box_id");
		lbl_vocabulary_boxid.addMouseWheelListener(myWheelListener);
		lbl_vocabulary_boxid.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_boxid.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_vocabulary_boxid);

		JLabel lbl_vocabulary_createdate = new JLabel("create_date");
		lbl_vocabulary_createdate.addMouseWheelListener(myWheelListener);
		lbl_vocabulary_createdate.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_createdate.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_vocabulary_createdate);

		JLabel lbl_vocabulary_updatedate = new JLabel("update_date");
		lbl_vocabulary_updatedate.addMouseWheelListener(myWheelListener);
		lbl_vocabulary_updatedate.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_updatedate.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_vocabulary_updatedate);

	}

	@Override
	public void showRow() {
		int idx = Integer.valueOf(this.getName()) + this.showRowControl.getFromIdx();
		int size = this.showRowControl.getResults().size();
		Component[] rows = this.getComponents();
		if (idx < size) {
			Vocabulary v = this.showRowControl.getResults().get(idx);
			((JLabel) rows[0]).setText("" + v.getId());
			((JLabel) rows[1]).setText("" + v.getVocabulary());
			((JLabel) rows[2]).setText("" + v.getTranslation());
			((JLabel) rows[3]).setText("" + v.getBox_id());
			((JLabel) rows[4]).setText("" + v.getCreate_date());
			((JLabel) rows[5]).setText("" + v.getUpdate_date());
		} else {
			((JLabel) rows[0]).setText("");
			((JLabel) rows[1]).setText("");
			((JLabel) rows[2]).setText("");
			((JLabel) rows[3]).setText("");
			((JLabel) rows[4]).setText("");
			((JLabel) rows[5]).setText("");
		}

	}

	@Override
	public void setShowRowControl(ShowRowControl<Vocabulary> control) {
		this.showRowControl = control;

	}

}

package com.control.view;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VocabularyRow extends JPanel {

	/**
	 * Create the panel.
	 */
	public VocabularyRow() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl_vocabulary_id = new JLabel("id");
		lbl_vocabulary_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_id.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_vocabulary_id);
		
		JLabel lbl_vocabulary_vocabulary = new JLabel("vocabulary");
		lbl_vocabulary_vocabulary.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_vocabulary.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_vocabulary_vocabulary);
		
		JLabel lbl_vocabulary_translation = new JLabel("translation");
		lbl_vocabulary_translation.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_translation.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_vocabulary_translation);
		
		JLabel lbl_vocabulary_boxid = new JLabel("box_id");
		lbl_vocabulary_boxid.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_boxid.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_vocabulary_boxid);
		
		JLabel lbl_vocabulary_createdate = new JLabel("create_date");
		lbl_vocabulary_createdate.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_createdate.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_vocabulary_createdate);
		
		JLabel lbl_vocabulary_updatedate = new JLabel("update_date");
		lbl_vocabulary_updatedate.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_vocabulary_updatedate.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_vocabulary_updatedate);

	}

}

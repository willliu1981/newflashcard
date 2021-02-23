package com.control.view;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class CardBoxRow extends JPanel {

	/**
	 * Create the panel.
	 */
	public CardBoxRow() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl_cardbox_id = new JLabel("id");
		lbl_cardbox_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cardbox_id.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_cardbox_id);
		
		JLabel lbl_cardbox_name = new JLabel("name");
		lbl_cardbox_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cardbox_name.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_cardbox_name);
		
		JLabel lbl_cardbox_count = new JLabel("count");
		lbl_cardbox_count.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cardbox_count.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_cardbox_count);
		
		JLabel lbl_cardbox_createdate = new JLabel("create_date");
		lbl_cardbox_createdate.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cardbox_createdate.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_cardbox_createdate);
		
		JLabel lbl_cardbox_updatedate = new JLabel("update_date");
		lbl_cardbox_updatedate.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cardbox_updatedate.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_cardbox_updatedate);

	}

}

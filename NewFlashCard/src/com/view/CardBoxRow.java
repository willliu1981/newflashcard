package com.view;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.control.dao.CardBoxDao;
import com.model.CardBox;
import com.view.control.ShowRow;
import com.view.control.ShowRowControl;

public class CardBoxRow extends JPanel implements ShowRow<CardBox> {
	public ShowRowControl<CardBox> showRowControl;

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

	public void setShowRowControl(ShowRowControl<CardBox> control) {
		this.showRowControl = control;
	}

	@Override
	public void showRow() {
		int idx = Integer.valueOf(this.getName());
		int size = this.showRowControl.getResults().size();
		Component[] rows = this.getComponents();
		if (idx < size) {
			CardBox cardBox = this.showRowControl.getResults().get(Integer.valueOf(this.getName()));
			((JLabel) rows[0]).setText("" + cardBox.getId());
			((JLabel) rows[1]).setText("" + cardBox.getName());
			((JLabel) rows[2]).setText("0");
			((JLabel) rows[3]).setText("" + cardBox.getCreate_date());
			((JLabel) rows[4]).setText("" + cardBox.getUpdate_date());
		} else {
			((JLabel) rows[0]).setText("");
			((JLabel) rows[1]).setText("");
			((JLabel) rows[2]).setText("");
			((JLabel) rows[3]).setText("");
			((JLabel) rows[4]).setText("");
		}
	}

}

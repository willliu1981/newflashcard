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
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardBoxRow extends JPanel implements ShowRow<CardBox> {
	public ShowRowControl<CardBox> showRowControl;
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
	private MouseAdapter myClickListener = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				System.out.println("L");
			} else if (e.getButton() == MouseEvent.BUTTON2) {
				System.out.println("c");
			} else if (e.getButton() == MouseEvent.BUTTON3) {
				System.out.println("R");
			}
		}
	};

	/**
	 * Create the panel.
	 */
	public CardBoxRow() {
		setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lbl_cardbox_id = new JLabel("id");
		lbl_cardbox_id.addMouseListener(myClickListener);
		lbl_cardbox_id.addMouseWheelListener(myWheelListener);
		lbl_cardbox_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cardbox_id.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_cardbox_id);

		JLabel lbl_cardbox_name = new JLabel("name");
		lbl_cardbox_name.addMouseWheelListener(myWheelListener);
		lbl_cardbox_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cardbox_name.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_cardbox_name);

		JLabel lbl_cardbox_count = new JLabel("count");
		lbl_cardbox_count.addMouseWheelListener(myWheelListener);
		lbl_cardbox_count.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cardbox_count.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_cardbox_count);

		JLabel lbl_cardbox_createdate = new JLabel("create_date");
		lbl_cardbox_createdate.addMouseWheelListener(myWheelListener);
		lbl_cardbox_createdate.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cardbox_createdate.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_cardbox_createdate);

		JLabel lbl_cardbox_updatedate = new JLabel("update_date");
		lbl_cardbox_updatedate.addMouseWheelListener(myWheelListener);
		lbl_cardbox_updatedate.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cardbox_updatedate.setFont(new Font("新細明體", Font.PLAIN, 18));
		add(lbl_cardbox_updatedate);

	}

	public void setShowRowControl(ShowRowControl<CardBox> control) {
		this.showRowControl = control;
	}

	@Override
	public void showRow() {
		int idx = Integer.valueOf(this.getName()) + this.showRowControl.getFromIdx();
		int size = this.showRowControl.getResults().size();
		Component[] rows = this.getComponents();
		if (idx < size) {
			CardBox cardBox = this.showRowControl.getResults().get(idx);
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

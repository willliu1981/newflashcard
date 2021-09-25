package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.control.dao.CardBoxDao;
import com.control.dao.VocabularyDao;
import com.control.pronounce.PronounceControl;
import com.model.CardBox;
import com.model.CardBox.StateResult;
import com.model.Vocabulary;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JTextPane;

public class SimpleAutoPlayerFrame extends JFrame {
	static class RunPlayer implements Runnable {
		List<Vocabulary> vocbList;
		int times = 3;
		boolean isCancelled = false;
		boolean isWaiting = false;

		public RunPlayer(List<Vocabulary> vocbs) {
			this.vocbList = vocbs;
		}

		@Override
		public void run() {
			int ptr = 0;
			int times = this.times;
			int quantity = vocbList.size();
			Vocabulary v = null;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while (!isCancelled) {
				if (!isWaiting) {

					v = vocbList.get(ptr);

					SimpleAutoPlayerFrame.lblProgress.setText(
							String.format("%d / %d", ptr + 1, quantity));
					SimpleAutoPlayerFrame.txtpnNumber
							.setText("" + (ptr + 1) + ".");
					SimpleAutoPlayerFrame.txtpnVocabulary
							.setText(v.getVocabulary());
					SimpleAutoPlayerFrame.txtpnTranslation
							.setText(v.getTranslation());

					if (v.getExplanation() != null) {
						StringBuilder sb = new StringBuilder(
								v.getExplanation());
						int cutIdx = sb.indexOf("\n\n\n");
						String explanation = "";
						if (cutIdx == -1) {
							explanation = sb.toString();
						} else {
							explanation = sb.substring(0, cutIdx);
						}

						SimpleAutoPlayerFrame.txtpnExplanation
								.setText(explanation);
					} else {
						SimpleAutoPlayerFrame.txtpnExplanation.setText("");
					}

					boolean r = PronounceControl.play(v.getVocabulary());

					if (!r || --times == 0) {
						times = this.times;
						ptr++;
					}

					if (ptr >= vocbList.size()) {
						ptr = 0;
					}

				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void cancel() {
			this.isCancelled = true;
		}

		public void pause() {
			this.isWaiting = true;
		}

		public void play() {
			this.isWaiting = false;
		}
	}

	private final static Color background = new Color(41, 57, 55);
	private final static Color chalkWhite = new Color(239, 238, 233);
	private final static Color chalkYellow = new Color(240, 219, 66);
	private final static Color chalkRed = new Color(219, 78, 95);
	private final static Color chalkGreen = new Color(91, 160, 116);
	private final static Color chalkBlue = new Color(62, 154, 229);
	private JPanel contentPane;
	RunPlayer runPlayer;
	ExecutorService esPlayer = Executors.newFixedThreadPool(1);
	List<Vocabulary> playList = new ArrayList<>();
	private static JLabel lblProgress;
	private static JButton btnPlay;
	private static JButton btnPause;
	private static JButton btnStop;
	private static JTextPane txtpnTranslation;
	private static JTextPane txtpnExplanation;
	private static JTextPane txtpnVocabulary;
	private static JTextPane txtpnNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleAutoPlayerFrame frame = new SimpleAutoPlayerFrame();
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
	public SimpleAutoPlayerFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1218, 859);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				stop();
			}
		});

		load();

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);

		ButtonGroup radioPlayerGroup = new ButtonGroup();
		JRadioButton rdbtnNewRadioButton = new JRadioButton("全部");
		rdbtnNewRadioButton.setEnabled(false);
		rdbtnNewRadioButton.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(rdbtnNewRadioButton);
		radioPlayerGroup.add(rdbtnNewRadioButton);

		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("當前測驗中");
		rdbtnNewRadioButton_1.setEnabled(false);
		rdbtnNewRadioButton_1.setSelected(true);
		rdbtnNewRadioButton_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(rdbtnNewRadioButton_1);
		radioPlayerGroup.add(rdbtnNewRadioButton_1);

		JPanel panel_10 = new JPanel();
		panel_1.add(panel_10);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("所有已開始未完成");
		rdbtnNewRadioButton_2.setEnabled(false);
		rdbtnNewRadioButton_2.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(rdbtnNewRadioButton_2);
		radioPlayerGroup.add(rdbtnNewRadioButton_2);

		JPanel panel_11 = new JPanel();
		panel_1.add(panel_11);

		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("已完成");
		rdbtnNewRadioButton_3.setEnabled(false);
		rdbtnNewRadioButton_3.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(rdbtnNewRadioButton_3);
		radioPlayerGroup.add(rdbtnNewRadioButton_3);

		JPanel panel_12 = new JPanel();
		panel_1.add(panel_12);

		JRadioButton rdbtnNewRadioButton_3_1 = new JRadioButton("未開始");
		rdbtnNewRadioButton_3_1.setEnabled(false);
		rdbtnNewRadioButton_3_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		panel_1.add(rdbtnNewRadioButton_3_1);
		radioPlayerGroup.add(rdbtnNewRadioButton_3_1);

		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(240, 10));
		panel.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("  播放清單");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 16));
		panel_2.add(lblNewLabel, BorderLayout.WEST);

		lblProgress = new JLabel("0/0");
		panel_2.add(lblProgress, BorderLayout.EAST);
		lblProgress.setFont(new Font("新細明體", Font.PLAIN, 24));

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(10, 380));
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 0));

		txtpnTranslation = new JTextPane();
		txtpnTranslation.setPreferredSize(new Dimension(7, 135));
		txtpnTranslation.setForeground(chalkWhite);
		txtpnTranslation.setBackground(background);
		txtpnTranslation.setText("translate");
		txtpnTranslation.setFont(new Font("DialogInput", Font.PLAIN, 52));
		panel_4.add(txtpnTranslation, BorderLayout.SOUTH);
		setAlignment(txtpnTranslation);

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));

		JPanel panel_14 = new JPanel();
		panel_14.setBackground(background);
		panel_5.add(panel_14);
		panel_14.setLayout(new BorderLayout(0, 0));

		JPanel panel_15 = new JPanel();
		panel_14.add(panel_15, BorderLayout.SOUTH);
		panel_15.setLayout(new BorderLayout(0, 0));

		JPanel panel_17 = new JPanel();
		panel_17.setBackground(background);
		panel_15.add(panel_17, BorderLayout.CENTER);
		panel_17.setLayout(new BorderLayout(0, 0));

		txtpnNumber = new JTextPane();
		txtpnNumber.setForeground(chalkBlue);
		txtpnNumber.setBackground(background);
		txtpnNumber.setText("0");
		txtpnNumber.setFont(new Font("DialogInput", Font.PLAIN, 48));
		panel_17.add(txtpnNumber, BorderLayout.EAST);
		setAlignment(txtpnNumber, StyleConstants.ALIGN_RIGHT);

		JPanel panel_13 = new JPanel();
		panel_13.setBackground(background);
		panel_5.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));

		JPanel panel_18 = new JPanel();
		panel_13.add(panel_18, BorderLayout.SOUTH);
		panel_18.setLayout(new BorderLayout(0, 0));

		txtpnVocabulary = new JTextPane();
		txtpnVocabulary.setForeground(chalkYellow);
		txtpnVocabulary.setBackground(background);
		panel_18.add(txtpnVocabulary);
		txtpnVocabulary.setText("content");
		txtpnVocabulary.setFont(new Font("DialogInput", Font.BOLD, 82));
		setAlignment(txtpnVocabulary);

		JPanel panel_16 = new JPanel();
		panel_16.setBackground(background);
		panel_5.add(panel_16);
		panel_16.setLayout(new BorderLayout(0, 0));

		JPanel panel_19 = new JPanel();
		panel_19.setBackground(background);
		panel_19.setPreferredSize(new Dimension(10, 120));
		panel_4.add(panel_19, BorderLayout.NORTH);

		JPanel panel_5_1 = new JPanel();
		panel_3.add(panel_5_1, BorderLayout.CENTER);
		panel_5_1.setLayout(new BorderLayout(0, 0));

		txtpnExplanation = new JTextPane();
		txtpnExplanation.setForeground(chalkWhite);
		txtpnExplanation.setBackground(background);
		txtpnExplanation.setText("explanation");
		txtpnExplanation.setFont(new Font("DialogInput", Font.PLAIN, 38));
		panel_5_1.add(txtpnExplanation, BorderLayout.CENTER);
		setAlignment(txtpnExplanation);

		JPanel panel_6 = new JPanel();
		panel_6.setPreferredSize(new Dimension(10, 80));
		contentPane.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);

		btnPlay = new JButton("▶");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				play();
			}
		});
		btnPlay.setFocusPainted(false);
		btnPlay.setBackground(SystemColor.controlHighlight);
		btnPlay.setFont(new Font("DialogInput", Font.BOLD, 20));
		panel_7.add(btnPlay);

		btnPause = new JButton("❚❚");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pause();
			}
		});
		btnPause.setFocusPainted(false);
		btnPause.setBackground(SystemColor.controlHighlight);
		btnPause.setFont(new Font("DialogInput", Font.BOLD, 20));
		panel_7.add(btnPause);

		btnStop = new JButton("■");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stop();
			}
		});
		btnStop.setFocusPainted(false);
		btnStop.setBackground(SystemColor.controlHighlight);
		btnStop.setFont(new Font("DialogInput", Font.BOLD, 20));
		panel_7.add(btnStop);

		JPanel panel_8 = new JPanel();
		panel_8.setPreferredSize(new Dimension(10, 20));
		panel_6.add(panel_8, BorderLayout.NORTH);
	}

	private void setAlignment(JTextPane pane) {
		setAlignment(pane, StyleConstants.ALIGN_CENTER);
	}

	private void setAlignment(JTextPane pane, int styleConst) {
		StyledDocument doc = pane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
	}

	public void load() {
		CardBoxDao cbDao = new CardBoxDao();
		List<CardBox> boxs = cbDao.queryAll();
		VocabularyDao vocbDao = new VocabularyDao();
		List<Vocabulary> vocbs = vocbDao.queryAll();
		playList = new ArrayList<>();

		vocbs.stream().forEach(v -> {
			if (v.getBox_id() > 0) {
				Optional<CardBox> box = boxs.stream()
						.filter(b -> b.getId().equals(v.getBox_id()))
						.findFirst();
				if (box.isPresent()) {
					if (box.get().isTesting()) {
						playList.add(v);
					}
				}
			}
		});

	}

	public void play() {
		if (playList.size() == 0) {
			return;
		}

		if (runPlayer == null && playList != null) {
			Collections.shuffle(playList);
			runPlayer = new RunPlayer(playList);
			Thread thd = new Thread(runPlayer);
			esPlayer.submit(thd);
		} else {
			runPlayer.play();
		}
		initControlButton();
		btnPlay.setEnabled(false);
	}

	public void pause() {
		if (runPlayer == null) {
			return;
		}
		runPlayer.pause();
		initControlButton();
		btnPause.setEnabled(false);
	}

	public void stop() {
		if (runPlayer != null) {
			runPlayer.cancel();
			runPlayer = null;
		}
		initControlButton();
		clear();
	}

	public void initControlButton() {
		btnPlay.setEnabled(true);
		btnPause.setEnabled(true);
		btnStop.setEnabled(true);
	}

	public void clear() {
		SimpleAutoPlayerFrame.txtpnNumber.setText("");
		SimpleAutoPlayerFrame.txtpnVocabulary.setText("");
		SimpleAutoPlayerFrame.txtpnTranslation.setText("");
		SimpleAutoPlayerFrame.txtpnExplanation.setText("");
	}
}

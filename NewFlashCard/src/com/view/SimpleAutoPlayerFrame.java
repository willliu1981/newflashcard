package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.control.dao.CardBoxDao;
import com.control.dao.VocabularyDao;
import com.control.pronounce.PronounceControl;
import com.model.CardBox;
import com.model.Vocabulary;
import com.tool.MyColor;
import java.awt.event.MouseAdapter;
import java.awt.Insets;

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

				while (!isCancelled) {
					if (!isWaiting) {

						v = vocbList.get(ptr);
						/*
						SimpleAutoPlayerFrame.txtpnNumber
								.setText("" + (ptr + 1) + ".");
						SimpleAutoPlayerFrame.txtpnNumber_ghost
								.setText("" + (ptr + 1) + "");
						//*/
						SimpleAutoPlayerFrame.txtpnNumber_righttop
								.setText("" + (ptr + 1) + "");
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

							Thread.sleep(2000);

						}

					}

					Thread.sleep(2000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
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

	private static SimpleAutoPlayerFrame singletonFrame;
	private final static Color background = new Color(41, 57, 55);
	private final static Color chalkWhite = new Color(239, 238, 233);
	private final static Color chalkYellow = new Color(240, 219, 66);
	private final static Color chalkRed = new Color(219, 78, 95);
	private final static Color chalkGreen = new Color(91, 160, 116);
	private final static Color chalkBlue = new Color(62, 154, 229);
	private static JPanel singletonContentPane;
	static RunPlayer runPlayer;
	ExecutorService esPlayer = Executors.newFixedThreadPool(1);
	List<Vocabulary> playList = new ArrayList<>();
	private static JButton btnPlay;
	private static JButton btnPause;
	private static JButton btnStop;
	private static JTextPane txtpnTranslation;
	private static JTextPane txtpnExplanation;
	private static JTextPane txtpnVocabulary;
	private static JTextPane txtpnNumber;
	private static JTextPane txtpnNumber_ghost;
	private JPanel panel_bottom;
	private JPanel panel_playerButtonCenter;
	private JPanel panel_playerButtonTop;
	private static Point lastPoint;
	private static JTextPane txtpnNumber_righttop;

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

	public SimpleAutoPlayerFrame() {
		this(false);
	}

	/**
	 * Create the frame.
	 */
	public SimpleAutoPlayerFrame(boolean unDecorate) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1218, 859);
		singletonContentPane = new JPanel();
		singletonContentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(singletonContentPane);
		if (unDecorate) {
			this.setUndecorated(unDecorate);
			this.setLocation(lastPoint);
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				stop();
			}
		});

		this.addWindowStateListener(new WindowStateListener() {

			@Override
			public void windowStateChanged(WindowEvent state) {
				if (state.getNewState() == JFrame.MAXIMIZED_BOTH) {
					createNewFrame(true);
					SimpleAutoPlayerFrame.singletonFrame.setVisible(true);
				}
			}

		});

		load();

		JPanel panel_3 = new JPanel();
		singletonContentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(10, 420));
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 0));

		txtpnTranslation = new JTextPane();
		txtpnTranslation.setMargin(new Insets(20, 20, 20, 20));
		txtpnTranslation.setPreferredSize(new Dimension(7, 180));
		txtpnTranslation.setForeground(chalkWhite);
		txtpnTranslation.setBackground(background);
		txtpnTranslation.setText("翻譯");
		txtpnTranslation.setFont(new Font("DialogInput", Font.PLAIN, 52));
		panel_4.add(txtpnTranslation, BorderLayout.SOUTH);
		setAlignment(txtpnTranslation);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(background);
		panel_4.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));

		JPanel panel_14 = new JPanel();
		panel_14.setBackground(background);
		panel_5.add(panel_14);
		panel_14.setLayout(new BorderLayout(0, 0));

		JPanel panel_15 = new JPanel();
		panel_15.setBackground(background);
		panel_14.add(panel_15, BorderLayout.SOUTH);
		panel_15.setLayout(new BorderLayout(0, 0));

		JPanel panel_17 = new JPanel();
		panel_17.setBackground(background);
		panel_15.add(panel_17, BorderLayout.CENTER);
		panel_17.setLayout(new BorderLayout(0, 0));

		txtpnNumber = new JTextPane();
		txtpnNumber.setForeground(background);
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
		txtpnVocabulary.setFont(new Font("DialogInput", Font.BOLD, 92));
		setAlignment(txtpnVocabulary);

		JPanel panel_16 = new JPanel();
		panel_16.setBackground(background);
		panel_5.add(panel_16);
		panel_16.setLayout(new BorderLayout(0, 0));

		JPanel panel_15_1 = new JPanel();
		panel_15_1.setBackground(background);
		panel_16.add(panel_15_1, BorderLayout.CENTER);
		panel_15_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_17_1 = new JPanel();
		panel_17_1.setBackground(background);
		panel_15_1.add(panel_17_1, BorderLayout.CENTER);
		panel_17_1.setLayout(new BorderLayout(0, 0));

		txtpnNumber_ghost = new JTextPane();
		txtpnNumber_ghost.setText("0");
		txtpnNumber_ghost.setForeground(background);
		txtpnNumber_ghost.setFont(new Font("DialogInput", Font.PLAIN, 48));
		txtpnNumber_ghost.setBackground(new Color(41, 57, 55));
		panel_17_1.add(txtpnNumber_ghost, BorderLayout.CENTER);
		this.setAlignment(txtpnNumber_ghost);

		JPanel panel_20 = new JPanel();
		panel_20.setPreferredSize(new Dimension(10, 20));
		panel_20.setBackground(background);
		panel_15_1.add(panel_20, BorderLayout.NORTH);

		JPanel panel_19 = new JPanel();
		panel_19.setBackground(background);
		panel_19.setPreferredSize(new Dimension(10, 120));
		panel_4.add(panel_19, BorderLayout.NORTH);
		panel_19.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(100, 10));
		panel_1.setBackground(background);
		panel_19.add(panel_1, BorderLayout.EAST);

		txtpnNumber_righttop = new JTextPane();
		txtpnNumber_righttop.setText("0");
		txtpnNumber_righttop.setForeground(new Color(91, 160, 116));
		txtpnNumber_righttop.setFont(new Font("DialogInput", Font.PLAIN, 48));
		txtpnNumber_righttop.setBackground(new Color(41, 57, 55));
		panel_1.add(txtpnNumber_righttop);

		JPanel panel_5_1 = new JPanel();
		panel_3.add(panel_5_1, BorderLayout.CENTER);
		panel_5_1.setLayout(new BorderLayout(0, 0));

		txtpnExplanation = new JTextPane();
		txtpnExplanation.setMargin(new Insets(20, 20, 20, 20));
		txtpnExplanation.setForeground(chalkWhite);
		txtpnExplanation.setBackground(background);
		txtpnExplanation.setText("解釋");
		txtpnExplanation.setFont(new Font("DialogInput", Font.PLAIN, 38));
		panel_5_1.add(txtpnExplanation, BorderLayout.CENTER);
		setAlignment(txtpnExplanation);

		panel_bottom = new JPanel();
		panel_bottom.addMouseListener(new MouseAdapter() {
			boolean isOut = false;

			@Override
			public void mouseExited(MouseEvent arg0) {
				if (!panel_playerButtonCenter.isVisible()) {
					isOut = true;
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (isOut) {
					if (!panel_playerButtonCenter.isVisible()) {
						restorePlayerButton();
						isOut = false;
					}
				}
			}
		});
		panel_bottom.setPreferredSize(new Dimension(10, 80));
		singletonContentPane.add(panel_bottom, BorderLayout.SOUTH);
		panel_bottom.setLayout(new BorderLayout(0, 0));

		panel_playerButtonCenter = new JPanel();
		panel_bottom.add(panel_playerButtonCenter);

		btnPlay = new JButton("▶");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				play();
			}
		});
		btnPlay.setFocusPainted(false);
		btnPlay.setBackground(SystemColor.controlHighlight);
		btnPlay.setFont(new Font("DialogInput", Font.BOLD, 20));
		panel_playerButtonCenter.add(btnPlay);

		btnPause = new JButton("❚❚");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pause();
			}
		});
		btnPause.setFocusPainted(false);
		btnPause.setBackground(SystemColor.controlHighlight);
		btnPause.setFont(new Font("DialogInput", Font.BOLD, 20));
		panel_playerButtonCenter.add(btnPause);

		btnStop = new JButton("■");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stop();
			}
		});
		btnStop.setFocusPainted(false);
		btnStop.setBackground(SystemColor.controlHighlight);
		btnStop.setFont(new Font("DialogInput", Font.BOLD, 20));
		panel_playerButtonCenter.add(btnStop);

		JPanel panel = new JPanel();
		panel_playerButtonCenter.add(panel);

		JLabel lblNewLabel = new JLabel("｜");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 28));
		panel.add(lblNewLabel);

		JButton btnStop_1 = new JButton("▣");
		btnStop_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createNewFrame(false);
				SimpleAutoPlayerFrame.singletonFrame.setVisible(true);
				SimpleAutoPlayerFrame.singletonFrame.setLocation(lastPoint);
			}
		});
		btnStop_1.setFont(new Font("DialogInput", Font.BOLD, 20));
		btnStop_1.setFocusPainted(false);
		btnStop_1.setBackground(SystemColor.controlHighlight);
		panel_playerButtonCenter.add(btnStop_1);

		panel_playerButtonTop = new JPanel();
		panel_playerButtonTop.setPreferredSize(new Dimension(10, 20));
		panel_bottom.add(panel_playerButtonTop, BorderLayout.NORTH);
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
		if (playList == null || playList.size() == 0) {
			return;
		}

		if (runPlayer == null) {
			Collections.shuffle(playList);
			runPlayer = new RunPlayer(playList);
			Thread thd = new Thread(runPlayer);
			esPlayer.submit(thd);
		} else {
			runPlayer.play();
		}
		initControlPlayerButton();
		btnPlay.setEnabled(false);
		hidePlayerButton();
	}

	public void pause() {
		if (runPlayer == null) {
			return;
		}
		runPlayer.pause();
		initControlPlayerButton();
		btnPause.setEnabled(false);
	}

	public void stop() {
		if (runPlayer != null) {
			runPlayer.cancel();
			runPlayer = null;
		}
		initControlPlayerButton();
		clear();
	}

	public void initControlPlayerButton() {
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

	private void restorePlayerButton() {
		panel_playerButtonCenter.setVisible(true);
		panel_playerButtonTop.setBackground(MyColor.defaultColor());
	}

	private void hidePlayerButton() {
		panel_playerButtonCenter.setVisible(false);
		panel_playerButtonTop.setBackground(background);
		panel_bottom.setBackground(background);
	}

	public static SimpleAutoPlayerFrame getFrame() {
		if (SimpleAutoPlayerFrame.singletonFrame == null) {
			createNewFrame(false);
		}
		return SimpleAutoPlayerFrame.singletonFrame;
	}

	public static void createNewFrame(boolean maxSize) {
		if (SimpleAutoPlayerFrame.singletonFrame != null) {
			SimpleAutoPlayerFrame.singletonFrame.setVisible(false);
			lastPoint = new Point(SimpleAutoPlayerFrame.singletonFrame.getX(),
					SimpleAutoPlayerFrame.singletonFrame.getY());
			SimpleAutoPlayerFrame.singletonFrame.dispose();
		}
		SimpleAutoPlayerFrame.singletonFrame = new SimpleAutoPlayerFrame(
				maxSize);
	}

}

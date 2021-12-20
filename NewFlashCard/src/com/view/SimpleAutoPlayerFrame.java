package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.control.dao.CardBoxDao;
import com.control.dao.VocabularyDao;
import com.control.pronounce.PronounceControl;
import com.model.CardBox;
import com.model.CardBox.StateResult;
import com.model.Vocabulary;
import com.tool.MyColor;

public class SimpleAutoPlayerFrame extends JFrame {
	static class RunPlayer implements Runnable {
		List<Vocabulary> vocbList;
		static List<String> optionsList = new ArrayList<>();
		static int ROUNDMAXTIMES = 4;
		// static int HINDTIMES = 2;
		boolean isCancelled = false;
		boolean isWaiting = false;
		static final String defaultHint = "-";

		public RunPlayer(List<Vocabulary> vocbs) {
			this.vocbList = vocbs;
		}

		@Override
		public void run() {
			int ptr = 0;
			int times = 0;
			int quantity = vocbList.size();

			Vocabulary v = null;
			SimpleAutoPlayerFrame.getFrame().setTitle("" + quantity);
			int correctSequence = 0;
			try {
				Thread.sleep(2000);
				while (!isCancelled) {
					int roundDelayTime = 1500;
					int allRoundDelayTime = 3000;
					int baseSoundPlayDelayTime = 500;
					int soundPlayDelayTime = 250;
					boolean ignoreSoundPlay = false;
					Thread.sleep(1);
					if (!isWaiting) {
						v = vocbList.get(ptr);
						SimpleAutoPlayerFrame.txtpnNumber_righttop.setText("");
						SimpleAutoPlayerFrame.txtpnNumber_righttop
								.setText("" + (ptr + 1) + "");
						switch (times) {
						case 0:
							SimpleAutoPlayerFrame.txtpnVocabulary.setText(v
									.getVocabulary().substring(0, 1)
									+ createHint(v.getVocabulary().length() - 2)
									+ v.getVocabulary().substring(
											v.getVocabulary().length() - 1));
							correctSequence = (int) (Math.random() * 4);
							SimpleAutoPlayerFrame.txtpnTranslation.setText("");
							optionsList.clear();
							optionsList.add(getRandomTranslate(vocbList, 0,
									correctSequence, ptr));
							optionsList.add(getRandomTranslate(vocbList, 1,
									correctSequence, ptr));
							optionsList.add(getRandomTranslate(vocbList, 2,
									correctSequence, ptr));
							optionsList.add(getRandomTranslate(vocbList, 3,
									correctSequence, ptr));

							SimpleAutoPlayerFrame.txtpnExplanation
									.setText(getCorrectMark(-1, correctSequence)
											+ getOptionsSign(0)
											+ optionsList.get(0) + "\n"
											+ getCorrectMark(-1,
													correctSequence)
											+ getOptionsSign(1)
											+ optionsList.get(1) + "\n"
											+ getCorrectMark(-1,
													correctSequence)
											+ getOptionsSign(2)
											+ optionsList.get(2) + "\n"
											+ getCorrectMark(-1,
													correctSequence)
											+ getOptionsSign(3)
											+ optionsList.get(3));
							break;
						case 1:
							roundDelayTime += 1000;
							break;
						case 2:
							ignoreSoundPlay = true;
							roundDelayTime += 1500;
							SimpleAutoPlayerFrame.txtpnVocabulary
									.setText(v.getVocabulary());
							soundPlayDelayTime = baseSoundPlayDelayTime - 250;
							SimpleAutoPlayerFrame.txtpnTranslation
									.setText(v.getTranslation());
							SimpleAutoPlayerFrame.txtpnExplanation
									.setText(getCorrectMark(0, correctSequence,
											"👉 ", "")
											+ getCorrectMark(0, correctSequence)
											+ getOptionsSign(0)
											+ optionsList.get(0)
											+ getCorrectMark(0, correctSequence,
													" 👈", "")
											+ "\n"
											+ getCorrectMark(1, correctSequence,
													"👉 ", "")
											+ getCorrectMark(1, correctSequence)
											+ getOptionsSign(1)
											+ optionsList.get(1)
											+ getCorrectMark(1, correctSequence,
													" 👈", "")
											+ "\n"
											+ getCorrectMark(2, correctSequence,
													"👉 ", "")
											+ getCorrectMark(2, correctSequence)
											+ getOptionsSign(2)
											+ optionsList.get(2)
											+ getCorrectMark(2, correctSequence,
													" 👈", "")
											+ "\n"
											+ getCorrectMark(3, correctSequence,
													"👉 ", "")
											+ getCorrectMark(3, correctSequence)
											+ getOptionsSign(3)
											+ optionsList.get(3)
											+ getCorrectMark(3, correctSequence,
													" 👈", ""));
							break;
						case 3:

							// case 4:
							// case 5:
							ignoreSoundPlay = false;
							soundPlayDelayTime = baseSoundPlayDelayTime + 1250;
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
								SimpleAutoPlayerFrame.txtpnExplanation
										.setText("");
							}
							break;
						default:
							break;
						}

						Thread.sleep(soundPlayDelayTime);
						boolean isPlayable = false;
						if (ignoreSoundPlay == false) {
							isPlayable = PronounceControl
									.play(v.getVocabulary());
						} else {
							isPlayable = true;
						}

						if (++times == ROUNDMAXTIMES) {
							Thread.sleep(allRoundDelayTime);
							times = 0;
							ptr++;
						} else {
							if (isPlayable) {
								Thread.sleep(roundDelayTime);
							} else {
								Thread.sleep(roundDelayTime / 2);
							}

						}

						if (ptr >= vocbList.size()) {
							ptr = 0;
							Thread.sleep(2000);
							int value = JOptionPane.showConfirmDialog(thisFrame,
									"已播放完一輪,是否藉此使測驗通過?", "更新測驗進度",
									JOptionPane.YES_NO_OPTION);
							if (value == JOptionPane.YES_OPTION) {
								CardBoxDao dao = new CardBoxDao();
								boxList.forEach(x -> {
									x.state();
									dao.updateTest(x, x.getId());
								});
							}
						}

					}

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		private static String createHint(int num, String hint) {
			StringBuilder sb = new StringBuilder();
			while (--num >= 0) {
				sb = sb.append(hint);
			}
			return sb.toString();
		}

		private static String createHint(int num) {

			return createHint(num, defaultHint);
		}

		private static String getRandomTranslate(List<Vocabulary> list,
				int sequence, int correctSequence, int correctIndex) {
			String translate = null;
			if (correctSequence == sequence) {
				translate = list.get(correctIndex).getTranslation();
			} else {
				int rnd = (int) (Math.random() * list.size());
				translate = list.get(rnd).getTranslation();

			}

			return translate;
		}

		private static String getCorrectMark(int sequence,
				int correctSequence) {
			return getCorrectMark(sequence, correctSequence, "♜ ", "♖ ");
		}

		private static String getCorrectMark(int sequence, int correctSequence,
				String trueMark, String falseMark) {
			if (correctSequence == sequence) {
				return trueMark;
			} else {
				return falseMark;
			}
		}

		private static String getOptionsSign(int sequence) {
			switch (sequence) {
			case 0:
				return "A) ";
			case 1:
				return "B) ";
			case 2:
				return "C) ";
			case 3:
				return "D) ";
			default:
				return "?) ";
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

	private static SimpleAutoPlayerFrame thisFrame;
	private final static Color background = new Color(41, 57, 55);
	private final static Color chalkWhite = new Color(239, 238, 233);
	private final static Color chalkYellow = new Color(240, 219, 66);
	private final static Color chalkRed = new Color(219, 78, 95);
	private final static Color chalkGreen = new Color(91, 160, 116);
	private final static Color chalkBlue = new Color(62, 154, 229);
	private static JPanel singletonContentPane;
	static RunPlayer runPlayer;
	ExecutorService esPlayer = Executors.newFixedThreadPool(1);
	static List<Vocabulary> playList;
	static Set<CardBox> boxList;
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
					SimpleAutoPlayerFrame.thisFrame.setVisible(true);
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
				SimpleAutoPlayerFrame.thisFrame.setVisible(true);
				SimpleAutoPlayerFrame.thisFrame.setLocation(lastPoint);
			}
		});
		btnStop_1.setFont(new Font("DialogInput", Font.BOLD, 20));
		btnStop_1.setFocusPainted(false);
		btnStop_1.setBackground(SystemColor.controlHighlight);
		panel_playerButtonCenter.add(btnStop_1);

		JButton btnStop_1_1 = new JButton("⟲⌛");
		btnStop_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				load();
			}
		});
		btnStop_1_1.setFont(new Font("DialogInput", Font.BOLD, 20));
		btnStop_1_1.setFocusPainted(false);
		btnStop_1_1.setBackground(SystemColor.controlHighlight);
		panel_playerButtonCenter.add(btnStop_1_1);

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
		boxList = new HashSet<>();

		vocbs.stream().forEach(v -> {
			if (v.getBox_id() > 0) {
				Optional<CardBox> opBox = boxs.stream()
						.filter(b -> b.getId().equals(v.getBox_id()))
						.findFirst();
				if (opBox.isPresent()) {
					CardBox box = opBox.get();
					if (box.isCurrentTesting() || (!box.isFinish()
							&& box.getStateResult() == StateResult.Default)) {
						playList.add(v);
						boxList.add(box);
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
		if (SimpleAutoPlayerFrame.thisFrame == null) {
			createNewFrame(false);
		}
		return SimpleAutoPlayerFrame.thisFrame;
	}

	public static void createNewFrame(boolean maxSize) {
		if (SimpleAutoPlayerFrame.thisFrame != null) {
			SimpleAutoPlayerFrame.thisFrame.setVisible(false);
			lastPoint = new Point(SimpleAutoPlayerFrame.thisFrame.getX(),
					SimpleAutoPlayerFrame.thisFrame.getY());
			SimpleAutoPlayerFrame.thisFrame.dispose();
		}
		SimpleAutoPlayerFrame.thisFrame = new SimpleAutoPlayerFrame(maxSize);
	}

}

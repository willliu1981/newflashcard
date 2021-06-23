package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;

public class HelpFrame extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea_help;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpFrame frame = new HelpFrame();
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
	public HelpFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 492, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		textArea_help = new JTextArea();
		textArea_help.setEditable(false);
		textArea_help.setFont(new Font("Monospaced", Font.PLAIN, 18));
		textArea_help.setLineWrap(true);
		textArea_help.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(textArea_help);
		
		initializeContent();
		
	}
	
	private void initializeContent() {
		
		textArea_help.setText("\r\n列表清單 :\r\n\t滑鼠中鍵+滾動 可快速捲動資料列\r\n\r\nTest:\r\n\t測驗字卡在選中正確答案後,再點選答案區的字卡可開啟Explanation Frame,或由功能列->編輯->編輯解釋和例句\r\n\r\nExplanation Frame : \r\n\t滑鼠中鍵以解鎖(或按下解鎖按鈕)\r\n\r\n功能列->編輯->新增詞彙 以打開新增詞彙視窗\r\n\r\n通用編輯框功能:\r\n\tCtrl+X : 刪行\r\n\tCtrl+Z : 復原上一動\r\n\t框選+滑鼠右鍵 以開啟簡易即時翻譯(或按下翻譯按鈕)\r\n\r\n記錄檔位於 data/sample.db ,目前暫無讀/存檔功能,請手動備份還原檔案\r\n\r\n程式目前仍在更新中,將來有可能會出現其它版本,以致資料不相容問題\r\n\r\n\r\n\r\n發音來源編輯:\r\n取得網站網址,以 %s 取得 目標詞彙,例如:\r\nhttps://s.yimg.com/bg/dict/dreye/live/f/value.mp3 則格式網址為 https://s.yimg.com/bg/dict/dreye/live/f/%s.mp3\r\n\r\n\r\n\r\n目前難以維修的bug:\r\ncard box: 不小心再點到 Add(上面的),新增vocabulary 後,再點下面的 add 並不會新增");
	}

}

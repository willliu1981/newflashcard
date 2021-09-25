package com.control.pronounce;

import static org.junit.Assert.assertEquals;

import java.awt.Component;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Test;

import com.tool.ConfigFactory;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PronounceControl {
	final static JFXPanel fxPanel = new JFXPanel();// for javafx initialize
	final static String soundType = ConfigFactory.getPropInstance().getProperty("pronounce_sound_type");
	final static String basePath = ConfigFactory.getPropInstance().getProperty("pronounce_sound_basepath");
	final static String basePathFormatStr = basePath + "/%s/%s." + soundType;
	final static Map<String, String> rule = new LinkedHashMap<>();
	static MediaPlayer mediaPlayer;
	static {
		rule.put("[\\s]+", "_");
		rule.put("[_]+", "-");
		rule.put("[-]", "_");
	}

	protected static boolean checkPath(String path) {
		return checkPath(path, false);
	}

	protected static boolean checkPath(String path, boolean mkdir) {
		boolean r = false;
		File f = null;
		r = (f = new File(path)).exists();
		if (!r && mkdir) {
			f.mkdirs();
		}

		return r; 
	}

	@Test
	public void testPlay() {
		assertEquals(true, play("among"));
	}

	public static boolean play(String vocabulary) {
		Iterator<Entry<String, String>> ruletab = rule.entrySet().iterator();

		while (ruletab.hasNext()) {
			Entry<String, String> k = ruletab.next();
			String path = String.format(basePathFormatStr, vocabulary.charAt(0),
					vocabulary.replaceAll(k.getKey(), k.getValue()));
			if (checkPath(path)) {
				playSoundWithPath(path);
				return true;
			}
		}

		return false;
	}

	protected static void playSoundWithPath(String path) {
		if (!checkPath(path)) {
			return;
		}
		File f = new File(path);
		Media hit = new Media(f.toURI().toString());
		mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
		mediaPlayer.setVolume(1.0);
	}

	public static boolean download(String vocabulary) {

		String[] strarr = PronounceFormatStrFactory.getEffectiveArr();

		Iterator<Entry<String, String>> ruletab = null;
		for (String s : strarr) {
			ruletab = rule.entrySet().iterator();
			while (ruletab.hasNext()) {
				Entry<String, String> entry = ruletab.next();
				String v = vocabulary.replaceAll(entry.getKey(), entry.getValue());
				try {
					download(s, v);
				} catch (IOException e) {
					// e.printStackTrace();
					continue;
				}
				return true;
			}
		}
		return false;
	}

	protected static void download(String urlFormatStr, String vocabulary) throws IOException {
		URL url = new URL(String.format(urlFormatStr, vocabulary));
		String prefixPath = String.format(basePath + "/%s", vocabulary.charAt(0));
		checkPath(prefixPath, true);

		String path = String.format(basePathFormatStr, vocabulary.charAt(0), vocabulary);
		if (!checkPath(path)) {
			try {
				BufferedInputStream bis = new BufferedInputStream(url.openStream());
				FileOutputStream fis = new FileOutputStream(path);
				byte[] buffer = new byte[1024];
				int count = 0;
				while ((count = bis.read(buffer, 0, 1024)) != -1) {
					fis.write(buffer, 0, count);
				}
				fis.close();
				bis.close();
			} catch (IOException e) {
				throw new IOException("網頁資源不存在 : " + vocabulary);
			}
		}

	}

}

package com.control.pronounce;

import static org.junit.Assert.assertEquals;

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

import org.junit.Test;

import com.tool.PropertiesFactory;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PronounceControl {
	final static JFXPanel fxPanel = new JFXPanel();// for javafx initialize
	final static String soundType = PropertiesFactory.getInstance().getProperty("pronounce_sound_type");
	final static String basePath = PropertiesFactory.getInstance().getProperty("pronounce_sound_basepath");
	final static String basePathFormatStr = basePath + "/%s/%s." + soundType;
	final static Map<String, String> rule = new LinkedHashMap<>();
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
		Iterator<Entry<String, String>> tab = rule.entrySet().iterator();

		while (tab.hasNext()) {
			Entry<String, String> k = tab.next();
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
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	}

	public static boolean download(String vocabulary) {
		String[] strarr = PronounceFactory.getEffectiveArr();

		Iterator<Entry<String, String>> it = null;
		String v = null;
		for (String s : strarr) {
			it = rule.entrySet().iterator();
			while (it.hasNext()) {
				try {
					Entry<String, String> entry = it.next();
					v = vocabulary.replaceAll(entry.getKey(), entry.getValue());
					download(s, v);
					return true;
				} catch (IOException e) {
					// e.printStackTrace();
					continue;
				}
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

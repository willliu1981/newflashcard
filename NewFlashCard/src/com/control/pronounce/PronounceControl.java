package com.control.pronounce;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import com.tool.PropertiesFactory;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PronounceControl {
	final static JFXPanel fxPanel = new JFXPanel();// for javafx initialize
	final static String soundType = PropertiesFactory.getInstance().getProperty("pronounce_sound_type");
	final static String basePath = PropertiesFactory.getInstance().getProperty("pronounce_sound_basepath");

	protected static boolean checkSoundPathExist(String path) {
		return checkSoundPathExist(path, false);
	}

	protected static boolean checkSoundPathExist(String path, boolean mkdir) {
		boolean r = false;
		File f = null;
		r = (f = new File(path)).exists();
		if (!r && mkdir) {
			f.mkdirs();
		}

		return r;
	}

	public static void playSound(String vocabulary) {
		String path = String.format(basePath + "/%s/%s.%s", vocabulary.charAt(0), vocabulary, soundType);
		if (!checkSoundPathExist(path)) {
			return;
		}
		File f = new File(path);
		Media hit = new Media(f.toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	}

	public static void downloadUsingStream(String vocabulary)  {
		String [] strarr=PronounceFactory .getFormatStrArr();
		for (String s:strarr) {
			try {
				downloadUsingStream(s,vocabulary);
			} catch (IOException e) {
				//e.printStackTrace();
				continue;
			}
			break;
		}
		
	}

	public static void downloadUsingStream(String urlFormatStr, String vocabulary) throws IOException {
		URL url = new URL(String.format( urlFormatStr,vocabulary));
		String prefixPath = String.format(basePath + "/%s", vocabulary.charAt(0));
		checkSoundPathExist(prefixPath, true);

		String path = String.format(prefixPath + "/%s.%s", vocabulary, soundType);
		if (!checkSoundPathExist(path)) {
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

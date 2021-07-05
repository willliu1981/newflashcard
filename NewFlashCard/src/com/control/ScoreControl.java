package com.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import com.model.Score;

public class ScoreControl {
	public static class Model {
		private static final double[] rate = { 1.0, 1.2, 1.33, 1.47, 1.6, 1.77, 2.0, 2.2, 2.4, 2.64 };

		public static double getRate(int index) {
			double r = 0;
			if (index >= rate.length) {
				r = rate[rate.length - 1];
			} else {
				r = rate[index];
			}
			return r;
		}
	}

	private static final String path = "data/score.properties";
	private static final Properties prop = new Properties();
	private static final String KEY_STARCOIN = "starcoin";
	private static final String KEY_EXPERIENCE = "experience";
	public static final double RATE_DEFAULT = 0.5;
	private static final int PRIMARYEXPERIENCE = 100;
	private static final int SECONDARYEXPERIENCE = 50;
	private static final int BASECOIN = 50;
	private static final int ALLFINISHCOIN = 250;
	private static Score score = new Score();
	public static Model model = new Model();

	static {
		try {
			File f = new File(path);
			if (!f.exists()) {
				if (!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
				f.createNewFile();
			}
			prop.load(new FileInputStream(new File(path)));
			String p = null;
			score.setStarcoin(Integer.valueOf((p = prop.getProperty(KEY_STARCOIN)) == null ? "0" : p));
			score.setExperience(Integer.valueOf((p = prop.getProperty(KEY_EXPERIENCE)) == null ? "0" : p));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void store() {
		try {
			prop.setProperty(KEY_STARCOIN, score.getStarcoin().toString());
			prop.setProperty(KEY_EXPERIENCE, score.getExperience().toString());
			prop.store(new FileOutputStream(new File(path)),null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int primaryExp() {
		return primaryExp(1.0);
	}

	public static int primaryExp(double rate) {
		int v = 0;
		score.addExperience(v = (int) (PRIMARYEXPERIENCE * rate));
		return v;
	}

	public static int secondaryExp() {
		return secondaryExp(1.0);
	}

	public static int secondaryExp(double rate) {
		int v = 0;
		score.addExperience(v = (int) (SECONDARYEXPERIENCE * rate));
		return SECONDARYEXPERIENCE;
	}

	public static int gainBaseCoin(double rate) {
		int v = 0;
		score.addCoin(v = (int) (BASECOIN * rate));
		return v;
	}

	public static int gainAllFinishCoin(double rate) {
		int v = 0;
		score.addCoin(v = (int) (ALLFINISHCOIN * rate));
		return v;
	}

	public static Score getScore() {
		return score;
	}
}

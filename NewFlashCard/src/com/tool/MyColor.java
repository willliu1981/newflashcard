package com.tool;

import java.awt.Color;

public class MyColor {
	
	public static Color defaultColor() {
		return new Color(0xf0f0f0);
	}
	public static Color darkGreen() {
		return new Color(0,96,24);
	}
	

	public static Color heightenColor(Color color, double rate) {
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		r = colorInRange(r + r * rate);
		g = colorInRange(g + g * rate);
		b = colorInRange(b + b * rate);

		return new Color(r, g, b);
	}

	private static int colorInRange(double value) {
		return colorInRange((int) value);
	}

	private static int colorInRange(int value) {
		if (value < 0) {
			value = 0;
		} else if (value > 255) {
			value = 255;
		}
		return value;
	}

}

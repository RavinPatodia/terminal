package com.langmy.terminal.common.utils;

import java.util.Random;

public class ColorUtils {
	static String[] color = { "#FF0F00", "#FF6600", "#FF9E01", "#FCD202",
			"#B0DE09", "#04D215", "#0D8ECF", "#0D52D1", "#2A0CD0",
			"#8A0CCF", "#CD0D74", "#754DEB", "#DDDDDD", "#999999", "#333333",
			"#000000","#FF0F00", "#FF6600", "#FF9E01", "#FCD202",
			"#B0DE09", "#04D215", "#0D8ECF", "#0D52D1", "#2A0CD0",
			"#8A0CCF", "#CD0D74", "#754DEB", "#DDDDDD", "#999999", "#333333",
			"#000000"  };

	public static String getRandomColor() {
		String r, g, b;
		Random random = new Random();
		r = Integer.toHexString(random.nextInt(256)).toUpperCase();
		g = Integer.toHexString(random.nextInt(256)).toUpperCase();
		b = Integer.toHexString(random.nextInt(256)).toUpperCase();

		r = r.length() == 1 ? "0" + r : r;
		g = g.length() == 1 ? "0" + g : g;
		b = b.length() == 1 ? "0" + b : b;

		System.out.println(r + g + b);
		return r + g + b;
	}

	public static String[] getColor() {
		return color;
	}

}

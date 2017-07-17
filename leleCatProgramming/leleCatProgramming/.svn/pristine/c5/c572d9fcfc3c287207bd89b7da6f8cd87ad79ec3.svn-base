package cc.lelecat.framework;

/**
 * Created by ACat on 2016/9/23.
 * ACat i lele.
 */
public class Escape {
	public static String encode(String str) {
		if (str == null) return null;
		return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;");
	}

	public static String decode(String str) {
		if (str == null) return null;
		return str.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"");
	}
}

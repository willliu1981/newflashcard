package com.control.page;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Webpage {
	private String pageUrl;// 定義需要操作的網頁地址
	private String pageEncode = "UTF8";// 定義需要操作的網頁的編碼

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getPageEncode() {
		return pageEncode;
	}

	public void setPageEncode(String pageEncode) {
		this.pageEncode = pageEncode;
	}

//定義取原始碼的方法
	public String getPageSource() {
		StringBuffer sb = new StringBuffer();
		try {
//構建一URL物件
			URL url = new URL(pageUrl);
//使用openStream得到一輸入流並由此構造一個BufferedReader物件
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), pageEncode));
			String line;
//讀取www資源
			while ((line = in.readLine()) != null) {
				sb.append(line+"\n");
			}
			in.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
		return sb.toString();
	}

//定義一個把HTML標籤刪除過的原始碼的方法
	public String getPageSourceWithoutHtml() {
		final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定義script的正規表示式
		final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定義style的正規表示式
		final String regEx_html = "<[^>] >"; // 定義HTML標籤的正規表示式
		final String regEx_space = "\\s*|\t|\r|\n";// 定義空格回車換行符
		String htmlStr = getPageSource();// 獲取未處理過的原始碼
		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 過濾script標籤
		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 過濾style標籤
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 過濾html標籤
		Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
		Matcher m_space = p_space.matcher(htmlStr);
		htmlStr = m_space.replaceAll(""); // 過濾空格回車標籤
		htmlStr = htmlStr.trim(); // 返回文字字串
		htmlStr = htmlStr.replaceAll(" ", "");
		htmlStr = htmlStr.substring(0, htmlStr.indexOf("。"));
		return htmlStr;
	}
}

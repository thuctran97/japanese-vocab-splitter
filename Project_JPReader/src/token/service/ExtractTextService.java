package token.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class ExtractTextService {
	public static java.lang.String extractMeaning(java.lang.String word){
		String url = "http://2.vndic.net/"+word+"-jp_vi.html";
		StringBuffer meaning = new StringBuffer();
		try {
			Document document = Jsoup.connect(url).get();
			Elements link = document.getElementsByClass("tacon");
			int count = 0;
			for (Element element : link) {
				count++;
				if (count<4 || element.text().equals("")) continue;
				if (element.text().contains(":")) {
					meaning.append(". ").append(element.text()).append("\n");
				} else {
					meaning.append("+ ").append(element.text()).append("\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return meaning.toString();
	}

	@Test
	public void test() {
		java.lang.String result = extractMeaning("ＴＰＰ交渉");
		System.out.println(result);
	}

	public static String extractTextFromURL(String url) {
		String urlAfterFix = url;
		if (url.startsWith(","))
			urlAfterFix = url.substring(1, url.length());
		StringBuffer resultText = new StringBuffer();
		try {
			Document document = Jsoup.connect(urlAfterFix).get();
			System.out.println("Title : " + document.title());
			Elements link = document.select("div");
			for (Element element : link) {
				String text = element.text();
				if (checkText(text)) {
					resultText.append(element.text());
					System.out.println(element.text());
				}

			}
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
		return resultText.toString();
	}

	private static boolean checkText(String text) {
		if (text.contains("JavaScript"))
			return false;
		if (text.equals(" ") || text.equals(""))
			return false;
		return true;
	}
}

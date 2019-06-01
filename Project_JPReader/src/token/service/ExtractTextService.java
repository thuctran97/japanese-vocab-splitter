package token.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class ExtractTextService {
	public static String extractMeaning(String word) {
		String url = "http://2.vndic.net/" + word + "-jp_vi.html";
		StringBuffer meaning = new StringBuffer();
		try {
			Document document = Jsoup.connect(url).get();
			Elements link = document.getElementsByClass("tacon");
			int count = 0;
			for (Element element : link) {
				count++;
				if (count < 4 || element.text().equals(""))
					continue;
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

//	@Test
	public void test() {
		String result = extractMeaning("ＴＰＰ交渉");
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

	@Test
	public void getLv() throws Exception{
		URL u;
		Scanner s;
		u = new URL("https://jisho.org/search/%E3%81%99%E3%82%8B");
		s = new Scanner(u.openStream());
		int id=0;
		while (s.hasNext()) {
			String t = s.nextLine();
			System.out.println(id+" "+t);
			if (t.contains("Wanikani level 40")) break;
			id++;
		}
		s.close();
	}

	public static int getLevel(String baseForm) {
		String urlLink = "https://jisho.org/search/" + baseForm;
		// try {
		// Document document = Jsoup.connect(url).get();
		// Elements link = document.getElementsByClass("concept_light-tag label");
		// for (Element element : link) {
		// System.out.println(element.text());
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		try {
			URL url = new URL(urlLink);

			// read text returned by server
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String line;
			while ((line = in.readLine()) != null) {
				if (line.contains("Wanikani level")) {
					System.out.println(line);
					break;
				}
			}
			in.close();
		} catch (MalformedURLException e) {
			System.out.println("Malformed URL: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
		}
		return 1;
	}
}

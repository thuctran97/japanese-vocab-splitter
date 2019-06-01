package token.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author giasutinhoc.vn
 */
public class SQLLoad {
//	 static int id = 2;
	static int id = 39025;

	public static void main(String[] args) throws IOException {
		readUnicode();
	}

	private static void readUnicode() throws IOException {
		String str;
//		BufferedReader bufReader = new BufferedReader(
//				new InputStreamReader(new FileInputStream("d:/Github/Dict/hiraInCompled.txt"), "UTF-8"));
//		Writer w = new OutputStreamWriter(new FileOutputStream("d:/Github/Dict/hiraOutCompleted.txt"), "UTF-8");
		BufferedReader bufReader = new BufferedReader(
				new InputStreamReader(new FileInputStream("d:/Github/Dict/kataIn.txt"), "UTF-8"));
		Writer w = new OutputStreamWriter(new FileOutputStream("d:/Github/Dict/kataOutCompleted.txt"), "UTF-8");
		//
		// BufferedReader bufReader = new BufferedReader(
		// new InputStreamReader(new FileInputStream("d:/Github/Dict/NSQL2.txt"),
		// "UTF-8"));
		// Writer w = new OutputStreamWriter(new
		// FileOutputStream("d:/Github/Dict/NSQLOut.txt"), "UTF-8");

		while ((str = bufReader.readLine()) != null) {
			System.out.println(str);
//			w.write(loadSQL(str));
			 w.write(loadSQLkata(str));
			// w.write(loadTrim(str));
			w.write("\n");
		}
		bufReader.close();
		w.close();
	}

	private static String loadTrim(String str) {
		StringBuffer s = new StringBuffer();
		int begin = str.indexOf("S(");
		// System.out.println(begin);
		for (int i = 0; i < str.length(); i++) {
			if (i < begin) {
				s.append(str.charAt(i));
			} else {
				if (str.charAt(i) != ' ' && str.charAt(i) != '	' && str.charAt(i) != '	') {
					s.append(str.charAt(i));
				}
			}
		}
		return s.toString();
	}

	private static String loadSQLkata(String str) {
		StringBuffer result = new StringBuffer();
		String queryBegin = "INSERT INTO JVDICT (Id, HikaWord, Kanji, Spell , Meaning, Level) VALUES (";
		String kanji = "";
		String hikaword = "";
		String meaning = "";
		String spell = "";
		int level = 0;
		if (str.contains("「 ")) {
			int begin = str.indexOf("「 ");
			int thang1 = str.indexOf("#");
			int thang2 = str.indexOf("#", thang1 + 1);
			int thang3 = str.indexOf("#", thang2 + 1);
			int ngoacmo = str.indexOf("「", thang2 + 1);
			int end = str.indexOf("|=", thang3 + 2);
			if (end == -1)
				end = str.length();
			hikaword = str.substring(ngoacmo + 1, thang3 - 1).trim();
			spell = str.substring(0, thang1).trim();
			meaning = str.substring(thang3 + 3, end).trim();
			kanji = str.substring(thang2 + 1, ngoacmo - 1).trim();
			level = getLevel(hikaword, kanji);
			result.append(queryBegin).append(id++).append(",\"" + hikaword + "\"").append(",\"" + kanji + "\"")
					.append(",\"" + spell + "\"").append(",\"" + meaning + "\"," + level + ");");
		} else {
			int thang1 = str.indexOf("#");
			int thang2 = str.indexOf("#", thang1 + 1);
			int thang3 = str.indexOf("#", thang2 + 1);
			int end = str.indexOf("|=", thang3 + 2);
			if (end == -1)
				end = str.length();
			hikaword = str.substring(thang2 + 1, thang3).trim();
			spell = str.substring(0, thang1).trim();
			meaning = str.substring(thang3 + 3, end).trim();
			level = getLevel(hikaword, kanji);
			result.append(queryBegin).append(id++).append(",\"" + hikaword + "\"").append(",\"" + kanji + "\"")
					.append(",\"" + spell + "\"").append(",\"" + meaning + "\"," + level + ");");
		}
		System.out.println(result);
		return result.toString();
	}

	private static String loadSQL(String str) {
		StringBuffer result = new StringBuffer();
		String queryBegin = "INSERT INTO JVDICT (Id, HikaWord, Kanji, Spell , Meaning, Level) VALUES (";
		int thangIndex = str.indexOf("##");
		String spell = str.substring(0, thangIndex);
		System.out.println(spell);
		String kanji = "";
		String hikaword = "";
		String meaning = "";
		int level = 0;
		if (str.contains("「 ")) {
			int begin = str.indexOf("「 ");
			hikaword = str.substring(thangIndex + 2, begin).trim();
			String[] splitKanji = str.split("「 ");
			for (int i = 0; i < splitKanji.length; i++)
				if (i > 0) {
					int end = splitKanji[i].indexOf(" 」");
					kanji = splitKanji[i].substring(0, end);
					String[] splitPart = splitKanji[i].split("=");
					for (String string : splitPart) {
						if (!string.contains("|-") && !string.contains(" 」") && string.length() != 1) {
							meaning = string.trim();
							break;
						}
					}
					if (meaning.endsWith("|")) {
						meaning = meaning.substring(0, meaning.length() - 1);
					}
					level = getLevel(hikaword, kanji);
					result.append(queryBegin).append(id++).append(",\"" + hikaword + "\"").append(",\"" + kanji + "\"")
							.append(",\"" + spell + "\"").append(",\"" + meaning + "\"," + level + ");");
					if (i != splitKanji.length - 1) {
						result.append("\n");
					}
				}
		} else {
			int begin = str.indexOf("#", thangIndex + 2);
			hikaword = str.substring(thangIndex + 2, begin).trim();
			System.out.println(hikaword);
			System.out.println(kanji);
			String[] parts = str.split("= ");
			for (String part : parts) {
				System.out.println(part);
				if (!part.contains("#") && !part.contains("=") && !part.contains(":")) {
					meaning = part.trim();
					if (meaning.endsWith("|")) {
						meaning = meaning.substring(0, meaning.length() - 1);
					}
					break;
				}
			}
			System.out.println("-----------Meaning:" + meaning);
			level = getLevel(hikaword, kanji);
			result.append(queryBegin).append(id++).append(",\"" + hikaword + "\"").append(",\"" + kanji + "\"")
					.append(",\"" + spell + "\"").append(",\"" + meaning + "\"," + level + ");");
		}
		System.out.println(result);
		return result.toString();
	}

	private static int getLevel(String hikaword, String kanji) {
		int level = 0;
		String str;
		String word = "\"" + hikaword + "\"";
		String kan = "\"" + kanji + "\"";
		int lid = 0;
		try {
			BufferedReader bufReader = new BufferedReader(
					new InputStreamReader(new FileInputStream("d:/Github/Dict/NSQLOut.txt"), "UTF-8"));
			while ((str = bufReader.readLine()) != null) {
				lid++;
				if ((!word.equals("\"\"") && str.contains(word)) || (!kan.equals("\"\"") && str.contains(kan))) {
					level = Character.getNumericValue(str.charAt(str.length() - 3));
					System.out.println("++++++++++++++++++++++++level: " + level + "+++id: " + lid);
					return level;
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return level;
	}

}

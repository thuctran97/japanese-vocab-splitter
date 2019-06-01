package token.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import token.service.ExtractTextService;
import token.service.Flashcard;
import token.service.JVDictService;

@Controller
public class HomeController {
	private List<AdditionalToken> tokensWithId = new ArrayList<AdditionalToken>();

	@Autowired
	JVDictService dictService;

	@Autowired
	Flashcard flashcard;

	@RequestMapping("home")
	public String home() {
		return "home/index";
	}
	
	@RequestMapping(value = "result", method = RequestMethod.POST)
	public String resultForText(ModelMap model, @RequestParam("content") String myText) {
		System.out.println(myText);
		String processText = myText;
		if (myText.contains("http")) {
			processText = ExtractTextService.extractTextFromURL(processText);
		}
		tokensWithId = new ArrayList<AdditionalToken>();
		Tokenizer tokenizer;
		tokenizer = Tokenizer.builder().build();
		List<Token> tokens = tokenizer.tokenize(processText);
		int id = 0;
		System.out.println("Number of tokens: " + tokens.size());
		for (Token token : tokens) {
			System.out.println("BEGIN- Set token for: " + token.getSurfaceForm() + " ---" + token.getReading());
			AdditionalToken at = new AdditionalToken();
			at.setId(id++);
			at.setSurfaceForm(token.getSurfaceForm());
			at.setReading(token.getReading());
			at.setPartOfSpeech(token.getPartOfSpeech());
			at.setBaseForm(token.getBaseForm());
			if (checkToken(token)) {
				at.setValidWord(true);
				at.setMeaning(dictService.getMeaning(token.getBaseForm(), token.getReading()));
//				System.out.println(ExtractTextService.getLevel(token.getBaseForm()));
			} 
			tokensWithId.add(at);
			System.out.println("END- Set token number" + id);
		}
		model.addAttribute("text", tokensWithId);

		return "home/index";
	}

	private boolean checkToken(Token token) {
		if (token.getBaseForm() == null)
			return false;
		if (token.getBaseForm().equals("、"))
			return false;
		if (token.getBaseForm().equals("。")) {
			return false;
		}
		if (token.getBaseForm().equals("「")) {
			return false;
		}
		if (token.getBaseForm().equals("」")) {
			return false;
		}
		if (token.getBaseForm().equals("・")) {
			return false;
		}
		System.out.println("check token:---" + token.getBaseForm() + "---------");
		return true;
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "select")
	public JSONObject choice(@RequestParam("id") int id) {
		AdditionalToken t = tokensWithId.get(id);
		flashcard.add(t);
		System.out.println("-------------FLASH CARD SIZE: "+flashcard.getSize());
		JSONObject json = new JSONObject();
		json.put("reading", t.getReading());
		json.put("surfaceForm", t.getSurfaceForm());
		json.put("partOfSpeech", t.getPartOfSpeech());
		json.put("baseForm", t.getBaseForm());
		return json;
	}
}

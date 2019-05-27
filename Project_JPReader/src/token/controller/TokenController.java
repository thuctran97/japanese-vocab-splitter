package token.controller;

import java.util.ArrayList;
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

import token.entity.JVDict;
import token.service.ExtractTextService;
import token.service.JVDictService;

@Controller
public class TokenController {
	private List<AdditionalToken> tokensWithId = new ArrayList<AdditionalToken>();

	@Autowired
	JVDictService dictService;

	@RequestMapping("home")
	public String home() {
		return "home";
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
			if (token.getBaseForm() != null) {
				System.out.println("BEGIN- Set token for: " + token.getBaseForm());
				AdditionalToken at = new AdditionalToken();
				at.setId(id++);
				at.setSurfaceForm(token.getSurfaceForm());
				at.setReading(token.getReading());
				at.setPartOfSpeech(token.getPartOfSpeech());
				at.setBaseForm(token.getBaseForm());
				at.setMeaning(dictService.getMeaning(token.getSurfaceForm()));
				tokensWithId.add(at);
				System.out.println("END- Set token number" + id);
			}
		}
		model.addAttribute("text", tokensWithId);
		return "home";
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "select")
	public JSONObject choice(@RequestParam("id") int id) {
		AdditionalToken t = tokensWithId.get(id);
		JSONObject json = new JSONObject();
		json.put("reading", t.getReading());
		json.put("surfaceForm", t.getSurfaceForm());
		json.put("partOfSpeech", t.getPartOfSpeech());
		json.put("baseForm", t.getBaseForm());
		return json;
	}
}

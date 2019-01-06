package token.controller;


import java.util.ArrayList;
import java.util.List;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TokenController {
	List<AdditionalToken> tokensWithId = new ArrayList<AdditionalToken>();
	@RequestMapping("home")
	public String index() {
		return "index";
	}
	@RequestMapping(value="result", method=RequestMethod.POST)
	public String result(ModelMap model,
			@RequestParam("content") String myText) {
		model.addAttribute("message",myText);
		Tokenizer tokenizer;
		tokenizer = Tokenizer.builder().build();
		List<Token> tokens = tokenizer.tokenize(myText);	
		int id=0;
        for (Token token : tokens) {
        	AdditionalToken at = new AdditionalToken();
        	at.setId(id++);
        	at.setSurfaceForm(token.getSurfaceForm());
        	at.setReading(token.getReading());
        	tokensWithId.add(at);
            System.out.println(token.getSurfaceForm() + "\t" + token.getAllFeatures());
        }

		model.addAttribute("text",tokensWithId);
		return "index";
	}
//	@ResponseBody
//	@RequestMapping(value="select")
//	public java.lang.String choice(ModelMap model, @RequestParam("id") int id){
//		AdditionalToken t= tokensWithId.get(id);
//		String json = String.format("[%s, %s]", t.getReading(), t.getSurfaceForm());
//		return json;
//	}
}
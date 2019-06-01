package token.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import token.service.Flashcard;

@Controller
@RequestMapping("flashcard")
public class FlashCardController {
	@Autowired 
	Flashcard flashcard;
	@RequestMapping("view")
	public String view(ModelMap model) {
		model.addAttribute("list", flashcard);
		return "home/flashcard_view";
	}
}

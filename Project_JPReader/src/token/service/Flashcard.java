package token.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import token.controller.AdditionalToken;

@Component("flashcard")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Flashcard {
	private int id = 0;
	Map<Integer, AdditionalToken> map = new HashMap<Integer, AdditionalToken>();

	public void add(AdditionalToken at) {
		map.put(id++, at);
	}
	public void clear() {
		map.clear();
	}
	public int getSize() {
		return map.size();
	}
	public Collection<AdditionalToken> getFlashcards() {
		return map.values();
	}
}

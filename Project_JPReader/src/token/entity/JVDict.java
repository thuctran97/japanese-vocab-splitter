package token.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JVDICT")
public class JVDict {
	@Id
	@Column(name="Id")
	int id;
	@Column(name="HikaWord")
	String hikaWord;
	@Column(name="Kanji")
	String kanji;
	@Column(name="Spell")
	String spell;
	@Column(name="Meaning")
	String meaning;
	@Column(name="Level")
	int level;
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHikaWord() {
		return hikaWord;
	}
	public void setHikaWord(String hikaWord) {
		this.hikaWord = hikaWord;
	}
	public String getKanji() {
		return kanji;
	}
	public void setKanji(String kanji) {
		this.kanji = kanji;
	}
	public String getSpell() {
		return spell;
	}
	public void setSpell(String spell) {
		this.spell = spell;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	
	
	
}

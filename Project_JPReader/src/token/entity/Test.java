package token.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Test")
public class Test {
	@Id
	@Column(name="Id")
	int id;
	@Column(name="Kanji")
	String kanji;
	@Column(name="Hira")
	String hira;
	@Column(name="Level")
	int level;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKanji() {
		return kanji;
	}
	public void setKanji(String kanji) {
		this.kanji = kanji;
	}
	public String getHira() {
		return hira;
	}
	public void setHira(String hira) {
		this.hira = hira;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}

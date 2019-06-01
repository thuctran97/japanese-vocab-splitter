package token.controller;

public class AdditionalToken {
	private int id;
	private int level;
	private String surfaceForm;
	private String reading;
	private String baseForm;
	private String partOfSpeech;
	private String meaning;
	private boolean validWord;
	public boolean isValidWord() {
		return validWord;
	}
	public void setValidWord(boolean validWord) {
		this.validWord = validWord;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getSurfaceForm() {
		return surfaceForm;
	}
	public void setSurfaceForm(String surfaceForm) {
		this.surfaceForm = surfaceForm;
	}
	public String getReading() {
		return reading;
	}
	public void setReading(String reading) {
		this.reading = reading;
	}
	public String getBaseForm() {
		return baseForm;
	}
	public void setBaseForm(String baseForm) {
		this.baseForm = baseForm;
	}
	public String getPartOfSpeech() {
		return partOfSpeech;
	}
	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	
}

package token.controller;

public class AdditionalToken {
	private int id;
	private int level;
	private java.lang.String surfaceForm;
	private java.lang.String reading;
	private java.lang.String baseForm;
	private java.lang.String partOfSpeech;
	private String meaning;
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public java.lang.String getBaseForm() {
		return baseForm;
	}
	public void setBaseForm(java.lang.String baseForm) {
		this.baseForm = baseForm;
	}
	public java.lang.String getPartOfSpeech() {
		return partOfSpeech;
	}
	public void setPartOfSpeech(java.lang.String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public java.lang.String getSurfaceForm() {
		return surfaceForm;
	}
	public void setSurfaceForm(java.lang.String surfaceForm) {
		this.surfaceForm = surfaceForm;
	}
	public java.lang.String getReading() {
		return reading;
	}
	public void setReading(java.lang.String reading) {
		this.reading = reading;
	}
}

package token.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FlashcardInfos")
public class FlashcardInfo {
	@Id
	String flashcardId;
	
	@ManyToOne
	@JoinColumn(name="UserId")
	User user;
	java.lang.String surfaceForm;
	java.lang.String reading;
	java.lang.String partOfSpeech;
	java.lang.String baseForm;
	public String getFlashcardId() {
		return flashcardId;
	}
	public void setFlashcardId(String flashcardId) {
		this.flashcardId = flashcardId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	String level;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public java.lang.String getPartOfSpeech() {
		return partOfSpeech;
	}
	public void setPartOfSpeech(java.lang.String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}
	public java.lang.String getBaseForm() {
		return baseForm;
	}
	public void setBaseForm(java.lang.String baseForm) {
		this.baseForm = baseForm;
	}
	
	
}

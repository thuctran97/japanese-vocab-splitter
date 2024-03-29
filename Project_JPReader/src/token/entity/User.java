package token.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Users")
public class User {
	@Id
	String id;
	String name;
	String password;
	String email;
	@OneToMany(mappedBy="user")
	Collection<FlashcardInfo> infos;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<FlashcardInfo> getInfos() {
		return infos;
	}
	public void setInfos(Collection<FlashcardInfo> infos) {
		this.infos = infos;
	}
	
}

package de.maxhoch.onlineshop;

import java.io.Serializable;

public class Customer implements Serializable {

	
	private static final long serialVersionUID = 1231123223453465345L;
	
	private String email;
	
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}

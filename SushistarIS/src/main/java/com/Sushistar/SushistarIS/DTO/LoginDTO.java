package com.Sushistar.SushistarIS.DTO;

public class LoginDTO
{
	public LoginDTO() {}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String email;
	private String password;
}

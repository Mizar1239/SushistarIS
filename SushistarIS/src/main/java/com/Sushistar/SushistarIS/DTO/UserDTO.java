package com.Sushistar.SushistarIS.DTO;

public class UserDTO
{
	public UserDTO(String email, int roleId)
	{
		this.email = email;
		this.roleId = roleId;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public int getRoleId() {
		return roleId;
	}

	private String email;
	private String password;
	private String username;
	private int roleId;
}

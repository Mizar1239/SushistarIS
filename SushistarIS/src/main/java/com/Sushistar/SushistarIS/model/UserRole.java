package com.Sushistar.SpringDbMockup.SushistarSpringDatabase.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable
{
	public UserRole() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleTag() {
		return roleTag;
	}

	public void setRoleTag(String roleTag) {
		this.roleTag = roleTag;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "role_tag0", length = 20, nullable = false)
	private String roleTag;
}
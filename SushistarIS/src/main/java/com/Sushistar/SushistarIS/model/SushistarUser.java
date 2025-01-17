package com.Sushistar.SushistarIS.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name = "sushistar_user")
public class SushistarUser implements Serializable
{
	public SushistarUser() {}

	public SushistarUser(Long id){this.id = id;}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 40)
	private String username;

	@Column(nullable = false, unique = true, length = 50)
	private String email;

	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 30)
	private String firstname;

	@Column(nullable = false, length = 30)
	private String lastname;

	@Column(nullable = false, length = 200)
	private String address;

	@Column(length = 15)
	private String phoneNumber;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	@ManyToOne
	@JoinColumn(name = "user_role", nullable = false)
	private UserRole role;

	String card_number;
	String cvv;
	String expirationDate;
	String cardOwner;
	String addressConsegna;
	String cap;


	public void setCardNumber(String number) {
		this.card_number = number;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}


	public void setOwnerName(String cardOwner) {
		this.cardOwner = cardOwner;
	}


	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCap() {
		return cap;
	}

	public String getOwnerName() {
		return cardOwner;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public String getCvv() {
		return cvv;
	}

	public String getCardNumber() {
		return card_number;
	}
}
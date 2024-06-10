package com.chn.customer.management.entities;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUSTOMER", schema = "system")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_id_gen")
	@SequenceGenerator(name = "CUSTOMER_id_gen", sequenceName = "CUSTOMER_ID_SEQ", allocationSize = 1)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NAME", nullable = false, length = 100)
	private String name;

	@Column(name = "LASTNAME", nullable = false, length = 100)
	private String lastname;

	@Column(name = "IDENTIFICATION", nullable = false, length = 50)
	private String identification;

	@Column(name = "BIRTHDATE")
	private Date birthdate;

	@Column(name = "EMAIL", length = 200)
	private String email;

	@Column(name = "PHONE", length = 12)
	private String phone;

	@Column(name = "DISABLED", insertable = false)
	private Long disabled;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	@JsonBackReference
	private Set<Account> accounts = new LinkedHashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getDisabled() {
		return disabled;
	}

	public void setDisabled(Long disabled) {
		this.disabled = disabled;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

}
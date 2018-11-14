package com.cheops.candidatemanager.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "iduser")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "username")
	@Size(min = 3, max = 45, message = "{user.username.empty}")
	private String username;

	@Column(name = "email")
	@Size(max = 45, message = "{email.size}")
	@Email(message = "{email.invalid}")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	@NotBlank(message = "{name.empty}")
	@Size(max = 45, message = "{name.size}")
	private String name;

	@Column(name = "last_name")
	@Size(max = 45, message = "{last_name.size}")
	private String lastName;

	@Column(name = "active")
	private boolean active;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@NotEmpty(message = "{user.roles.empty}")
	private Set<Role> roles;

	public User() {
	}

	public User(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.name = user.getName();
		this.lastName = user.getLastName();
		this.active = user.getActive();
		this.roles = user.getRoles();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", lastName="
				+ lastName + ", active=" + active + ", roles=" + roles + "]";
	}

}

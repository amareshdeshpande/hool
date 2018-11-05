package com.hool.app.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "member")
public class Member{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "member_id")
	private long id;
	
	@Column(name = "login_id")
	@NotEmpty(message = "*Please provide an username")
	private String userName;
	
	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")	
	private String email;
	
	//@JsonIgnore
	@Column(name = "password")
	@Length(min = 2, message = "*Your password must have at least 2 characters")
	//@Transient
	private String password;
	
	@Column(name = "active")
	private int active;
	
	@Column(name = "online", columnDefinition="DEFAULT 0")	
	private int online;
	
	
	@Column(name = "image_name")
	@Length(max =50 )
	private String imageName;	
	
	@Column(name = "last_login", updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")	
	private Date lastLogin;	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "member_role", joinColumns = @JoinColumn(name = "member_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	
	/*@OneToMany(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
	@JoinColumn(name="member_id", referencedColumnName="member_id")
    private List<GameTableMember> gameTableMember;*/
	
	public Member(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	
}

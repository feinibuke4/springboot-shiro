package com.doain;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="user")
public class UserDomain {

	@Id
	private Integer id;
	private String name;
	private String password;
	private String perms;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getPerms() {
		return perms;
	}
	public void setPerms(String perms) {
		this.perms = perms;
	}
	public UserDomain(String name, String password, String perms) {
		super();
		this.name = name;
		this.password = password;
		this.perms = perms;
	}
	public UserDomain() {
		super();
	}
	@Override
	public String toString() {
		return "UserDomain [id=" + id + ", name=" + name + ", password=" + password + ", perms=" + perms + "]";
	}
	
	
}

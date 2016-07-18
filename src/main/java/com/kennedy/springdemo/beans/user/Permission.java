package com.kennedy.springdemo.beans.user;

public class Permission {
	private Integer id;
	private String persionname;
	private Integer roleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPersionname() {
		return persionname;
	}

	public void setPersionname(String persionname) {
		this.persionname = persionname;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}

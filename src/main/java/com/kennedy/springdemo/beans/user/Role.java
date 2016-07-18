package com.kennedy.springdemo.beans.user;

import java.util.List;
import java.util.Set;

public class Role {
	private Integer id;
	private String rolename;
	private Set<Permission> permissionList;
	private List<String> permissionsName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Set<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(Set<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	public List<String> getPermissionsName() {
		return permissionsName;
	}

	public void setPermissionsName(List<String> permissionsName) {
		this.permissionsName = permissionsName;
	}
}

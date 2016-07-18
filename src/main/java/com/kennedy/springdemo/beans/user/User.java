package com.kennedy.springdemo.beans.user;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.kennedy.springdemo.common.BaseData;

public class User extends BaseData implements Serializable {
    private static final long serialVersionUID = -5450606930485978802L;

    @NotNull
    @Size(min=2,max=12)
    private String username;

    private String nickname;
    @NotNull
    @Size(min=6,max=15)
    private String password;

    private String email;

    private String telephone;

    private String address;

    private List<Role> roleList;

    private Set<String> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}

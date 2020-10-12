package com.exchange.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserSecurity extends User {

    @Column
    private String password;
    @Column
    private String token;
    @Column
    private String role;

    public UserSecurity() {}

    public UserSecurity(String password, String token ) {
        this.password = password;
        this.token = token;
    }

    public UserSecurity( String email, String password, String token, String role ) {
        this.setEmail( email );
        this.password = password;
        this.token = token;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    @Override
    public String toString() {
        return "UserSecurity [id="+ this.getId() + ", role=" + role + ", password=" + password + ", token=" + token + "]";
    }

}
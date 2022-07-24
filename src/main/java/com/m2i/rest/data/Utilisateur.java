package com.m2i.rest.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateurs")
@JsonInclude(Include.NON_NULL)
public class Utilisateur implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100)
    private String lastname;
    @Column(length = 100)
    private String firstname;
    @Column(length = 100)
    private String role;
    @Column(length = 100)
    private String email;
    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(length = 100)
    private String password;

    public Utilisateur() {
    }

    public Utilisateur(int id, String lastname, String firstname, String role, String email, String password) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public Utilisateur(String lastname, String firstname, String role, String email, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public void copy(Utilisateur data) {
        if (data.getLastname() != null) {
            this.lastname = data.getLastname();
        }

        if (data.getFirstname() != null) {
            this.firstname = data.getFirstname();
        }

        if (data.getEmail() != null) {
            this.email = data.getEmail();
        }

        if (data.getPassword() != null) {
            this.password = data.getPassword();
        }

        if (data.getRole() != null) {
            this.role = data.getRole();
        }
    }
}

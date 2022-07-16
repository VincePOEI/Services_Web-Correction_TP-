package com.m2i.rest.movie.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "users")
@JsonInclude(Include.NON_NULL)
public class User extends AbstractEntity<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String firstname;
    
    @Column
    private String lastname;
    
    @Column
    private String nickname;
    
    @Column
    private String email;
    
    @Column
    private String password;
    
    @JoinColumn(name = "role_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    public User() {
    }

    public User(String firstname, String lastname, String nickname, String email, String password, Role role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    
    public User(int id, String firstname, String lastname, String nickname, String email, String password, Role role) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public void copy(User entityToCopy) {
        
        if (entityToCopy.getFirstname() != null) {
            this.firstname = entityToCopy.getFirstname();
        }
        
        if (entityToCopy.getLastname() != null) {
            this.lastname = entityToCopy.getLastname();
        }
        
        if (entityToCopy.getNickname() != null) {
            this.nickname = entityToCopy.getNickname();
        } 
        
        if (entityToCopy.getEmail() != null) {
            this.email = entityToCopy.getEmail();
        }
        
        if (entityToCopy.getPassword() != null) {
            this.password = entityToCopy.getPassword();
        }
        
        if (entityToCopy.getRole() != null) {
            this.role = entityToCopy.getRole();
        }
        
    }
    
    
}

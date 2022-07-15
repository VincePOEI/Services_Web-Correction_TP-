package com.m2i.rest.lesson;

public class Utilisateur {

    private Role role;
    private String email;
    private String motPasse;

    public Utilisateur() {
    }

    public Utilisateur(Role role, String email, String motPasse) {
        this.role = role;
        this.email = email;
        this.motPasse = motPasse;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }
}

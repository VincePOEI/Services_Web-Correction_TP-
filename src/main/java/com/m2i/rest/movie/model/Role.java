package com.m2i.rest.movie.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;

@Entity
@Table(name = "roles")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role extends AbstractEntity<Role> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "identifiant", length = 50)
    private String identifiant;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public Role() {
    }

    public Role(int id, String identifiant, String description) {
        this.id = id;
        this.identifiant = identifiant;
        this.description = description;
    }

    public Role(String identifiant, String description) {
        this.identifiant = identifiant;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
  
    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setNotNullData(Role roleData) {
        if (roleData.getIdentifiant() != null) {
            this.setIdentifiant(roleData.getIdentifiant());
        }

        if (roleData.getDescription() != null) {
            this.setDescription(roleData.getDescription());
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        return this.id != other.getId();
    }

    @Override
    public String toString() {
        return String.format("[id=%s, identifiant=%s, description=%s]", id, identifiant, description);
    }

    @Override
    public void copy(Role entityToCopy) {
        
        if (entityToCopy.getIdentifiant() != null) {
            this.identifiant = entityToCopy.getIdentifiant();
        }
        
        if (entityToCopy.getDescription() != null) {
            this.description = entityToCopy.getDescription();
        }
        
    }
    
    
}
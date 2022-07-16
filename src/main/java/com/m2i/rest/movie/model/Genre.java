package com.m2i.rest.movie.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;

@Entity
@Table(name = "genres")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Genre extends AbstractEntity<Genre> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String name;
    
    public Genre() {
    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void copy(Genre entityToCopy) {
        
        if (entityToCopy.getName() != null) {
            this.name = entityToCopy.getName();
        }
        
    }
    
    
}

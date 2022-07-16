package com.m2i.rest.movie.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "actors")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Actor extends AbstractEntity<Actor> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String firstname;
    
    @Column
    private String lastname;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date birthDate;
    
    @JoinTable(name = "actor_movies", joinColumns = @JoinColumn(name = "actor_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Movie> movies;

    public Actor() {
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public void copy(Actor entityToCopy) {
        
        if (entityToCopy.getFirstname() != null) {
            this.firstname = entityToCopy.getFirstname();
        }
        
        if (entityToCopy.getLastname() != null) {
            this.lastname = entityToCopy.getLastname();
        }
        
        if (entityToCopy.getBirthDate() != null) {
            this.birthDate = entityToCopy.getBirthDate();
        }
        
        if (entityToCopy.getMovies() != null) {
            this.movies = entityToCopy.getMovies();
        }
    }
    
    
}
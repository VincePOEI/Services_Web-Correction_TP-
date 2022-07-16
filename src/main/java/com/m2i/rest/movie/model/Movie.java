package com.m2i.rest.movie.model;


import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String name;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "release_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date releaseDate;
    
    @Column
    private int duration;
    
    @Column
    private float rating;
    
    @Column(columnDefinition = "TEXT")
    private String synopsis;
    
    @JoinTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Genre> genres;
    
    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
    private List<Actor> actors;
    
    @Column
    private String originCountry;
    
    @Column
    @ElementCollection
    @CollectionTable(name = "languages", joinColumns = @JoinColumn(name = "movie_id"))
    private List<String> languages;
    
    @OneToMany(mappedBy = "movie", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Comment> comments;

    public Movie() {
    }

    public Movie(int id, String name, Date releaseDate, int duration, float rating, String synopsis, List<Genre> genres, List<Actor> actors, String originCountry, List<String> languages, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.rating = rating;
        this.synopsis = synopsis;
        this.genres = genres;
        this.actors = actors;
        this.originCountry = originCountry;
        this.languages = languages;
        this.comments = comments;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }   
}

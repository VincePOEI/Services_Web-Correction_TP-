package com.m2i.rest.movie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "comments")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment extends AbstractEntity<Comment> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    
    @Column(columnDefinition = "TEXT")
    private String content;
    
    @JsonIgnore
    @JoinColumn(name = "movie_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date creationDate;

    public Comment() {
    }

    public Comment(int id, User user, String content, Movie movie, Date creationDate) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.movie = movie;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public void copy(Comment entityToCopy) {
        
        if (entityToCopy.getUser() != null) {
            this.user = entityToCopy.getUser();
        }
        
        if (entityToCopy.getMovie() != null) {
            this.movie = entityToCopy.getMovie();
        }
        
        if (entityToCopy.getCreationDate() != null) {
            this.creationDate = entityToCopy.getCreationDate();
        }
        
        if (entityToCopy.getContent() != null) {
            this.content = entityToCopy.getContent();
        }
        
    }
    
    
}

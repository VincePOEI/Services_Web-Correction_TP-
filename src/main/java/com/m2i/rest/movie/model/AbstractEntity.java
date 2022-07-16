package com.m2i.rest.movie.model;


public abstract class AbstractEntity<T> {

    public abstract void copy(T entityToCopy);
    
}

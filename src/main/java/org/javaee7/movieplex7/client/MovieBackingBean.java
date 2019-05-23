/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee7.movieplex7.client;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author arbit
 */
@Named
@RequestScoped
public class MovieBackingBean {
    int movieId;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    
    
    
}
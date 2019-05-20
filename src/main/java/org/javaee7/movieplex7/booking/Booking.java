/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee7.movieplex7.booking;

import java.util.StringTokenizer;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.javaee7.movieplex7.entities.Movie;

/**
 *
 * @author arbit
 */
@Named
@FlowScoped("booking")
public class Booking {
    @PersistenceContext
    EntityManager em;
    
    private int movieId;
    private String startTime;
    private int startTimeId;
    
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        StringTokenizer tokens = new StringTokenizer(startTime, ",");
        startTimeId = Integer.parseInt(tokens.nextToken());
        this.startTime = tokens.nextToken();
    }

    public int getStartTimeId() {
        return startTimeId;
    }
    
    public String getMovieName() {
        try {
            return em.createNamedQuery("Movie.findById", Movie.class)
                    .setParameter("id", movieId)
                    .getSingleResult()
                    .getName();
        } catch (NoResultException e) {
            return "";
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee7.movieplex7.booking;

import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.javaee7.movieplex7.entities.Movie;
import org.javaee7.movieplex7.entities.ShowTiming;

/**
 *
 * @author arbit
 */
@Named
@FlowScoped("booking")
public class Booking implements Serializable {
    @PersistenceContext
    EntityManager em;
    
    int movieId;
    String startTime;
    int startTimeId;
    
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
    
    public String getTheater() {
        // ��� ������ � ������
        try {
            //������ ������������ ������ �� ��������� �����������
            List<ShowTiming> list = 
                    em.createNamedQuery("ShowTiming.findByMovieAndTimingId", 
                                        ShowTiming.class)
                            .setParameter("movieId", movieId)
                            .setParameter("timingId", startTimeId)
                            .getResultList();
            if (list.isEmpty()) {
                return "none";
            }
            
            return list
                    .get(0)
                    .getTheaterId()
                    .getId().toString();
        } catch (NoResultException e) {
            return "none";
        }
    }
    
}

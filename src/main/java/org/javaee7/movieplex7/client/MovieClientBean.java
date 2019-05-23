/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee7.movieplex7.client;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.javaee7.movieplex7.entities.Movie;

/**
 *
 * @author arbit
 * Этот бин будет использоваться для вызова конечной точки REST
 */
@Named
@RequestScoped
public class MovieClientBean {
    Client client;
    WebTarget target;
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/movieplex7/webresources/movie/");
    }
    
    @PreDestroy
    public void destruct() {
        client.close();
    }
    
    public Movie[] getMovies() {
        return target.request().get(Movie[].class);
    }
    
}

package com.iabd.videoclub.models;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    private Long id;
    private String title;
    private String director;
    private String gender;
    private String poster;
    private String synopsis;

    public Movie(Long id, String title, String director, String gender, String poster, String synopsis) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.gender = gender;
        this.poster = poster;
        this.synopsis = synopsis;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name = "director", nullable = false)
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }


    @Column(name = "gender", nullable = false)
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    @Column(name = "poster", nullable = false)
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
    @Column(name = "synopsis", nullable = false)
    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}

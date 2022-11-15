package com.yassir.moviesapp.Models;

public class Movie {
    private long id;
    private String title,desc,imgPoster,imgBackDrop,releaseDate;
    private double vote_average;

    public Movie() {
    }

    public Movie(long id, String title, String desc, String imgPoster, String imgBackDrop, String releaseDate, double vote_average) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.imgPoster = imgPoster;
        this.imgBackDrop = imgBackDrop;
        this.releaseDate = releaseDate;
        this.vote_average = vote_average;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgPoster() {
        return imgPoster;
    }

    public void setImgPoster(String imgPoster) {
        this.imgPoster = imgPoster;
    }

    public String getImgBackDrop() {
        return imgBackDrop;
    }

    public void setImgBackDrop(String imgBackDrop) {
        this.imgBackDrop = imgBackDrop;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(long vote_average) {
        this.vote_average = vote_average;
    }
}

package com.clainchoupi.model;

import java.util.ArrayList;

public class Client {

    ArrayList<String> likes,dislikes;
    Integer nbLikes, nbDislikes;

    public Client() {
        likes = new ArrayList<String>();
        dislikes = new ArrayList<String>();
    }

    public ArrayList<String> getLikes() {
        return likes;
    }

    public void setLikes(String[] likes) {
        for (String like : likes) {
            this.likes.add(like);
        }
    }

    public ArrayList<String> getDislikes() {
        return dislikes;
    }

    public void setDisikes(String[] dislikes) {
        for (String dislike : dislikes) {
            this.dislikes.add(dislike);
        }
    }

    public Integer getNbLikes() {
        return nbLikes;
    }

    public void setNbLikes(Integer nbLikes) {
        this.nbLikes = nbLikes;
    }

    public Integer getNbDislikes() {
        return nbDislikes;
    }

    public void setNbDislikes(Integer nbDislikes) {
        this.nbDislikes = nbDislikes;
    }

    
}

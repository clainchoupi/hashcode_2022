package com.clainchoupi.model;

import java.util.ArrayList;
import java.util.HashSet;

public class Result {

    HashSet<String> ingredients = new HashSet<String>();
    HashSet<String> allLiked = new HashSet<String>();
    HashSet<String> allDisliked = new HashSet<String>();

    ArrayList<String> listLiked = new ArrayList<String>();
    ArrayList<String> listDisliked = new ArrayList<String>();

    public ArrayList<String> getListLiked() {
        return listLiked;
    }

    public ArrayList<String> getListDisliked() {
        return listDisliked;
    }

    public HashSet<String> getIngredients() {
        return ingredients;
    }

    public HashSet<String> getAllLiked() {
        //Distinct
        return allLiked;
    }

    public HashSet<String> getAllDisliked() {
        //Distinct
        return allDisliked;
    }
    
}

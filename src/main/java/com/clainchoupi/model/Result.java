package com.clainchoupi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Result {

    HashSet<String> ingredients = new HashSet<String>();
    HashSet<String> allLiked = new HashSet<String>();
    HashSet<String> allDisliked = new HashSet<String>();

    ArrayList<String> listDisliked = new ArrayList<String>();

    public ArrayList<String> getListDisliked() {
        return listDisliked;
    }

    public HashSet<String> getIngredients() {
        return ingredients;
    }

    public HashSet<String> getAllLiked() {
        return allLiked;
    }

    public HashSet<String> getAllDisliked() {
        return allDisliked;
    }
    
}

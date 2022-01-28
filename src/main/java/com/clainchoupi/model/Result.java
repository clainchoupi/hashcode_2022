package com.clainchoupi.model;

import java.util.HashSet;

public class Result {

    HashSet<String> ingredients = new HashSet<String>();
    HashSet<String> allLiked = new HashSet<String>();
    HashSet<String> allDisliked = new HashSet<String>();

    public HashSet<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashSet<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    public HashSet<String> getAllLiked() {
        return allLiked;
    }

    public HashSet<String> getAllDisliked() {
        return allDisliked;
    }
    
}

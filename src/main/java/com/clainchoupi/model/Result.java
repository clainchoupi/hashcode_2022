package com.clainchoupi.model;

import java.util.HashSet;

public class Result {

    private HashSet<String> ingredients = new HashSet<String>();;

    public HashSet<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashSet<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }
    
}

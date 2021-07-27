package com.chaipoint;
import java.util.ArrayList;
import java.util.List;

// Each beverage will have a different recipe to create it. Every recipe is just a list of ingredients.

public class Recipe {
    private List<Ingredient> ingredients;
    public Recipe() {
        ingredients = new ArrayList<>();
    }
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
    }
    public List<Ingredient> getIngredients(){
        return this.ingredients;
    }

}
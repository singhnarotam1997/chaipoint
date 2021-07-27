package com.chaipoint;


// Here ingredient class is made in accordance with recipe class. Example 10 ml of ginger syrup will be 
// counted as an ingredient.
public class Ingredient {
    private String name;
    private double quantity;

    public Ingredient(String name, double quantity){
        this.quantity = quantity;
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public double getQuantity(){
        return quantity;
    }
}

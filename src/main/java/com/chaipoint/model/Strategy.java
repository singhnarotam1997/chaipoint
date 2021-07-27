package com.chaipoint;


// This class is used to validate and then create a beverage according to the type of beverage.
public abstract class Strategy {
    protected Recipe recipe;
    public Validator validator;     // Validator object is a singleton object and is used by all beverage classes.
    public abstract boolean isValid();
    public Beverage process() {
        if(!isValid()){
            return null;
        }
        for(int i=0;i<recipe.getIngredients().size();i++){
            Ingredient ing = recipe.getIngredients().get(i);
            validator.reduceItem(ing.getName(),ing.getQuantity()); 	// reduce the corresponding stock 
        }
        return this.createBeverage();
    }
    Strategy(Validator v,Recipe r){
        this.validator = v;
        this.recipe = r; 
    }
    Recipe getRecipe(){
        return this.recipe;
    }
    public abstract Beverage createBeverage();      
    /* This function is overriden by all subclasses to
     give the responsibilty to child classes to create 
    their own objects according to their requirements. This is done to increase 
    readability and extensibility of code.
    */
 }

 class GingerTeaStrategy extends Strategy {
     public GingerTeaStrategy(Validator validator, Recipe recipe) {
        super(validator,recipe);
     }
 
     public boolean isValid() {
         return validator.isValid(BeverageType.GINGER_TEA,this.recipe);
     }
     public Beverage createBeverage(){
         return new GingerTea();
     }
 } 
 
 
 class ElaichiTeaStrategy extends Strategy {
     public ElaichiTeaStrategy(Validator validator, Recipe recipe) {
        super(validator,recipe);
     }
     public Beverage createBeverage(){
        return new ElaichiTea();
    }
     public boolean isValid() {
         return validator.isValid(BeverageType.ELAICHI_TEA,this.recipe);
     }
 } 
 
 class CoffeeStrategy extends Strategy {
     public CoffeeStrategy(Validator validator, Recipe recipe) {
        super(validator,recipe);
     }
     public Beverage createBeverage(){
        return new Coffee();
    }
     public boolean isValid() {
         return validator.isValid(BeverageType.COFFEE,this.recipe);
     }

 } 
 class HotWaterStrategy extends Strategy {
    public HotWaterStrategy(Validator validator, Recipe recipe) {
        super(validator,recipe);
    }
    public Beverage createBeverage(){
        return new HotWater();
    }
    public boolean isValid() {
        return validator.isValid(BeverageType.HOT_WATER,this.recipe);
    }

} 
class HotMilkStrategy extends Strategy {
    public HotMilkStrategy(Validator validator, Recipe recipe) {
        super(validator,recipe);
    }
    public Beverage createBeverage(){
        return new HotMilk();
    }
    public boolean isValid() {
        return validator.isValid(BeverageType.HOT_MILK,this.recipe);
    }

} 
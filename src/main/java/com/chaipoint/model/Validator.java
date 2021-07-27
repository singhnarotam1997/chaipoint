package com.chaipoint;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Validator{

	private Map<String,Double> map;		// map to store the actual quantity availabe of all ingredients

	public Validator(Map<String,Double> m) {
		this.map = m;
	}
	public boolean isValid(BeverageType type, Recipe recipe) {
		for(int i=0;i<recipe.getIngredients().size();i++){
			Ingredient ing = recipe.getIngredients().get(i);
			if(!map.containsKey(ing.getName())){
				System.out.println(type.name() + " cannot be prepared because " +ing.getName()+" is not available.");
				return false;
			}

			if(ing.getQuantity()>map.get(ing.getName())){
				System.out.println(type.name() + " cannot be prepared because " +ing.getName()+" is insufficient.");
				return false;
			}
		}
		return true;
	}

	// No two machines should decrease stock at the same time to avoid conflicts. So this function
	// is made synchronised.
	public synchronized void reduceItem(String ing,Double q){
		if(map.containsKey(ing)){
			map.replace(ing, map.get(ing)-q);
			if(map.get(ing) == 0)
				map.remove(ing);
		}
	}

}
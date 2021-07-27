package com.chaipoint;

enum BeverageType {
    GINGER_TEA, ELAICHI_TEA, COFFEE, HOT_MILK,HOT_WATER;
}

public abstract class Beverage {
    public String name;
    public BeverageType type;
 
    public Beverage(String name, BeverageType type) {
        this.name = name;
        this.type = type;
        System.out.println("Beverage " + this.name + " served");
    }
 }
 
 
class GingerTea extends Beverage {
    public GingerTea() {
        super("Ginger Tea", BeverageType.GINGER_TEA);
    }
} 
class Coffee extends Beverage {
    public Coffee() {
        super("Coffee", BeverageType.COFFEE);
    }
}
class ElaichiTea extends Beverage {
    public ElaichiTea() {
        super("Elaichi Tea", BeverageType.ELAICHI_TEA);
    }
} 

class HotMilk extends Beverage {
    public HotMilk() {
        super("Hot Milk", BeverageType.HOT_MILK);
    }
} 
class HotWater extends Beverage {
    public HotWater() {
        super("Hot Water", BeverageType.HOT_WATER);
    }
} 

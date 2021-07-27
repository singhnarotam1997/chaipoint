package com.chaipoint;

public class Machine {

    private Strategy strategy;
    public Machine(){
        this.strategy = null;
    }
    public void assignStrategy(Strategy st) { 
        if(isIdle()){
            this.strategy = st;
            System.out.println("Order taken.");
        }else
            System.out.println("Cannot serve Machine already in use");
    } 

    public Strategy getStrategy(){
        return this.strategy;
    }

    //only one drink should be processed by machine at a time so this function is made as synchronised.
    public synchronized void process() {
        this.strategy.process();
        this.strategy = null;
    }

    public boolean isIdle(){
        if(this.strategy == null)
            return true;
        return false;
    }

}

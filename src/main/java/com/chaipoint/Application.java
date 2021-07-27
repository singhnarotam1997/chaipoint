package com.chaipoint;

public class Application {
    public static void main(String[] args){
        Processor pr = new Processor();
        Tests t = new Tests(pr);
        t.runAll();
    }
}

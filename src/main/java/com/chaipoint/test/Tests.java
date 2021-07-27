package com.chaipoint;
import java.util.ArrayList;
import java.util.List;

public class Tests {
    Processor pr;

    Tests(Processor p){
        this.pr = p;
    }
    
    void runAll(){
        System.out.println("Running Test1"); test1(); System.out.println("Finished Running Test1\n");
        System.out.println("Running Test2"); test2(); System.out.println("Finished Running Test2\n");

    }

    void test1(){
        List<Machine> machines = new ArrayList<>();
        int i=0;
        int number_of_orders = 5;
        int number_of_strategies = pr.getMenu().size();
        while(i<number_of_orders){
            Machine mach = pr.getIdleMachine();
            if(mach!=null){
                mach.assignStrategy((Strategy)pr.getMenu().values().toArray()[i%number_of_strategies]);
                machines.add(mach);
            }else{
                System.out.println("All machines booked.");
            }
            i++;
        }

        machines.parallelStream().forEach((o) -> {
            o.process();
        });
        
    }
    void test2(){
        pr.displayStock();
        System.out.println("Restocking....");
        pr.increaseStock("elaichi_syrup",5);
        pr.increaseStock("coffee_syrup",10);
        pr.displayStock();
        test1();
        pr.displayStock();
        test1();

    }
    void test3(){
        System.out.println("Running Test3");

        System.out.println("Finished Running Test3\n");

    }
    void test4(){
        System.out.println("Running Test4");

        System.out.println("Finished Running Test4\n");

    }
}

package com.chaipoint;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// main service class
public class Processor {
    Processor(){
        machines = new ArrayList<>();
        recipeStrategies = new HashMap<>(); 
        items = new HashMap<>(); 
        parse();
    }

    private List<Machine> machines; // all machines objects
    private Map<BeverageType,Strategy> recipeStrategies; // storing all the strategies, this is basically the menu
    private Map<String,Double> items;   // stock of all ingredients. Validator class uses this object to validate.

    public List<Machine> getMachines(){
        return this.machines;
    }
    public Map<BeverageType,Strategy> getMenu(){
        return this.recipeStrategies;
    }

    // display the remaining stock summary.
    public void displayStock(){
        System.out.println("\nStock Sumary :");
        for (Map.Entry<String, Double> entry : items.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();
    }

    // increase the stock of any ingredient in in-memory db
    public void increaseStock(String item,double quantity){
        if(items.containsKey(item)){
            items.replace(item,items.get(item)+quantity);
        }else{
            items.put(item,quantity);
        }
    }

    // get idle machine
    public Machine getIdleMachine(){
        for(int i=0;i<machines.size();i++){
            if(machines.get(i).isIdle())
                return machines.get(i);
        }
        return null;
    }

    // place order to to any idle machine. If no machine is idle return null
    public void placeOrder(Strategy s, Machine m){
        if(!m.isIdle()){
            System.out.println("Cannot place order because machine is not idle");
            return;
        }
        m.assignStrategy(s);
        m.process();
    }

    // create recipe object from json object
    public Recipe createRecipe(JSONObject r){
        Iterator<Map.Entry> itr2 = r.entrySet().iterator();
        Recipe rec = new Recipe();
        while (itr2.hasNext()) {
            Map.Entry pair = itr2.next();
            Ingredient ing = new Ingredient(pair.getKey().toString(),Double.parseDouble(pair.getValue().toString()));
            rec.addIngredient(ing);
        }
        return rec;
    }

    /* Creating the strategy from the recipes given in the json file */ 
    private Strategy createStrategy(String Beverage_name,Recipe r, Validator v){
        Beverage_name = Beverage_name.toUpperCase();
        BeverageType d = BeverageType.valueOf(Beverage_name);
        Strategy s = null;
        switch(d){
            case HOT_WATER:
                s =  new HotWaterStrategy(v,r);
                break;
            case HOT_MILK:
                s = new HotMilkStrategy(v,r);
                break;
            case GINGER_TEA:
                s = new GingerTeaStrategy(v,r);
                break;
            case ELAICHI_TEA:
                s = new ElaichiTeaStrategy(v,r);
                break;
            case COFFEE:
                s = new CoffeeStrategy(v,r);
                break;
            default:
                System.out.println("No such beverage exists in menu.");
        }
        return s;
    }


    /* function to parse the configuration file*/
    public void parse(){
        JSONObject myJsonobject;
        JSONParser parser = new JSONParser();
        Object obj = new Object();
        try {
            obj = parser.parse(new FileReader("src/main/resources/application.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        myJsonobject = (JSONObject) obj;
        JSONObject machine = (JSONObject)myJsonobject.get("machine");
        int number_of_machines = Integer.parseInt(((JSONObject)machine.get("outlets")).get("count_n").toString());
        for(int i = 0; i < number_of_machines; i++)
          machines.add(new Machine());
        JSONObject quantity = (JSONObject)machine.get("total_items_quantity");
        Iterator<Map.Entry> itr2 = quantity.entrySet().iterator();
        while (itr2.hasNext()) {
            Map.Entry pair = itr2.next();
            items.put(pair.getKey().toString(),Double.parseDouble(pair.getValue().toString()));
        }
        JSONObject beverages = (JSONObject)machine.get("beverages");
        itr2 = beverages.entrySet().iterator();
        Validator v = new Validator(items); // ingredients stock summary passed to validator object.
        // validator singleton object used by all the menu items to check if sufficient ingredients are available.

        while (itr2.hasNext()) {
            Map.Entry pair = itr2.next();
            Recipe r = createRecipe((JSONObject)pair.getValue());   // create recipe from give json
            Strategy s = createStrategy(pair.getKey().toString(),r,v); // create strategy gfrom given json
            if(s!=null)
                recipeStrategies.put(BeverageType.valueOf(pair.getKey().toString().toUpperCase()),s);
        }
    }
}



import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMachine {
    private int numberOfOutlet;
    private List<Beverage> beverages;
    private Map<String, Integer> maxIngredientMap;

    public CoffeeMachine(String configurationString) {
        this.maxIngredientMap = new HashMap<>();
        this.beverages = new ArrayList<>();
        parse(configurationString);
    }

    public CoffeeMachineResponse serveCoffee() {
        Map<String, String> beveragesStatus = new HashMap<>();
        int beveragesPrepared = 0;
        for (Beverage beverage : this.beverages) {
            Map<String, Integer> beforeMaxIngredientMap = maxIngredientMap; // storing in case of reverting the transaction
            if (beveragesPrepared > numberOfOutlet) { // check for available outlet to serve at a time
                break;
            }
            Map<String, Integer> ingredientRequired = beverage.getIngredientMap();
            boolean failToPrepare = false;
            for (Map.Entry<String, Integer> entry : ingredientRequired.entrySet()) {
                if (!this.maxIngredientMap.containsKey(entry.getKey())) {
                    failToPrepare = true;
                    beveragesStatus.put(beverage.getName(), String.format("cannot be prepared because %s is not available", entry.getKey()));
                    break;
                } else if (entry.getValue() > this.maxIngredientMap.get(entry.getKey())) {
                    failToPrepare = true;
                    beveragesStatus.put(beverage.getName(), String.format("cannot be prepared because item %s is not sufficient", entry.getKey()));
                    break;
                } else {
                    int valueAvailable = this.maxIngredientMap.get(entry.getKey());
                    this.maxIngredientMap.put(entry.getKey(), valueAvailable - entry.getValue());
                }
            }
            if (failToPrepare) {
                this.maxIngredientMap = beforeMaxIngredientMap; // reverting transaction
            }
            if (!failToPrepare) {
                beveragesStatus.put(beverage.getName(), "is prepared"); // storing prepared with status
                beveragesPrepared++;
            }
        }
        return new CoffeeMachineResponse(beveragesPrepared, beveragesStatus);
    }

    private void parse(String configurationString) {
        Gson gson = new Gson();
        CoffeeMachineConfiguration configuration = gson.fromJson(configurationString, CoffeeMachineConfiguration.class);

        //outlet number
        this.numberOfOutlet = configuration.getMachine().getOutlets().getNumberOfOutlets();

        //max ingredients available
        this.maxIngredientMap.putAll(Beverage.getIngredients(configuration.getMachine().getMaxIngredients()));

        //beverages available
        this.beverages.add(new Beverage("BlackTea", Beverage.getIngredients(configuration.getMachine().getBeverages().getBlackTeaIngredients())));
        this.beverages.add(new Beverage("GreenTea", Beverage.getIngredients(configuration.getMachine().getBeverages().getGreenTeaIngredients())));
        this.beverages.add(new Beverage("HotCoffee", Beverage.getIngredients(configuration.getMachine().getBeverages().getHotCoffeeIngredients())));
        this.beverages.add(new Beverage("HotTea", Beverage.getIngredients(configuration.getMachine().getBeverages().getHotTeaIngredients())));
    }
}

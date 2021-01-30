import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Beverage {
    private final String name;
    private final Map<String, Integer> ingredientMap;

    //static method to parse json and get ingredient map
    public static Map<String, Integer> getIngredients(CoffeeMachineConfiguration.Ingredients ingredients) {
        Map<String, Integer> ingredientMap = new HashMap<>();

        if (ingredients.getGingerSyrup() != 0)
            ingredientMap.put("GingerSyrup", ingredients.getGingerSyrup());
        if (ingredients.getSugarSyrup() != 0)
            ingredientMap.put("SugarSyrup", ingredients.getSugarSyrup());
        if (ingredients.getHotMilk() != 0)
            ingredientMap.put("HotMilk", ingredients.getHotMilk());
        if (ingredients.getHotWater() != 0)
            ingredientMap.put("HotWater", ingredients.getHotWater());
        if (ingredients.getTeaLeavesSyrup() != 0)
            ingredientMap.put("TeaLeavesSyrup", ingredients.getTeaLeavesSyrup());
        if (ingredients.getGreenMixture() != 0)
            ingredientMap.put("GreenMixture", ingredients.getGreenMixture());

        return ingredientMap;
    }
}

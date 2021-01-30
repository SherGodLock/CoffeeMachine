import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.Nullable;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class CoffeeMachineConfiguration {
    private Machine machine;

    @Getter
    static class Machine {
        private Outlets outlets;

        @SerializedName("total_items_quantity")
        private Ingredients maxIngredients;

        private Beverages beverages;
    }

    @Getter
    static class Beverages {
        @SerializedName("hot_tea")
        private Ingredients hotTeaIngredients;

        @SerializedName("hot_coffee")
        private Ingredients hotCoffeeIngredients;

        @SerializedName("black_tea")
        private Ingredients blackTeaIngredients;

        @SerializedName("green_tea")
        private Ingredients greenTeaIngredients;

        public Ingredients getHotTeaIngredients() {
            return hotTeaIngredients;
        }
    }

    @Getter
    static class Ingredients {
        @SerializedName("hot_water")
        private int hotWater;

        @SerializedName("hot_milk")
        private int hotMilk;

        @SerializedName("ginger_syrup")
        private int gingerSyrup;

        @SerializedName("sugar_syrup")
        private int sugarSyrup;

        @SerializedName("tea_leaves_syrup")
        private int teaLeavesSyrup;

        @SerializedName("green_mixture")
        private @Nullable int greenMixture;
    }

    @Getter
    static class Outlets {
        @SerializedName("count_n")
        private int numberOfOutlets;
    }
}

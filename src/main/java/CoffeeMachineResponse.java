import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class CoffeeMachineResponse {
    private final int numberOfBeverage;
    private final Map<String, String> beveragesStatus;
}

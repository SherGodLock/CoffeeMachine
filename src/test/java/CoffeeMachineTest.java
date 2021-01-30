import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class CoffeeMachineTest {

    @Test
    public void testServeCoffee() throws IOException {
        String configurationString = new String(Files.readAllBytes(Paths.get("src/test/resources/sample-configuration.json")));
        CoffeeMachine coffeeMachine = new CoffeeMachine(configurationString);
        CoffeeMachineResponse coffeeMachineResponse = coffeeMachine.serveCoffee();

        Assert.assertEquals(2, coffeeMachineResponse.getNumberOfBeverage());
        Map<String, String> beveragesStatusMap = coffeeMachineResponse.getBeveragesStatus();
        Assert.assertEquals("is prepared", beveragesStatusMap.get("BlackTea"));
        Assert.assertEquals("is prepared", beveragesStatusMap.get("HotCoffee"));
        Assert.assertEquals("cannot be prepared because GreenMixture is not available", beveragesStatusMap.get("GreenTea"));
        Assert.assertEquals("cannot be prepared because item HotWater is not sufficient", beveragesStatusMap.get("HotTea"));
    }
}
package ch.heig.menus.spec;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.DishesApi;
import org.openapitools.client.model.DishDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DishSteps {
    private final DishesApi api = new DishesApi();

    private DishDTO dish;
    private int dishId;

    private int statusCode;

    @Given("I have an dish payload")
    public void iHaveAnDishPayload() {
        dish = new DishDTO();
        dish.setName("Goulash");
    }

    @When("I POST it to the dishes endpoint")
    public void iPOSTItToTheDishesEndpoint() {
        try {
            var response = api.createDishWithHttpInfo(dish);
            statusCode = response.getStatusCode();
            dishId = response.getData().getId();
        } catch (ApiException e) {
            e.printStackTrace();
            statusCode = e.getCode();
        }

    }

    @When("I PUT it to the dishes endpoint")
    public void iPUTItToTheDishesEndpoint() {
        iPOSTItToTheDishesEndpoint(); // Ensure that we have access to the dishId
        try {
            var response = api.updateDishWithHttpInfo(dishId, dish);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            e.printStackTrace();
            statusCode = e.getCode();
        }
    }

    @When("I DELETE it to the dishes endpoint")
    public void iDELETEItToTheDishesEndpoint() {
        iPOSTItToTheDishesEndpoint(); // Ensure that we have access to the dishId
        try {
            var response = api.deleteDishWithHttpInfo(dishId);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            e.printStackTrace();
            statusCode = e.getCode();
        }
    }

    @Then("I receive a {int} status code for dish")
    public void i_receive_a_status_code_for_dish(int arg1) throws Throwable {
        assertEquals(arg1, statusCode);
    }
}

package ch.heig.menus.spec;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.api.ChefsApi;
import org.openapitools.client.model.ChefDTO;
import org.openapitools.client.model.ChefWithIdDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChefSteps {
    private final ChefsApi api = new ChefsApi();
    private ChefDTO chef;
    private int statusCode;
    private ApiResponse<ChefWithIdDTO> response;

    @Given("I have an chef payload")
    public void i_have_a_chef_payload() {
        chef = new ChefDTO();
        chef.setName("Chef 1");
    }

    @When("I POST it to the chefs endpoint")
    public void i_post_it_to_the_chef_endpoint() {
        try {
            response = api.createChefWithHttpInfo(chef);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            e.printStackTrace();
            statusCode = e.getCode();
        }
    }

    @Then("I receive a {int} status code")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, statusCode);
    }
}

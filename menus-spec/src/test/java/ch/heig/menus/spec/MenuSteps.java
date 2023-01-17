package ch.heig.menus.spec;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.api.MenusEndPointApi;
import org.openapitools.client.model.Menu;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MenuSteps {

    private final MenusEndPointApi api = new MenusEndPointApi();
    private Menu menu;
    private int statusCode;

    @Given("I have an menu payload")
    public void i_have_an_menu_payload() throws Throwable {
        menu = new Menu();
        menu.setAuthor("Coluche");
        menu.setCitation("Se pencher sur son passé, c'est risquer de tomber dans l'oubli.");
    }

    @When("I POST it to the \\/menus endpoint")
    public void i_POST_it_to_the_menus_endpoint() throws Throwable {
        try {
            ApiResponse response = api.addMenuWithHttpInfo(menu);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @Then("I receive a {int} status code")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        assertEquals(arg1, statusCode);
    }
}

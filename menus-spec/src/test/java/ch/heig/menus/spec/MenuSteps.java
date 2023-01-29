package ch.heig.menus.spec;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.MenusApi;
import org.openapitools.client.model.MenuDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuSteps {
    private final MenusApi api = new MenusApi();

    private MenuDTO menu;

    private String createdName;

    @Given("I have a menu named {string}")
    public void iHaveAMenuNamed(String arg0) {
        menu = new MenuDTO();
        menu.setName(arg0);
    }

    @When("I create the menu")
    public void iCreateTheMenu() throws ApiException {
        var response = api.createMenuWithHttpInfo(menu);
        createdName = response.getData().getName();
    }

    @Then("the menu should be created with name {string}")
    public void theMenuShouldBeCreatedWithName(String arg0) {
        assertEquals(arg0, createdName);
    }
}

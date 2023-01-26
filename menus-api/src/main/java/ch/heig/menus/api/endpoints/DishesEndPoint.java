package ch.heig.menus.api.endpoints;

import ch.heig.menus.api.repositories.DishRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.api.DishesApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DishesEndPoint implements DishesApi {

    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private DishRepository dishRepository;
}

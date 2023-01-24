package ch.heig.menus.api.endpoints;

import ch.heig.menus.api.entities.ChefEntity;
import ch.heig.menus.api.entities.DishEntity;
import ch.heig.menus.api.exceptions.DishNotFoundException;
import ch.heig.menus.api.repositories.DishRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.api.DishesApi;
import org.openapitools.model.ChefDTO;
import org.openapitools.model.DishDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DishesEndPoint implements DishesApi {

    @Autowired
    private DishRepository dishRepository;

    private final ModelMapper modelMapper = new ModelMapper();
}

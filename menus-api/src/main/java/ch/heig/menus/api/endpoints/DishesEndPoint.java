package ch.heig.menus.api.endpoints;

import ch.heig.menus.api.entities.DishEntity;
import ch.heig.menus.api.exceptions.DishNotFoundException;
import ch.heig.menus.api.repositories.DishRepository;
import org.openapitools.api.DishesApi;
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

    @Override
    public ResponseEntity<List<DishDTO>> getDishes() {
        List<DishEntity> quoteEntities= dishRepository.findAll();
        List<DishDTO> dishes = new ArrayList<>();
        for (DishEntity dishEntity : quoteEntities) {
            DishDTO dish = new DishDTO();
            dish.setId(dishEntity.getId());
            dish.setName(dishEntity.getName());
            dishes.add(dish);
        }
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addDish(@RequestBody DishDTO chef) {
        DishEntity dishEntity = new DishEntity();
        dishEntity.setName(chef.getName());
        DishEntity quoteAdded = dishRepository.save(dishEntity);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(quoteAdded.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<DishDTO> getDish(Integer id) {
        Optional<DishEntity> opt = dishRepository.findById(id);
        if (opt.isPresent()) {
            DishEntity chefEntity = opt.get();
            DishDTO dish = new DishDTO();
            dish.setId(chefEntity.getId());
            dish.setName(chefEntity.getName());
            return new ResponseEntity<>(dish, HttpStatus.OK);
        } else {
            throw new DishNotFoundException(id);
        }
    }
}

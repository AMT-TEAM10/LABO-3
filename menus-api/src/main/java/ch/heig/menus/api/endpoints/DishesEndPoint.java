package ch.heig.menus.api.endpoints;

import ch.heig.menus.api.services.DishesService;
import org.openapitools.api.DishesApi;
import org.openapitools.model.ChefDTO;
import org.openapitools.model.DishDTO;
import org.openapitools.model.DishWithRelationsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class DishesEndPoint implements DishesApi {

    private final DishesService dishesService;

    DishesEndPoint(@Autowired DishesService dishesService) {
        this.dishesService = dishesService;
    }

    @Override
    public ResponseEntity<List<DishWithRelationsDTO>> getDishes() {
        return ResponseEntity.ok(dishesService.getAll());
    }

    @Override
    public ResponseEntity<DishWithRelationsDTO> getDish(Integer id) {
        DishWithRelationsDTO dish = dishesService.get(id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dish.getId())
                .toUri();
        return ResponseEntity.created(location).body(dish);
    }

    @Override
    public ResponseEntity<DishDTO> createDish(DishDTO dishDTO) {
        DishDTO dish = dishesService.create(dishDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(dish.getId())
                .toUri();

        return ResponseEntity.created(location).body(dish);
    }

    @Override
    public ResponseEntity<DishDTO> updateDish(Integer id, DishDTO dishDTO) {
        return ResponseEntity.ok(dishesService.update(id, dishDTO));
    }

    @Override
    public ResponseEntity<Void> deleteDish(Integer id) {
        dishesService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ChefDTO>> getChefsByDish(Integer id) {
        return ResponseEntity.ok(dishesService.get(id).getChefs());
    }

    @Override
    public ResponseEntity<DishWithRelationsDTO> putChefByDish(Integer id, ChefDTO chefDTO) {
        return ResponseEntity.ok(dishesService.addChefToDish(id, chefDTO));
    }

    @Override
    public ResponseEntity<Void> deleteChefByDish(Integer id, Integer chefId) {
        dishesService.removeChefFromDish(id, chefId);
        return ResponseEntity.noContent().build();
    }
}

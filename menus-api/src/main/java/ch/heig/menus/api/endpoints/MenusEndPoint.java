package ch.heig.menus.api.endpoints;

import ch.heig.menus.api.services.MenusService;
import org.openapitools.api.MenusApi;
import org.openapitools.model.DishType;
import org.openapitools.model.DishWithRelationsDTO;
import org.openapitools.model.MenuDTO;
import org.openapitools.model.MenuWithRelationsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class MenusEndPoint implements MenusApi {

    private final MenusService menusService;

    MenusEndPoint(@Autowired MenusService menusService) {
        this.menusService = menusService;
    }

    @Override
    public ResponseEntity<List<MenuWithRelationsDTO>> getMenus() {
        return ResponseEntity.ok(menusService.getAll());
    }

    @Override
    public ResponseEntity<MenuWithRelationsDTO> getMenu(Integer id) {
        return ResponseEntity.ok(menusService.get(id));
    }

    @Override
    public ResponseEntity<MenuWithRelationsDTO> createMenu(MenuDTO menuDTO) {
        var menu = menusService.create(menuDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(menu.getId())
                .toUri();

        return ResponseEntity.created(location).body(menu);
    }

    @Override
    public ResponseEntity<Void> deleteMenu(Integer id) {
        menusService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<MenuDTO> updateMenu(Integer id, MenuDTO menuDTO) {
        return ResponseEntity.ok(menusService.update(id, menuDTO));
    }

    @Override
    public ResponseEntity<DishWithRelationsDTO> getDishByType(Integer id, DishType dishType) {
        return ResponseEntity.ok(menusService.getDish(id, dishType));
    }

    @Override
    public ResponseEntity<Void> addOrUpdateDishByType(Integer id, DishType dishType, Integer dishId) {
        menusService.updateDish(id, dishId, dishType);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteDishByType(Integer id, DishType dishType) {
        menusService.removeDish(id, dishType);
        return ResponseEntity.noContent().build();
    }
}

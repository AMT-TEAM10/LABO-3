package ch.heig.menus.api.endpoints;

import ch.heig.menus.api.exceptions.MenuNotFoundException;
import ch.heig.menus.api.services.MenusService;
import org.openapitools.api.MenusApi;
import org.openapitools.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(menusService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MenuWithRelationsDTO> getMenu(Integer id) {
        try {
            var menu = menusService.get(id);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(menu.getId())
                    .toUri();
            return ResponseEntity.created(location).body(menu);
        } catch (MenuNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<MenuWithRelationsDTO> createMenu(MenuDTO menuDTO) {
        var menu = menusService.create(menuDTO);
        return new ResponseEntity<>(menu, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteMenu(Integer id) {
        try {
            menusService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MenuNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<MenuDTO> updateMenu(Integer id, MenuDTO menuDTO) {
        try {
            var menu = menusService.update(id, menuDTO);
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } catch (MenuNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<DishWithRelationsDTO> getDishByType(Integer id, DishType dishType) {
        try {
            var dish = menusService.getDish(id, dishType);
            return new ResponseEntity<>(dish, HttpStatus.OK);
        } catch (MenuNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> addOrUpdateDishByType(Integer id, DishType dishType, Integer dishId) {
        try {
            menusService.updateDish(id, dishId, dishType);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MenuNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> deleteDishByType(Integer id, DishType dishType) {
        try {
            menusService.removeDish(id, dishType);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MenuNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package ch.heig.menus.api.endpoints;

import ch.heig.menus.api.entities.ChefEntity;
import ch.heig.menus.api.entities.DishEntity;
import ch.heig.menus.api.entities.MenuEntity;
import ch.heig.menus.api.exceptions.MenuNotFoundException;
import org.modelmapper.ModelMapper;
import org.openapitools.api.MenusApi;
import ch.heig.menus.api.repositories.MenuRepository;
import org.openapitools.model.*;
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
public class MenusEndPoint implements MenusApi {

    @Autowired
    private MenuRepository menuRepository;


    private final ModelMapper modelMapper = new ModelMapper();

    private MenuWithRelationsDTO convertToDto(MenuEntity menuEntity) {
        var menu = modelMapper.map(menuEntity, MenuWithRelationsDTO.class);
        menu.setDessert(convertToDto(menuEntity.getDessert()));
        menu.setMain(convertToDto(menuEntity.getMain()));
        menu.setStarter(convertToDto(menuEntity.getStarter()));
        return menu;
    }

    private DishWithRelationsDTO convertToDto(DishEntity dishEntity) {
        var dish = modelMapper.map(dishEntity, DishWithRelationsDTO.class);
        var chefs = dishEntity.getChefs().stream().map(this::convertToDto).toList();
        dish.setChefs(chefs);
        return dish;
    }

    private ChefDTO convertToDto(ChefEntity chefEntity) {
        return modelMapper.map(chefEntity, ChefDTO.class);
    }

    @Override
    public ResponseEntity<List<MenuWithRelationsDTO>> getMenus() {
        List<MenuEntity> menusEntities = menuRepository.findAll();
        List<MenuWithRelationsDTO> menus = menusEntities.stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MenuWithRelationsDTO> getMenu(Integer id) {
        Optional<MenuEntity> opt = menuRepository.findById(id);
        if (opt.isPresent()) {
            MenuEntity menuEntity = opt.get();
            MenuWithRelationsDTO menu = convertToDto(menuEntity);
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else {
            throw new MenuNotFoundException(id);
        }
    }

    @Override
    public ResponseEntity<MenuWithRelationsDTO> createMenu(MenuDTO menuDTO) {
        MenuEntity menuEntity = modelMapper.map(menuDTO, MenuEntity.class);
        menuEntity = menuRepository.save(menuEntity);
        MenuWithRelationsDTO menu = convertToDto(menuEntity);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(menu.getId())
                .toUri();
        return ResponseEntity.created(location).body(menu);
    }

    @Override
    public ResponseEntity<Void> deleteMenu(Integer id) {
        Optional<MenuEntity> opt = menuRepository.findById(id);
        if (opt.isPresent()) {
            menuRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new MenuNotFoundException(id);
        }
    }
}

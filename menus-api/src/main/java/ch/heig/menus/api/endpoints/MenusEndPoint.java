package ch.heig.menus.api.endpoints;

import ch.heig.menus.api.entities.DishEntity;
import ch.heig.menus.api.entities.MenuEntity;
import ch.heig.menus.api.exceptions.MenuNotFoundException;
import org.modelmapper.ModelMapper;
import org.openapitools.api.MenusApi;
import ch.heig.menus.api.repositories.MenuRepository;
import org.openapitools.model.DishDTO;
import org.openapitools.model.MenuDTO;
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

    private MenuDTO convertToDto(MenuEntity menuEntity) {
        var menu = modelMapper.map(menuEntity, MenuDTO.class);
        menu.setDessert(convertToDto(menuEntity.getDessert()));
        menu.setMain(convertToDto(menuEntity.getMain()));
        menu.setStarter(convertToDto(menuEntity.getStarter()));
        return menu;
    }

    private DishDTO convertToDto(DishEntity dishEntity) {
        return modelMapper.map(dishEntity, DishDTO.class);
    }

    @Override
    public ResponseEntity<List<MenuDTO>> getMenus() {
        List<MenuEntity> menusEntities = menuRepository.findAll();
        List<MenuDTO> menusDTO = menusEntities.stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(menusDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addMenu(@RequestBody MenuDTO menu) {
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setName(menu.getName());
        MenuEntity quoteAdded = menuRepository.save(menuEntity);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(quoteAdded.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<MenuDTO> getMenu(Integer id) {
        Optional<MenuEntity> opt = menuRepository.findById(id);
        if (opt.isPresent()) {
            MenuEntity menuEntity = opt.get();
            MenuDTO menu = convertToDto(menuEntity);
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else {
            throw new MenuNotFoundException(id);
        }
    }
}

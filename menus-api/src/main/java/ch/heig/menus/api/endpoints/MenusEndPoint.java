package ch.heig.menus.api.endpoints;

import ch.heig.menus.api.entities.MenuEntity;
import ch.heig.menus.api.exceptions.MenuNotFoundException;
import org.openapitools.api.MenusApi;
import ch.heig.menus.api.repositories.MenuRepository;
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

    @Override
    public ResponseEntity<List<MenuDTO>> getMenus() {
        List<MenuEntity> quoteEntities= menuRepository.findAll();
        List<MenuDTO> menus = new ArrayList<>();
        for (MenuEntity menuEntity : quoteEntities) {
            MenuDTO menu = new MenuDTO();
            menu.setId(menuEntity.getId());
            menu.setName(menuEntity.getName());
            menus.add(menu);
        }
        return new ResponseEntity<>(menus, HttpStatus.OK);
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
            MenuDTO menu = new MenuDTO();
            menu.setId(menuEntity.getId());
            menu.setName(menuEntity.getName());
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else {
            throw new MenuNotFoundException(id);
        }
    }
}

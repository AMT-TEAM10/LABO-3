package ch.heig.menus.api.endpoints;

import ch.heig.menus.api.exceptions.MenuNotFoundException;
import org.openapitools.api.MenusApi;
import org.openapitools.model.Menu;
import ch.heig.menus.api.entities.MenuEntity;
import ch.heig.menus.api.repositories.MenuRepository;
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
    public ResponseEntity<List<Menu>> getMenus() {
        /*
        List<MenuEntity> quoteEntities= menuRepository.findAll();
        List<Menu> menus = new ArrayList<>();
        for (MenuEntity menuEntity : quoteEntities) {
            Menu menu = new Menu();
            menu.setId(menuEntity.getId());
            menus.add(menu);
        }
        return new ResponseEntity<List<Menu>>(menus,HttpStatus.OK);
        */
        return null;
    }

    @Override
    public ResponseEntity<Void> addMenu(@RequestBody Menu menu) {
        /*
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setAuthor(menu.getAuthor());
        menuEntity.setCitation(menu.getCitation());
        MenuEntity quoteAdded = menuRepository.save(menuEntity);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(quoteAdded.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
         */
        return null;
    }

    @Override
    public ResponseEntity<Menu> getMenu(Integer id) {
        /*
        Optional<MenuEntity> opt = menuRepository.findById(id);
        if (opt.isPresent()) {
            MenuEntity menuEntity = opt.get();
            Menu menu = new Menu();
            menu.setId(menuEntity.getId());
            menu.setAuthor(menuEntity.getAuthor());
            menu.setCitation(menuEntity.getCitation());
            return new ResponseEntity<Menu>(menu, HttpStatus.OK);
        } else {
//            return ResponseEntity.notFound().build();
            throw new MenuNotFoundException(id);
        }
         */
        return null;
    }

}

package ch.heig.menus.api.endpoints;

import ch.heig.menus.api.entities.ChefEntity;
import ch.heig.menus.api.exceptions.ChefNotFoundException;
import ch.heig.menus.api.repositories.ChefRepository;
import org.openapitools.api.ChefsApi;
import org.openapitools.model.Chef;
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
public class ChefsEndPoint implements ChefsApi {

    @Autowired
    private ChefRepository chefRepository;

    @Override
    public ResponseEntity<List<Chef>> getChefs() {
        List<ChefEntity> quoteEntities= chefRepository.findAll();
        List<Chef> chefs = new ArrayList<>();
        for (ChefEntity chefEntity : quoteEntities) {
            Chef chef = new Chef();
            chef.setId(chefEntity.getId());
            chef.setName(chefEntity.getName());
            chefs.add(chef);
        }
        return new ResponseEntity<>(chefs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addChef(@RequestBody Chef chef) {
        ChefEntity chefEntity = new ChefEntity();
        chefEntity.setName(chef.getName());
        ChefEntity quoteAdded = chefRepository.save(chefEntity);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(quoteAdded.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<Chef> getChef(Integer id) {
        Optional<ChefEntity> opt = chefRepository.findById(id);
        if (opt.isPresent()) {
            ChefEntity chefEntity = opt.get();
            Chef chef = new Chef();
            chef.setId(chefEntity.getId());
            chef.setName(chefEntity.getName());
            return new ResponseEntity<>(chef, HttpStatus.OK);
        } else {
            throw new ChefNotFoundException(id);
        }
    }
}

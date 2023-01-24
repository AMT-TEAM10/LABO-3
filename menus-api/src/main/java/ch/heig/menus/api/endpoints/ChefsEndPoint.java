package ch.heig.menus.api.endpoints;

import ch.heig.menus.api.exceptions.ChefNotFoundException;
import ch.heig.menus.api.services.ChefsService;
import org.openapitools.api.ChefsApi;
import org.openapitools.model.ChefDTO;
import org.openapitools.model.ChefWithRelationsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class ChefsEndPoint implements ChefsApi {

    private final ChefsService chefsService;

    ChefsEndPoint(@Autowired ChefsService chefsService) {
        this.chefsService = chefsService;
    }

    @Override
    public ResponseEntity<List<ChefWithRelationsDTO>> getChefs() {
        return ResponseEntity.ok(chefsService.getAll());
    }

    @Override
    public ResponseEntity<ChefWithRelationsDTO> getChef(Integer id) {
        try {
            return ResponseEntity.ok(chefsService.get(id));
        } catch (ChefNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ChefDTO> updateChef(Integer id, ChefDTO chefDTO) {
        try {
            return ResponseEntity.ok(chefsService.update(id, chefDTO));
        } catch (ChefNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ChefDTO> createChef(ChefDTO chefDTO) {
        var chef = chefsService.create(chefDTO);
        return ResponseEntity.created(URI.create("/chefs/" + chef.getId())).body(chef);
    }

    @Override
    public ResponseEntity<Void> deleteChef(Integer id) {
        try {
            chefsService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ChefNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

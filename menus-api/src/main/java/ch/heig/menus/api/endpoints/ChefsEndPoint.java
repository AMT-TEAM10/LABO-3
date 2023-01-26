package ch.heig.menus.api.endpoints;

import ch.heig.menus.api.services.ChefsService;
import org.openapitools.api.ChefsApi;
import org.openapitools.model.ChefDTO;
import org.openapitools.model.ChefWithRelationsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        return ResponseEntity.ok(chefsService.get(id));
    }

    @Override
    public ResponseEntity<ChefDTO> updateChef(Integer id, ChefDTO chefDTO) {
        return ResponseEntity.ok(chefsService.update(id, chefDTO));
    }

    @Override
    public ResponseEntity<ChefDTO> createChef(ChefDTO chefDTO) {
        var chef = chefsService.create(chefDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(chef.getId())
                .toUri();

        return ResponseEntity.created(location).body(chef);
    }

    @Override
    public ResponseEntity<Void> deleteChef(Integer id) {
        chefsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

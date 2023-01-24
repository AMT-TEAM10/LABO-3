package ch.heig.menus.api.endpoints;

import ch.heig.menus.api.entities.ChefEntity;
import ch.heig.menus.api.exceptions.ChefNotFoundException;
import ch.heig.menus.api.repositories.ChefRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.api.ChefsApi;
import org.openapitools.model.ChefDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ChefsEndPoint implements ChefsApi {

    @Autowired
    private ChefRepository chefRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    private ChefDTO convertToDto(ChefEntity chefEntity) {
        return modelMapper.map(chefEntity, ChefDTO.class);
    }

    @Override
    public ResponseEntity<List<ChefDTO>> getChefs() {
        List<ChefEntity> chefsEntities = chefRepository.findAll();
        List<ChefDTO> chefsDTO = chefsEntities.stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(chefsDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addChef(@RequestBody ChefDTO chef) {
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
    public ResponseEntity<ChefDTO> getChef(Integer id) {
        Optional<ChefEntity> opt = chefRepository.findById(id);
        if (opt.isPresent()) {
            ChefEntity chefEntity = opt.get();
            ChefDTO chef = new ChefDTO();
            chef.setId(chefEntity.getId());
            chef.setName(chefEntity.getName());
            return new ResponseEntity<>(chef, HttpStatus.OK);
        } else {
            throw new ChefNotFoundException(id);
        }
    }
}

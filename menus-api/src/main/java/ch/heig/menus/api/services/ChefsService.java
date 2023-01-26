package ch.heig.menus.api.services;

import ch.heig.menus.api.entities.ChefEntity;
import ch.heig.menus.api.exceptions.ChefNotFoundException;
import ch.heig.menus.api.repositories.ChefRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.model.ChefDTO;
import org.openapitools.model.ChefWithRelationsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefsService {
    private final ChefRepository chefRepository;
    private final ModelMapper modelMapper;

    ChefsService(@Autowired ChefRepository chefRepository, @Autowired ModelMapper modelMapper) {
        this.chefRepository = chefRepository;
        this.modelMapper = modelMapper;
    }

    public List<ChefWithRelationsDTO> getAll() {
        return chefRepository
                .findAll()
                .stream()
                .map(e -> modelMapper.map(e, ChefWithRelationsDTO.class))
                .toList();
    }

    public ChefWithRelationsDTO get(int id) throws ChefNotFoundException {
        var chefEntity = chefRepository.findById(id);
        return modelMapper.map(chefEntity, ChefWithRelationsDTO.class);
    }

    public ChefDTO create(ChefDTO chef) {
        var chefEntity = new ChefEntity();
        chefEntity.setId(10);
        chefEntity.setName(chef.getName());
        chefEntity = chefRepository.save(chefEntity);
        return modelMapper.map(chefEntity, ChefDTO.class);
    }

    public ChefDTO update(int id, ChefDTO chef) throws ChefNotFoundException {
        var chefEntity = chefRepository.findById(id).orElseThrow(() -> new ChefNotFoundException(id));
        chefEntity.setName(chef.getName());
        chefEntity = chefRepository.save(chefEntity);
        return modelMapper.map(chefEntity, ChefDTO.class);
    }

    public void delete(int id) throws ChefNotFoundException {
        var chefEntity = chefRepository.findById(id).orElseThrow(() -> new ChefNotFoundException(id));
        chefRepository.delete(chefEntity);
    }
}

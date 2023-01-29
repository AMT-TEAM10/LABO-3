package ch.heig.menus.api.services;

import ch.heig.menus.api.entities.ChefEntity;
import ch.heig.menus.api.exceptions.BadRequestException;
import ch.heig.menus.api.exceptions.ChefNotFoundException;
import ch.heig.menus.api.repositories.ChefRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.openapitools.model.ChefDTO;
import org.openapitools.model.ChefWithIdDTO;
import org.openapitools.model.ChefWithRelationsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChefsService {
    private final ChefRepository chefRepository;
    private final ModelMapper modelMapper;

    public List<ChefWithRelationsDTO> getAll() {
        return chefRepository
                .findAll()
                .stream()
                .map(e -> modelMapper.map(e, ChefWithRelationsDTO.class))
                .toList();
    }

    public ChefWithRelationsDTO get(int id) throws ChefNotFoundException {
        return chefRepository.findById(id)
                .map(e -> modelMapper.map(e, ChefWithRelationsDTO.class))
                .orElseThrow(() -> new ChefNotFoundException(id));
    }

    public ChefWithIdDTO create(ChefDTO chef) {
        if (chef == null) {
            throw new BadRequestException("Chef is required");
        }

        var chefEntity = new ChefEntity();
        if (chef.getName() == null) {
            throw new BadRequestException("Name is required");
        }
        chefEntity.setName(chef.getName());
        return modelMapper.map(
                chefRepository.save(chefEntity),
                ChefWithIdDTO.class
        );
    }

    public ChefWithIdDTO update(int id, ChefDTO chef) throws ChefNotFoundException {
        if (chef == null) {
            throw new BadRequestException("Chef is required");
        }

        var chefEntity = chefRepository.findById(id).orElseThrow(() -> new ChefNotFoundException(id));
        if (chef.getName() == null) {
            throw new BadRequestException("Name is required");
        }
        chefEntity.setName(chef.getName());
        return modelMapper.map(
                chefRepository.save(chefEntity),
                ChefWithIdDTO.class
        );
    }

    public void delete(int id) throws ChefNotFoundException {
        var chefEntity = chefRepository.findById(id).orElseThrow(() -> new ChefNotFoundException(id));
        chefRepository.delete(chefEntity);
    }
}

package ch.heig.menus.api.services;

import ch.heig.menus.api.entities.ChefEntity;
import ch.heig.menus.api.entities.DishEntity;
import ch.heig.menus.api.exceptions.BadRequestException;
import ch.heig.menus.api.exceptions.ChefNotFoundException;
import ch.heig.menus.api.exceptions.DishNotFoundException;
import ch.heig.menus.api.repositories.ChefRepository;
import ch.heig.menus.api.repositories.DishRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.openapitools.model.DishDTO;
import org.openapitools.model.DishWithIdDTO;
import org.openapitools.model.DishWithRelationsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishesService {
    private final DishRepository dishRepository;

    private final ChefRepository chefRepository;

    private final ModelMapper modelMapper;

    public List<DishWithRelationsDTO> getAll() {
        return dishRepository
                .findAll()
                .stream()
                .map(e -> modelMapper.map(e, DishWithRelationsDTO.class))
                .toList();
    }

    public DishWithRelationsDTO get(int id) throws DishNotFoundException {
        DishEntity dishEntity = dishRepository.findById(id).orElseThrow();
        return modelMapper.map(dishEntity, DishWithRelationsDTO.class);
    }

    public DishWithIdDTO create(DishDTO dish) {
        if (dish == null) {
            throw new BadRequestException("Dish is required");
        }

        DishEntity dishEntity = new DishEntity();
        if (dish.getName() == null) {
            throw new BadRequestException("Name is required");
        }
        dishEntity.setName(dish.getName());
        return modelMapper.map(
                dishRepository.save(dishEntity),
                DishWithIdDTO.class
        );
    }

    public DishWithIdDTO update(int id, DishDTO dish) throws DishNotFoundException {
        if (dish == null) {
            throw new BadRequestException("Dish is required");
        }

        var dishEntity = dishRepository.findById(id).orElseThrow(() -> new DishNotFoundException(id));
        if (dish.getName() == null) {
            throw new BadRequestException("Name is required");
        }
        dishEntity.setName(dish.getName());
        return modelMapper.map(
                dishRepository.save(dishEntity),
                DishWithIdDTO.class
        );
    }

    public void delete(int id) throws DishNotFoundException {
        var dishEntity = dishRepository.findById(id).orElseThrow(() -> new DishNotFoundException(id));
        for (ChefEntity chefEntity : dishEntity.getChefs()) {
            chefEntity.removeDish(dishEntity);
        }
        dishRepository.delete(dishEntity);
    }

    public DishWithRelationsDTO addChefToDish(int idDish, int chefId) throws DishNotFoundException, ChefNotFoundException {
        DishEntity dishEntity = dishRepository.findById(idDish).orElseThrow(() -> new DishNotFoundException(idDish));
        ChefEntity chefEntity = chefRepository.findById(chefId).orElseThrow(() -> new ChefNotFoundException(chefId));
        dishEntity.addChef(chefEntity);
        dishRepository.save(dishEntity);
        return modelMapper.map(dishEntity, DishWithRelationsDTO.class);
    }

    public void removeChefFromDish(int idDish, int idChef) throws DishNotFoundException, ChefNotFoundException {
        var dishEntity = dishRepository.findById(idDish).orElseThrow(() -> new DishNotFoundException(idDish));
        var chefEntity = dishEntity.getChefs().stream().filter(chef -> chef.getId() == idChef).findFirst().orElseThrow(() -> new ChefNotFoundException(idChef));
        dishEntity.removeChef(chefEntity);
        dishRepository.save(dishEntity);
    }
}

package ch.heig.menus.api.services;

import ch.heig.menus.api.entities.ChefEntity;
import ch.heig.menus.api.entities.DishEntity;
import ch.heig.menus.api.exceptions.ChefNotFoundException;
import ch.heig.menus.api.exceptions.DishNotFoundException;
import ch.heig.menus.api.repositories.ChefRepository;
import ch.heig.menus.api.repositories.DishRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishesService {
    private final DishRepository dishRepository;

    private final ChefRepository chefRepository;

    private final ModelMapper modelMapper;

    public DishesService(@Autowired DishRepository dishRepository, @Autowired ChefRepository chefRepository, @Autowired ModelMapper modelMapper) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
        this.modelMapper = modelMapper;
    }

    private DishWithRelationsDTO convertToDto(DishEntity dishEntity) {
        var dish = modelMapper.map(dishEntity, DishWithRelationsDTO.class);
        dish.setChefs(
                dishEntity
                        .getChefs()
                        .stream()
                        .map(chefEntity -> modelMapper.map(chefEntity, ChefDTO.class))
                        .toList()
        );
        return dish;
    }

    public List<DishWithRelationsDTO> getAll() {
        return dishRepository
                .findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public DishWithRelationsDTO get(int id) throws DishNotFoundException {
        DishEntity dishEntity = dishRepository.findById(id).orElseThrow();
        return modelMapper.map(dishEntity, DishWithRelationsDTO.class);
    }

    public DishDTO create(DishDTO dish) {
        DishEntity dishEntity = new DishEntity();
        dishEntity.setName(dish.getName());
        dishEntity = dishRepository.save(dishEntity);
        return modelMapper.map(dishEntity, DishDTO.class);
    }

    public DishDTO update(int id, DishDTO dish) throws DishNotFoundException {
        var dishEntity = dishRepository.findById(id).orElseThrow(() -> new DishNotFoundException(id));
        dishEntity.setName(dish.getName());
        dishEntity = dishRepository.save(dishEntity);
        return modelMapper.map(dishEntity, DishDTO.class);
    }

    public void delete(int id) throws DishNotFoundException {
        var dishEntity = dishRepository.findById(id).orElseThrow(() -> new DishNotFoundException(id));
        for (ChefEntity chefEntity : dishEntity.getChefs()) {
            chefEntity.getDishes().remove(dishEntity);
        }
        dishRepository.delete(dishEntity);
    }

    public DishWithRelationsDTO addChefToDish(int idDish, ChefDTO chef) throws DishNotFoundException, ChefNotFoundException {
        DishEntity dishEntity = dishRepository.findById(idDish).orElseThrow(() -> new DishNotFoundException(idDish));
        ChefEntity chefEntity = chefRepository.findById(chef.getId()).orElseThrow(() -> new DishNotFoundException(chef.getId()));
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

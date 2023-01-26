package ch.heig.menus.api.services;

import ch.heig.menus.api.entities.ChefEntity;
import ch.heig.menus.api.entities.DishEntity;
import ch.heig.menus.api.entities.MenuEntity;
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

    private final ModelMapper modelMapper;

    public DishesService(@Autowired DishRepository dishRepository, @Autowired ModelMapper modelMapper) {
        this.dishRepository = dishRepository;
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
        // dishEntity.setId(10);
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
        dishRepository.delete(dishEntity);
    }
}

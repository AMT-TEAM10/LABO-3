package ch.heig.menus.api.services;

import ch.heig.menus.api.entities.DishEntity;
import ch.heig.menus.api.entities.MenuEntity;
import ch.heig.menus.api.exceptions.DishNotFoundException;
import ch.heig.menus.api.exceptions.MenuNotFoundException;
import ch.heig.menus.api.repositories.DishRepository;
import ch.heig.menus.api.repositories.MenuRepository;
import org.modelmapper.ModelMapper;
import org.openapitools.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenusService {
    private final MenuRepository menuRepository;
    private final DishRepository dishRepository;
    private final ModelMapper modelMapper;

    private MenuWithRelationsDTO convertToDto(MenuEntity menuEntity) {
        var menu = modelMapper.map(menuEntity, MenuWithRelationsDTO.class);
        menu.setDessert(convertToDto(menuEntity.getDessert()));
        menu.setMain(convertToDto(menuEntity.getMain()));
        menu.setStarter(convertToDto(menuEntity.getStarter()));
        return menu;
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

    MenusService(@Autowired MenuRepository menuRepository, @Autowired ModelMapper modelMapper, @Autowired DishRepository dishRepository) {
        this.menuRepository = menuRepository;
        this.dishRepository = dishRepository;
        this.modelMapper = modelMapper;
    }

    public List<MenuWithRelationsDTO> getAll() {
        return menuRepository
                .findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public MenuWithRelationsDTO get(int id) throws MenuNotFoundException {
        var menuEntity = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException(id));
        return modelMapper.map(menuEntity, MenuWithRelationsDTO.class);
    }

    public MenuWithRelationsDTO create(MenuDTO menu) {
        var menuEntity = new MenuEntity();
        menu.setName(menu.getName());
        return modelMapper.map(menuEntity, MenuWithRelationsDTO.class);
    }

    public MenuDTO update(int id, MenuDTO menu) throws MenuNotFoundException {
        var menuEntity = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException(id));
        menuEntity.setName(menu.getName());
        menuEntity = menuRepository.save(menuEntity);
        return modelMapper.map(menuEntity, MenuDTO.class);
    }

    public DishWithRelationsDTO getDish(int id, DishType dishType) throws MenuNotFoundException {
        var menuEntity = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException(id));
        DishEntity dishEntity;
        switch (dishType) {
            case STARTER -> dishEntity = menuEntity.getStarter();
            case MAIN -> dishEntity = menuEntity.getMain();
            case DESSERT -> dishEntity = menuEntity.getDessert();
            default -> throw new IllegalStateException("Unexpected value: " + dishType);
        }
        return modelMapper.map(dishEntity, DishWithRelationsDTO.class);
    }

    public MenuWithRelationsDTO updateDish(int id, int dishId, DishType dishType) throws MenuNotFoundException {
        var menuEntity = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException(id));
        var dishEntity = dishRepository.findById(dishId).orElseThrow(() -> new DishNotFoundException(dishId));
        switch (dishType) {
            case STARTER -> menuEntity.setStarter(dishEntity);
            case MAIN -> menuEntity.setMain(dishEntity);
            case DESSERT -> menuEntity.setDessert(dishEntity);
            default -> throw new IllegalStateException("Unexpected value: " + dishType);
        }
        menuEntity = menuRepository.save(menuEntity);
        return modelMapper.map(menuEntity, MenuWithRelationsDTO.class);
    }

    public MenuWithRelationsDTO removeDish(int id, DishType dishType) throws MenuNotFoundException {
        var menuEntity = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException(id));
        switch (dishType) {
            case STARTER -> menuEntity.setStarter(null);
            case MAIN -> menuEntity.setMain(null);
            case DESSERT -> menuEntity.setDessert(null);
            default -> throw new IllegalStateException("Unexpected value: " + dishType);
        }
        menuEntity = menuRepository.save(menuEntity);
        return modelMapper.map(menuEntity, MenuWithRelationsDTO.class);
    }

    public void delete(int id) {
        var menu = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException(id));
        menuRepository.delete(menu);
    }
}

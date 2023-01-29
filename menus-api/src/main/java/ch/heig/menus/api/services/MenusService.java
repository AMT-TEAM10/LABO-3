package ch.heig.menus.api.services;

import ch.heig.menus.api.entities.DishEntity;
import ch.heig.menus.api.entities.MenuEntity;
import ch.heig.menus.api.exceptions.BadRequestException;
import ch.heig.menus.api.exceptions.DishNotFoundException;
import ch.heig.menus.api.exceptions.MenuNotFoundException;
import ch.heig.menus.api.repositories.DishRepository;
import ch.heig.menus.api.repositories.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.openapitools.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenusService {
    private final MenuRepository menuRepository;
    private final DishRepository dishRepository;
    private final ModelMapper modelMapper;

    public List<MenuWithRelationsDTO> getAll() {
        return menuRepository
                .findAll()
                .stream()
                .map(e -> modelMapper.map(e, MenuWithRelationsDTO.class))
                .toList();
    }

    public MenuWithRelationsDTO get(int id) throws MenuNotFoundException {
        var menuEntity = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException(id));
        return modelMapper.map(menuEntity, MenuWithRelationsDTO.class);
    }

    public MenuWithRelationsDTO create(MenuDTO menu) {
        if(menu == null) {
            throw new BadRequestException("Menu is required");
        }

        var menuEntity = new MenuEntity();
        if(menu.getName() == null) {
            throw new BadRequestException("Name is required");
        }
        menuEntity.setName(menu.getName());
        return modelMapper.map(
                menuRepository.save(menuEntity),
                MenuWithRelationsDTO.class
        );
    }

    public MenuWithIdDTO update(int id, MenuDTO menu) throws MenuNotFoundException {
        if(menu == null) {
            throw new BadRequestException("Menu is required");
        }

        var menuEntity = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException(id));
        if(menu.getName() == null) {
            throw new BadRequestException("Name is required");
        }
        menuEntity.setName(menu.getName());
        return modelMapper.map(
                menuRepository.save(menuEntity),
                MenuWithIdDTO.class
        );
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
        return modelMapper.map(
                menuRepository.save(menuEntity),
                MenuWithRelationsDTO.class
        );
    }

    public MenuWithRelationsDTO removeDish(int id, DishType dishType) throws MenuNotFoundException {
        var menuEntity = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException(id));
        switch (dishType) {
            case STARTER -> menuEntity.setStarter(null);
            case MAIN -> menuEntity.setMain(null);
            case DESSERT -> menuEntity.setDessert(null);
            default -> throw new IllegalStateException("Unexpected value: " + dishType);
        }
        return modelMapper.map(
                menuRepository.save(menuEntity),
                MenuWithRelationsDTO.class
        );
    }

    public void delete(int id) {
        var menu = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException(id));
        menuRepository.delete(menu);
    }
}

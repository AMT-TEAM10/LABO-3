package ch.heig.menus.api.repositories;

import ch.heig.menus.api.entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//TODO SET THE CORRECT TYPE ENTITY
@Repository
public interface DishRepository extends JpaRepository<MenuEntity, Integer> {
    MenuEntity findById(int id);
    List<MenuEntity> findByAuthorLike(String pattern);}

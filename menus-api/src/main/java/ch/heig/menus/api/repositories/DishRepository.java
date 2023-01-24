package ch.heig.menus.api.repositories;

import ch.heig.menus.api.entities.ChefEntity;
import ch.heig.menus.api.entities.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DishRepository extends JpaRepository<DishEntity, Integer> {
    DishEntity findById(int id);
}

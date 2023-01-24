package ch.heig.menus.api.repositories;

import ch.heig.menus.api.entities.ChefEntity;
import ch.heig.menus.api.entities.MenuEntity;
import ch.heig.menus.api.exceptions.ChefNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ChefRepository extends JpaRepository<ChefEntity, Integer> {
}

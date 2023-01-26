package ch.heig.menus.api.repositories;

import ch.heig.menus.api.entities.ChefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChefRepository extends JpaRepository<ChefEntity, Integer> {
}

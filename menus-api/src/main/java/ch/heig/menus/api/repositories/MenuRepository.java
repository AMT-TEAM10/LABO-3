package ch.heig.menus.api.repositories;

import ch.heig.menus.api.entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {
}

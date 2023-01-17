package ch.heig.menus.api.repositories;

import ch.heig.menus.api.entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChefRepository extends JpaRepository<ChefRepository, Integer> {
    MenuEntity findById(int id);
    List<ChefRepository> findByByNameLike(String pattern);
}

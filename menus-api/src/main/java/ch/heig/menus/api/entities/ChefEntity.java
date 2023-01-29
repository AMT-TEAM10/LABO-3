package ch.heig.menus.api.entities;

import ch.heig.menus.api.services.DishesService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity()
@Table(name = "chef")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChefEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column()
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<DishEntity> dishes;

    public void addDish(DishEntity dish) {
        dishes.add(dish);
    }

    public void removeDish(DishEntity dish) {
        dishes.remove(dish);
    }
}

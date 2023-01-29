package ch.heig.menus.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity()
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "dishes", fetch = FetchType.LAZY)
    private Set<ChefEntity> chefs;

    public void addChef(ChefEntity chef) {
        chefs.add(chef);
        chef.getDishes().add(this);
    }

    public void removeChef(ChefEntity chef) {
        chefs.remove(chef);
        chef.getDishes().remove(this);
    }
}

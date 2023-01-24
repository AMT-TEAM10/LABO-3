package ch.heig.menus.api.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Dish")
@Table(name = "dish")
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToMany
    private List<ChefEntity> chefs;

    public DishEntity() {}

    public DishEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package ch.heig.menus.api.entities;

import jakarta.persistence.*;

@Entity(name = "Dish")
@Table(name = "dishes")
public class DishEntity {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    public DishEntity() {}

    public DishEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

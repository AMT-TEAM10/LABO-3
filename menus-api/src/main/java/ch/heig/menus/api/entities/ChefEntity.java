package ch.heig.menus.api.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity()
@Table(name = "chef")
public class ChefEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column()
    private String name;

    @ManyToMany(mappedBy = "chefs")
    private List<DishEntity> dishes;

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

    public List<DishEntity> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishEntity> dishes) {
        this.dishes = dishes;
    }
}

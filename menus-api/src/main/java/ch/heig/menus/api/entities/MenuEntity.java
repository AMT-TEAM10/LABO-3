package ch.heig.menus.api.entities;


import jakarta.persistence.*;

@Entity(name = "Menu")
@Table(name = "menu")
public class MenuEntity {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    // @JoinColumn(name = "dish_id", foreignKey = @ForeignKey(name = "id"))
    private DishEntity starter;

    @ManyToOne
    private DishEntity main;

    @ManyToOne
    private DishEntity dessert;

    public MenuEntity() {}

    public MenuEntity(int id, DishEntity starter, DishEntity main, DishEntity dessert) {
        this.id = id;
        this.starter = starter;
        this.main = main;
        this.dessert = dessert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

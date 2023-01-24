package ch.heig.menus.api.entities;


import jakarta.persistence.*;

@Entity(name = "Menu")
@Table(name = "menu")
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne
    // @JoinColumn(name = "dish_id", foreignKey = @ForeignKey(name = "id"))
    private DishEntity starter;

    @ManyToOne
    private DishEntity main;

    @ManyToOne
    private DishEntity dessert;

    public MenuEntity() {}

    public MenuEntity(int id, String name, DishEntity starter, DishEntity main, DishEntity dessert) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishEntity getStarter() {
        return starter;
    }

    public void setStarter(DishEntity starter) {
        this.starter = starter;
    }

    public DishEntity getMain() {
        return main;
    }

    public void setMain(DishEntity main) {
        this.main = main;
    }

    public DishEntity getDessert() {
        return dessert;
    }

    public void setDessert(DishEntity dessert) {
        this.dessert = dessert;
    }
}

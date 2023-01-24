package ch.heig.menus.api.entities;


import jakarta.persistence.*;

@Entity()
@Table(name = "menu")
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private DishEntity starter;

    @ManyToOne(fetch = FetchType.LAZY)
    private DishEntity main;

    @ManyToOne(fetch = FetchType.LAZY)
    private DishEntity dessert;

    public MenuEntity() {}

    public MenuEntity(int id, String name) {
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

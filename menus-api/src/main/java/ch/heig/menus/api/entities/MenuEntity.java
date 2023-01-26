package ch.heig.menus.api.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity()
@Table(name = "menu")@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}

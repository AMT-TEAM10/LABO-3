package ch.heig.menus.api.entities;

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
}

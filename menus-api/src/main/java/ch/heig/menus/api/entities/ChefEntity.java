package ch.heig.menus.api.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Chef")
@Table(name = "Chef")
public class ChefEntity {
    @Id
    @GeneratedValue
    private int id;

    private String name;
}

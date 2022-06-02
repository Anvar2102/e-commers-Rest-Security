package uz.pdp.online.lesson_2_2.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    private Category category;
}

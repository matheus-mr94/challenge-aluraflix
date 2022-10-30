package br.com.alura.aluraflix.models.category;

import lombok.Getter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String color;

    public Category(String title, String color) {
        this.title = title;
        this.color = color;
    }
}

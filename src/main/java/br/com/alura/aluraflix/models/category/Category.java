package br.com.alura.aluraflix.models.category;

import br.com.alura.aluraflix.models.video.Video;
import lombok.Getter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String color;

    @OneToMany(mappedBy = "category")
    List<Video> videos;

    public Category(String title, String color) {
        this.title = title;
        this.color = color;
    }
}

package br.com.alura.aluraflix.models.category;

import br.com.alura.aluraflix.models.video.Video;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String color;
    @OneToMany(mappedBy = "category")
    List<Video> videos;

    public Category(String title, String color) {
        this.title = title.toUpperCase();
        this.color = color.toUpperCase();
    }

    public void update(CategoryForm form) {
        this.title = form.getTitle().toUpperCase();
        this.color = form.getColor().toUpperCase();
    }
}

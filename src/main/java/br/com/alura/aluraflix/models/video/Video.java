package br.com.alura.aluraflix.models.video;

import br.com.alura.aluraflix.models.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String url;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Video(String title, String description, String url, Category category) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.category = category;
    }

    public void update(VideoUpdateForm form) {
        this.title = form.getTitle();
        this.description = form.getDescription();
        this.url = form.getUrl();
        this.category = form.getCategory();
    }
}

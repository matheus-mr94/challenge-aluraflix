package br.com.alura.aluraflix.models.video;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public Video(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public void update(VideoUpdateForm form) {
        this.title = form.getTitle();
        this.description = form.getDescription();
        this.url = form.getUrl();
    }
}

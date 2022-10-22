package br.com.alura.aluraflix.models.video;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
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

}

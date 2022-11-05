package br.com.alura.aluraflix.models.video;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VideoView {

    public String title;
    public String description;
    public String url;
    public Long categoryId;

    public VideoView(Video video) {
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.url = video.getUrl();
        this.categoryId = video.getCategoryId();
    }
}

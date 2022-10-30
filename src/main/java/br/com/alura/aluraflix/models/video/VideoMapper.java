package br.com.alura.aluraflix.models.video;

import org.springframework.stereotype.Component;

@Component
public class VideoMapper {

    public VideoView toView(Video video) {
        return new VideoView(video.getTitle(), video.getDescription(), video.getUrl());
    }

    public Video toEntity(Form form) {
        return new Video(form.getTitle(), form.getDescription(), form.getUrl(), form.getCategory());
    }

}

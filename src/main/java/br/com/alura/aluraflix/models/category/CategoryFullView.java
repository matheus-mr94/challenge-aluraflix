package br.com.alura.aluraflix.models.category;

import br.com.alura.aluraflix.models.video.VideoView;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CategoryFullView {

    private String title;
    private String color;
    private List<VideoView> videos;

}
